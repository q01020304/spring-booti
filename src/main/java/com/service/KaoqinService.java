package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.entity.KaoqinEntity;
import com.utils.PageUtils;

import java.util.Map;

public interface KaoqinService extends IService<KaoqinEntity> {
    PageUtils queryPage(Map<String, Object> params);
}
