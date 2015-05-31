package com.tenjishen.service.affiliate.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenjishen.dao.EmailDao;
import com.tenjishen.model.affiliate.Email;
import com.tenjishen.model.affiliate.Ram;
import com.tenjishen.service.BaseServiceImpl;
import com.tenjishen.service.affiliate.EmailService;
import com.tenjishen.vo.PageBean;

@Service
@Transactional
public class EmailServiceImpl extends BaseServiceImpl<Email, Long> implements EmailService {
	@Resource
	private EmailDao emailDao;

	@Resource
	public void setBaseDao(EmailDao emailDao) {
		super.setBaseDao(emailDao);
	}

	@Override
	public List<Email> getUnregisteredEmailList(Ram ram) {
		return emailDao.getUnregisteredEmailList(ram);
	}

	@Override
	public PageBean findByPageBean(PageBean pageBean, Email email) {
		return emailDao.findByPageBean(pageBean, email);
	}

}
