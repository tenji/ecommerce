package com.tenjishen.service.system;

import java.util.List;

import com.tenjishen.model.SysGroup;
import com.tenjishen.model.SysUser;
import com.tenjishen.service.BaseService;

/**
 * Service接口 - 系统用户组
 * 
 * @author TENJI
 * 
 */
public interface SysGroupService extends BaseService<SysGroup, Long> {
	
	/**
	 * 获取用户所属用户组编号集合
	 * 
	 * @param sysUser
	 *            用户
	 */
	public List<Long> getAssignedGroupIds(SysUser sysUser);
	
	/**
	 * 保存新分配的用户组
	 * 
	 * @param sysUser
	 *            用户
	 * @param items
	 */
	public void saveAssignGroups(SysUser sysUser, Long[] itemIds);

}
