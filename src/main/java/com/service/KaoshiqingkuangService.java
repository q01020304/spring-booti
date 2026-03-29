package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.entity.KaoshiqingkuangEntity;
import com.utils.PageUtils;

import java.util.List;
import java.util.Map;

public interface KaoshiqingkuangService extends IService<KaoshiqingkuangEntity> {
    PageUtils queryPage(Map<String, Object> params, Wrapper<KaoshiqingkuangEntity> wrapper);
    Map<String, Object> monthlySummary(String month, Long xueyuanId, Long kechengId, String banji);
    List<Map<String, Object>> trend(String month, Long xueyuanId, Long kechengId);
}
