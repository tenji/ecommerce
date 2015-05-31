package com.tenjishen.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Cookie工具类
 * 
 * @author TENJI
 * 
 */
@SuppressWarnings("unused")
public class CookieUtil {

	private static final int period = 60 * 60 * 24 * 7 * 2; // 默认Cookie有效期是两周
	private static final Logger logger = Logger.getLogger(CookieUtil.class);

	/**
	 * 获取Cookie
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param name
	 *            需要获取的cookie名称
	 * @return value 需要获取的cookie值
	 */
	public static String getCookie(HttpServletRequest request, String name) {
		String value = null;
		try {
			for (Cookie c : request.getCookies()) {
				if (c.getName().equals(name)) {
					value = c.getValue();
				}
			}
		} catch (Exception e) {
			logger.error("getCookie Exception: " + e);
		}
		return value;
	}

	/**
	 * 设置Cookie
	 * 
	 * @param response
	 *            HttpServletResponse对象
	 * @param cookieName
	 *            cookie名称
	 * @param cookieValue
	 *            cookie值
	 * @param period
	 *            cookie生命周期
	 * @param path
	 *            cookie路径
	 */
	public static void setCookie(HttpServletResponse response,
			String cookieName, String cookieValue, int period, String path) {
		try {
			Cookie cookie = new Cookie(cookieName, cookieValue);
			cookie.setMaxAge(period); // 以秒为单位，设置Cookie过期时间
			if (null != path)
				cookie.setPath(path); // 指定Cookie适用的路径
			response.addCookie(cookie);

		} catch (Exception e) {
			logger.error("setCookie Exception: " + e);
		}
	}

	/**
	 * 删除Cookie
	 * 
	 * @param response
	 *            HttpServletResponse对象
	 * @param cookieName
	 *            cookie名称
	 * @param path
	 *            cookie路径
	 */
	public static void clearCookie(HttpServletResponse response,
			String cookieName, String path) {
		try {
			Cookie cookie = new Cookie(cookieName, null);
			cookie.setMaxAge(0); // 0表示立即删除；-1表示关闭浏览器删除；
			cookie.setPath(path); // 删除Cookie需指定路径
			response.addCookie(cookie);

		} catch (Exception e) {
			logger.error("clearCookie Exception: " + e);
		}
	}

	/**
	 * 更新Cookie
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param response
	 *            HttpServletResponse对象
	 * @param cookieName
	 *            cookie名称
	 * @param cookieValue
	 *            cookie值
	 * @param period
	 *            cookie周期
	 * @param path
	 *            cookie路径
	 * @return
	 */
	public static boolean updateCookie(HttpServletRequest request,
			HttpServletResponse response, String cookieName,
			String cookieValue, int period, String path) {
		String value = null;
		try {
			for (Cookie c : request.getCookies()) {
				if (c.getName().equals(cookieName)) {
					clearCookie(response, cookieName, path); // delete Cookie first
					value = c.getValue();
					c.setValue(cookieValue);
					c.setMaxAge(period);
					c.setPath(path);
					response.addCookie(c); // update Cookie
				}
			}
		} catch (Exception e) {
			logger.error("getCookie Exception: " + e);
		}

		return value == null ? false : true;
	}

}
