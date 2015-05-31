package com.tenjishen.service.product.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenjishen.dao.PropertyNameDao;
import com.tenjishen.model.Category;
import com.tenjishen.model.PropertyName;
import com.tenjishen.service.BaseServiceImpl;
import com.tenjishen.service.product.PropertyNameService;


@Service
@Transactional
public class PropertyNameServiceImpl extends BaseServiceImpl<PropertyName, Long> implements PropertyNameService {
	@Resource
	private PropertyNameDao propertyNameDao;
	
	@Resource
	public void setBaseDao(PropertyNameDao propertyNameDao) {
		super.setBaseDao(propertyNameDao);
	}

	@Override
	public List<PropertyName> getPropertyNameList(Category category) {
		
		return propertyNameDao.getPropertyNameList(category);
	}

}
