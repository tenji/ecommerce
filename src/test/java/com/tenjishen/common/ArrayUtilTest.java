package com.tenjishen.common;

import org.junit.Test;

import com.tenjishen.common.util.ArrayUtil;

/**
 * ArrayUtil测试
 * 
 * @author TENJI
 * 
 */
public class ArrayUtilTest {
	
	@Test
	public void shuffleTest() {
		Integer[] array = {1, 2, 4, 4, 5};
		array = ArrayUtil.shuffle(array);
		
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}

}
