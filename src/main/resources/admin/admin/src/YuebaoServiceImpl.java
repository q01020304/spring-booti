package com.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.YuebaoDao;
import com.entity.BaomingjiaofeiEntity;
import com.entity.ChengzhangpingjiaEntity;
import com.entity.KaoqinEntity;
import com.entity.YuebaoEntity;
import com.service.BaomingjiaofeiService;
import com.service.ChengzhangpingjiaService;
import com.service.KaoqinService;
import com.service.KaoshiqingkuangService;
import com.service.YuebaoService;
import com.utils.PageUtils;
import com.utils.Query;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service("yuebaoService")
public class YuebaoServiceImpl extends ServiceImpl<YuebaoDao, YuebaoEntity> implements YuebaoService {

    @Autowired
    private KaoqinService kaoqinService;
    @Autowired
    private KaoshiqingkuangService kaoshiqingkuangService;
    @Autowired
    private ChengzhangpingjiaService chengzhangpingjiaService;
    @Autowired
    private BaomingjiaofeiService baomingjiaofeiService;

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<YuebaoEntity> wrapper) {
        com.baomidou.mybatisplus.plugins.Page<YuebaoEntity> page = this.selectPage(new Query<YuebaoEntity>(params).getPage(), wrapper);
        return new PageUtils(page);
    }

    @Override
    public Map<String, Object> generateMonthlyReport(String month, Long xueyuanId, Long kechengId, String banji, String shengchengren) {
        Map<String, Object> result = new LinkedHashMap<String, Object>();
        Map<String, Integer> attend = attendance(month, xueyuanId, kechengId, banji);
        Map<String, Object> exam = kaoshiqingkuangService.monthlySummary(month, xueyuanId, kechengId, banji);
        BigDecimal evaluationScore = latestEvaluationScore(month, xueyuanId, kechengId);
        Map<String, BigDecimal> payment = paymentSummary(xueyuanId, kechengId);

        result.put("month", month);
        result.put("attendance", attend);
        result.put("exam", exam);
        result.put("evaluationScore", evaluationScore);
        result.put("payment", payment);

        String summaryText = buildSummary(month, attend, exam, evaluationScore, payment);
        String suggestionText = buildSuggestion(attend, exam, evaluationScore);
        result.put("summaryText", summaryText);
        result.put("suggestionText", suggestionText);

        YuebaoEntity entity = new YuebaoEntity();
        entity.setId(new Date().getTime() + (long) Math.floor(Math.random() * 1000));
        entity.setYuefen(month);
        entity.setXueyuanid(xueyuanId);
        entity.setBanji(banji);
        entity.setKechengid(kechengId);
        entity.setKaoqincishu(attend.get("total"));
        entity.setChuqincishu(attend.get("present"));
        entity.setChidaocishu(attend.get("late"));
        entity.setQingjiacishu(attend.get("leave"));
        entity.setQuekincishu(attend.get("absent"));
        entity.setZhoucecishu(((Integer) exam.get("zhouceCount")));
        entity.setYuececishu(((Integer) exam.get("yueceCount")));
        entity.setPingjunchengji((BigDecimal) exam.get("avgScore"));
        entity.setZuigaochengji((BigDecimal) exam.get("maxScore"));
        entity.setZuixiaochengji((BigDecimal) exam.get("minScore"));
        entity.setPingjiafen(evaluationScore);
        entity.setYingjiaofei(payment.get("due"));
        entity.setShijiaofei(payment.get("paid"));
        entity.setYuebaozhaiyao(summaryText);
        entity.setJianyi(suggestionText);
        entity.setShengchengren(shengchengren);
        entity.setShengchengshijian(new Date());
        entity.setAddtime(new Date());
        this.insert(entity);
        result.put("savedReport", entity);
        return result;
    }

    private Map<String, Integer> attendance(String month, Long xueyuanId, Long kechengId, String banji) {
        EntityWrapper<KaoqinEntity> ew = new EntityWrapper<KaoqinEntity>();
        if (xueyuanId != null) ew.eq("xueyuan_id", xueyuanId);
        if (kechengId != null) ew.eq("kecheng_id", kechengId);
        if (StringUtils.isNotBlank(banji)) ew.eq("banji", banji);
        if (StringUtils.isNotBlank(month) && month.length() == 7) {
            ew.ge("kaoqinriqi", month + "-01 00:00:00");
            ew.lt("kaoqinriqi", nextMonth(month) + "-01 00:00:00");
        }
        List<KaoqinEntity> list = kaoqinService.selectList(ew);
        Map<String, Integer> map = new LinkedHashMap<String, Integer>();
        int present = 0, late = 0, leave = 0, absent = 0;
        for (KaoqinEntity k : list) {
            if ("出勤".equals(k.getKaoqinzhuangtai())) present++;
            if ("迟到".equals(k.getKaoqinzhuangtai())) late++;
            if ("请假".equals(k.getKaoqinzhuangtai())) leave++;
            if ("缺勤".equals(k.getKaoqinzhuangtai())) absent++;
        }
        map.put("total", list.size());
        map.put("present", present);
        map.put("late", late);
        map.put("leave", leave);
        map.put("absent", absent);
        return map;
    }

    private BigDecimal latestEvaluationScore(String month, Long xueyuanId, Long kechengId) {
        EntityWrapper<ChengzhangpingjiaEntity> ew = new EntityWrapper<ChengzhangpingjiaEntity>();
        if (xueyuanId != null) ew.eq("xueyuan_id", xueyuanId);
        if (kechengId != null) ew.eq("kecheng_id", kechengId);
        if (StringUtils.isNotBlank(month) && month.length() == 7) {
            ew.ge("pingjiashijian", month + "-01 00:00:00");
            ew.lt("pingjiashijian", nextMonth(month) + "-01 00:00:00");
        }
        ew.orderBy("pingjiashijian", false);
        List<ChengzhangpingjiaEntity> list = chengzhangpingjiaService.selectList(ew);
        if (list == null || list.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return list.get(0).getZonghedefen() == null ? BigDecimal.ZERO : list.get(0).getZonghedefen();
    }

    private Map<String, BigDecimal> paymentSummary(Long xueyuanId, Long kechengId) {
        EntityWrapper<BaomingjiaofeiEntity> ew = new EntityWrapper<BaomingjiaofeiEntity>();
        if (xueyuanId != null) ew.eq("xueyuanid", xueyuanId);
        if (kechengId != null) ew.eq("kechengid", kechengId);
        List<BaomingjiaofeiEntity> list = baomingjiaofeiService.selectList(ew);
        BigDecimal due = BigDecimal.ZERO;
        BigDecimal paid = BigDecimal.ZERO;
        for (BaomingjiaofeiEntity item : list) {
            due = due.add(item.getShifujine() == null ? BigDecimal.ZERO : item.getShifujine());
            if ("已缴费".equals(item.getJiaofeizhuangtai())) {
                paid = paid.add(item.getShifujine() == null ? BigDecimal.ZERO : item.getShifujine());
            }
        }
        Map<String, BigDecimal> map = new LinkedHashMap<String, BigDecimal>();
        map.put("due", due.setScale(2, RoundingMode.HALF_UP));
        map.put("paid", paid.setScale(2, RoundingMode.HALF_UP));
        map.put("unpaid", due.subtract(paid).setScale(2, RoundingMode.HALF_UP));
        return map;
    }

    private String buildSummary(String month, Map<String, Integer> attend, Map<String, Object> exam, BigDecimal evaluationScore, Map<String, BigDecimal> payment) {
        return month + "月共记录考勤" + attend.get("total") + "次，其中出勤" + attend.get("present") + "次、迟到" + attend.get("late") + "次、请假" + attend.get("leave") + "次、缺勤" + attend.get("absent") +
                "次；本月考试" + exam.get("totalExamCount") + "次（周测" + exam.get("zhouceCount") + "次，月测" + exam.get("yueceCount") + "次），平均成绩" + exam.get("avgScore") + "分，最高" + exam.get("maxScore") +
                "分；阶段评价得分" + evaluationScore + "分；缴费应收" + payment.get("due") + "元，实收" + payment.get("paid") + "元。";
    }

    private String buildSuggestion(Map<String, Integer> attend, Map<String, Object> exam, BigDecimal evaluationScore) {
        BigDecimal avg = (BigDecimal) exam.get("avgScore");
        if (attend.get("absent") > 0 || attend.get("late") > 2) {
            return "建议下月优先加强出勤管理和到课提醒，稳定学习节奏。";
        }
        if (avg.compareTo(new BigDecimal("75")) < 0) {
            return "建议针对薄弱知识点安排课后复盘和专项训练，周测后及时订正。";
        }
        if (evaluationScore.compareTo(new BigDecimal("85")) >= 0) {
            return "整体表现较好，建议继续保持课堂参与度，并增加拔高训练。";
        }
        return "建议继续跟踪考试与课堂表现，保持稳定练习频率，逐步提升综合成绩。";
    }

    private String nextMonth(String month) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM").parse(month);
            java.util.Calendar c = java.util.Calendar.getInstance();
            c.setTime(date);
            c.add(java.util.Calendar.MONTH, 1);
            return new SimpleDateFormat("yyyy-MM").format(c.getTime());
        } catch (Exception e) {
            return month;
        }
    }
}
