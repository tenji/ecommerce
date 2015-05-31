package com.tenjishen.controller.admin.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller - 错误处理
 * 
 * @author tenji
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/admin")
public class ErrorController {

	// VIEW
	protected static final String NO_AUTH_VIEW = "/admin/noPermission";

	// REDIRECT

	// 进入登陆页面
	@RequestMapping("/noAuth")
	public ModelAndView toLoginPage() {
		
		return new ModelAndView(NO_AUTH_VIEW);
	}

}
