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
import com.tenjishen.model.SysOperation;
import com.tenjishen.service.system.SysOperationService;
import com.tenjishen.service.system.SysRoleService;
import com.tenjishen.vo.json.DefaultJsonBean;
import com.tenjishen.vo.json.ZTreeJsonBean;

import com.tenjishen.vo.PageBean;

/**
 * Controller - 操作权限控制器
 * 
 * @author tenji
 *
 */
@Controller
@RequestMapping("/admin/system/sysOperation")
public class SysOperationController {
	
	// VIEW
	protected static final String LIST_VIEW = "/admin/system/sysOperation/list";
	protected static final String READ_ENTRANCE_VIEW = "/admin/system/sysOperation/readEntrance";
	protected static final String UPDATE_ENTRANCE_VIEW = "/admin/system/sysOperation/updateEntrance";
	protected static final String DELETE_ENTRANCE_VIEW = "/admin/system/sysOperation/deleteEntrance";
	protected static final String ADD_ENTRANCE_VIEW = "/admin/system/sysOperation/addEntrance";

	@Resource
	private SysOperationService sysOperationService;
	@Resource
	private SysRoleService sysRoleService;
	
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
		
		pageBean = sysOperationService.findByPageBean(pageBean);
		model.addAttribute("pageBean", pageBean);
		
		return new ModelAndView(LIST_VIEW);
	}
	
	// 查看页面入口，Ajax请求
	@RequestMapping("/readEntrance")
	public ModelAndView readEntrance(Long id, Model model) {
		SysOperation sysOperation = sysOperationService.getById(id);
		model.addAttribute("model", sysOperation);
		
		return new ModelAndView(READ_ENTRANCE_VIEW);
	}
	
	// 查看，Ajax请求
	@RequestMapping("/read")
	public void read(Long id) {
	}
	
	// 添加页面入口，Ajax请求
	@RequestMapping("/addEntrance")
	public ModelAndView addEntrance() {

		return new ModelAndView(ADD_ENTRANCE_VIEW);
	}
	
	// 添加，Ajax请求
	@RequestMapping("/add")
	@ResponseBody
	public SysOperation add(SysOperation sysOperation, Long parentId) {
		// 保存新添加的操作权限，需要事务控制
		sysOperation = sysOperationService.saveNewOperation(sysOperation, parentId);
		
		// 新增成功后返回新增的权限
		return sysOperation;
	}
	
	// 获取操作权限集合，Ajax请求
	@RequestMapping("/getAllOperations")
	@ResponseBody
	public List<SysOperation> getAllOperations() {
		List<SysOperation> list = sysOperationService.getAll();
		
		return list;
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
		SysOperation sysOperation = sysOperationService.getById(id);
		DefaultJsonBean defaultJsonBean = null;
		
		// 存在需要删除的用户
		if (null != sysOperation) {
			sysOperationService.deleteById(sysOperation.getId());
			// 返回正确提示
			defaultJsonBean = new DefaultJsonBean(Constants.JSON_SUCCESS, Constants.JSON_SUCCESS_VALUE);
				
		} else {
			// 返回未知错误提示
			defaultJsonBean = new DefaultJsonBean(Constants.JSON_UNDEFINED_ERROR, Constants.JSON_UNDEFINED_ERROR_VALUE);
		}
		
		return defaultJsonBean;
	}
	
	// 编辑页面入口，Ajax请求
	@RequestMapping("/updateEntrance")
	public ModelAndView updateEntrance() {
		
		return new ModelAndView(UPDATE_ENTRANCE_VIEW);
	}
	
	// 更新操作权限
	@RequestMapping("/update")
	@ResponseBody
	public SysOperation update(SysOperation sysOperation) {
		SysOperation existedOperation = sysOperationService.getById(sysOperation.getId()); // 修改前权限
		existedOperation.setOperationName(sysOperation.getOperationName());
		existedOperation.setUrl(sysOperation.getUrl());
		// 如果选择添加的是顶级菜单
		if (0 == sysOperation.getParent().getId()) {
			existedOperation.setIsTop(1);
			existedOperation.getParent().setId(sysOperation.getId());
			// existedOperation.setParentOperationId(sysOperation.getId());
			sysOperationService.update(existedOperation);
		} else {
			existedOperation.setIsTop(0);
			existedOperation.getParent().setId(sysOperation.getId());
			sysOperationService.update(existedOperation);
		}
		
		return existedOperation;
	}
	
	// 生成操作zTree树，Ajax请求
	@RequestMapping("/getAssignedOperationsTree")
	@ResponseBody
	public List<ZTreeJsonBean> getAssignedOperationsTree(Long roleId) {
		List<Long> assignedOperationIds = new ArrayList<Long>();
		if (null != roleId) {
			assignedOperationIds = sysOperationService.getAssignedOperationIds(sysRoleService.getById(roleId));
		}
		List<SysOperation> sysOperationList = sysOperationService.getAll();
		List<ZTreeJsonBean> zTreeJsonBeans = new ArrayList<ZTreeJsonBean>();
		
		for (SysOperation sysOperation : sysOperationList) {
			ZTreeJsonBean zTreeJsonBean = new ZTreeJsonBean();
			// 菜单是否被选中
			if (assignedOperationIds.contains(sysOperation.getId())) {
				zTreeJsonBean.setChecked(true);
			} else {
				zTreeJsonBean.setChecked(false);
			}
			// 菜单是否顶级菜单
			if (0 == sysOperation.getLevel()) {
				zTreeJsonBean.setpId((long)0);
			} else {
				zTreeJsonBean.setpId(sysOperation.getParent().getId());
			}
			zTreeJsonBean.setId(sysOperation.getId());
			zTreeJsonBean.setName(sysOperation.getOperationName());
			
			zTreeJsonBeans.add(zTreeJsonBean);
		}
		
		return zTreeJsonBeans;
	}
	
}
