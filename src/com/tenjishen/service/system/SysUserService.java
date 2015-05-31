package com.tenjishen.service.system;

import java.util.List;

import com.tenjishen.model.SysRole;
import com.tenjishen.model.SysUser;
import com.tenjishen.service.BaseService;

/**
 * Service接口 - 系统用户
 * 
 * @author TENJI
 * 
 */
public interface SysUserService extends BaseService<SysUser, Long> {

	/**
	 * 获取已分配角色编号集合
	 * 
	 * @param sysUserId
	 *            系统用户编号
	 */
	public List<Long> getAssignedRoleIds(SysUser sysUser);
	
	/**
	 * 根据角色获取关联用户
	 * 
	 * @param sysRole
	 *            关联角色
	 * 
	 * @return 角色关联用户列表
	 */
	public List<SysUser> getSysUserList(SysRole sysRole);

}
