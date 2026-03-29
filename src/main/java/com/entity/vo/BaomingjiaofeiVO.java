package com.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BaomingjiaofeiVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String dingdanhao;
    private String xuehao;
    private String xueyuanxingming;
    private String kechengmingcheng;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date baomingriqi;
    private BigDecimal shifujine;
    private String jiaofeifangshi;
    private String jiaofeizhuangtai;

    public String getDingdanhao() { return dingdanhao; }
    public void setDingdanhao(String dingdanhao) { this.dingdanhao = dingdanhao; }
    public String getXuehao() { return xuehao; }
    public void setXuehao(String xuehao) { this.xuehao = xuehao; }
    public String getXueyuanxingming() { return xueyuanxingming; }
    public void setXueyuanxingming(String xueyuanxingming) { this.xueyuanxingming = xueyuanxingming; }
    public String getKechengmingcheng() { return kechengmingcheng; }
    public void setKechengmingcheng(String kechengmingcheng) { this.kechengmingcheng = kechengmingcheng; }
    public Date getBaomingriqi() { return baomingriqi; }
    public void setBaomingriqi(Date baomingriqi) { this.baomingriqi = baomingriqi; }
    public BigDecimal getShifujine() { return shifujine; }
    public void setShifujine(BigDecimal shifujine) { this.shifujine = shifujine; }
    public String getJiaofeifangshi() { return jiaofeifangshi; }
    public void setJiaofeifangshi(String jiaofeifangshi) { this.jiaofeifangshi = jiaofeifangshi; }
    public String getJiaofeizhuangtai() { return jiaofeizhuangtai; }
    public void setJiaofeizhuangtai(String jiaofeizhuangtai) { this.jiaofeizhuangtai = jiaofeizhuangtai; }
}
