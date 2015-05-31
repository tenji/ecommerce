package com.tenjishen.dao;

import java.util.List;

import com.tenjishen.model.SysRole;
import com.tenjishen.model.SysUser;

public interface SysUserDao extends BaseDao<SysUser, Long> {

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
