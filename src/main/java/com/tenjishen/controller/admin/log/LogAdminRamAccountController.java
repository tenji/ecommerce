package com.tenjishen.controller.admin.log;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tenjishen.common.constants.Constants;
import com.tenjishen.model.log.LogAdminRamAccount;
import com.tenjishen.service.log.LogAdminRamAccountService;
import com.tenjishen.vo.PageBean;
import com.tenjishen.vo.json.DefaultJsonBean;

/**
 * Controller - Admin Login Log
 *
 */
@Controller
@RequestMapping("/admin/log/logAdminRamAccount")
public class LogAdminRamAccountController {
	
	// VIEW
	protected static final String LIST_VIEW = "/admin/log/logAdminRamAccount/list";
	protected static final String READ_ENTRANCE_VIEW = "/admin/log/logAdminRamAccount/readEntrance";
	protected static final String UPDATE_ENTRANCE_VIEW = "/admin/log/logAdminRamAccount/updateEntrance";
	protected static final String DELETE_ENTRANCE_VIEW = "/admin/log/logAdminRamAccount/deleteEntrance";
	protected static final String ADD_ENTRANCE_VIEW = "/admin/log/logAdminRamAccount/addEntrance";

	@Resource
	private LogAdminRamAccountService logAdminRamAccountService;
	
	// 查看列表
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			LogAdminRamAccount adminRamAccount, Model model, HttpServletRequest request) {
		PageBean pageBean = new PageBean();
		if (null != page && 0 != page) {
			pageBean.setCurrentPage(page);
		}
		if (null != pageSize && 0 != pageSize) {
			pageBean.setPageSize(pageSize);
		}
		pageBean.generateSearchUrl(request); // 生成分页URL参数
		
		pageBean = logAdminRamAccountService.findByPageBean(pageBean);
		model.addAttribute("pageBean", pageBean);
		
		return new ModelAndView(LIST_VIEW);
	}
	
	// 查看页面入口，Ajax请求
	@RequestMapping("/readEntrance")
	public ModelAndView readEntrance(Long id, Model model) {
		LogAdminRamAccount adminRamAccount = logAdminRamAccountService.getById(id);
		model.addAttribute("model", adminRamAccount);
		
		return new ModelAndView(READ_ENTRANCE_VIEW);
	}
	
	// 查看，Ajax请求
	public void read() {
	}
	
	// 删除页面入口，Ajax请求
	public String deleteEntrance() {
		
		return "deleteEntrance";
	}
	
	// 删除，Ajax请求
	@RequestMapping("/delete")
	@ResponseBody
	public DefaultJsonBean delete(Long id) {
		LogAdminRamAccount adminRamAccount = logAdminRamAccountService.getById(id);
		DefaultJsonBean defaultJsonBean = null;
		
		// 存在需要删除的菜单
		if (null != adminRamAccount) {
			logAdminRamAccountService.deleteById(adminRamAccount.getId());
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
		LogAdminRamAccount adminRamAccount = logAdminRamAccountService.getById(id);
		model.addAttribute("model", adminRamAccount);
		
		return new ModelAndView(UPDATE_ENTRANCE_VIEW);
	}
	
	// 更新，Ajax请求
	@RequestMapping("/update")
	public LogAdminRamAccount update(Long id) {
		LogAdminRamAccount adminRamAccount = logAdminRamAccountService.getById(id);
		
		logAdminRamAccountService.update(adminRamAccount); // Update Ram Account
		
		return adminRamAccount;
	}
	
	/*
	 * 从积分变更记录中获取最近n周的积分，如果当天没有积分记录，则默认与下一天积分相同
	 * 用于分析最近两周的积分变化趋势，Ajax请求
	 */
	@RequestMapping("/getPointList")
	@ResponseBody
	public List<Integer> getPointList(Long ramAccountId) {
		List<Integer> pointList = logAdminRamAccountService.getPointList(ramAccountId, 4);
		
		return pointList;
	}
}
