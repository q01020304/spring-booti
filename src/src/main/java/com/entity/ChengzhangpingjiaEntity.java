package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.beanutils.BeanUtils;

@TableName("chengzhangpingjia")
public class ChengzhangpingjiaEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public ChengzhangpingjiaEntity() {}

    public ChengzhangpingjiaEntity(T t) {
        try {
            BeanUtils.copyProperties(this, t);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @TableId
    private Long id;
    private Long xueyuanId;
    private String xuehao;
    private String xueyuanxingming;
    private Long kechengId;
    private String kechengmingcheng;
    private String jieguanduan;
    private Integer xuexitaidu;
    private Integer ketangbiaoxian;
    private Integer zuoyewancheng;
    private BigDecimal zonghedefen;
    private String pingyuneirong;
    private String pingjiaren;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date pingjiashijian;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date addtime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getXueyuanId() { return xueyuanId; }
    public void setXueyuanId(Long xueyuanId) { this.xueyuanId = xueyuanId; }
    public String getXuehao() { return xuehao; }
    public void setXuehao(String xuehao) { this.xuehao = xuehao; }
    public String getXueyuanxingming() { return xueyuanxingming; }
    public void setXueyuanxingming(String xueyuanxingming) { this.xueyuanxingming = xueyuanxingming; }
    public Long getKechengId() { return kechengId; }
    public void setKechengId(Long kechengId) { this.kechengId = kechengId; }
    public String getKechengmingcheng() { return kechengmingcheng; }
    public void setKechengmingcheng(String kechengmingcheng) { this.kechengmingcheng = kechengmingcheng; }
    public String getJieguanduan() { return jieguanduan; }
    public void setJieguanduan(String jieguanduan) { this.jieguanduan = jieguanduan; }
    public Integer getXuexitaidu() { return xuexitaidu; }
    public void setXuexitaidu(Integer xuexitaidu) { this.xuexitaidu = xuexitaidu; }
    public Integer getKetangbiaoxian() { return ketangbiaoxian; }
    public void setKetangbiaoxian(Integer ketangbiaoxian) { this.ketangbiaoxian = ketangbiaoxian; }
    public Integer getZuoyewancheng() { return zuoyewancheng; }
    public void setZuoyewancheng(Integer zuoyewancheng) { this.zuoyewancheng = zuoyewancheng; }
    public BigDecimal getZonghedefen() { return zonghedefen; }
    public void setZonghedefen(BigDecimal zonghedefen) { this.zonghedefen = zonghedefen; }
    public String getPingyuneirong() { return pingyuneirong; }
    public void setPingyuneirong(String pingyuneirong) { this.pingyuneirong = pingyuneirong; }
    public String getPingjiaren() { return pingjiaren; }
    public void setPingjiaren(String pingjiaren) { this.pingjiaren = pingjiaren; }
    public Date getPingjiashijian() { return pingjiashijian; }
    public void setPingjiashijian(Date pingjiashijian) { this.pingjiashijian = pingjiashijian; }
    public Date getAddtime() { return addtime; }
    public void setAddtime(Date addtime) { this.addtime = addtime; }
}
