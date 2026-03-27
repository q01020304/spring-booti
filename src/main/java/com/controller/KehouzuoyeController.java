package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.KehouzuoyeEntity;
import com.entity.view.KehouzuoyeView;

import com.service.KehouzuoyeService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 课后作业
 * 后端接口
 * @author 
 * @email 
 * @date 2024-03-08 09:01:42
 */
@RestController
@RequestMapping("/kehouzuoye")
public class KehouzuoyeController {
    @Autowired
    private KehouzuoyeService kehouzuoyeService;




    



    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,KehouzuoyeEntity kehouzuoye,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiaoshi")) {
			kehouzuoye.setJiaoshigonghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("xueyuan")) {
			kehouzuoye.setXuehao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<KehouzuoyeEntity> ew = new EntityWrapper<KehouzuoyeEntity>();

		PageUtils page = kehouzuoyeService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kehouzuoye), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,KehouzuoyeEntity kehouzuoye, 
		HttpServletRequest request){
        EntityWrapper<KehouzuoyeEntity> ew = new EntityWrapper<KehouzuoyeEntity>();

		PageUtils page = kehouzuoyeService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kehouzuoye), params), params));
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( KehouzuoyeEntity kehouzuoye){
       	EntityWrapper<KehouzuoyeEntity> ew = new EntityWrapper<KehouzuoyeEntity>();
      	ew.allEq(MPUtil.allEQMapPre( kehouzuoye, "kehouzuoye")); 
        return R.ok().put("data", kehouzuoyeService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(KehouzuoyeEntity kehouzuoye){
        EntityWrapper< KehouzuoyeEntity> ew = new EntityWrapper< KehouzuoyeEntity>();
 		ew.allEq(MPUtil.allEQMapPre( kehouzuoye, "kehouzuoye")); 
		KehouzuoyeView kehouzuoyeView =  kehouzuoyeService.selectView(ew);
		return R.ok("查询课后作业成功").put("data", kehouzuoyeView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        KehouzuoyeEntity kehouzuoye = kehouzuoyeService.selectById(id);
        return R.ok().put("data", kehouzuoye);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        KehouzuoyeEntity kehouzuoye = kehouzuoyeService.selectById(id);
        return R.ok().put("data", kehouzuoye);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody KehouzuoyeEntity kehouzuoye, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(kehouzuoye);
        kehouzuoyeService.insert(kehouzuoye);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody KehouzuoyeEntity kehouzuoye, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(kehouzuoye);
        kehouzuoyeService.insert(kehouzuoye);
        return R.ok();
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody KehouzuoyeEntity kehouzuoye, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kehouzuoye);
        kehouzuoyeService.updateById(kehouzuoye);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        kehouzuoyeService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	







    /**
     * 总数量
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params,KehouzuoyeEntity kehouzuoye, HttpServletRequest request){
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("jiaoshi")) {
            kehouzuoye.setJiaoshigonghao((String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("xueyuan")) {
            kehouzuoye.setXuehao((String)request.getSession().getAttribute("username"));
        }
        EntityWrapper<KehouzuoyeEntity> ew = new EntityWrapper<KehouzuoyeEntity>();
        int count = kehouzuoyeService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kehouzuoye), params), params));
        return R.ok().put("data", count);
    }



}
