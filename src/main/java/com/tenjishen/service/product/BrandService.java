package com.tenjishen.service.product;

import java.util.List;

import com.tenjishen.model.Brand;
import com.tenjishen.model.Category;
import com.tenjishen.service.BaseService;

public interface BrandService extends BaseService<Brand, Long> {

	/**
	 * 添加品牌
	 * 
	 * @param brand
	 *            品牌对象
	 * @param categoryIds
	 *            新分配的所属类目编号集合
	 */
	public Brand save(Brand brand, Long[] categoryIds);

	/**
	 * 获取类目下的品牌
	 * 
	 * @param categoryId
	 *            类目编号
	 */
	public List<Brand> getBrandList(Category category);

	/**
	 * 更新品牌
	 * 
	 * @param brand
	 *            品牌对象
	 * @param categoryIds
	 *            新分配的所属类目编号集合
	 */
	public Brand update(Brand brand, Long[] categoryIds);
	
	/**
	 * 获取类目集合下的品牌
	 * 
	 * @param categoryId
	 *            类目编号
	 */
	public List<Brand> getBrandList(List<Category> categories);

}
