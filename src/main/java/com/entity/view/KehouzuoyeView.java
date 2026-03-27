package com.entity.view;

import com.entity.KehouzuoyeEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 课后作业
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2024-03-08 09:01:42
 */
@TableName("kehouzuoye")
public class KehouzuoyeView  extends KehouzuoyeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public KehouzuoyeView(){
	}
 
 	public KehouzuoyeView(KehouzuoyeEntity kehouzuoyeEntity){
 	try {
			BeanUtils.copyProperties(this, kehouzuoyeEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
