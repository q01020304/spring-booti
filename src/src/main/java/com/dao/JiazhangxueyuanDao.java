package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.JiazhangxueyuanEntity;
import com.entity.view.JiazhangxueyuanView;
import com.entity.vo.JiazhangxueyuanVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JiazhangxueyuanDao extends BaseMapper<JiazhangxueyuanEntity> {
    List<JiazhangxueyuanVO> selectListVO(@Param("ew") Wrapper<JiazhangxueyuanEntity> wrapper);
    JiazhangxueyuanVO selectVO(@Param("ew") Wrapper<JiazhangxueyuanEntity> wrapper);
    List<JiazhangxueyuanView> selectListView(@Param("ew") Wrapper<JiazhangxueyuanEntity> wrapper);
    List<JiazhangxueyuanView> selectListView(Pagination page, @Param("ew") Wrapper<JiazhangxueyuanEntity> wrapper);
    JiazhangxueyuanView selectView(@Param("ew") Wrapper<JiazhangxueyuanEntity> wrapper);
}
