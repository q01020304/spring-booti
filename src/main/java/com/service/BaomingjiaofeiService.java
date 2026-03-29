package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.entity.BaomingjiaofeiEntity;
import com.entity.view.BaomingjiaofeiView;
import com.entity.vo.BaomingjiaofeiVO;
import com.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BaomingjiaofeiService extends IService<BaomingjiaofeiEntity> {
    PageUtils queryPage(Map<String, Object> params);
    List<BaomingjiaofeiVO> selectListVO(Wrapper<BaomingjiaofeiEntity> wrapper);
    BaomingjiaofeiVO selectVO(@Param("ew") Wrapper<BaomingjiaofeiEntity> wrapper);
    List<BaomingjiaofeiView> selectListView(Wrapper<BaomingjiaofeiEntity> wrapper);
    BaomingjiaofeiView selectView(@Param("ew") Wrapper<BaomingjiaofeiEntity> wrapper);
    PageUtils queryPage(Map<String, Object> params, Wrapper<BaomingjiaofeiEntity> wrapper);
    List<Map<String, Object>> paymentSummary(Long xueyuanId, Long jiazhangId);
}
