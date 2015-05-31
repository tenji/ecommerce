package com.tenjishen.controller.admin.product;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tenjishen.common.constants.Constants;
import com.tenjishen.model.Brand;
import com.tenjishen.model.Category;
import com.tenjishen.service.product.BrandService;
import com.tenjishen.vo.json.DefaultJsonBean;

import com.tenjishen.vo.PageBean;


/**
 * Controller - 品牌管理
 * 
 * @author tenji
 *
 */
@Scope("prototype")
@Controller("brandAction")
public class BrandController {

	Brand brand = new Brand();
	
	@Resource
	private BrandService brandService;
	private List<Brand> list;
	
	private int page;	// 第几页
	private PageBean pageBean;	// 包含发布信息的Bean
	
	private Long[] categoryIds; // 所属类目编号集合
	private Long categoryId; // 所属类目编号
	
	// 查看列表
	public String list() {
		if (0 != page) {
			pageBean = new PageBean();
			pageBean.setCurrentPage(page);
		}
		this.pageBean = brandService.findByPageBean(pageBean);
		
		return "list";
	}
	
	// 查看页面入口，Ajax请求
	public String readEntrance() {
		brand = brandService.getById(brand.getId());
		
		return "readEntrance";
	}
	
	// 查看，Ajax请求
	public void read() {
	}
	
	// 添加页面入口，Ajax请求
	public String addEntrance() {
		
		return "addEntrance";
	}
	
	// 添加，Ajax请求
	public void add() {
		
		brand.setCreateTime(new Date());
		brandService.save(brand, categoryIds); // 保存新的品牌
		
		// 新增成功后返回新增的权限
		// JsonUtil.responseJson(JsonUtil.writeListToJson(brand));
	}
	
	// 更新页面入口，Ajax请求
	public String updateEntrance() {
		brand = brandService.getById(brand.getId());
		
		return "updateEntrance";
	}
	
	// 更新，Ajax请求
	public void update() {
		brand = brandService.update(brand, categoryIds);
		
		// JsonUtil.responseJson(JsonUtil.writeListToJson(brand));
	}
	
	// 删除页面入口，Ajax请求
	public String deleteEntrance() {
		
		return "deleteEntrance";
	}
	
	// 删除，Ajax请求
	public void delete() {
		brand = brandService.getById(brand.getId());
		DefaultJsonBean defaultJsonBean = null;
		
		// 存在需要删除的菜单
		if (null != brand) {
			brandService.deleteById(brand.getId());
			// 返回正确提示
			defaultJsonBean = new DefaultJsonBean(Constants.JSON_SUCCESS, Constants.JSON_SUCCESS_VALUE);
				
		} else {
			// 返回未知错误提示
			defaultJsonBean = new DefaultJsonBean(Constants.JSON_UNDEFINED_ERROR, Constants.JSON_UNDEFINED_ERROR_VALUE);
		}
		
		// JsonUtil.responseJson(JsonUtil.writeListToJson(defaultJsonBean));
	}
	
	public void getBrandsByCategoryId() {
		if (null != categoryId) {
			list = brandService.getBrandList(new Category(categoryId));
		}
		
		// JsonUtil.responseJson(JsonUtil.writeListToJson(list));
	}
	
	
	
	public List<Brand> getList() {
		return list;
	}

	public void setList(List<Brand> list) {
		this.list = list;
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

	public void setCategoryIds(Long[] categoryIds) {
		this.categoryIds = categoryIds;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
}
