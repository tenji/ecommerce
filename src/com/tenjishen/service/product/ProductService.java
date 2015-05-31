package com.tenjishen.service.product;

import java.util.List;

import com.tenjishen.model.Category;
import com.tenjishen.model.Product;
import com.tenjishen.service.BaseService;

public interface ProductService extends BaseService<Product, Long> {

	/**
	 * 根据产品所属类目查找产品列表
	 * 
	 * @param category
	 *            类目
	 * @return
	 */
	public List<Product> getProductList(Category category);

}
