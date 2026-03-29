package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.entity.ChengjijiluEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ChengjijiluDao extends BaseMapper<ChengjijiluEntity> {
    List<Map<String, Object>> selectStudentTrend(@Param("xueyuanId") Long xueyuanId,
                                                 @Param("kechengId") Long kechengId);

    List<Map<String, Object>> selectCourseAvgTrend(@Param("kechengId") Long kechengId);
}
