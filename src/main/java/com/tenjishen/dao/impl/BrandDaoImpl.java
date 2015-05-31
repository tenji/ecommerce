package com.tenjishen.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tenjishen.dao.BrandDao;
import com.tenjishen.model.Brand;
import com.tenjishen.model.Category;

@Repository
public class BrandDaoImpl extends BaseDaoImpl<Brand, Long> implements BrandDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Brand> getBrandList(Category category) {
		String hql = "SELECT brand FROM Brand brand JOIN brand.categories category WHERE category.id=?";
		
		return getSession().createQuery(hql).setParameter(0, category.getId()).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Brand> getBrandList(List<Category> categories) {
		// 使用DISTINCT关键字去掉重复记录
		String hql = "SELECT DISTINCT brand FROM Brand brand LEFT JOIN brand.categories category WHERE category.id in(:ids)";
		
		Long[] ids = new Long[categories.size()];
		int i = 0;
		for (Category category: categories) {
			ids[i] = category.getId();
			i++;
		}
		
		return getSession().createQuery(hql).setParameterList("ids", ids).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Brand> getBrandList(Long[] categoryIds) {
		String hql = "SELECT brand FROM Brand brand JOIN brand.categories category WHERE category.id in(:ids)";
		
		return getSession().createQuery(hql).setParameterList("ids", categoryIds).list();
	}
	
}
