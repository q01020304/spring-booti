package com.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.KaoshiqingkuangDao;
import com.entity.KaoshiqingkuangEntity;
import com.service.KaoshiqingkuangService;
import com.utils.PageUtils;
import com.utils.Query;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service("kaoshiqingkuangService")
public class KaoshiqingkuangServiceImpl extends ServiceImpl<KaoshiqingkuangDao, KaoshiqingkuangEntity> implements KaoshiqingkuangService {

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<KaoshiqingkuangEntity> wrapper) {
        com.baomidou.mybatisplus.plugins.Page<KaoshiqingkuangEntity> page = this.selectPage(new Query<KaoshiqingkuangEntity>(params).getPage(), wrapper);
        return new PageUtils(page);
    }

    @Override
    public Map<String, Object> monthlySummary(String month, Long xueyuanId, Long kechengId, String banji) {
        List<KaoshiqingkuangEntity> list = this.selectList(buildWrapper(month, xueyuanId, kechengId, banji));
        Map<String, Object> result = new LinkedHashMap<String, Object>();
        int total = list.size();
        int zhouce = 0;
        int yuece = 0;
        BigDecimal totalScore = BigDecimal.ZERO;
        BigDecimal max = null;
        BigDecimal min = null;
        List<Map<String, Object>> detail = new ArrayList<Map<String, Object>>();
        for (KaoshiqingkuangEntity item : list) {
            if ("周测".equals(item.getKaoshileixing())) {
                zhouce++;
            }
            if ("月测".equals(item.getKaoshileixing())) {
                yuece++;
            }
            BigDecimal score = item.getChengji() == null ? BigDecimal.ZERO : item.getChengji();
            totalScore = totalScore.add(score);
            if (max == null || score.compareTo(max) > 0) max = score;
            if (min == null || score.compareTo(min) < 0) min = score;
            Map<String, Object> row = new LinkedHashMap<String, Object>();
            row.put("date", item.getKaoshiriqi());
            row.put("type", item.getKaoshileixing());
            row.put("course", item.getKechengmingcheng());
            row.put("score", item.getChengji());
            row.put("ranking", item.getPaiming());
            detail.add(row);
        }
        BigDecimal avg = total == 0 ? BigDecimal.ZERO : totalScore.divide(new BigDecimal(total), 2, RoundingMode.HALF_UP);
        result.put("month", month);
        result.put("totalExamCount", total);
        result.put("zhouceCount", zhouce);
        result.put("yueceCount", yuece);
        result.put("avgScore", avg);
        result.put("maxScore", max == null ? BigDecimal.ZERO : max);
        result.put("minScore", min == null ? BigDecimal.ZERO : min);
        result.put("detail", detail);
        result.put("trend", trend(month, xueyuanId, kechengId));
        return result;
    }

    @Override
    public List<Map<String, Object>> trend(String month, Long xueyuanId, Long kechengId) {
        List<KaoshiqingkuangEntity> list = this.selectList(buildWrapper(month, xueyuanId, kechengId, null).orderBy("kaoshiriqi", true));
        List<Map<String, Object>> trend = new ArrayList<Map<String, Object>>();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        for (KaoshiqingkuangEntity item : list) {
            Map<String, Object> row = new LinkedHashMap<String, Object>();
            row.put("label", item.getKaoshiriqi() == null ? "未知日期" : sdf.format(item.getKaoshiriqi()));
            row.put("type", item.getKaoshileixing());
            row.put("score", item.getChengji());
            row.put("course", item.getKechengmingcheng());
            trend.add(row);
        }
        return trend;
    }

    private EntityWrapper<KaoshiqingkuangEntity> buildWrapper(String month, Long xueyuanId, Long kechengId, String banji) {
        EntityWrapper<KaoshiqingkuangEntity> ew = new EntityWrapper<KaoshiqingkuangEntity>();
        if (xueyuanId != null) {
            ew.eq("xueyuanid", xueyuanId);
        }
        if (kechengId != null) {
            ew.eq("kechengid", kechengId);
        }
        if (StringUtils.isNotBlank(banji)) {
            ew.eq("banji", banji);
        }
        if (StringUtils.isNotBlank(month) && month.length() == 7) {
            ew.ge("kaoshiriqi", month + "-01 00:00:00");
            ew.lt("kaoshiriqi", nextMonth(month) + "-01 00:00:00");
        }
        ew.orderBy("kaoshiriqi", true);
        return ew;
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
