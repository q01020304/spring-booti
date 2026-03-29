package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 考试情况（周测/月测）
 */
@TableName("kaoshiqingkuang")
public class KaoshiqingkuangEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public KaoshiqingkuangEntity() {}

    public KaoshiqingkuangEntity(T t) {
        try {
            BeanUtils.copyProperties(this, t);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @TableId
    private Long id;
    private Long xueyuanid;
    private String xuehao;
    private String xueyuanxingming;
    private String banji;
    private Long kechengid;
    private String kechengmingcheng;
    private String kaoshileixing;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date kaoshiriqi;

    private BigDecimal chengji;
    private Integer paiming;
    private BigDecimal manyuanzhi;
    private String beizhu;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date addtime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getXueyuanid() { return xueyuanid; }
    public void setXueyuanid(Long xueyuanid) { this.xueyuanid = xueyuanid; }
    public String getXuehao() { return xuehao; }
    public void setXuehao(String xuehao) { this.xuehao = xuehao; }
    public String getXueyuanxingming() { return xueyuanxingming; }
    public void setXueyuanxingming(String xueyuanxingming) { this.xueyuanxingming = xueyuanxingming; }
    public String getBanji() { return banji; }
    public void setBanji(String banji) { this.banji = banji; }
    public Long getKechengid() { return kechengid; }
    public void setKechengid(Long kechengid) { this.kechengid = kechengid; }
    public String getKechengmingcheng() { return kechengmingcheng; }
    public void setKechengmingcheng(String kechengmingcheng) { this.kechengmingcheng = kechengmingcheng; }
    public String getKaoshileixing() { return kaoshileixing; }
    public void setKaoshileixing(String kaoshileixing) { this.kaoshileixing = kaoshileixing; }
    public Date getKaoshiriqi() { return kaoshiriqi; }
    public void setKaoshiriqi(Date kaoshiriqi) { this.kaoshiriqi = kaoshiriqi; }
    public BigDecimal getChengji() { return chengji; }
    public void setChengji(BigDecimal chengji) { this.chengji = chengji; }
    public Integer getPaiming() { return paiming; }
    public void setPaiming(Integer paiming) { this.paiming = paiming; }
    public BigDecimal getManyuanzhi() { return manyuanzhi; }
    public void setManyuanzhi(BigDecimal manyuanzhi) { this.manyuanzhi = manyuanzhi; }
    public String getBeizhu() { return beizhu; }
    public void setBeizhu(String beizhu) { this.beizhu = beizhu; }
    public Date getAddtime() { return addtime; }
    public void setAddtime(Date addtime) { this.addtime = addtime; }
}
