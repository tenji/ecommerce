package com.tenjishen.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tenjishen.dao.ProductDao;
import com.tenjishen.model.Category;
import com.tenjishen.model.Product;

@Repository
public class ProductDaoImpl extends BaseDaoImpl<Product, Long> implements ProductDao {
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Product> getProductList(Category category) {
		String hql = "SELECT product FROM Product product JOIN product.category category WHERE category.id = ?";
		
		return getSession().createQuery(hql).setParameter(0, category.getId()).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProductList(List<Category> categories) {
		String hql = "SELECT product FROM Product product JOIN product.category category WHERE category.id in(:ids)";
		
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
	public List<Product> getProductList(Long[] categoryIds) {
		String hql = "SELECT product FROM Product product JOIN product.category category WHERE category.id in(:ids)";
		
		return getSession().createQuery(hql).setParameterList("ids", categoryIds).list();
	}
	
}
