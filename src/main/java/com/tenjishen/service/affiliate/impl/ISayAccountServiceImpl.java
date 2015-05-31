package com.tenjishen.service.affiliate.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenjishen.dao.ISayAccountDao;
import com.tenjishen.model.affiliate.ISayAccount;
import com.tenjishen.service.BaseServiceImpl;
import com.tenjishen.service.affiliate.ISayAccountService;

@Service
@Transactional
public class ISayAccountServiceImpl extends BaseServiceImpl<ISayAccount, Long> implements ISayAccountService {
	@Resource
	private ISayAccountDao iSayAccountDao;

	@Resource
	public void setBaseDao(ISayAccountDao iSayAccountDao) {
		super.setBaseDao(iSayAccountDao);
	}

}
