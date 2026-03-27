package com.dao;

import com.entity.XuexizhongxinEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.XuexizhongxinVO;
import com.entity.view.XuexizhongxinView;


/**
 * 学习中心
 * 
 * @author 
 * @email 
 * @date 2024-03-08 09:01:42
 */
public interface XuexizhongxinDao extends BaseMapper<XuexizhongxinEntity> {
	
	List<XuexizhongxinVO> selectListVO(@Param("ew") Wrapper<XuexizhongxinEntity> wrapper);
	
	XuexizhongxinVO selectVO(@Param("ew") Wrapper<XuexizhongxinEntity> wrapper);
	
	List<XuexizhongxinView> selectListView(@Param("ew") Wrapper<XuexizhongxinEntity> wrapper);

	List<XuexizhongxinView> selectListView(Pagination page,@Param("ew") Wrapper<XuexizhongxinEntity> wrapper);

	
	XuexizhongxinView selectView(@Param("ew") Wrapper<XuexizhongxinEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<XuexizhongxinEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<XuexizhongxinEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<XuexizhongxinEntity> wrapper);



}
