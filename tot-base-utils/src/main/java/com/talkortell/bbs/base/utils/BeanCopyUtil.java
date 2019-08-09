package com.talkortell.bbs.base.utils;

import java.util.Collections;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

public class BeanCopyUtil {
	@SuppressWarnings("unchecked")
	public static <F, T> List<T> copyList(List<F> sourceList, Class<T> destinationClass){
		if(CollectionUtils.isEmpty(sourceList)) {
			return Collections.emptyList();
		}
		if(sourceList.get(0).getClass().equals(destinationClass)) {
			return (List<T>)sourceList;
		}
		List<T> destinationList = Lists.newArrayListWithExpectedSize(sourceList.size());
		for(Object sourceObject : sourceList) {
			T destinationObject = copy(sourceObject, destinationClass);
			destinationList.add(destinationObject);
		}
		return destinationList;
	}
	
	@SuppressWarnings("unchecked")
	public static <F, T> T copy(F from, Class<T> destinationClass) {
		if(null == from) {
			return null;
		}
		if(from.getClass().equals(destinationClass)) {
			return (T) from;
		}
		return JSON.parseObject(JSON.toJSONString(from), destinationClass);
	}
}
