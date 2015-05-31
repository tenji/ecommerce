package com.tenjishen.dao;

import java.util.List;

import com.tenjishen.model.SysOperation;
import com.tenjishen.model.SysRole;
import com.tenjishen.model.SysUser;

public interface SysOperationDao extends BaseDao<SysOperation, Long> {

	/**
	 * 根据用户获取操作权限
	 * 
	 * @param sysUser
	 *            用户
	 * @return
	 */
	public List<SysOperation> getOperationList(SysUser sysUser);

	/**
	 * 根据角色获取操作权限
	 * 
	 * @param sysRole
	 *            角色
	 * @return
	 */
	public List<SysOperation> getOperationList(SysRole sysRole);

}
