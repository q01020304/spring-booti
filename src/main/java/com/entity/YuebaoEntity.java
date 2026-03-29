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
 * 月报记录
 */
@TableName("yuebao")
public class YuebaoEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public YuebaoEntity() {}

    public YuebaoEntity(T t) {
        try {
            BeanUtils.copyProperties(this, t);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @TableId
    private Long id;
    private String yuefen;
    private Long xueyuanid;
    private String xuehao;
    private String xueyuanxingming;
    private String banji;
    private Long kechengid;
    private String kechengmingcheng;
    private Integer kaoqincishu;
    private Integer chuqincishu;
    private Integer chidaocishu;
    private Integer qingjiacishu;
    private Integer quekincishu;
    private Integer zhoucecishu;
    private Integer yuececishu;
    private BigDecimal pingjunchengji;
    private BigDecimal zuigaochengji;
    private BigDecimal zuixiaochengji;
    private BigDecimal pingjiafen;
    private BigDecimal yingjiaofei;
    private BigDecimal shijiaofei;
    private String yuebaozhaiyao;
    private String jianyi;
    private String shengchengren;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date shengchengshijian;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date addtime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getYuefen() { return yuefen; }
    public void setYuefen(String yuefen) { this.yuefen = yuefen; }
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
    public Integer getKaoqincishu() { return kaoqincishu; }
    public void setKaoqincishu(Integer kaoqincishu) { this.kaoqincishu = kaoqincishu; }
    public Integer getChuqincishu() { return chuqincishu; }
    public void setChuqincishu(Integer chuqincishu) { this.chuqincishu = chuqincishu; }
    public Integer getChidaocishu() { return chidaocishu; }
    public void setChidaocishu(Integer chidaocishu) { this.chidaocishu = chidaocishu; }
    public Integer getQingjiacishu() { return qingjiacishu; }
    public void setQingjiacishu(Integer qingjiacishu) { this.qingjiacishu = qingjiacishu; }
    public Integer getQuekincishu() { return quekincishu; }
    public void setQuekincishu(Integer quekincishu) { this.quekincishu = quekincishu; }
    public Integer getZhoucecishu() { return zhoucecishu; }
    public void setZhoucecishu(Integer zhoucecishu) { this.zhoucecishu = zhoucecishu; }
    public Integer getYuececishu() { return yuececishu; }
    public void setYuececishu(Integer yuececishu) { this.yuececishu = yuececishu; }
    public BigDecimal getPingjunchengji() { return pingjunchengji; }
    public void setPingjunchengji(BigDecimal pingjunchengji) { this.pingjunchengji = pingjunchengji; }
    public BigDecimal getZuigaochengji() { return zuigaochengji; }
    public void setZuigaochengji(BigDecimal zuigaochengji) { this.zuigaochengji = zuigaochengji; }
    public BigDecimal getZuixiaochengji() { return zuixiaochengji; }
    public void setZuixiaochengji(BigDecimal zuixiaochengji) { this.zuixiaochengji = zuixiaochengji; }
    public BigDecimal getPingjiafen() { return pingjiafen; }
    public void setPingjiafen(BigDecimal pingjiafen) { this.pingjiafen = pingjiafen; }
    public BigDecimal getYingjiaofei() { return yingjiaofei; }
    public void setYingjiaofei(BigDecimal yingjiaofei) { this.yingjiaofei = yingjiaofei; }
    public BigDecimal getShijiaofei() { return shijiaofei; }
    public void setShijiaofei(BigDecimal shijiaofei) { this.shijiaofei = shijiaofei; }
    public String getYuebaozhaiyao() { return yuebaozhaiyao; }
    public void setYuebaozhaiyao(String yuebaozhaiyao) { this.yuebaozhaiyao = yuebaozhaiyao; }
    public String getJianyi() { return jianyi; }
    public void setJianyi(String jianyi) { this.jianyi = jianyi; }
    public String getShengchengren() { return shengchengren; }
    public void setShengchengren(String shengchengren) { this.shengchengren = shengchengren; }
    public Date getShengchengshijian() { return shengchengshijian; }
    public void setShengchengshijian(Date shengchengshijian) { this.shengchengshijian = shengchengshijian; }
    public Date getAddtime() { return addtime; }
    public void setAddtime(Date addtime) { this.addtime = addtime; }
}
