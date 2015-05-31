package com.tenjishen.controller.shops.product;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tenjishen.model.Brand;
import com.tenjishen.model.Category;
import com.tenjishen.model.Product;
import com.tenjishen.service.product.BrandService;
import com.tenjishen.service.product.CategoryService;
import com.tenjishen.service.product.ProductService;

import com.tenjishen.vo.PageBean;

/**
 * Controller - 产品
 * 
 * @author tenji
 *
 */
@Controller(value = "shops.ProductController")
@Scope("prototype")
public class ProductController {

	Product product = new Product();
	
	@Resource
	private ProductService productService;
	@Resource
	private BrandService brandService;
	@Resource
	private CategoryService categoryService;
	
	private List<Product> productList;
	private List<Brand> brandList;
	private List<Category> categoryList;
	
	private int page;	// 第几页
	private PageBean pageBean;	// 包含发布信息的Bean
	
	private Long categoryId; // 类目编号
	
	// 产品列表
	public String itemList() {
		Category category = categoryService.getById(categoryId);
		productList = productService.getProductList(category); // 本类目及子孙类目下的产品列表
		categoryList = categoryService.getChildrenCategoryList(category); // 子类目列表
		categoryList.add(category); // 添加本类目
		brandList = brandService.getBrandList(categoryList);
		categoryList.remove(category); // 删除本类目
		
		return "itemList";
	}
	
	// 产品详情
	public String item() {
		product = productService.getById(product.getId());
		
		return "item";
	}
	
	/*------------------------------Getters and Setters-------------------------------*/

	public List<Product> getProductList() {
		return productList;
	}

	public List<Category> getCategoryList() {
		return categoryList;
	}

	public List<Brand> getBrandList() {
		return brandList;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

}
