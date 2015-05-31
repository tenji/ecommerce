package com.tenjishen.service.system;

import com.tenjishen.model.SysRole;
import com.tenjishen.model.SysUser;
import com.tenjishen.service.BaseService;

/**
 * Service接口 - 权限
 * 
 * Created On: 2013-11-12
 * @author tenji
 *
 */
public interface SysRoleService extends BaseService<SysRole, Long> {
	
	/**
	 * 保存分配的权限
	 * 
	 * @param sysUserId 系统用户编号
	 * @param items 前台提交过来的roleId数组
	 */
	public void saveAssignRoles(SysUser sysUser, Long[] items);
	
}
