package com.tenjishen.service.system.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tenjishen.dao.SysUserDao;
import com.tenjishen.model.SysRole;
import com.tenjishen.model.SysUser;
import com.tenjishen.service.BaseServiceImpl;
import com.tenjishen.service.system.SysUserService;


@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, Long> implements SysUserService {
	
	@Resource
	private SysUserDao sysUserDao;
	
	@Resource
	public void setBaseDao(SysUserDao sysUserDao) {
		super.setBaseDao(sysUserDao);
	}
	
	@Override
	public List<Long> getAssignedRoleIds(SysUser sysUser) {
		List<Long> assignedItems  = new ArrayList<Long>();
		
		sysUser = sysUserDao.getById(sysUser.getId());
		for (SysRole sysRole : sysUser.getSysRoles()) {
			assignedItems.add(sysRole.getId());
		}
		
		return assignedItems;
		
	}

	@Override
	public List<SysUser> getSysUserList(SysRole sysRole) {
		
		return sysUserDao.getSysUserList(sysRole);
	}

}
