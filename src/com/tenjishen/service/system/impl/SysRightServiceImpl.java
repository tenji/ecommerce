package com.tenjishen.service.system.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenjishen.dao.SysRightDao;
import com.tenjishen.model.SysRight;
import com.tenjishen.service.BaseServiceImpl;
import com.tenjishen.service.system.SysRightService;


@Service
@Transactional
public class SysRightServiceImpl extends BaseServiceImpl<SysRight, Long> implements SysRightService {
	private SysRightDao sysRightDao;
	
	@Resource
	public void setSysRightDao(SysRightDao sysRightDao) {
		this.sysRightDao = sysRightDao;
	}
	
	@Resource
	public void setBaseDao(SysRightDao sysRightDao) {
		super.setBaseDao(sysRightDao);
	}

}
