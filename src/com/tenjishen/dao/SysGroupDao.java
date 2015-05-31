package com.tenjishen.dao;

import java.util.List;

import com.tenjishen.model.SysGroup;
import com.tenjishen.model.SysUser;

public interface SysGroupDao extends BaseDao<SysGroup, Long> {
	
	/**
	 * 获取用户所属用户组编号集合
	 * 
	 * @param sysUser 用户
	 * @return
	 */
	public List<Long> getAssignedGroupIds(SysUser sysUser);
}
