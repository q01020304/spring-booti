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
 * 家长
 */
@TableName("jiazhang")
public class JiazhangEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public JiazhangEntity() {
    }

    public JiazhangEntity(T t) {
        try {
            BeanUtils.copyProperties(this, t);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @TableId
    private Long id;
    private String jiazhangzhanghao;
    private String mima;
    private String jiazhangxingming;
    private String xingbie;
    private String touxiang;
    private String shouji;
    private String youxiang;
    private String beizhu;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date addtime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getJiazhangzhanghao() { return jiazhangzhanghao; }
    public void setJiazhangzhanghao(String jiazhangzhanghao) { this.jiazhangzhanghao = jiazhangzhanghao; }
    public String getMima() { return mima; }
    public void setMima(String mima) { this.mima = mima; }
    public String getJiazhangxingming() { return jiazhangxingming; }
    public void setJiazhangxingming(String jiazhangxingming) { this.jiazhangxingming = jiazhangxingming; }
    public String getXingbie() { return xingbie; }
    public void setXingbie(String xingbie) { this.xingbie = xingbie; }
    public String getTouxiang() { return touxiang; }
    public void setTouxiang(String touxiang) { this.touxiang = touxiang; }
    public String getShouji() { return shouji; }
    public void setShouji(String shouji) { this.shouji = shouji; }
    public String getYouxiang() { return youxiang; }
    public void setYouxiang(String youxiang) { this.youxiang = youxiang; }
    public String getBeizhu() { return beizhu; }
    public void setBeizhu(String beizhu) { this.beizhu = beizhu; }
    public Date getAddtime() { return addtime; }
    public void setAddtime(Date addtime) { this.addtime = addtime; }
}
