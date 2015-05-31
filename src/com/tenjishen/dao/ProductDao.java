package com.tenjishen.dao;

import java.util.List;

import com.tenjishen.model.Category;
import com.tenjishen.model.Product;

public interface ProductDao extends BaseDao<Product, Long> {

	/**
	 * 根据产品所属类目查找产品列表
	 * 
	 * @param category
	 *            类目
	 * @return
	 */
	public List<Product> getProductList(Category category);

	/**
	 * 查找类目集合下的产品列表
	 * 
	 * @param categories
	 *            类目集合
	 * @return
	 */
	public List<Product> getProductList(List<Category> categories);
	
	/**
	 * 根据类目编号数组产品列表（备用）
	 * 
	 * @param categoryIds
	 *            类目编号数组
	 * @return
	 */
	public List<Product> getProductList(Long[] categoryIds);

}
