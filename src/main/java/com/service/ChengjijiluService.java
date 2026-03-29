package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.entity.ChengjijiluEntity;
import com.utils.PageUtils;

import java.util.List;
import java.util.Map;

public interface ChengjijiluService extends IService<ChengjijiluEntity> {
    PageUtils queryPage(Map<String, Object> params);
    List<Map<String, Object>> getStudentTrend(Long xueyuanId, Long kechengId);
    List<Map<String, Object>> getCourseAvgTrend(Long kechengId);
}
