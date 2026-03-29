package com.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.entity.BaomingjiaofeiEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

@TableName("baomingjiaofei")
public class BaomingjiaofeiView extends BaomingjiaofeiEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    public BaomingjiaofeiView() {}
    public BaomingjiaofeiView(BaomingjiaofeiEntity entity) {
        try {
            BeanUtils.copyProperties(this, entity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
