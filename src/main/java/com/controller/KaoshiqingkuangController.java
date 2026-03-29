package com.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.KaoshiqingkuangEntity;
import com.service.KaoshiqingkuangService;
import com.utils.MPUtil;
import com.utils.PageUtils;
import com.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/kaoshiqingkuang")
public class KaoshiqingkuangController {

    @Autowired
    private KaoshiqingkuangService kaoshiqingkuangService;

    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, KaoshiqingkuangEntity entity) {
        EntityWrapper<KaoshiqingkuangEntity> ew = new EntityWrapper<KaoshiqingkuangEntity>();
        PageUtils page = kaoshiqingkuangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, entity), params), params));
        return R.ok().put("data", page);
    }

    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, KaoshiqingkuangEntity entity) {
        EntityWrapper<KaoshiqingkuangEntity> ew = new EntityWrapper<KaoshiqingkuangEntity>();
        PageUtils page = kaoshiqingkuangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, entity), params), params));
        return R.ok().put("data", page);
    }

    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        return R.ok().put("data", kaoshiqingkuangService.selectById(id));
    }

    @RequestMapping("/save")
    public R save(@RequestBody KaoshiqingkuangEntity entity) {
        entity.setId(new Date().getTime() + (long) Math.floor(Math.random() * 1000));
        if (entity.getManyuanzhi() == null) {
            entity.setManyuanzhi(new BigDecimal("100"));
        }
        entity.setAddtime(new Date());
        kaoshiqingkuangService.insert(entity);
        return R.ok();
    }

    @RequestMapping("/update")
    public R update(@RequestBody KaoshiqingkuangEntity entity) {
        kaoshiqingkuangService.updateById(entity);
        return R.ok();
    }

    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        kaoshiqingkuangService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    @GetMapping("/summary")
    public R summary(@RequestParam String month,
                     @RequestParam(required = false) Long xueyuanId,
                     @RequestParam(required = false) Long kechengId,
                     @RequestParam(required = false) String banji) {
        return R.ok().put("data", kaoshiqingkuangService.monthlySummary(month, xueyuanId, kechengId, banji));
    }

    @GetMapping("/trend")
    public R trend(@RequestParam String month,
                   @RequestParam(required = false) Long xueyuanId,
                   @RequestParam(required = false) Long kechengId) {
        return R.ok().put("data", kaoshiqingkuangService.trend(month, xueyuanId, kechengId));
    }
}
