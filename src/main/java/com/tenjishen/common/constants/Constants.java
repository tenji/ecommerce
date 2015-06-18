package com.tenjishen.common.constants;

import com.tenjishen.common.util.PropertyUtil;

/**
 * 常量类 - 保存项目中需要用到的常量
 * 
 * @author TENJI
 *
 */
public class Constants {

	/*----------------------- request或session中的参数名 ---------------------*/
	// 后台登录时写入session的变量名
	public static final String SYSTEM_SESSION_BEAN = "systemSessionBean";
	// 前台登陆时下入session的变量名
	public static final String MEMBER_SESSION_BEAN = "memberSessionBean";
	// 生成验证码时写入session的变量名
	public static final String VALIDATION_CODE_BEAN = "validationCodeBean";
	/*--------------------------------------------------------------------*/
	
	
	
	/*------------------------ AJAX操作返回提示信息常量 ------------------------*/
	public static final String JSON_SUCCESS = "success";
	public static final String JSON_SUCCESS_VALUE = "操作成功";
	
	public static final String JSON_NOT_LOGIN = "notLogin";
	public static final String JSON_NOT_LOGIN_VALUE = "您尚未未登录！";
	
	public static final String JSON_NO_PERMISSION = "noPermission";
	public static final String JSON_NO_PERMISSION_VALUE = "权限不足！";
	
	public static final String JSON_UNDEFINED_ERROR = "undefinedError";
	public static final String JSON_UNDEFINED_ERROR_VALUE = "未定义错误";
	
	
	
	/*--------------------------- 文件路径相关常量 ----------------------------*/
	// 静态产品页存放目录
	public static final String STATIC_PAGE_DIR_PRODUCT = "\\static\\product\\";
	
	// 产品图片存放目录
	public static final String IMAGE_DIR_PRODUCT = "\\images\\products\\";
	
	
	/*----------------------- Ram Account Related ------------------------*/
	// Ram Site Login Url
	public static final String RAM_SITE_LOGIN_URL = "http://www2.rampanel.com/en/login";
	// Times Between Each Operation
	public static final String TIMES_BETWEEN_OPERATION = (String) PropertyUtil.get("TIMES_BETWEEN_OPERATION");
	
}