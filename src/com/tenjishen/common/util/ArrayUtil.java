package com.tenjishen.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 数组工具类
 * 
 * @author TENJI
 * @since
 * @date 2014-9-9
 */
public class ArrayUtil {

	private static final Logger logger = Logger.getLogger(ArrayUtil.class);

	/**
	 * 打乱数组元素顺序
	 * 
	 * @date 2014-9-9
	 * @param array 需要打乱顺序的数组
	 * @return
	 */
	public static <T> T[] shuffle(T[] array) {
		logger.info("---------- 开始打乱数组元素顺序 ----------");
		List<T> list = new ArrayList<T>();
		
		// 1. 将顺序排列的数组添加到集合中
		for (int i = 0; i < array.length; i++) {
			list.add(array[i]);
		}
		
		// 2. 使用集合帮助类Collenctions的shuffle()方法
		Collections.shuffle(list);
		
		// 3. 遍历集合，并生成打乱顺序后的数组
		for (int i = 0; i < list.size(); i++) {
			array[i] = list.get(i);
		}
		
		return array;
	}
}
