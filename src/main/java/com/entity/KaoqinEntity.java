package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import org.apache.commons.beanutils.BeanUtils;

@TableName("kaoqin")
public class KaoqinEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public KaoqinEntity() {}

    public KaoqinEntity(T t) {
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

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date kaoqinriqi;

    private String kaoqinzhuangtai;
    private String beizhu;

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
    public Date getKaoqinriqi() { return kaoqinriqi; }
    public void setKaoqinriqi(Date kaoqinriqi) { this.kaoqinriqi = kaoqinriqi; }
    public String getKaoqinzhuangtai() { return kaoqinzhuangtai; }
    public void setKaoqinzhuangtai(String kaoqinzhuangtai) { this.kaoqinzhuangtai = kaoqinzhuangtai; }
    public String getBeizhu() { return beizhu; }
    public void setBeizhu(String beizhu) { this.beizhu = beizhu; }
    public Date getAddtime() { return addtime; }
    public void setAddtime(Date addtime) { this.addtime = addtime; }
}
