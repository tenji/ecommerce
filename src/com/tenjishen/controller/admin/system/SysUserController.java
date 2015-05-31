package com.tenjishen.controller.admin.system;

import java.util.Date;
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
import com.tenjishen.common.util.JsonUtil;
import com.tenjishen.common.util.encryption.MD5Util;
import com.tenjishen.model.SysRole;
import com.tenjishen.model.SysUser;
import com.tenjishen.service.system.SysGroupService;
import com.tenjishen.service.system.SysRoleService;
import com.tenjishen.service.system.SysUserService;
import com.tenjishen.vo.json.DefaultJsonBean;

import com.tenjishen.vo.PageBean;

/**
 * Controller - 用户管理
 *
 */
@Controller
@RequestMapping("/admin/system/sysUser")
public class SysUserController {
	
	// VIEW
	protected static final String LIST_VIEW = "/admin/system/sysUser/list";
	protected static final String READ_ENTRANCE_VIEW = "/admin/system/sysUser/readEntrance";
	protected static final String UPDATE_ENTRANCE_VIEW = "/admin/system/sysUser/updateEntrance";
	protected static final String DELETE_ENTRANCE_VIEW = "/admin/system/sysUser/deleteEntrance";
	protected static final String ADD_ENTRANCE_VIEW = "/admin/system/sysUser/addEntrance";
	protected static final String ASSIGN_ROLES_ENTRANCE_VIEW = "/admin/system/sysUser/assignRolesEntrance";
	protected static final String ASSIGN_GROUPS_ENTRANCE_VIEW = "/admin/system/sysUser/assignGroupsEntrance";

	@Resource
	private SysUserService sysUserService;
	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private SysGroupService sysGroupService;
	
	// 查看列表
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
		
		pageBean = sysUserService.findByPageBean(pageBean);
		model.addAttribute("pageBean", pageBean);
		
		return new ModelAndView(LIST_VIEW);
	}
	
	// 查看页面入口，Ajax请求
	@RequestMapping("/readEntrance")
	public ModelAndView readEntrance(Long id, Model model) {
		SysUser sysUser = sysUserService.getById(id);
		model.addAttribute("model", sysUser);
		
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
	public SysUser add(SysUser sysUser) {
		sysUser.setLoginPassword(MD5Util.getMD5(sysUser.getLoginPassword())); // 密码使用MD5加密
		sysUser.setCreateTime(new Date()); // 创建时间
		sysUser.setModifyTime(new Date()); // 修改时间
		sysUserService.save(sysUser);
		
		// 新增成功后返回新增的权限
		return sysUser;
	}
	
	// 删除页面入口，Ajax请求
	@RequestMapping("/deleteEntrance")
	public ModelAndView deleteEntrance() {
		
		return new ModelAndView(DELETE_ENTRANCE_VIEW);
	}
	
	// 删除用户，Ajax请求
	@RequestMapping("/delete")
	@ResponseBody
	public DefaultJsonBean delete(Long id) {
		SysUser sysUser = sysUserService.getById(id);
		// 新建默认JSON格式实体，默认返回错误提示
		DefaultJsonBean defaultJsonBean = null;
		// 存在需要删除的用户
		if (null != sysUser) {
			sysUserService.deleteById(sysUser.getId());
			// 返回正确提示
			defaultJsonBean = new DefaultJsonBean(Constants.JSON_SUCCESS, Constants.JSON_SUCCESS_VALUE);
				
		} else {
			// 返回未知错误提示
			defaultJsonBean = new DefaultJsonBean(Constants.JSON_UNDEFINED_ERROR, Constants.JSON_UNDEFINED_ERROR_VALUE);
		}
		
		return defaultJsonBean;
	}
	
	// 分配角色页面入口，Ajax请求
	@RequestMapping("/assignRolesEntrance")
	public ModelAndView assignRolesEntrance(Model model) {
		List<SysRole> sysRoleList = sysRoleService.getAll();
		model.addAttribute("sysRoleList", sysRoleList);
		
		return new ModelAndView(ASSIGN_ROLES_ENTRANCE_VIEW);
	}
	
	// 获取用户已分配权限的角色编号集合，Ajax请求
	@RequestMapping("/getAssignedRoles")
	@ResponseBody
	public List<Long> getAssignedRoles(Long id) {
		List<Long> assignedItems = sysUserService.getAssignedRoleIds(new SysUser(id));
		
		return assignedItems;
	}
	
	// 保存分配的角色
	@RequestMapping("/assignRoles")
	@ResponseBody
	public String assignRoles(Long id, Long[] itemIds) {
		// 保存分配角色
		sysRoleService.saveAssignRoles(new SysUser(id), itemIds);
		
		return JsonUtil.objToJson("true");

	}
	
	// 分配用户组页面入口，Ajax请求
	@RequestMapping("/assignGroupsEntrance")
	public ModelAndView assignGroupsEntrance() {
		
		return new ModelAndView(ASSIGN_GROUPS_ENTRANCE_VIEW);
	}
	
	// 保存分配的用户组
	@RequestMapping("/assignGroups")
	@ResponseBody
	public String assignGroups(Long id, Long[] itemIds) {
		// 保存分配的用户组
		sysGroupService.saveAssignGroups(new SysUser(id), itemIds);
		
		return JsonUtil.objToJson("true");

	}

}
