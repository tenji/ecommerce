package com.tenjishen.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tenjishen.common.constants.Constants;
import com.tenjishen.common.util.HttpUtil;
import com.tenjishen.common.util.JsonUtil;
import com.tenjishen.vo.json.DefaultJsonBean;
import com.tenjishen.vo.session.SystemSessionBean;

/**
 * Interceptor - 登录验证拦截器
 * 
 * @author TENJI
 * @version 1.0
 * 
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	// 该方法将在整个请求完成之后执行，主要作用是清理资源
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	// 在Controller处理之前调用
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		// 获取systemSessionBean实体
		SystemSessionBean systemSessionBean = (SystemSessionBean) request
				.getSession().getAttribute(Constants.SYSTEM_SESSION_BEAN);

		// systemSessionBean实体不存在
		if (null == systemSessionBean) {
			systemSessionBean = new SystemSessionBean();
			// 获取用户请求URL
			StringBuffer url = request.getRequestURL();
			systemSessionBean.setUrl(url.toString());

			// 将URI存入systemSessionBean实体(有可能为空)
			request.getSession().setAttribute(Constants.SYSTEM_SESSION_BEAN,
					systemSessionBean);

			// 如果是Ajax请求
			if (HttpUtil.isAjaxRequest(request)) {
				DefaultJsonBean defaultJsonBean = new DefaultJsonBean(
						Constants.JSON_NOT_LOGIN,
						Constants.JSON_NOT_LOGIN_VALUE);
				HttpUtil.responseJson(JsonUtil.objToJson(defaultJsonBean), response);

				return false;
			} else {
				
				response.sendRedirect(request.getContextPath() + "/admin/login"); // 非AJAX请求直接跳转到登录页面
				return false;
			}
		} else {
			// 用户未登录
			if (null == systemSessionBean.getUserName()) {
				// 如果是Ajax请求
				if (HttpUtil.isAjaxRequest(request)) {
					DefaultJsonBean defaultJsonBean = new DefaultJsonBean(
							Constants.JSON_NOT_LOGIN,
							Constants.JSON_NOT_LOGIN_VALUE);
					HttpUtil.responseJson(JsonUtil.objToJson(defaultJsonBean), response);

					return false;
				} else {
					
					response.sendRedirect(request.getContextPath() + "/admin/login"); // 非AJAX请求直接跳转到登录页面
					return false;
				}
			}
		}

		return true; // 退出此拦截器，继续执行下一个Interceptor或者Controller
	}
}
