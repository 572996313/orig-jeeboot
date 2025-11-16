package org.jeecg.modules.system.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *  工具类：对象转换工具类
 * 用于实现Entity对象到Model对象的转换
 * @Author  张代浩
 *
 */
@Slf4j
public class oConvertUtils4 {

	/**
	 * 将entityList转换成modelList
	 * @param fromList
	 * @param tClass
	 * @param <F>
	 * @param <T>
	 * @return
	 */
	public static<F,T> List<T> entityListToModelList(List<F> fromList, Class<T> tClass){
		if(fromList == null || fromList.isEmpty()){
			return null;
		}
		List<T> tList = new ArrayList<>();
		for(F f : fromList){
			T t = entityToModel(f, tClass);
			tList.add(t);
		}
		return tList;
	}

	public static<F,T> T entityToModel(F entity, Class<T> modelClass) {
		log.debug("entityToModel : Entity属性的值赋值到Model");
		Object model = null;
		if (entity == null || modelClass ==null) {
			return null;
		}

		try {
			model = modelClass.newInstance();
		} catch (InstantiationException e) {
			log.error("entityToModel : 实例化异常", e);
		} catch (IllegalAccessException e) {
			log.error("entityToModel : 安全权限异常", e);
		}
		BeanUtils.copyProperties(entity, model);
		return (T)model;
	}
}