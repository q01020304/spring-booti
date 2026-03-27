package com.dao;

import com.entity.DiscusspeixunkechengEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscusspeixunkechengVO;
import com.entity.view.DiscusspeixunkechengView;


/**
 * 培训课程评论表
 * 
 * @author 
 * @email 
 * @date 2024-03-08 09:01:43
 */
public interface DiscusspeixunkechengDao extends BaseMapper<DiscusspeixunkechengEntity> {
	
	List<DiscusspeixunkechengVO> selectListVO(@Param("ew") Wrapper<DiscusspeixunkechengEntity> wrapper);
	
	DiscusspeixunkechengVO selectVO(@Param("ew") Wrapper<DiscusspeixunkechengEntity> wrapper);
	
	List<DiscusspeixunkechengView> selectListView(@Param("ew") Wrapper<DiscusspeixunkechengEntity> wrapper);

	List<DiscusspeixunkechengView> selectListView(Pagination page,@Param("ew") Wrapper<DiscusspeixunkechengEntity> wrapper);

	
	DiscusspeixunkechengView selectView(@Param("ew") Wrapper<DiscusspeixunkechengEntity> wrapper);
	

}
