package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.entity.ChengzhangpingjiaEntity;
import com.utils.PageUtils;

import java.util.Map;

public interface ChengzhangpingjiaService extends IService<ChengzhangpingjiaEntity> {
    PageUtils queryPage(Map<String, Object> params);
}
