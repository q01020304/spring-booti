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

import com.entity.PeixunkechengEntity;
import com.entity.view.PeixunkechengView;

import com.service.PeixunkechengService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;
import com.service.StoreupService;
import com.entity.StoreupEntity;

/**
 * 培训课程
 * 后端接口
 * @author 
 * @email 
 * @date 2024-03-08 09:01:42
 */
@RestController
@RequestMapping("/peixunkecheng")
public class PeixunkechengController {
    @Autowired
    private PeixunkechengService peixunkechengService;

    @Autowired
    private StoreupService storeupService;



    



    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,PeixunkechengEntity peixunkecheng,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiaoshi")) {
			peixunkecheng.setJiaoshigonghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<PeixunkechengEntity> ew = new EntityWrapper<PeixunkechengEntity>();

		PageUtils page = peixunkechengService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, peixunkecheng), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,PeixunkechengEntity peixunkecheng, 
		HttpServletRequest request){
        EntityWrapper<PeixunkechengEntity> ew = new EntityWrapper<PeixunkechengEntity>();

		PageUtils page = peixunkechengService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, peixunkecheng), params), params));
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( PeixunkechengEntity peixunkecheng){
       	EntityWrapper<PeixunkechengEntity> ew = new EntityWrapper<PeixunkechengEntity>();
      	ew.allEq(MPUtil.allEQMapPre( peixunkecheng, "peixunkecheng")); 
        return R.ok().put("data", peixunkechengService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(PeixunkechengEntity peixunkecheng){
        EntityWrapper< PeixunkechengEntity> ew = new EntityWrapper< PeixunkechengEntity>();
 		ew.allEq(MPUtil.allEQMapPre( peixunkecheng, "peixunkecheng")); 
		PeixunkechengView peixunkechengView =  peixunkechengService.selectView(ew);
		return R.ok("查询培训课程成功").put("data", peixunkechengView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        PeixunkechengEntity peixunkecheng = peixunkechengService.selectById(id);
		peixunkecheng.setClicknum(peixunkecheng.getClicknum()+1);
		peixunkecheng.setClicktime(new Date());
		peixunkechengService.updateById(peixunkecheng);
        peixunkecheng = peixunkechengService.selectView(new EntityWrapper<PeixunkechengEntity>().eq("id", id));
        return R.ok().put("data", peixunkecheng);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        PeixunkechengEntity peixunkecheng = peixunkechengService.selectById(id);
		peixunkecheng.setClicknum(peixunkecheng.getClicknum()+1);
		peixunkecheng.setClicktime(new Date());
		peixunkechengService.updateById(peixunkecheng);
        peixunkecheng = peixunkechengService.selectView(new EntityWrapper<PeixunkechengEntity>().eq("id", id));
        return R.ok().put("data", peixunkecheng);
    }
    


    /**
     * 赞或踩
     */
    @RequestMapping("/thumbsup/{id}")
    public R vote(@PathVariable("id") String id,String type){
        PeixunkechengEntity peixunkecheng = peixunkechengService.selectById(id);
        if(type.equals("1")) {
        	peixunkecheng.setThumbsupnum(peixunkecheng.getThumbsupnum()+1);
        } else {
        	peixunkecheng.setCrazilynum(peixunkecheng.getCrazilynum()+1);
        }
        peixunkechengService.updateById(peixunkecheng);
        return R.ok("投票成功");
    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody PeixunkechengEntity peixunkecheng, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(peixunkecheng);
        peixunkechengService.insert(peixunkecheng);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody PeixunkechengEntity peixunkecheng, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(peixunkecheng);
        peixunkechengService.insert(peixunkecheng);
        return R.ok();
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody PeixunkechengEntity peixunkecheng, HttpServletRequest request){
        //ValidatorUtils.validateEntity(peixunkecheng);
        peixunkechengService.updateById(peixunkecheng);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        peixunkechengService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前端智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,PeixunkechengEntity peixunkecheng, HttpServletRequest request,String pre){
        EntityWrapper<PeixunkechengEntity> ew = new EntityWrapper<PeixunkechengEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicknum");
        params.put("order", "desc");
		PageUtils page = peixunkechengService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, peixunkecheng), params), params));
        return R.ok().put("data", page);
    }


    /**
     * 协同算法（按收藏推荐）
     */
    @RequestMapping("/autoSort2")
    public R autoSort2(@RequestParam Map<String, Object> params,PeixunkechengEntity peixunkecheng, HttpServletRequest request){
        String userId = request.getSession().getAttribute("userId").toString();
        String inteltypeColumn = "kechengfenlei";
        List<StoreupEntity> storeups = storeupService.selectList(new EntityWrapper<StoreupEntity>().eq("type", 1).eq("userid", userId).eq("tablename", "peixunkecheng").orderBy("addtime", false));
        List<String> inteltypes = new ArrayList<String>();
        Integer limit = params.get("limit")==null?10:Integer.parseInt(params.get("limit").toString());
        List<PeixunkechengEntity> peixunkechengList = new ArrayList<PeixunkechengEntity>();
        //去重
        if(storeups!=null && storeups.size()>0) {
            for(StoreupEntity s : storeups) {
                peixunkechengList.addAll(peixunkechengService.selectList(new EntityWrapper<PeixunkechengEntity>().eq(inteltypeColumn, s.getInteltype())));
            }
        }
        EntityWrapper<PeixunkechengEntity> ew = new EntityWrapper<PeixunkechengEntity>();
        params.put("sort", "id");
        params.put("order", "desc");
        PageUtils page = peixunkechengService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, peixunkecheng), params), params));
        List<PeixunkechengEntity> pageList = (List<PeixunkechengEntity>)page.getList();
        if(peixunkechengList.size()<limit) {
            int toAddNum = (limit-peixunkechengList.size())<=pageList.size()?(limit-peixunkechengList.size()):pageList.size();
            for(PeixunkechengEntity o1 : pageList) {
                boolean addFlag = true;
                for(PeixunkechengEntity o2 : peixunkechengList) {
                    if(o1.getId().intValue()==o2.getId().intValue()) {
                        addFlag = false;
                        break;
                    }
                }
                if(addFlag) {
                    peixunkechengList.add(o1);
                    if(--toAddNum==0) break;
                }
            }
        } else if(peixunkechengList.size()>limit) {
            peixunkechengList = peixunkechengList.subList(0, limit);
        }
        page.setList(peixunkechengList);
        return R.ok().put("data", page);
    }





    /**
     * 总数量
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params,PeixunkechengEntity peixunkecheng, HttpServletRequest request){
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("jiaoshi")) {
            peixunkecheng.setJiaoshigonghao((String)request.getSession().getAttribute("username"));
        }
        EntityWrapper<PeixunkechengEntity> ew = new EntityWrapper<PeixunkechengEntity>();
        int count = peixunkechengService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, peixunkecheng), params), params));
        return R.ok().put("data", count);
    }



}
