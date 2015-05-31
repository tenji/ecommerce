package com.tenjishen.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * property读取工具类
 * 
 * @author TENJI
 * 
 */
public class PropertyUtil {

	/**
	 * 通过属性名和属性文件名读取
	 * 
	 * @param propName
	 *            属性名
	 * @param propFileName
	 *            属性文件名
	 * @return
	 */
	public static Object get(String propName, String propFileName) {
		Object obj = null;
		InputStream inputStream = null;
		try {
			inputStream = PropertyUtil.class.getClassLoader()
					.getResourceAsStream(propFileName);
			Properties props = new Properties();
			props.load(inputStream);
			obj = props.getProperty(propName);
		} catch (IOException e) {
		} finally {
			if (null != inputStream) {
				try {
					inputStream.close();
				} catch (IOException e) {
				}
			}
		}
		return obj;
	}

	/**
	 * 重载，通过属性名读取(默认属性文件名为application.properties)
	 * 
	 * @param propName
	 *            属性名
	 * @return
	 */
	public static Object get(String propName) {
		return get(propName, "application.properties");
	}
}
