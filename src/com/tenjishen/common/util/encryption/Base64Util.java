package com.tenjishen.common.util.encryption;

import org.apache.commons.codec.binary.Base64;

/**
 * BASE64工具类
 * 
*　One　of　the　<b>fastest</b>　Base64　encoder/decoder　implementations.　Base64
*　encoding　is　defined　in　RFC　2045.
*/
public class Base64Util {
	
	/**
	 * BASE64编码
	 * 
	 * @param str
	 * @return
	 */
	public static String getBase64(String str) {
		if (null == str) return null;
		
		return new String((new Base64()).encode(str.getBytes()));
	}
	
	/**
	 * BASE64解码
	 * 
	 * @param str
	 * @return
	 */
	public static String getFromBase64(String str) {
		if (null == str) return null;
		
		return new String((new Base64()).decode(str.getBytes()));
	}
	
}
