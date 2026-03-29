package com.controller;

import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.JiazhangEntity;
import com.entity.view.JiazhangView;
import com.service.JiazhangService;
import com.service.TokenService;
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
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/jiazhang")
public class JiazhangController {
    @Autowired
    private JiazhangService jiazhangService;
    @Autowired
    private TokenService tokenService;

    @IgnoreAuth
    @RequestMapping("/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        JiazhangEntity u = jiazhangService.selectOne(new EntityWrapper<JiazhangEntity>().eq("jiazhangzhanghao", username));
        if (u == null || !u.getMima().equals(password)) {
            return R.error("账号或密码不正确");
        }
        String token = tokenService.generateToken(u.getId(), username, "jiazhang", "家长");
        return R.ok().put("token", token);
    }

    @IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody JiazhangEntity jiazhang) {
        JiazhangEntity old = jiazhangService.selectOne(new EntityWrapper<JiazhangEntity>().eq("jiazhangzhanghao", jiazhang.getJiazhangzhanghao()));
        if (old != null) {
            return R.error("注册用户已存在");
        }
        jiazhang.setId(new Date().getTime());
        jiazhangService.insert(jiazhang);
        return R.ok();
    }

    @RequestMapping("/logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }

    @RequestMapping("/session")
    public R session(HttpServletRequest request) {
        Long id = (Long) request.getSession().getAttribute("userId");
        JiazhangEntity u = jiazhangService.selectById(id);
        return R.ok().put("data", u);
    }

    @IgnoreAuth
    @RequestMapping("/resetPass")
    public R resetPass(String username) {
        JiazhangEntity u = jiazhangService.selectOne(new EntityWrapper<JiazhangEntity>().eq("jiazhangzhanghao", username));
        if (u == null) {
            return R.error("账号不存在");
        }
        u.setMima("123456");
        jiazhangService.updateById(u);
        return R.ok("密码已重置为：123456");
    }

    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, JiazhangEntity jiazhang) {
        EntityWrapper<JiazhangEntity> ew = new EntityWrapper<JiazhangEntity>();
        PageUtils page = jiazhangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiazhang), params), params));
        return R.ok().put("data", page);
    }

    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, JiazhangEntity jiazhang) {
        EntityWrapper<JiazhangEntity> ew = new EntityWrapper<JiazhangEntity>();
        PageUtils page = jiazhangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiazhang), params), params));
        return R.ok().put("data", page);
    }

    @RequestMapping("/lists")
    public R lists(JiazhangEntity jiazhang) {
        EntityWrapper<JiazhangEntity> ew = new EntityWrapper<JiazhangEntity>();
        ew.allEq(MPUtil.allEQMapPre(jiazhang, "jiazhang"));
        return R.ok().put("data", jiazhangService.selectListView(ew));
    }

    @RequestMapping("/query")
    public R query(JiazhangEntity jiazhang) {
        EntityWrapper<JiazhangEntity> ew = new EntityWrapper<JiazhangEntity>();
        ew.allEq(MPUtil.allEQMapPre(jiazhang, "jiazhang"));
        JiazhangView view = jiazhangService.selectView(ew);
        return R.ok("查询家长成功").put("data", view);
    }

    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        return R.ok().put("data", jiazhangService.selectById(id));
    }

    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id) {
        return R.ok().put("data", jiazhangService.selectById(id));
    }

    @RequestMapping("/save")
    public R save(@RequestBody JiazhangEntity jiazhang) {
        if (jiazhangService.selectCount(new EntityWrapper<JiazhangEntity>().eq("jiazhangzhanghao", jiazhang.getJiazhangzhanghao())) > 0) {
            return R.error("家长账号已存在");
        }
        jiazhang.setId(new Date().getTime() + (long) Math.floor(Math.random() * 1000));
        jiazhangService.insert(jiazhang);
        return R.ok();
    }

    @RequestMapping("/add")
    public R add(@RequestBody JiazhangEntity jiazhang) {
        return save(jiazhang);
    }

    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody JiazhangEntity jiazhang) {
        JiazhangEntity old = jiazhangService.selectOne(new EntityWrapper<JiazhangEntity>()
                .eq("jiazhangzhanghao", jiazhang.getJiazhangzhanghao())
                .notIn("id", jiazhang.getId()));
        if (old != null) {
            return R.error("家长账号已存在");
        }
        jiazhangService.updateById(jiazhang);
        return R.ok();
    }

    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        jiazhangService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
}
