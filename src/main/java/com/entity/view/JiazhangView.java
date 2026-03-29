package com.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.entity.JiazhangEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

@TableName("jiazhang")
public class JiazhangView extends JiazhangEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    public JiazhangView() {}
    public JiazhangView(JiazhangEntity entity) {
        try {
            BeanUtils.copyProperties(this, entity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
