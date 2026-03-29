package com.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.KaoqinDao;
import com.entity.KaoqinEntity;
import com.service.KaoqinService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("kaoqinService")
public class KaoqinServiceImpl extends ServiceImpl<KaoqinDao, KaoqinEntity> implements KaoqinService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<KaoqinEntity> page = this.selectPage(new Query<KaoqinEntity>(params).getPage(), new EntityWrapper<KaoqinEntity>());
        return new PageUtils(page);
    }
}
