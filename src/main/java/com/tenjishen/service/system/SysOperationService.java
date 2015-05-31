package com.tenjishen.service.system;

import java.util.List;

import com.tenjishen.model.SysOperation;
import com.tenjishen.model.SysRole;
import com.tenjishen.model.SysUser;
import com.tenjishen.service.BaseService;

public interface SysOperationService extends BaseService<SysOperation, Long> {

	/**
	 * 保存新添加的操作权限
	 * 
	 * @param sysMenu
	 */
	public SysOperation saveNewOperation(SysOperation sysOperation,
			Long parentId);

	/**
	 * 根据用户角色获取已分配操作权限编号集合
	 * 
	 * @param sysRole
	 *            角色
	 */
	public List<Long> getAssignedOperationIds(SysRole sysRole);

	/**
	 * 保存新分配的操作权限
	 * 
	 * @param sysRole
	 *            角色编号
	 * @param items
	 */
	public void saveAssignOperations(SysRole sysRole, Long[] itemIds);

	/**
	 * 获取指定用户菜单权限集合
	 * 
	 * @param sysUser
	 *            用户
	 * @return
	 */
	public List<SysOperation> getOperationList(SysUser sysUser);

	/**
	 * 获取指定角色操作权限集合
	 * 
	 * @param sysRole
	 *            角色
	 * @return
	 */
	public List<SysOperation> getOperationList(SysRole sysRole);

}
