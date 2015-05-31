package com.tenjishen.dao;

import java.util.List;

import com.tenjishen.model.Brand;
import com.tenjishen.model.Category;

public interface BrandDao extends BaseDao<Brand, Long> {

	/**
	 * 获取类目下的品牌
	 * 
	 * @param category
	 *            类目
	 */
	public List<Brand> getBrandList(Category category);

	/**
	 * 获取类目集合下的品牌
	 * 
	 * @param categories
	 * @return
	 */
	public List<Brand> getBrandList(List<Category> categories);

	/**
	 * 根据类目编号数组品牌列表（备用）
	 * 
	 * @param categories
	 *            类目编号数组
	 * @return
	 */
	public List<Brand> getBrandList(Long[] categoryIds);

}
