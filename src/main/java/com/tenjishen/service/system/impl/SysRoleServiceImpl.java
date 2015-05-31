package com.tenjishen.service.system.impl;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tenjishen.dao.SysRoleDao;
import com.tenjishen.dao.SysUserDao;
import com.tenjishen.model.SysRole;
import com.tenjishen.model.SysUser;
import com.tenjishen.service.BaseServiceImpl;
import com.tenjishen.service.system.SysRoleService;


/**
 * Service实现类 - 权限
 * 
 *
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, Long> implements SysRoleService {
	
	@Resource
	private SysRoleDao sysRoleDao;
	@Resource
	private SysUserDao sysUserDao;
	
	@Resource
	public void setBaseDao(SysRoleDao sysRoleDao) {
		super.setBaseDao(sysRoleDao);
	}
	
	@Override
	public void saveAssignRoles(SysUser sysUser, Long[] items) {
		sysUser = sysUserDao.getById(sysUser.getId());
		/*----------------------------分配思路------------------------------------*/
		/* 由于角色不多而且变化不大，所以采取暴力分配方式，先删除所有已分配的角色，然后再添加新角色
		 */
		/*----------------------------------------------------------------------*/
		Set<SysRole> sysRoles = sysUser.getSysRoles();
		sysRoles.clear(); // 删除旧的用户角色
		
		for (int i = 0; i < items.length; i++) {
			SysRole sysRole = sysRoleDao.getById(items[i]);
			sysRoles.add(sysRole); // 添加新的角色
		}
		
		sysUser.setSysRoles(sysRoles);
		sysUserDao.update(sysUser);
	}

}
