package com.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.ChengjijiluDao;
import com.entity.ChengjijiluEntity;
import com.service.ChengjijiluService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("chengjijiluService")
public class ChengjijiluServiceImpl extends ServiceImpl<ChengjijiluDao, ChengjijiluEntity> implements ChengjijiluService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ChengjijiluEntity> page = this.selectPage(new Query<ChengjijiluEntity>(params).getPage(), new EntityWrapper<ChengjijiluEntity>());
        return new PageUtils(page);
    }

    @Override
    public List<Map<String, Object>> getStudentTrend(Long xueyuanId, Long kechengId) {
        return baseMapper.selectStudentTrend(xueyuanId, kechengId);
    }

    @Override
    public List<Map<String, Object>> getCourseAvgTrend(Long kechengId) {
        return baseMapper.selectCourseAvgTrend(kechengId);
    }
}
