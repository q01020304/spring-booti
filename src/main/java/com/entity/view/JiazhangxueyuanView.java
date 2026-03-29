package com.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.entity.JiazhangxueyuanEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

@TableName("jiazhangxueyuan")
public class JiazhangxueyuanView extends JiazhangxueyuanEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    public JiazhangxueyuanView() {}
    public JiazhangxueyuanView(JiazhangxueyuanEntity entity) {
        try {
            BeanUtils.copyProperties(this, entity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
