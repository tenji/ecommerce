package com.tenjishen.service.product.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenjishen.dao.PropertyValueDao;
import com.tenjishen.model.PropertyValue;
import com.tenjishen.service.BaseServiceImpl;
import com.tenjishen.service.product.PropertyValueService;


@Service
@Transactional
public class PropertyValueServiceImpl extends BaseServiceImpl<PropertyValue, Long> implements PropertyValueService {
	@Resource
	private PropertyValueDao propertyValueDao;
	
	@Resource
	public void setBaseDao(PropertyValueDao propertyValueDao) {
		super.setBaseDao(propertyValueDao);
	}

}
