package com.tenjishen.controller.admin.account;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tenjishen.common.constants.Constants;
import com.tenjishen.model.SysUser;
import com.tenjishen.service.system.SysUserService;
import com.tenjishen.vo.session.SystemSessionBean;


/**
 * Controller - 个人中心
 * 
 * @author TENJI
 *
 */
@Controller
@RequestMapping("/admin/account/profile")
public class ProfileController {
	
	// VIEW
	protected static final String VIEW_VIEW = "/admin/account/profile/view";
	protected static final String AVATAR_VIEW = "/admin/account/profile/avatar";
	protected static final String SETTING_VIEW = "/admin/account/profile/setting";
	protected static final String RESET_PASSWORD_VIEW = "/admin/account/profile/resetPassword";
	
	@Resource
	private SysUserService sysUserService;
	
	// 查看个人资料
	@RequestMapping("/view")
	public ModelAndView view(Model model, HttpSession session) {
		// 获取systemSessionBean实体
		SystemSessionBean systemSessionBean = (SystemSessionBean) session
				.getAttribute(Constants.SYSTEM_SESSION_BEAN);
		SysUser sysUser = sysUserService.getById(systemSessionBean.getUserId());
		model.addAttribute("model", sysUser);
		
		return new ModelAndView(VIEW_VIEW);
	}
	
	// 更新个人资料
	@RequestMapping("/update")
	public void update(SysUser sysUser, HttpSession session) {
		// 获取systemSessionBean实体
		SystemSessionBean systemSessionBean = (SystemSessionBean) session
				.getAttribute(Constants.SYSTEM_SESSION_BEAN);
		SysUser oldSysUser = sysUserService.getById(systemSessionBean.getUserId());
		oldSysUser.setLoginPassword(sysUser.getLoginPassword());
		
		sysUserService.update(oldSysUser); // 更新用户资料
	}
	
	// 头像设置
	@RequestMapping("/avatar")
	public ModelAndView avatar(Model model, HttpSession session) {
		
		return new ModelAndView(AVATAR_VIEW);
	}
	
	// 账户设置
	@RequestMapping("/setting")
	public ModelAndView setting(Model model, HttpSession session) {
		
		return new ModelAndView(AVATAR_VIEW);
	}
	
	// 重置密码
	@RequestMapping("/resetPwd")
	public ModelAndView resetPwd(Model model, HttpSession session) {
		
		return new ModelAndView(RESET_PASSWORD_VIEW);
	}
	
}
