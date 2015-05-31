package com.tenjishen.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tenjishen.dao.PropertyNameDao;
import com.tenjishen.model.Category;
import com.tenjishen.model.PropertyName;

@Repository
public class PropertyNameDaoImpl extends BaseDaoImpl<PropertyName, Long> implements PropertyNameDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<PropertyName> getPropertyNameList(Category category) {
		String hql = "SELECT propertyName FROM PropertyName propertyName JOIN propertyName.category category WHERE category.id=?";
		
		return getSession().createQuery(hql).setParameter(0, category.getId()).list();
	}

}
