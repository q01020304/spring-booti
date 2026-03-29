package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.entity.JiazhangxueyuanEntity;
import com.entity.view.JiazhangxueyuanView;
import com.entity.vo.JiazhangxueyuanVO;
import com.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface JiazhangxueyuanService extends IService<JiazhangxueyuanEntity> {
    PageUtils queryPage(Map<String, Object> params);
    List<JiazhangxueyuanVO> selectListVO(Wrapper<JiazhangxueyuanEntity> wrapper);
    JiazhangxueyuanVO selectVO(@Param("ew") Wrapper<JiazhangxueyuanEntity> wrapper);
    List<JiazhangxueyuanView> selectListView(Wrapper<JiazhangxueyuanEntity> wrapper);
    JiazhangxueyuanView selectView(@Param("ew") Wrapper<JiazhangxueyuanEntity> wrapper);
    PageUtils queryPage(Map<String, Object> params, Wrapper<JiazhangxueyuanEntity> wrapper);
}
