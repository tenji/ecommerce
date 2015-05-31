package com.tenjishen.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tenjishen.common.constants.Constants;
import com.tenjishen.model.SysOperation;
import com.tenjishen.vo.MenusTreeBean;
import com.tenjishen.vo.json.DefaultJsonBean;
import com.tenjishen.vo.session.SystemSessionBean;

import com.tenjishen.common.util.HttpUtil;
import com.tenjishen.common.util.JsonUtil;

/**
 * Interceptor - 权限拦截器，过滤用户所有越权操作
 * 
 * @author tenji
 * 
 */
public class AuthorizationInterceptor implements HandlerInterceptor {

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
		String uri = request.getRequestURI().replaceFirst(
				request.getContextPath(), ""); // 获取用户请求URI

		// 遍历菜单权限
		for (MenusTreeBean topMenu : systemSessionBean.getMenusTree()) {
			if (topMenu.getUrl().equals(uri)) {
				
				return true; // 存在菜单权限

			} else {
				for (MenusTreeBean subMenu : topMenu.getSubMenus()) {
					if (subMenu.getUrl().equals(uri)) {
						return true;
					}
				}
			}

		}
		// 遍历操作权限
		for (SysOperation sysOperation : systemSessionBean
				.getSysOperationList()) {
			if (sysOperation.getUrl().equals(uri)) {
				
				return true; // 存在操作权限
			}
		}

		// 如果是Ajax请求
		if (HttpUtil.isAjaxRequest(request)) {
			DefaultJsonBean defaultJsonBean = new DefaultJsonBean(
					Constants.JSON_NO_PERMISSION,
					Constants.JSON_NO_PERMISSION_VALUE);
			
			HttpUtil.responseJson(JsonUtil.objToJson(defaultJsonBean), response);
			return false;
		}

		// 越权操作，跳转到无权限访问页
		response.sendRedirect(request.getContextPath() + "/admin/noAuth");
		return false;
	}

}
