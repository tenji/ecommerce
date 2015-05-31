package com.tenjishen.service.product.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenjishen.dao.BasicPropertyDao;
import com.tenjishen.model.BasicProperty;
import com.tenjishen.service.BaseServiceImpl;
import com.tenjishen.service.product.BasicPropertyService;


@Service
@Transactional
public class BasicPropertyServiceImpl extends BaseServiceImpl<BasicProperty, Long> implements BasicPropertyService {
	@Resource
	private BasicPropertyDao basicPropertyDao;
	
	@Resource
	public void setBaseDao(BasicPropertyDao basicPropertyDao) {
		super.setBaseDao(basicPropertyDao);
	}

}
