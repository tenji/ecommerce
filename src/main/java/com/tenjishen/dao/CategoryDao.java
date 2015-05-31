package com.tenjishen.dao;

import java.util.List;

import com.tenjishen.model.Category;


public interface CategoryDao extends BaseDao<Category, Long> {
	
	/**
	 * 根据品牌ID获取所属类目编号集合
	 * 
	 * @param brandId 品牌ID
	 */
	public List<Long> getAssignedCategoryIds(Long brandId);
	
	/**
	 * 获取所有顶级商品分类集合;
	 * 
	 * @return 所有顶级商品分类集合
	 * 
	 */
	public List<Category> getRootCategoryList();
	
	/**
	 * 根据Category对象获取父亲集合，若无父类则返回null;
	 * 
	 * @return 父类集合
	 * 
	 */
	public List<Category> getParentCategoryList(Category category);
	
	/**
	 * 根据Category对象获取祖先集合，若无父类则返回null;
	 * 
	 * @return 父类集合
	 * 
	 */
	public List<Category> getAncestorCategoryList(Category category);
	
	/**
	 * 根据Category对象获取孩子集合，若无子类则返回null;
	 * 
	 * @return 子类集合
	 * 
	 */
	public List<Category> getChildrenCategoryList(Category category);
	
	/**
	 * 根据Category对象获取子孙集合，若无子类则返回null;
	 * 
	 * @return 子类集合
	 * 
	 */
	public List<Category> getDescendanceCategoryList(Category category);
}
