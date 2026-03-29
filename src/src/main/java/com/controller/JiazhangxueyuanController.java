package com.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.JiazhangxueyuanEntity;
import com.entity.view.JiazhangxueyuanView;
import com.service.JiazhangxueyuanService;
import com.utils.MPUtil;
import com.utils.PageUtils;
import com.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jiazhangxueyuan")
public class JiazhangxueyuanController {
    @Autowired
    private JiazhangxueyuanService jiazhangxueyuanService;

    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, JiazhangxueyuanEntity entity, HttpServletRequest request) {
        String tableName = String.valueOf(request.getSession().getAttribute("tableName"));
        if ("jiazhang".equals(tableName)) {
            entity.setJiazhangzhanghao(String.valueOf(request.getSession().getAttribute("username")));
        }
        EntityWrapper<JiazhangxueyuanEntity> ew = new EntityWrapper<JiazhangxueyuanEntity>();
        PageUtils page = jiazhangxueyuanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, entity), params), params));
        return R.ok().put("data", page);
    }

    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, JiazhangxueyuanEntity entity) {
        EntityWrapper<JiazhangxueyuanEntity> ew = new EntityWrapper<JiazhangxueyuanEntity>();
        PageUtils page = jiazhangxueyuanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, entity), params), params));
        return R.ok().put("data", page);
    }

    @RequestMapping("/lists")
    public R lists(JiazhangxueyuanEntity entity) {
        EntityWrapper<JiazhangxueyuanEntity> ew = new EntityWrapper<JiazhangxueyuanEntity>();
        ew.allEq(MPUtil.allEQMapPre(entity, "jiazhangxueyuan"));
        return R.ok().put("data", jiazhangxueyuanService.selectListView(ew));
    }

    @RequestMapping("/query")
    public R query(JiazhangxueyuanEntity entity) {
        EntityWrapper<JiazhangxueyuanEntity> ew = new EntityWrapper<JiazhangxueyuanEntity>();
        ew.allEq(MPUtil.allEQMapPre(entity, "jiazhangxueyuan"));
        JiazhangxueyuanView view = jiazhangxueyuanService.selectView(ew);
        return R.ok("查询关联成功").put("data", view);
    }

    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        return R.ok().put("data", jiazhangxueyuanService.selectById(id));
    }

    @RequestMapping("/save")
    public R save(@RequestBody JiazhangxueyuanEntity entity) {
        EntityWrapper<JiazhangxueyuanEntity> ew = new EntityWrapper<JiazhangxueyuanEntity>()
                .eq("jiazhangid", entity.getJiazhangid())
                .eq("xueyuanid", entity.getXueyuanid());
        if (jiazhangxueyuanService.selectCount(ew) > 0) {
            return R.error("该家长与学员已绑定");
        }
        entity.setId(new Date().getTime() + (long) Math.floor(Math.random() * 1000));
        jiazhangxueyuanService.insert(entity);
        return R.ok();
    }

    @RequestMapping("/add")
    public R add(@RequestBody JiazhangxueyuanEntity entity) {
        return save(entity);
    }

    @RequestMapping("/update")
    public R update(@RequestBody JiazhangxueyuanEntity entity) {
        jiazhangxueyuanService.updateById(entity);
        return R.ok();
    }

    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        jiazhangxueyuanService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    @RequestMapping("/children/{jiazhangId}")
    public R children(@PathVariable("jiazhangId") Long jiazhangId) {
        List<JiazhangxueyuanEntity> list = jiazhangxueyuanService.selectList(new EntityWrapper<JiazhangxueyuanEntity>().eq("jiazhangid", jiazhangId));
        return R.ok().put("data", list);
    }
}
