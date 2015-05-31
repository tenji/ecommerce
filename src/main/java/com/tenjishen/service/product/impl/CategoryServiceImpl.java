package com.tenjishen.service.product.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenjishen.dao.CategoryDao;
import com.tenjishen.model.Category;
import com.tenjishen.service.BaseServiceImpl;
import com.tenjishen.service.product.CategoryService;


@Service
@Transactional
public class CategoryServiceImpl extends BaseServiceImpl<Category, Long> implements CategoryService {
	@Resource
	private CategoryDao categoryDao;

	@Resource
	public void setBaseDao(CategoryDao categoryDao) {
		super.setBaseDao(categoryDao);
	}

	@Override
	public List<Long> getAssignedCategoryIds(Long brandId) {
		
		return categoryDao.getAssignedCategoryIds(brandId);
	}

	@Override
	public List<Category> getChildrenCategoryList(Category category) {
		
		return categoryDao.getChildrenCategoryList(category);
	}

}
