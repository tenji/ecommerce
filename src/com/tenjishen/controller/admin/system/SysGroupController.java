package com.tenjishen.controller.admin.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.tenjishen.common.constants.Constants;
import com.tenjishen.model.SysGroup;
import com.tenjishen.model.SysRole;
import com.tenjishen.model.SysUser;
import com.tenjishen.service.system.SysGroupService;
import com.tenjishen.service.system.SysUserService;
import com.tenjishen.vo.json.DefaultJsonBean;
import com.tenjishen.vo.json.ZTreeJsonBean;

import com.tenjishen.vo.PageBean;

/**
 * Controller - 用户组管理
 *
 */
@Controller
public class SysGroupController {

	SysGroup sysGroup = new SysGroup();
	
	@Resource
	private SysGroupService sysGroupService;
	@Resource
	private SysUserService sysUserService;
	
	private List<SysUser> list;
	private List<SysRole> sysRoleList;
	
	private int page; // 第几页
	private PageBean pageBean; // 包含发布信息的Bean
	
	private Long userId; // 需要分配用户组的用户编号
	private Long parentId; // 父用户组编号
	
	// 查看列表
	public String list() {
		if (0 != page) {
			pageBean = new PageBean();
			pageBean.setCurrentPage(page);
		}
		this.pageBean = sysGroupService.findByPageBean(pageBean);
		
		return "list";
	}
	
	// 查看页面入口，Ajax请求
	public String readEntrance() {
		sysGroup = sysGroupService.getById(sysGroup.getId());
		
		return "readEntrance";
	}
	
	// 查看，Ajax请求
	public void read() {
	}
	
	// 添加页面入口，Ajax请求
	public String addEntrance() {
		
		return "addEntrance";
	}
	
	// 添加，Ajax请求
	public void add() {
		if (0 == parentId) { // 顶级用户组
			sysGroup.setParent(null);
		} else { 
			sysGroup.setParent(sysGroupService.getById(parentId));
		}
		sysGroup.setCreateTime(new Date()); // 创建时间
		sysGroup.setModifyTime(new Date()); // 修改时间
		sysGroupService.save(sysGroup);
		// 新增成功后返回新增的权限
		// JsonUtil.responseJson(JsonUtil.writeListToJson(sysGroup));
	}
	
	// 删除页面入口，Ajax请求
	public String deleteEntrance() {
		
		return "deleteEntrance";
	}
	
	// 删除用户，Ajax请求
	public void delete() {
		sysGroup = sysGroupService.getById(sysGroup.getId());
		// 新建默认JSON格式实体，默认返回错误提示
		DefaultJsonBean defaultJsonBean = null;
		// 存在需要删除的用户
		if (null != sysGroup) {
			sysGroupService.deleteById(sysGroup.getId());
			// 返回正确提示
			defaultJsonBean = new DefaultJsonBean(Constants.JSON_SUCCESS, Constants.JSON_SUCCESS_VALUE);
				
		} else {
			// 返回未知错误提示
			defaultJsonBean = new DefaultJsonBean(Constants.JSON_UNDEFINED_ERROR, Constants.JSON_UNDEFINED_ERROR_VALUE);
		}
		
		// JsonUtil.responseJson(JsonUtil.writeListToJson(defaultJsonBean));
	}
	
	// 生成用户组zTree树，Ajax请求
	public void getAssignedGroupsTree() {
		List<Long> assignedGroupIds = new ArrayList<Long>();
		if (null != userId) {
			assignedGroupIds = sysGroupService.getAssignedGroupIds(sysUserService.getById(userId));
		}
		List<SysGroup> sysGroupList = sysGroupService.getAll();
		List<ZTreeJsonBean> zTreeJsonBeans = new ArrayList<ZTreeJsonBean>();
		
		for (SysGroup sysGroup : sysGroupList) {
			ZTreeJsonBean zTreeJsonBean = new ZTreeJsonBean();
			// 菜单是否被选中
			if (assignedGroupIds.contains(sysGroup.getId())) {
				zTreeJsonBean.setChecked(true);
			} else {
				zTreeJsonBean.setChecked(false);
			}
			// 菜单是否顶级菜单
			if (0 == sysGroup.getLevel()) {
				zTreeJsonBean.setpId((long)0);
			} else {
				zTreeJsonBean.setpId(sysGroup.getParent().getId());
			}
			zTreeJsonBean.setId(sysGroup.getId());
			zTreeJsonBean.setName(sysGroup.getName());
			
			zTreeJsonBeans.add(zTreeJsonBean);
		}
		
		// JsonUtil.responseJson(JsonUtil.writeListToJson(zTreeJsonBeans));
	}
	

	public List<SysUser> getList() {
		return list;
	}

	public List<SysRole> getSysRoleList() {
		return sysRoleList;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
