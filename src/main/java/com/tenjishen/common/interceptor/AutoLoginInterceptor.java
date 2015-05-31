package com.tenjishen.common.interceptor;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tenjishen.common.constants.Constants;
import com.tenjishen.common.util.CookieUtil;
import com.tenjishen.common.util.PropertyUtil;
import com.tenjishen.common.util.encryption.Base64Util;
import com.tenjishen.common.util.encryption.MD5Util;
import com.tenjishen.model.SysUser;
import com.tenjishen.service.common.LoginService;
import com.tenjishen.service.system.SysMenuService;
import com.tenjishen.service.system.SysOperationService;
import com.tenjishen.vo.session.SystemSessionBean;

/**
 * Interceptor - 自动登录拦截器
 * 
 * @author TENJI
 * @version 1.0
 * 
 */
public class AutoLoginInterceptor implements HandlerInterceptor {
	
	/*
	 * 保存用户信息阶段
	 * 在保存用户信息阶段，主要的工作是对用户的信息进行加密并保存到客户端。
	 * 1. 得到用户名、经MD5加密后的用户密码、cookie有效时间(保存在配置文件中)
	 * 2. 自定义的一个webKey，这个Key是我们为自己的网站定义的一个字符串常量，这个可根据自己需要随意设置
	 * 3. 将上两步得到的四个值使用":"连接成一个新的字符串，再进行MD5加密，这样就得到了一个MD5明文字符串
	 * 4. 将用户名、cookie有效时间、MD5明文字符串再使用":"间隔连接起来，再对这个连接后的新字符串进行Base64编码
	 * 5. 设置一个cookieName,将cookieName和上一步产生的Base64编码写入到客户端。
	 * 
	 * 
	 * 读取用户信息阶段
	 * 1. 根据设置的cookieName，得到cookieValue，如果值为空，就不帮用户进行自动登陆；否则执行读取方法
	 * 2. 将cookieValue进行Base64解码，将取得的字符串以split(":")进行拆分，得到一个String数组cookieValues（此操作与保存阶段的第4步正好相反），这一步将得到三个值：
	 * 		cookieValues[0] ---- 用户名
	 * 		cookieValues[1] ---- cookie有效时间
	 * 		cookieValues[2] ---- MD5明文字符串
	 * 3. 判断cookieValues的长度是否为3，如果不为3则进行错误处理。
	 * 4. 如果长度等于3，取出第二个,即cookieValues[1]，此时将会得到有效时间（long型），将有效时间与服务器系统当前时间比较，如果小于当前时间，则说明cookie过期，进行错误处理。
	 * 5. 如果cookie没有过期，就取cookieValues[0]，这样就可以得到用户名了，然后去数据库按用户名查找用户。
	 * 6. 如果上一步返回为空，进行错误处理。如果不为空，那么将会得到一个已经封装好用户信息的User实例对象user
	 * 7. 取出实例对象user的用户名、密码、cookie有效时间（即cookieValues[1]）、webKey，然后将四个值用":"连接起来，然后进行MD5加密，这样做也会得到一个MD5明文字符串（此操作与保存阶段的第3步类似）
	 * 8. 将上一步得到MD5明文与cookieValues[2]进行equals比较，如果是false，进行错误处理；如果是true，则将user对象添加到session中，帮助用户完成自动登陆
	 */
	@Resource
	private LoginService loginService;
	@Resource
	private SysMenuService sysMenuService;
	@Resource
	private SysOperationService sysOperationService;
	
	private final Logger logger = Logger.getLogger(AutoLoginInterceptor.class);

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

		SystemSessionBean systemSessionBean = (SystemSessionBean) request
				.getSession().getAttribute(
						Constants.SYSTEM_SESSION_BEAN);
		if (null != systemSessionBean && null != systemSessionBean.getUserName()) {
			return true; // 已登陆
		}
		// 获取Cookie
		String cookieValue = CookieUtil.getCookie(request, "a-login");
		if (null == cookieValue) { // Cookie为空
			logger.info("Cookie is Null, skip auto login operation!" + "--------------------");
			return true;
		}
		
		String cookieValueAfterDecode = Base64Util.getFromBase64(cookieValue);
		String[] cookieValues = cookieValueAfterDecode.split(":");
		if (3 != cookieValues.length) {
			logger.info("You are using a non-normal way to enter this site..." + "--------------------");
		}
		
		// 判断Cookie是否在有效期内，过期就删除
		long expireTimeInCookie = new Long(cookieValues[1]);
		if (expireTimeInCookie < System.currentTimeMillis()) {
			// 删除Cookie
			// CookieUtil.clearCookie(response, "a-login", "/");
			logger.info("Your Cookie has expired, please login again" + "-------------------");
		}
		
		// 去除Cookie中的用户名，并到数据库中检查这个用户名
		String loginName = cookieValues[0];
		
		// 检测用户是否存在
		SysUser sysUser = loginService.getByPropertyName("loginName", loginName);
		if (null != sysUser) {
			String md5ValueInCookie = cookieValues[2];
			String md5ValueFromUser = MD5Util.getMD5(sysUser.getLoginName()
					+ ":" + sysUser.getLoginPassword() + ":"
					+ String.valueOf(expireTimeInCookie) + ":"
					+ (String) PropertyUtil.get("autoLogin.webkey"));
			
			// 将结果与Cookie中的MD5码比较，如果相同，写入Session，自动登陆成功
			if (md5ValueFromUser.equals(md5ValueInCookie)) {
				/*
				 * 自动登陆
				 */
				if (null == systemSessionBean)
					systemSessionBean = new SystemSessionBean();
				systemSessionBean.setUserId(sysUser.getId());
				systemSessionBean.setUserName(sysUser.getLoginName());
				systemSessionBean.setLoginTime(new Date()); // 保存登陆成功时间
				systemSessionBean.setMenusTree(sysMenuService
						.getMenusTreeByUser(sysUser)); // 设置可访问菜单
				systemSessionBean.setSysOperationList(sysOperationService
						.getOperationList(sysUser)); // 设置合法操作
				// 将systemSessionBean存入Session
				request.getSession().setAttribute(
						Constants.SYSTEM_SESSION_BEAN, systemSessionBean);
				logger.info("Auto-Login Successfully" + "--------------------------");
				
				// Update Cookie Peroid
				Long expireTime = Long.parseLong((String) PropertyUtil.get("autoLogin.expireTime")); // Cookie有效时间
				CookieUtil.updateCookie(request, response, "a-login", cookieValue, expireTime.intValue(), "/");
			}
		} else {
			logger.info("Cookie Validation Error" + "----------------------------------");
		}

		return true; // 退出此拦截器，继续执行下一个Interceptor或者Controller
	}
}
