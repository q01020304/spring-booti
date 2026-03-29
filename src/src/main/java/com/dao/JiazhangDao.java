package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.JiazhangEntity;
import com.entity.view.JiazhangView;
import com.entity.vo.JiazhangVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JiazhangDao extends BaseMapper<JiazhangEntity> {
    List<JiazhangVO> selectListVO(@Param("ew") Wrapper<JiazhangEntity> wrapper);
    JiazhangVO selectVO(@Param("ew") Wrapper<JiazhangEntity> wrapper);
    List<JiazhangView> selectListView(@Param("ew") Wrapper<JiazhangEntity> wrapper);
    List<JiazhangView> selectListView(Pagination page, @Param("ew") Wrapper<JiazhangEntity> wrapper);
    JiazhangView selectView(@Param("ew") Wrapper<JiazhangEntity> wrapper);
}
