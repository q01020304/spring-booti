package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * 家长学员关联
 */
@TableName("jiazhangxueyuan")
public class JiazhangxueyuanEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public JiazhangxueyuanEntity() {}

    public JiazhangxueyuanEntity(T t) {
        try {
            BeanUtils.copyProperties(this, t);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @TableId
    private Long id;
    private Long jiazhangid;
    private String jiazhangzhanghao;
    private String jiazhangxingming;
    private Long xueyuanid;
    private String xuehao;
    private String xueyuanxingming;
    private String guanxi;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date addtime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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
    public String getGuanxi() { return guanxi; }
    public void setGuanxi(String guanxi) { this.guanxi = guanxi; }
    public Date getAddtime() { return addtime; }
    public void setAddtime(Date addtime) { this.addtime = addtime; }
}
