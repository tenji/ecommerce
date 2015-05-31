package com.tenjishen.controller.admin.common;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tenjishen.common.constants.Constants;
import com.tenjishen.common.util.CookieUtil;
import com.tenjishen.common.util.HttpUtil;
import com.tenjishen.common.util.PropertyUtil;
import com.tenjishen.common.util.encryption.Base64Util;
import com.tenjishen.common.util.encryption.MD5Util;
import com.tenjishen.model.SysUser;
import com.tenjishen.service.common.LoginService;
import com.tenjishen.service.system.SysMenuService;
import com.tenjishen.service.system.SysOperationService;
import com.tenjishen.vo.session.SystemSessionBean;
import com.tenjishen.vo.session.ValidationCodeBean;

/**
 * Controller - 登录
 * 
 * @author tenji
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/admin")
public class LoginController {

	// VIEW
	protected static final String TO_LOGIN_PAGE_VIEW = "/admin/login";
	protected static final String TO_LOGIN_WITH_SIGN_UP_PAGE_VIEW = "/admin/loginWithSignUp";

	// REDIRECT
	protected static final String TO_LOGIN_PAGE_CONTROLLER = "redirect:/admin/login";
	protected static final String TO_LOGIN_WITH_SIGN_UP_PAGE_CONTROLLER = "redirect:/admin/loginOther";
	protected static final String TO_MY_DASHBOARD_CONTROLLER = "redirect:/admin/myDashboard";

	@Resource
	private LoginService loginService;
	@Resource
	private SysMenuService sysMenuService;
	@Resource
	private SysOperationService sysOperationService;
	
	private final Logger logger = Logger.getLogger(LoginController.class);

	// 进入登陆页面
	@RequestMapping("/login")
	public ModelAndView toLoginPage() {
		logger.info("---------- Enter Login Page ----------");
		
		return new ModelAndView(TO_LOGIN_PAGE_VIEW);
	}
	
	// 进入登陆页面（带注册和忘记密码页面）
	@RequestMapping("/loginOther")
	public ModelAndView toLoginWithSignUpPage() {
		
		return new ModelAndView(TO_LOGIN_WITH_SIGN_UP_PAGE_VIEW);
	}

	// 处理用户登陆请求
	@RequestMapping("/loginProcess")
	public ModelAndView loginProcess(@Valid SysUser sysUser,
			BindingResult result, String validateCode, String chkRememberMe,
			HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes attributes) {

		if (result.hasErrors()) { // 提交数据验证失败
			List<ObjectError> validErrors = result.getAllErrors();
			attributes.addFlashAttribute("validErrors", validErrors);
			return new ModelAndView(TO_LOGIN_PAGE_CONTROLLER); // 验证失败，跳回登陆页面
		}
		
		// 用户名密码和验证码都正确
		if (loginService.validate(sysUser)
				&& isValidated(validateCode, request)) {
			// 获取systemSessionBean实体
			SystemSessionBean systemSessionBean = (SystemSessionBean) request
					.getSession().getAttribute(Constants.SYSTEM_SESSION_BEAN);

			sysUser = loginService.getByPropertyName("loginName",
					sysUser.getLoginName()); // 登陆成功，获取登陆用户信息
			sysUser.setLastLoginTime(new Date(System.currentTimeMillis())); // 最后登陆时间
			sysUser.setLastLoginIp(HttpUtil.getIpv4(request)); // 最后登陆IP
			loginService.update(sysUser); // 更新用户最后登陆时间
			
			if ("1".equals(chkRememberMe)) { // 自动登陆
				autoLogin(sysUser, response);
			}
			
			// 已经经过登陆拦截器处理
			if (null != systemSessionBean) {
				String url = systemSessionBean.getUrl();
				systemSessionBean.setUserId(sysUser.getId());
				systemSessionBean.setUserName(sysUser.getLoginName());
				systemSessionBean.setLoginTime(new Date()); // 保存登陆成功时间
				systemSessionBean.setMenusTree(sysMenuService.getMenusTreeByUser(sysUser)); // 设置可访问菜单
				systemSessionBean.setSysOperationList(sysOperationService.getOperationList(sysUser)); // 设置合法操作

				// 直接跳转到请求的URI
				return new ModelAndView("redirect:" + url);
			} else {
				systemSessionBean = new SystemSessionBean();
				systemSessionBean.setUserId(sysUser.getId());
				systemSessionBean.setUserName(sysUser.getLoginName());
				systemSessionBean.setLoginTime(new Date()); // 保存登陆成功时间
				systemSessionBean.setMenusTree(sysMenuService.getMenusTreeByUser(sysUser)); // 设置可访问菜单
				systemSessionBean.setSysOperationList(sysOperationService.getOperationList(sysUser)); // 设置合法操作
				
				// 将systemSessionBean存入Session
				request.getSession().setAttribute(
						Constants.SYSTEM_SESSION_BEAN, systemSessionBean);

				return new ModelAndView(TO_MY_DASHBOARD_CONTROLLER);
			}

		}

		// 登录失败
		return new ModelAndView(TO_LOGIN_PAGE_CONTROLLER);
	}
	
	// 进入管理后台
	@RequestMapping("/myDashboard")
	public ModelAndView toMyDashboard() {
		
		return new ModelAndView("/admin/myDashboard");
	}

	// 处理用户注销请求
	@RequestMapping("/logout")
	public ModelAndView logout(HttpSession httpSession, HttpServletResponse response) {
		CookieUtil.clearCookie(response, "a-login", "/"); // 清除自动登陆Cookie
		httpSession.invalidate(); // 销毁Session

		return new ModelAndView(TO_LOGIN_WITH_SIGN_UP_PAGE_CONTROLLER);
	}

	// 判断用户是否存在，AJAX请求，用于jQuery Validation验证，必须返回true或者false
	@RequestMapping("/isUserExist")
	@ResponseBody
	public String isUserExist(String loginName) {
		// 用户不存在
		if (!loginService.isUserExist(loginName)) {
			return "false";
		}

		return "true";
	}

	// 判断验证码是否正确
	@RequestMapping("/isValidateCodeRight")
	@ResponseBody
	public String isValidateCodeRight(String validateCode,
			HttpServletRequest request) {
		if (!isValidated(validateCode, request)) { // 验证码不正确
			return "false";
		}

		return "true";
	}

	// 验证码判断
	private boolean isValidated(String validateCode, HttpServletRequest request) {
		if (StringUtils.isNotBlank(validateCode)) {
			// 验证码不为空
			ValidationCodeBean validationCodeBean = (ValidationCodeBean) request
					.getSession().getAttribute(Constants.VALIDATION_CODE_BEAN);

			// 匹配验证码(不分大小写)以及检查验证码有效期
			if (null != validationCodeBean
					&& validationCodeBean.isNotExpired()
					&& validateCode.equalsIgnoreCase(validationCodeBean
							.getCode())) {
				return true;
			}
		}

		return false;
	}
	
	// 完成自动登陆
	private void autoLogin(SysUser sysUser, HttpServletResponse response) {
		String webKey = (String) PropertyUtil
				.get("autoLogin.webkey"); 
		Long expireTime = Long.parseLong((String) PropertyUtil.get("autoLogin.expireTime")); // Cookie有效时间
		
		String plainText = MD5Util.getMD5(sysUser.getLoginName() + ":"
				+ sysUser.getLoginPassword() + ":"
				+ String.valueOf(expireTime) + ":" + webKey); // MD5明文字符串
		
		String Base64 = Base64Util.getBase64(sysUser.getLoginName()
				+ ":" + String.valueOf(expireTime) + ":" + plainText);
		
		CookieUtil.setCookie(response, "a-login", Base64,
				expireTime.intValue(), "/"); // 设置Cookie
	}

}
