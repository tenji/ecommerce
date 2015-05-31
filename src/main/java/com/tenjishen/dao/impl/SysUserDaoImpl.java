package com.tenjishen.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tenjishen.dao.SysUserDao;
import com.tenjishen.model.SysRole;
import com.tenjishen.model.SysUser;

@Repository
public class SysUserDaoImpl extends BaseDaoImpl<SysUser, Long> implements SysUserDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<SysUser> getSysUserList(SysRole sysRole) {
		String hql = "SELECT sysUser FROM SysUser sysUser JOIN sysUser.sysRoles sysRole WHERE sysRole.id = ?";
		
		return getSession().createQuery(hql).setParameter(0, sysRole.getId()).list();
	}
	
}
