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
 * 报名缴费
 */
@TableName("baomingjiaofei")
public class BaomingjiaofeiEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public BaomingjiaofeiEntity() {}

    public BaomingjiaofeiEntity(T t) {
        try {
            BeanUtils.copyProperties(this, t);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @TableId
    private Long id;
    private String dingdanhao;
    private Long jiazhangid;
    private String jiazhangzhanghao;
    private String jiazhangxingming;
    private Long xueyuanid;
    private String xuehao;
    private String xueyuanxingming;
    private Long kechengid;
    private String kechengmingcheng;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date baomingriqi;

    private BigDecimal jiaofeijine;
    private BigDecimal youhuijine;
    private BigDecimal shifujine;
    private String jiaofeifangshi;
    private String jiaofeizhuangtai;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date jiaofeishijian;

    private String beizhu;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date addtime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDingdanhao() { return dingdanhao; }
    public void setDingdanhao(String dingdanhao) { this.dingdanhao = dingdanhao; }
    public Long getJiazhangid() { return jiazhangid; }
    public void setJiazhangid(Long jiazhangid) { this.jiazhangid = jiazhangid; }
    public String getJiazhangzhanghao() { return jiazhangzhanghao; }
    public void setJiazhangzhanghao(String jiazhangzhanghao) { this.jiazhangzhanghao = jiazhangzhanghao; }
    public String getJiazhangxingming() { return jiazhangxingming; }
    public void setJiazhangxingming(String jiazhangxingming) { this.jiazhangxingming = jiazhangxingming; }
    public Long getXueyuanid() { return xueyuanid; }
    public void setXueyuanid(Long xueyuanid) { this.xueyuanid = xueyuanid; }
    public String getXuehao() { return xuehao; }
    public void setXuehao(String xuehao) { this.xuehao = xuehao; }
    public String getXueyuanxingming() { return xueyuanxingming; }
    public void setXueyuanxingming(String xueyuanxingming) { this.xueyuanxingming = xueyuanxingming; }
    public Long getKechengid() { return kechengid; }
    public void setKechengid(Long kechengid) { this.kechengid = kechengid; }
    public String getKechengmingcheng() { return kechengmingcheng; }
    public void setKechengmingcheng(String kechengmingcheng) { this.kechengmingcheng = kechengmingcheng; }
    public Date getBaomingriqi() { return baomingriqi; }
    public void setBaomingriqi(Date baomingriqi) { this.baomingriqi = baomingriqi; }
    public BigDecimal getJiaofeijine() { return jiaofeijine; }
    public void setJiaofeijine(BigDecimal jiaofeijine) { this.jiaofeijine = jiaofeijine; }
    public BigDecimal getYouhuijine() { return youhuijine; }
    public void setYouhuijine(BigDecimal youhuijine) { this.youhuijine = youhuijine; }
    public BigDecimal getShifujine() { return shifujine; }
    public void setShifujine(BigDecimal shifujine) { this.shifujine = shifujine; }
    public String getJiaofeifangshi() { return jiaofeifangshi; }
    public void setJiaofeifangshi(String jiaofeifangshi) { this.jiaofeifangshi = jiaofeifangshi; }
    public String getJiaofeizhuangtai() { return jiaofeizhuangtai; }
    public void setJiaofeizhuangtai(String jiaofeizhuangtai) { this.jiaofeizhuangtai = jiaofeizhuangtai; }
    public Date getJiaofeishijian() { return jiaofeishijian; }
    public void setJiaofeishijian(Date jiaofeishijian) { this.jiaofeishijian = jiaofeishijian; }
    public String getBeizhu() { return beizhu; }
    public void setBeizhu(String beizhu) { this.beizhu = beizhu; }
    public Date getAddtime() { return addtime; }
    public void setAddtime(Date addtime) { this.addtime = addtime; }
}
