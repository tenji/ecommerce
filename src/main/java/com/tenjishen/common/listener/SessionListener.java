package com.tenjishen.common.listener;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tenjishen.common.constants.Constants;
import com.tenjishen.model.SysUser;
import com.tenjishen.model.log.LogAdminLogin;
import com.tenjishen.service.log.AdminLoginService;
import com.tenjishen.vo.session.SystemSessionBean;

public class SessionListener implements HttpSessionListener {

	// Session创建事件
	@Override
	public void sessionCreated(HttpSessionEvent se) {

	}

	// Session销毁事件
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		if (null != session) {
			// 获取session
			SystemSessionBean systemSessionBean = (SystemSessionBean) se
					.getSession().getAttribute(Constants.SYSTEM_SESSION_BEAN);

			// 记录登陆日志
			LogAdminLogin adminLogin = new LogAdminLogin();
			if (null != systemSessionBean
					&& null != systemSessionBean.getLoginTime()) {
				adminLogin.setLoginTime(systemSessionBean.getLoginTime()); // 登陆时间
				adminLogin.setLogoutTime(new Date()); // 退出时间
				adminLogin.setSysUser(new SysUser(systemSessionBean.getUserId()));
			}

			// 通过WebApplicationContextUtils得到Spring容器的实例
			ApplicationContext application = WebApplicationContextUtils
					.getWebApplicationContext(session.getServletContext());
			AdminLoginService adminLoginService = application
					.getBean(AdminLoginService.class);

			adminLoginService.save(adminLogin); // 保存日志
		}

	}

}
