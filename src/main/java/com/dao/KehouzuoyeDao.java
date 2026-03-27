package com.dao;

import com.entity.KehouzuoyeEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.KehouzuoyeVO;
import com.entity.view.KehouzuoyeView;


/**
 * 课后作业
 * 
 * @author 
 * @email 
 * @date 2024-03-08 09:01:42
 */
public interface KehouzuoyeDao extends BaseMapper<KehouzuoyeEntity> {
	
	List<KehouzuoyeVO> selectListVO(@Param("ew") Wrapper<KehouzuoyeEntity> wrapper);
	
	KehouzuoyeVO selectVO(@Param("ew") Wrapper<KehouzuoyeEntity> wrapper);
	
	List<KehouzuoyeView> selectListView(@Param("ew") Wrapper<KehouzuoyeEntity> wrapper);

	List<KehouzuoyeView> selectListView(Pagination page,@Param("ew") Wrapper<KehouzuoyeEntity> wrapper);

	
	KehouzuoyeView selectView(@Param("ew") Wrapper<KehouzuoyeEntity> wrapper);
	

}
