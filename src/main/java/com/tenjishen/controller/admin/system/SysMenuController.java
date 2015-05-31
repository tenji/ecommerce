package com.tenjishen.controller.admin.system;

import java.util.ArrayList;
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
import com.tenjishen.model.SysMenu;
import com.tenjishen.service.system.SysMenuService;
import com.tenjishen.service.system.SysRoleService;
import com.tenjishen.vo.json.DefaultJsonBean;
import com.tenjishen.vo.json.ZTreeJsonBean;

import com.tenjishen.vo.PageBean;

/**
 * Controller - 菜单管理
 * 
 */
@Controller
@RequestMapping("/admin/system/sysMenu")
public class SysMenuController {
	
	// VIEW
	protected static final String LIST_VIEW = "/admin/system/sysMenu/list";
	protected static final String READ_ENTRANCE_VIEW = "/admin/system/sysMenu/readEntrance";
	protected static final String UPDATE_ENTRANCE_VIEW = "/admin/system/sysMenu/updateEntrance";
	protected static final String DELETE_ENTRANCE_VIEW = "/admin/system/sysMenu/deleteEntrance";
	protected static final String ADD_ENTRANCE_VIEW = "/admin/system/sysMenu/addEntrance";

	@Resource
	private SysMenuService sysMenuService;
	@Resource
	private SysRoleService sysRoleService;
	
	// 查看列表，Ajax请求
	@RequestMapping("/list")
	public ModelAndView list(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			Model model, HttpServletRequest request) {
		PageBean pageBean = new PageBean();
		if (null != page && 0 != page) {
			pageBean.setCurrentPage(page);
		}
		if (null != pageSize && 0 != pageSize) {
			pageBean.setPageSize(pageSize);
		}
		pageBean.generateSearchUrl(request); // 生成分页URL参数
		
		pageBean = sysMenuService.findByPageBean(pageBean);
		model.addAttribute("pageBean", pageBean);
		
		return new ModelAndView(LIST_VIEW);
	}
	
	// 查看页面入口，Ajax请求
	@RequestMapping("/readEntrance")
	public ModelAndView readEntrance(Long id, Model model) {
		SysMenu sysMenu = sysMenuService.getById(id);
		model.addAttribute("model", sysMenu);
		
		return new ModelAndView(READ_ENTRANCE_VIEW);
	}
	
	// 查看，Ajax请求
	@RequestMapping("/read")
	public void read() {
	}
	
	// 添加页面入口，Ajax请求
	@RequestMapping("/addEntrance")
	public ModelAndView addEntrance(Model model) {
		List<SysMenu> list = sysMenuService.getRootMenuList();
		model.addAttribute("list", list);
		
		return new ModelAndView(ADD_ENTRANCE_VIEW);
	}
	
	// 添加，Ajax请求
	@RequestMapping("/add")
	public SysMenu add(SysMenu sysMenu, Long parentId) {
		sysMenu = sysMenuService.saveNewMenu(sysMenu, parentId);
		
		return sysMenu;
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
		SysMenu sysMenu = sysMenuService.getById(id);
		DefaultJsonBean defaultJsonBean = null;
		
		// 存在需要删除的菜单
		if (null != sysMenu) {
			sysMenuService.deleteById(sysMenu.getId());
			// 返回正确提示
			defaultJsonBean = new DefaultJsonBean(Constants.JSON_SUCCESS, Constants.JSON_SUCCESS_VALUE);
				
		} else {
			// 返回未知错误提示
			defaultJsonBean = new DefaultJsonBean(Constants.JSON_UNDEFINED_ERROR, Constants.JSON_UNDEFINED_ERROR_VALUE);
		}
		
		return defaultJsonBean;
	}
	
	// 生成菜单zTree树，Ajax请求
	@RequestMapping("/getAssignedMenusTree")
	@ResponseBody
	public List<ZTreeJsonBean> getAssignedMenusTree(
			@RequestParam(value = "roleId", required = false) Long roleId) {
		List<Long> assignedMenuIds = sysMenuService.getAssignedMenuIds(sysRoleService.getById(roleId));
		List<SysMenu> sysMenuList = sysMenuService.getAll();
		List<ZTreeJsonBean> zTreeJsonBeans = new ArrayList<ZTreeJsonBean>();
		
		for (SysMenu sysMenu : sysMenuList) {
			ZTreeJsonBean zTreeJsonBean = new ZTreeJsonBean();
			// 菜单是否被选中
			if (assignedMenuIds.contains(sysMenu.getId())) {
				zTreeJsonBean.setChecked(true);
			} else {
				zTreeJsonBean.setChecked(false);
			}
			// 菜单是否顶级菜单
			if (0 == sysMenu.getLevel()) {
				zTreeJsonBean.setpId((long)0);
			} else {
				zTreeJsonBean.setpId(sysMenu.getParent().getId());
			}
			zTreeJsonBean.setId(sysMenu.getId());
			zTreeJsonBean.setName(sysMenu.getMenuDescription());
			
			zTreeJsonBeans.add(zTreeJsonBean);
		}
		
		return zTreeJsonBeans;
	}
}
