package com.tenjishen.service.product;

import java.util.List;

import com.tenjishen.model.Category;
import com.tenjishen.service.BaseService;

public interface CategoryService extends BaseService<Category, Long> {

	/**
	 * 根据品牌ID获取所属类目编号集合
	 * 
	 * @param brandId
	 *            品牌ID
	 */
	public List<Long> getAssignedCategoryIds(Long brandId);

	/**
	 * 根据Category对象获取孩子集合，若无子类则返回null;
	 * 
	 * @return 子类集合
	 * 
	 */
	public List<Category> getChildrenCategoryList(Category category);
	
}
