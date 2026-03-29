package com.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.YuebaoEntity;
import com.service.YuebaoService;
import com.utils.MPUtil;
import com.utils.PageUtils;
import com.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/yuebao")
public class YuebaoController {

    @Autowired
    private YuebaoService yuebaoService;

    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, YuebaoEntity entity) {
        EntityWrapper<YuebaoEntity> ew = new EntityWrapper<YuebaoEntity>();
        PageUtils page = yuebaoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, entity), params), params));
        return R.ok().put("data", page);
    }

    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, YuebaoEntity entity) {
        EntityWrapper<YuebaoEntity> ew = new EntityWrapper<YuebaoEntity>();
        PageUtils page = yuebaoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, entity), params), params));
        return R.ok().put("data", page);
    }

    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        return R.ok().put("data", yuebaoService.selectById(id));
    }

    @RequestMapping("/save")
    public R save(@RequestBody YuebaoEntity entity) {
        entity.setId(new Date().getTime() + (long) Math.floor(Math.random() * 1000));
        entity.setAddtime(new Date());
        if (entity.getShengchengshijian() == null) {
            entity.setShengchengshijian(new Date());
        }
        yuebaoService.insert(entity);
        return R.ok();
    }

    @RequestMapping("/update")
    public R update(@RequestBody YuebaoEntity entity) {
        yuebaoService.updateById(entity);
        return R.ok();
    }

    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        yuebaoService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    @GetMapping("/generate")
    public R generate(@RequestParam String month,
                      @RequestParam(required = false) Long xueyuanId,
                      @RequestParam(required = false) Long kechengId,
                      @RequestParam(required = false) String banji,
                      @RequestParam(required = false) String shengchengren) {
        return R.ok().put("data", yuebaoService.generateMonthlyReport(month, xueyuanId, kechengId, banji, shengchengren));
    }
}
