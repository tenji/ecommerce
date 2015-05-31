package com.tenjishen.service.log.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenjishen.dao.AdminLoginDao;
import com.tenjishen.model.log.LogAdminLogin;
import com.tenjishen.service.BaseServiceImpl;
import com.tenjishen.service.log.AdminLoginService;

@Service
@Transactional
public class AdminLoginServiceImpl extends BaseServiceImpl<LogAdminLogin, Long> implements AdminLoginService {
	@Resource
	private AdminLoginDao adminLoginDao;

	@Resource
	public void setBaseDao(AdminLoginDao adminLoginDao) {
		super.setBaseDao(adminLoginDao);
	}

}
