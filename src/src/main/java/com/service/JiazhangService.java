package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.entity.JiazhangEntity;
import com.entity.view.JiazhangView;
import com.entity.vo.JiazhangVO;
import com.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface JiazhangService extends IService<JiazhangEntity> {
    PageUtils queryPage(Map<String, Object> params);
    List<JiazhangVO> selectListVO(Wrapper<JiazhangEntity> wrapper);
    JiazhangVO selectVO(@Param("ew") Wrapper<JiazhangEntity> wrapper);
    List<JiazhangView> selectListView(Wrapper<JiazhangEntity> wrapper);
    JiazhangView selectView(@Param("ew") Wrapper<JiazhangEntity> wrapper);
    PageUtils queryPage(Map<String, Object> params, Wrapper<JiazhangEntity> wrapper);
}
