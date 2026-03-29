package com.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.JiazhangxueyuanDao;
import com.entity.JiazhangxueyuanEntity;
import com.entity.view.JiazhangxueyuanView;
import com.entity.vo.JiazhangxueyuanVO;
import com.service.JiazhangxueyuanService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("jiazhangxueyuanService")
public class JiazhangxueyuanServiceImpl extends ServiceImpl<JiazhangxueyuanDao, JiazhangxueyuanEntity> implements JiazhangxueyuanService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JiazhangxueyuanEntity> page = this.selectPage(new Query(params).getPage(), new EntityWrapper<JiazhangxueyuanEntity>());
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<JiazhangxueyuanEntity> wrapper) {
        Page<JiazhangxueyuanView> page = new Query(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        return new PageUtils(page);
    }

    @Override
    public List<JiazhangxueyuanVO> selectListVO(Wrapper<JiazhangxueyuanEntity> wrapper) { return baseMapper.selectListVO(wrapper); }
    @Override
    public JiazhangxueyuanVO selectVO(Wrapper<JiazhangxueyuanEntity> wrapper) { return baseMapper.selectVO(wrapper); }
    @Override
    public List<JiazhangxueyuanView> selectListView(Wrapper<JiazhangxueyuanEntity> wrapper) { return baseMapper.selectListView(wrapper); }
    @Override
    public JiazhangxueyuanView selectView(Wrapper<JiazhangxueyuanEntity> wrapper) { return baseMapper.selectView(wrapper); }
}
