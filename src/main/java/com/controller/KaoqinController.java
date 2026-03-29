package com.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.KaoqinEntity;
import com.service.KaoqinService;
import com.utils.MPUtil;
import com.utils.PageUtils;
import com.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/kaoqin")
public class KaoqinController {

    @Autowired
    private KaoqinService kaoqinService;

    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, KaoqinEntity kaoqin, HttpServletRequest request) {
        EntityWrapper<KaoqinEntity> ew = new EntityWrapper<>();
        PageUtils page = kaoqinService.queryPage(params);
        return R.ok().put("data", page);
    }

    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, KaoqinEntity kaoqin) {
        Wrapper<KaoqinEntity> ew = new EntityWrapper<>();
        ew = MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kaoqin), params), params);
        return R.ok().put("data", kaoqinService.selectList(ew));
    }

    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        return R.ok().put("data", kaoqinService.selectById(id));
    }

    @RequestMapping("/save")
    public R save(@RequestBody KaoqinEntity kaoqin) {
        kaoqin.setId(new Date().getTime() + (long) Math.floor(Math.random() * 1000));
        kaoqin.setAddtime(new Date());
        kaoqinService.insert(kaoqin);
        return R.ok();
    }

    @RequestMapping("/update")
    public R update(@RequestBody KaoqinEntity kaoqin) {
        kaoqinService.updateById(kaoqin);
        return R.ok();
    }

    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        kaoqinService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    @GetMapping("/summary/{xueyuanId}")
    public R summary(@PathVariable("xueyuanId") Long xueyuanId) {
        int total = kaoqinService.selectCount(new EntityWrapper<KaoqinEntity>().eq("xueyuan_id", xueyuanId));
        int chuqin = kaoqinService.selectCount(new EntityWrapper<KaoqinEntity>().eq("xueyuan_id", xueyuanId).eq("kaoqinzhuangtai", "出勤"));
        int chidao = kaoqinService.selectCount(new EntityWrapper<KaoqinEntity>().eq("xueyuan_id", xueyuanId).eq("kaoqinzhuangtai", "迟到"));
        int qingjia = kaoqinService.selectCount(new EntityWrapper<KaoqinEntity>().eq("xueyuan_id", xueyuanId).eq("kaoqinzhuangtai", "请假"));
        int queqin = kaoqinService.selectCount(new EntityWrapper<KaoqinEntity>().eq("xueyuan_id", xueyuanId).eq("kaoqinzhuangtai", "缺勤"));
        return R.ok().put("data", new java.util.HashMap<String, Object>() {{
            put("total", total);
            put("chuqin", chuqin);
            put("chidao", chidao);
            put("qingjia", qingjia);
            put("queqin", queqin);
            put("attendanceRate", total == 0 ? 0 : Math.round(chuqin * 10000.0 / total) / 100.0);
        }});
    }
}
