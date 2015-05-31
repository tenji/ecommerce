package com.tenjishen.dao;

import java.util.List;

import com.tenjishen.model.Category;
import com.tenjishen.model.PropertyName;

public interface PropertyNameDao extends BaseDao<PropertyName, Long> {
	
	/**
	 * 获取类目下的属性名
	 * 
	 * @param categoryId
	 *            类目编号
	 */
	public List<PropertyName> getPropertyNameList(Category category);

}
