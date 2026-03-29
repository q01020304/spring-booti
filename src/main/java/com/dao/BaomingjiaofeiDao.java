package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.BaomingjiaofeiEntity;
import com.entity.view.BaomingjiaofeiView;
import com.entity.vo.BaomingjiaofeiVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BaomingjiaofeiDao extends BaseMapper<BaomingjiaofeiEntity> {
    List<BaomingjiaofeiVO> selectListVO(@Param("ew") Wrapper<BaomingjiaofeiEntity> wrapper);
    BaomingjiaofeiVO selectVO(@Param("ew") Wrapper<BaomingjiaofeiEntity> wrapper);
    List<BaomingjiaofeiView> selectListView(@Param("ew") Wrapper<BaomingjiaofeiEntity> wrapper);
    List<BaomingjiaofeiView> selectListView(Pagination page, @Param("ew") Wrapper<BaomingjiaofeiEntity> wrapper);
    BaomingjiaofeiView selectView(@Param("ew") Wrapper<BaomingjiaofeiEntity> wrapper);
    List<Map<String, Object>> paymentSummary(@Param("xueyuanId") Long xueyuanId, @Param("jiazhangId") Long jiazhangId);
}
