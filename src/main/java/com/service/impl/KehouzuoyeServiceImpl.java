package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.KehouzuoyeDao;
import com.entity.KehouzuoyeEntity;
import com.service.KehouzuoyeService;
import com.entity.vo.KehouzuoyeVO;
import com.entity.view.KehouzuoyeView;

@Service("kehouzuoyeService")
public class KehouzuoyeServiceImpl extends ServiceImpl<KehouzuoyeDao, KehouzuoyeEntity> implements KehouzuoyeService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<KehouzuoyeEntity> page = this.selectPage(
                new Query<KehouzuoyeEntity>(params).getPage(),
                new EntityWrapper<KehouzuoyeEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<KehouzuoyeEntity> wrapper) {
		  Page<KehouzuoyeView> page =new Query<KehouzuoyeView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<KehouzuoyeVO> selectListVO(Wrapper<KehouzuoyeEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public KehouzuoyeVO selectVO(Wrapper<KehouzuoyeEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<KehouzuoyeView> selectListView(Wrapper<KehouzuoyeEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public KehouzuoyeView selectView(Wrapper<KehouzuoyeEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
