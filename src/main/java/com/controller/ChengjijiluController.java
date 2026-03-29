package com.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.ChengjijiluEntity;
import com.service.ChengjijiluService;
import com.utils.MPUtil;
import com.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/chengjijilu")
public class ChengjijiluController {

    @Autowired
    private ChengjijiluService chengjijiluService;

    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, ChengjijiluEntity entity) {
        Wrapper<ChengjijiluEntity> ew = new EntityWrapper<>();
        ew = MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, entity), params), params);
        return R.ok().put("data", chengjijiluService.selectList(ew));
    }

    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        return R.ok().put("data", chengjijiluService.selectById(id));
    }

    @RequestMapping("/save")
    public R save(@RequestBody ChengjijiluEntity entity) {
        entity.setId(new Date().getTime() + (long) Math.floor(Math.random() * 1000));
        entity.setAddtime(new Date());
        chengjijiluService.insert(entity);
        return R.ok();
    }

    @RequestMapping("/update")
    public R update(@RequestBody ChengjijiluEntity entity) {
        chengjijiluService.updateById(entity);
        return R.ok();
    }

    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        chengjijiluService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    @GetMapping("/trend/student/{xueyuanId}")
    public R studentTrend(@PathVariable("xueyuanId") Long xueyuanId,
                          @RequestParam(value = "kechengId", required = false) Long kechengId) {
        return R.ok().put("data", chengjijiluService.getStudentTrend(xueyuanId, kechengId));
    }

    @GetMapping("/trend/course/{kechengId}")
    public R courseTrend(@PathVariable("kechengId") Long kechengId) {
        return R.ok().put("data", chengjijiluService.getCourseAvgTrend(kechengId));
    }
}
