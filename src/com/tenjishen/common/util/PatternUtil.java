package com.tenjishen.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 * 
 * @author TENJI
 *
 */
public class PatternUtil {
	
	// 是否合法手机号
	public static boolean isMobileNo(String mobileNo) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobileNo);
		
		return m.matches();
	}

}
