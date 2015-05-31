package com.tenjishen.controller.admin.system;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tenjishen.common.constants.Constants;
import com.tenjishen.common.util.JsonUtil;
import com.tenjishen.common.util.StringUtil;
import com.tenjishen.model.SysMenu;
import com.tenjishen.model.SysOperation;
import com.tenjishen.model.SysRole;
import com.tenjishen.model.SysUser;
import com.tenjishen.service.system.SysMenuService;
import com.tenjishen.service.system.SysOperationService;
import com.tenjishen.service.system.SysRoleService;
import com.tenjishen.service.system.SysUserService;
import com.tenjishen.vo.json.DefaultJsonBean;

import com.tenjishen.vo.PageBean;

/**
 * Controller - 角色管理
 * 
 */
@Controller
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {
	
	// VIEW
	protected static final String LIST_VIEW = "/admin/system/sysRole/list";
	protected static final String READ_ENTRANCE_VIEW = "/admin/system/sysRole/readEntrance";
	protected static final String UPDATE_ENTRANCE_VIEW = "/admin/system/sysRole/updateEntrance";
	protected static final String DELETE_ENTRANCE_VIEW = "/admin/system/sysRole/deleteEntrance";
	protected static final String ADD_ENTRANCE_VIEW = "/admin/system/sysRole/addEntrance";
	protected static final String ASSIGN_RIGHTS_ENTRANCE_VIEW = "/admin/system/sysRole/assignRightsEntrance";
	
	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private SysUserService sysUserService;
	@Resource
	private SysMenuService sysMenuService;
	@Resource
	private SysOperationService sysOperationService;
	
	// 查看权限列表
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
		
		pageBean = sysRoleService.findByPageBean(pageBean);
		model.addAttribute("pageBean", pageBean);
		
		return new ModelAndView(LIST_VIEW);
	}
	
	// 查看页面入口，Ajax请求
	@RequestMapping("/readEntrance")
	public ModelAndView readEntrance(Long id, Model model) {
		SysRole sysRole = sysRoleService.getById(id);
		List<SysUser> sysUserList = sysUserService.getSysUserList(sysRole);
		List<SysMenu> sysMenuList = sysMenuService.getMenuList(sysRole);
		List<SysOperation> sysOperationList = sysOperationService.getOperationList(sysRole);
		
		model.addAttribute("sysRole", sysRole);
		model.addAttribute("sysUserList", sysUserList);
		model.addAttribute("sysMenuList", sysMenuList);
		model.addAttribute("sysOperationList", sysOperationList);
		
		return new ModelAndView(READ_ENTRANCE_VIEW);
	}
	
	// 查看，Ajax请求
	public void read() {
	}
	
	// 添加页面入口，Ajax请求
	@RequestMapping("/addEntrance")
	public ModelAndView addEntrance() {
		
		return new ModelAndView(ADD_ENTRANCE_VIEW);
	}
	
	// 添加，Ajax请求
	@RequestMapping("/add")
	@ResponseBody
	public SysRole add(SysRole sysRole) {
		sysRole.setDeletable(1);	// 默认权限可删除
		sysRole.setCreateTime(new Date());
		
		sysRoleService.save(sysRole);
		return sysRole;
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
		SysRole sysRole = sysRoleService.getById(id);
		// 新建默认JSON格式实体，默认返回错误提示
		DefaultJsonBean defaultJsonBean = null;
		// 存在需要删除的权限
		if (null != sysRole) {
			// 权限可删除
			if (0 != sysRole.getDeletable()) {
				sysRoleService.deleteById(sysRole.getId());
				// 返回正确提示
				defaultJsonBean = new DefaultJsonBean(Constants.JSON_SUCCESS, Constants.JSON_SUCCESS_VALUE);
				
			} else {
				// 返回未知错误提示
				defaultJsonBean = new DefaultJsonBean(Constants.JSON_UNDEFINED_ERROR, Constants.JSON_UNDEFINED_ERROR_VALUE);
			}
		}
		
		return defaultJsonBean;
	}
	
	// 修改页面入口，Ajax请求
	@RequestMapping("/updateEntrance")
	public ModelAndView updateEntrance() {
		
		return new ModelAndView(UPDATE_ENTRANCE_VIEW);
	}
	
	// 修改，Ajax请求
	@RequestMapping("/update")
	public SysRole update(SysRole sysRole) {
		sysRole.setModifyTime(new Date());
		sysRoleService.update(sysRole);
		
		return sysRole;
	}
	
	// 分配权限页面入口，Ajax请求
	@RequestMapping("/assignRightsEntrance")
	public ModelAndView assignRightsEntrance() {
		
		return new ModelAndView(ASSIGN_RIGHTS_ENTRANCE_VIEW);
	}
	
	// 保存分配的权限，Ajax请求
	@RequestMapping("/assignRights")
	@ResponseBody
	public String assignRights(SysRole sysRole, String menuIds, String operationIds) {
		if (StringUtils.isBlank(menuIds)) {
			menuIds = "";
		}
		if (StringUtils.isBlank(operationIds)) {
			operationIds = "";
		}
		Long[] selectedMenuIds = StringUtil.parseLongArray(menuIds.split(","));
		sysMenuService.saveAssignMenus(sysRole, selectedMenuIds); // 保存菜单权限
		Long[] selectedOperationIds = StringUtil.parseLongArray(operationIds.split(","));
		sysOperationService.saveAssignOperations(sysRole, selectedOperationIds); // 保存操作权限
		
		return JsonUtil.objToJson("true");
	}
	
}
