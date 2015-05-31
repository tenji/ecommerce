package com.tenjishen.vo.json;

import com.tenjishen.common.constants.Constants;

/**
 * 默认JSON格式Bean
 * 
 * @author TENJI
 *
 */
public class DefaultJsonBean {
	private String resultCode;		// 返回标识符
	private String value;	// 值
	
	// 默认构造函数
	public DefaultJsonBean() {
		this.resultCode = Constants.JSON_SUCCESS;
		this.value = Constants.JSON_SUCCESS_VALUE;
	}
	
	// 构造函数
	public DefaultJsonBean(String resultCode, String value) {
		this.resultCode = resultCode;
		this.value = value;
	}
	
	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

}
