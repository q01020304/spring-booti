package com.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.BaomingjiaofeiEntity;
import com.entity.view.BaomingjiaofeiView;
import com.service.BaomingjiaofeiService;
import com.utils.MPUtil;
import com.utils.PageUtils;
import com.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/baomingjiaofei")
public class BaomingjiaofeiController {
    @Autowired
    private BaomingjiaofeiService baomingjiaofeiService;

    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, BaomingjiaofeiEntity entity, HttpServletRequest request) {
        String tableName = String.valueOf(request.getSession().getAttribute("tableName"));
        if ("jiazhang".equals(tableName)) {
            entity.setJiazhangzhanghao(String.valueOf(request.getSession().getAttribute("username")));
        }
        if ("xueyuan".equals(tableName)) {
            entity.setXuehao(String.valueOf(request.getSession().getAttribute("username")));
        }
        EntityWrapper<BaomingjiaofeiEntity> ew = new EntityWrapper<BaomingjiaofeiEntity>();
        PageUtils page = baomingjiaofeiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, entity), params), params));
        return R.ok().put("data", page);
    }

    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, BaomingjiaofeiEntity entity) {
        EntityWrapper<BaomingjiaofeiEntity> ew = new EntityWrapper<BaomingjiaofeiEntity>();
        PageUtils page = baomingjiaofeiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, entity), params), params));
        return R.ok().put("data", page);
    }

    @RequestMapping("/lists")
    public R lists(BaomingjiaofeiEntity entity) {
        EntityWrapper<BaomingjiaofeiEntity> ew = new EntityWrapper<BaomingjiaofeiEntity>();
        ew.allEq(MPUtil.allEQMapPre(entity, "baomingjiaofei"));
        return R.ok().put("data", baomingjiaofeiService.selectListView(ew));
    }

    @RequestMapping("/query")
    public R query(BaomingjiaofeiEntity entity) {
        EntityWrapper<BaomingjiaofeiEntity> ew = new EntityWrapper<BaomingjiaofeiEntity>();
        ew.allEq(MPUtil.allEQMapPre(entity, "baomingjiaofei"));
        BaomingjiaofeiView view = baomingjiaofeiService.selectView(ew);
        return R.ok("查询报名缴费成功").put("data", view);
    }

    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        return R.ok().put("data", baomingjiaofeiService.selectById(id));
    }

    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id) {
        return R.ok().put("data", baomingjiaofeiService.selectById(id));
    }

    @RequestMapping("/save")
    public R save(@RequestBody BaomingjiaofeiEntity entity) {
        if (entity.getDingdanhao() == null || "".equals(entity.getDingdanhao())) {
            entity.setDingdanhao("BM" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + (int) (Math.random() * 900 + 100));
        }
        if (entity.getBaomingriqi() == null) {
            entity.setBaomingriqi(new Date());
        }
        if (entity.getYouhuijine() == null) {
            entity.setYouhuijine(BigDecimal.ZERO);
        }
        if (entity.getJiaofeijine() == null) {
            entity.setJiaofeijine(BigDecimal.ZERO);
        }
        if (entity.getShifujine() == null) {
            entity.setShifujine(entity.getJiaofeijine().subtract(entity.getYouhuijine()));
        }
        if (entity.getJiaofeizhuangtai() == null || "".equals(entity.getJiaofeizhuangtai())) {
            entity.setJiaofeizhuangtai("待缴费");
        }
        entity.setId(new Date().getTime() + (long) Math.floor(Math.random() * 1000));
        baomingjiaofeiService.insert(entity);
        return R.ok();
    }

    @RequestMapping("/add")
    public R add(@RequestBody BaomingjiaofeiEntity entity) {
        return save(entity);
    }

    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody BaomingjiaofeiEntity entity) {
        if (entity.getJiaofeijine() != null) {
            BigDecimal discount = entity.getYouhuijine() == null ? BigDecimal.ZERO : entity.getYouhuijine();
            entity.setShifujine(entity.getJiaofeijine().subtract(discount));
        }
        if ("已缴费".equals(entity.getJiaofeizhuangtai()) && entity.getJiaofeishijian() == null) {
            entity.setJiaofeishijian(new Date());
        }
        baomingjiaofeiService.updateById(entity);
        return R.ok();
    }

    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        baomingjiaofeiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    @RequestMapping("/markPaid/{id}")
    public R markPaid(@PathVariable("id") Long id, String payType) {
        BaomingjiaofeiEntity entity = baomingjiaofeiService.selectById(id);
        if (entity == null) {
            return R.error("订单不存在");
        }
        entity.setJiaofeizhuangtai("已缴费");
        entity.setJiaofeifangshi(payType);
        entity.setJiaofeishijian(new Date());
        baomingjiaofeiService.updateById(entity);
        return R.ok("缴费状态更新成功");
    }

    @RequestMapping("/summary")
    public R summary(Long xueyuanId, Long jiazhangId) {
        List<Map<String, Object>> list = baomingjiaofeiService.paymentSummary(xueyuanId, jiazhangId);
        return R.ok().put("data", list);
    }
}
