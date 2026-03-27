package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscusspeixunkechengEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscusspeixunkechengVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscusspeixunkechengView;


/**
 * 培训课程评论表
 *
 * @author 
 * @email 
 * @date 2024-03-08 09:01:43
 */
public interface DiscusspeixunkechengService extends IService<DiscusspeixunkechengEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscusspeixunkechengVO> selectListVO(Wrapper<DiscusspeixunkechengEntity> wrapper);
   	
   	DiscusspeixunkechengVO selectVO(@Param("ew") Wrapper<DiscusspeixunkechengEntity> wrapper);
   	
   	List<DiscusspeixunkechengView> selectListView(Wrapper<DiscusspeixunkechengEntity> wrapper);
   	
   	DiscusspeixunkechengView selectView(@Param("ew") Wrapper<DiscusspeixunkechengEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscusspeixunkechengEntity> wrapper);

   	

}

