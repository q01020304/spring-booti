package com.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.BaomingjiaofeiDao;
import com.entity.BaomingjiaofeiEntity;
import com.entity.view.BaomingjiaofeiView;
import com.entity.vo.BaomingjiaofeiVO;
import com.service.BaomingjiaofeiService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("baomingjiaofeiService")
public class BaomingjiaofeiServiceImpl extends ServiceImpl<BaomingjiaofeiDao, BaomingjiaofeiEntity> implements BaomingjiaofeiService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BaomingjiaofeiEntity> page = this.selectPage(new Query(params).getPage(), new EntityWrapper<BaomingjiaofeiEntity>());
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<BaomingjiaofeiEntity> wrapper) {
        Page<BaomingjiaofeiView> page = new Query(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        return new PageUtils(page);
    }

    @Override
    public List<BaomingjiaofeiVO> selectListVO(Wrapper<BaomingjiaofeiEntity> wrapper) { return baseMapper.selectListVO(wrapper); }
    @Override
    public BaomingjiaofeiVO selectVO(Wrapper<BaomingjiaofeiEntity> wrapper) { return baseMapper.selectVO(wrapper); }
    @Override
    public List<BaomingjiaofeiView> selectListView(Wrapper<BaomingjiaofeiEntity> wrapper) { return baseMapper.selectListView(wrapper); }
    @Override
    public BaomingjiaofeiView selectView(Wrapper<BaomingjiaofeiEntity> wrapper) { return baseMapper.selectView(wrapper); }
    @Override
    public List<Map<String, Object>> paymentSummary(Long xueyuanId, Long jiazhangId) { return baseMapper.paymentSummary(xueyuanId, jiazhangId); }
}
