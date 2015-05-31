package com.tenjishen.service.product.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenjishen.dao.CategoryDao;
import com.tenjishen.dao.ProductDao;
import com.tenjishen.model.Category;
import com.tenjishen.model.Product;
import com.tenjishen.service.BaseServiceImpl;
import com.tenjishen.service.product.ProductService;


@Service
@Transactional
public class ProductServiceImpl extends BaseServiceImpl<Product, Long> implements ProductService {
	@Resource
	private ProductDao productDao;
	
	@Resource
	private CategoryDao categoryDao;

	@Resource
	public void setBaseDao(ProductDao productDao) {
		super.setBaseDao(productDao);
	}

	@Override
	public List<Product> getProductList(Category category) {
		List<Category> descendanceCategoryList = categoryDao.getDescendanceCategoryList(category);
		descendanceCategoryList.add(category); // 包括本目录
		
		return productDao.getProductList(descendanceCategoryList);
	}

}
