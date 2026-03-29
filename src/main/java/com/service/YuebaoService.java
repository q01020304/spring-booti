package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.entity.YuebaoEntity;
import com.utils.PageUtils;

import java.util.Map;

public interface YuebaoService extends IService<YuebaoEntity> {
    PageUtils queryPage(Map<String, Object> params, Wrapper<YuebaoEntity> wrapper);
    Map<String, Object> generateMonthlyReport(String month, Long xueyuanId, Long kechengId, String banji, String shengchengren);
}
