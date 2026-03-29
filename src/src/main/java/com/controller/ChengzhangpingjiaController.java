package com.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.ChengzhangpingjiaEntity;
import com.service.ChengzhangpingjiaService;
import com.utils.MPUtil;
import com.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/chengzhangpingjia")
public class ChengzhangpingjiaController {

    @Autowired
    private ChengzhangpingjiaService chengzhangpingjiaService;

    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, ChengzhangpingjiaEntity entity) {
        EntityWrapper<ChengzhangpingjiaEntity> ew = new EntityWrapper<>();
        ew = MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, entity), params), params);
        return R.ok().put("data", chengzhangpingjiaService.selectList(ew));
    }

    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        return R.ok().put("data", chengzhangpingjiaService.selectById(id));
    }

    @RequestMapping("/save")
    public R save(@RequestBody ChengzhangpingjiaEntity entity) {
        entity.setId(new Date().getTime() + (long) Math.floor(Math.random() * 1000));
        entity.setAddtime(new Date());
        if (entity.getPingjiashijian() == null) {
            entity.setPingjiashijian(new Date());
        }
        chengzhangpingjiaService.insert(entity);
        return R.ok();
    }

    @RequestMapping("/update")
    public R update(@RequestBody ChengzhangpingjiaEntity entity) {
        chengzhangpingjiaService.updateById(entity);
        return R.ok();
    }

    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        chengzhangpingjiaService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    @GetMapping("/latest/{xueyuanId}")
    public R latest(@PathVariable("xueyuanId") Long xueyuanId) {
        ChengzhangpingjiaEntity latest = chengzhangpingjiaService.selectOne(
                new EntityWrapper<ChengzhangpingjiaEntity>()
                        .eq("xueyuan_id", xueyuanId)
                        .orderBy("pingjiashijian", false)
                        .last("limit 1")
        );
        return R.ok().put("data", latest);
    }
}
