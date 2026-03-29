package com.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.ChengzhangpingjiaDao;
import com.entity.ChengzhangpingjiaEntity;
import com.service.ChengzhangpingjiaService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

@Service("chengzhangpingjiaService")
public class ChengzhangpingjiaServiceImpl extends ServiceImpl<ChengzhangpingjiaDao, ChengzhangpingjiaEntity> implements ChengzhangpingjiaService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ChengzhangpingjiaEntity> page = this.selectPage(new Query<ChengzhangpingjiaEntity>(params).getPage(), new EntityWrapper<ChengzhangpingjiaEntity>());
        return new PageUtils(page);
    }

    @Override
    public boolean insert(ChengzhangpingjiaEntity entity) {
        fillScore(entity);
        return super.insert(entity);
    }

    @Override
    public boolean updateById(ChengzhangpingjiaEntity entity) {
        fillScore(entity);
        return super.updateById(entity);
    }

    private void fillScore(ChengzhangpingjiaEntity entity) {
        int a = entity.getXuexitaidu() == null ? 0 : entity.getXuexitaidu();
        int b = entity.getKetangbiaoxian() == null ? 0 : entity.getKetangbiaoxian();
        int c = entity.getZuoyewancheng() == null ? 0 : entity.getZuoyewancheng();
        BigDecimal total = BigDecimal.valueOf(a + b + c)
                .divide(BigDecimal.valueOf(3), 2, RoundingMode.HALF_UP);
        entity.setZonghedefen(total);
    }
}
