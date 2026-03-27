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


import com.dao.DiscusspeixunkechengDao;
import com.entity.DiscusspeixunkechengEntity;
import com.service.DiscusspeixunkechengService;
import com.entity.vo.DiscusspeixunkechengVO;
import com.entity.view.DiscusspeixunkechengView;

@Service("discusspeixunkechengService")
public class DiscusspeixunkechengServiceImpl extends ServiceImpl<DiscusspeixunkechengDao, DiscusspeixunkechengEntity> implements DiscusspeixunkechengService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscusspeixunkechengEntity> page = this.selectPage(
                new Query<DiscusspeixunkechengEntity>(params).getPage(),
                new EntityWrapper<DiscusspeixunkechengEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscusspeixunkechengEntity> wrapper) {
		  Page<DiscusspeixunkechengView> page =new Query<DiscusspeixunkechengView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<DiscusspeixunkechengVO> selectListVO(Wrapper<DiscusspeixunkechengEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscusspeixunkechengVO selectVO(Wrapper<DiscusspeixunkechengEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscusspeixunkechengView> selectListView(Wrapper<DiscusspeixunkechengEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscusspeixunkechengView selectView(Wrapper<DiscusspeixunkechengEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
