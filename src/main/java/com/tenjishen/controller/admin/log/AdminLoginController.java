package com.tenjishen.controller.admin.log;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tenjishen.common.constants.Constants;
import com.tenjishen.model.log.LogAdminLogin;
import com.tenjishen.service.log.AdminLoginService;
import com.tenjishen.vo.json.DefaultJsonBean;

import com.tenjishen.vo.PageBean;

/**
 * Controller - Admin Login Log
 *
 */
@Controller
@RequestMapping("/admin/log/adminLogin")
public class AdminLoginController {
	
	// VIEW
	protected static final String LIST_VIEW = "/admin/log/adminLogin/list";
	protected static final String READ_ENTRANCE_VIEW = "/admin/log/adminLogin/readEntrance";
	protected static final String UPDATE_ENTRANCE_VIEW = "/admin/log/adminLogin/updateEntrance";
	protected static final String DELETE_ENTRANCE_VIEW = "/admin/log/adminLogin/deleteEntrance";
	protected static final String ADD_ENTRANCE_VIEW = "/admin/log/adminLogin/addEntrance";

	@Resource
	private AdminLoginService adminLoginService;
	
	// 查看列表
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			LogAdminLogin adminLogin, Model model, HttpServletRequest request) {
		PageBean pageBean = new PageBean();
		if (null != page && 0 != page) {
			pageBean.setCurrentPage(page);
		}
		if (null != pageSize && 0 != pageSize) {
			pageBean.setPageSize(pageSize);
		}
		pageBean.generateSearchUrl(request); // 生成分页URL参数
		
		pageBean = adminLoginService.findByPageBean(pageBean);
		model.addAttribute("pageBean", pageBean);
		
		return new ModelAndView(LIST_VIEW);
	}
	
	// 查看页面入口，Ajax请求
	@RequestMapping("/readEntrance")
	public ModelAndView readEntrance(Long id, Model model) {
		LogAdminLogin adminLogin = adminLoginService.getById(id);
		model.addAttribute("model", adminLogin);
		
		return new ModelAndView(READ_ENTRANCE_VIEW);
	}
	
	// 查看，Ajax请求
	public void read() {
	}
	
	// 删除页面入口，Ajax请求
	@RequestMapping("/deleteEntrance")
	public ModelAndView deleteEntrance() {
		
		return new ModelAndView(DELETE_ENTRANCE_VIEW);
	}
	
	// 删除，Ajax请求
	@RequestMapping("/delete")
	@ResponseBody
	public DefaultJsonBean delete(Long id) {
		LogAdminLogin adminLogin = adminLoginService.getById(id);
		DefaultJsonBean defaultJsonBean = null;
		
		// 存在需要删除的菜单
		if (null != adminLogin) {
			adminLoginService.deleteById(adminLogin.getId());
			// 返回正确提示
			defaultJsonBean = new DefaultJsonBean(Constants.JSON_SUCCESS, Constants.JSON_SUCCESS_VALUE);
				
		} else {
			// 返回未知错误提示
			defaultJsonBean = new DefaultJsonBean(Constants.JSON_UNDEFINED_ERROR, Constants.JSON_UNDEFINED_ERROR_VALUE);
		}
		
		return defaultJsonBean;
	}
	
	// 更新页面入口，Ajax请求
	@RequestMapping("/updateEntrance")
	public ModelAndView updateEntrance(Long id, Model model) {
		LogAdminLogin adminLogin = adminLoginService.getById(id);
		model.addAttribute("model", adminLogin);
		
		return new ModelAndView(UPDATE_ENTRANCE_VIEW);
	}
	
	// 更新，Ajax请求
	@RequestMapping("/update")
	public LogAdminLogin update(Long id) {
		LogAdminLogin oldAdminLogin = adminLoginService.getById(id);
		
		adminLoginService.update(oldAdminLogin); // Update Ram Account
		
		return oldAdminLogin;
	}

}
