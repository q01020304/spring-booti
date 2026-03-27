package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.KehouzuoyeEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.KehouzuoyeVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.KehouzuoyeView;


/**
 * 课后作业
 *
 * @author 
 * @email 
 * @date 2024-03-08 09:01:42
 */
public interface KehouzuoyeService extends IService<KehouzuoyeEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<KehouzuoyeVO> selectListVO(Wrapper<KehouzuoyeEntity> wrapper);
   	
   	KehouzuoyeVO selectVO(@Param("ew") Wrapper<KehouzuoyeEntity> wrapper);
   	
   	List<KehouzuoyeView> selectListView(Wrapper<KehouzuoyeEntity> wrapper);
   	
   	KehouzuoyeView selectView(@Param("ew") Wrapper<KehouzuoyeEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<KehouzuoyeEntity> wrapper);

   	

}

