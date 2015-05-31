package com.tenjishen.service.common.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tenjishen.common.util.encryption.MD5Util;
import com.tenjishen.dao.SysUserDao;
import com.tenjishen.model.SysUser;
import com.tenjishen.service.BaseServiceImpl;
import com.tenjishen.service.common.LoginService;

@Service
public class LoginServiceImpl extends BaseServiceImpl<SysUser, Long> implements LoginService {
	
	private SysUserDao sysUserDao;
	
	@Resource
	public void setSysUserDao(SysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
	}
	
	@Resource
	public void setBaseDao(SysUserDao sysUserDao) {
		super.setBaseDao(sysUserDao);
	}
	
	@Override
	public boolean validate(SysUser sysUser) {
		SysUser user = sysUserDao.getByPropertyName("loginName", sysUser.getLoginName());
		
		if (null != user) {
			// 用户存在且密码正确
			if (MD5Util.getMD5(sysUser.getLoginPassword()).equalsIgnoreCase(user.getLoginPassword())) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean isUserExist(String username) {
		SysUser sysUser = sysUserDao.getByPropertyName("loginName", username);
		// 用户存在
		if (null != sysUser) {
			return true;
		}
		
		return false;
	}

}
