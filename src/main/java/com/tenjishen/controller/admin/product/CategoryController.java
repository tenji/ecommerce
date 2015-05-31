package com.tenjishen.controller.admin.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tenjishen.common.constants.Constants;
import com.tenjishen.model.Brand;
import com.tenjishen.model.Category;
import com.tenjishen.service.product.BrandService;
import com.tenjishen.service.product.CategoryService;
import com.tenjishen.vo.json.DefaultJsonBean;
import com.tenjishen.vo.json.ZTreeJsonBean;

import com.tenjishen.vo.PageBean;

/**
 * Controller - 类目管理
 * 
 * @author tenji
 *
 */
@Controller
@Scope("prototype")
public class CategoryController {

	Category category = new Category();
	
	@Resource
	private CategoryService categoryService;
	@Resource
	private BrandService brandService;
	
	private List<Category> list;
	private List<Brand> brandList;
	
	private int page;	// 第几页
	private PageBean pageBean;	// 包含发布信息的Bean
	
	private Long parentId; // 父类目编号
	private Long brandId; // 品牌编号
	
	// 查看列表
	public String list() {
		if (0 != page) {
			pageBean = new PageBean();
			pageBean.setCurrentPage(page);
		}
		this.pageBean = categoryService.findByPageBean(pageBean);
		
		return "list";
	}
	
	// 查看页面入口，Ajax请求
	public String readEntrance() {
		category = categoryService.getById(category.getId());
		List<Category> categories = categoryService.getChildrenCategoryList(category); // 子孙类目
		categories.add(category); // 包括本类目
		brandList = brandService.getBrandList(categories);
		
		return "readEntrance";
	}
	
	// 根据编号查看，Ajax请求
	public void read() {
	}
	
	// 添加页面入口，异步请求
	public String addEntrance() {
		
		return "addEntrance";
	}
	
	// 添加，异步请求
	public void add() {
		category.setCreateTime(new Date()); // 创建时间
		category.setModifyTime(new Date()); // 修改时间
		// 如果选择添加的是顶级菜单
		if (0 == parentId) {
			category.setIsTop(1);
			category.setParent(null);
		} else {
			category.setIsTop(0);
			Category parentCategory = categoryService.loadById(parentId);
			category.setParent(parentCategory);
		}
		
		categoryService.save(category);
		// 新增成功后返回新增的权限
		// JsonUtil.responseJson(JsonUtil.writeListToJson(category));
	}
	
	// 删除页面入口，异步请求
	public String deleteEntrance() {
		
		return "deleteEntrance";
	}
	
	// 删除
	public void delete() {
		category = categoryService.getById(category.getId());
		DefaultJsonBean defaultJsonBean = null;
		
		// 存在需要删除的菜单
		if (null != category) {
			categoryService.deleteById(category.getId());
			// 返回正确提示
			defaultJsonBean = new DefaultJsonBean(Constants.JSON_SUCCESS, Constants.JSON_SUCCESS_VALUE);
				
		} else {
			// 返回未知错误提示
			defaultJsonBean = new DefaultJsonBean(Constants.JSON_UNDEFINED_ERROR, Constants.JSON_UNDEFINED_ERROR_VALUE);
		}
		
		// JsonUtil.responseJson(JsonUtil.writeListToJson(defaultJsonBean));
	}
	
	// 生成zTree产品类目树，Ajax请求
	public void getAssignedCategoriesTree() {
		List<Long> assignedCategoryIds = null;
		// 是否需要获取该品牌所属类目
		if (null != brandId) {
			assignedCategoryIds = categoryService.getAssignedCategoryIds(brandId);
		}
		List<Category> categoryList = categoryService.getAll(); // 全部类目
		List<ZTreeJsonBean> zTreeJsonBeans = new ArrayList<ZTreeJsonBean>();
		Iterator<Category> categoryIterator = categoryList.iterator();
		Category category = new Category();
		
		while (categoryIterator.hasNext()) {
			category = categoryIterator.next();
			ZTreeJsonBean zTreeJsonBean = new ZTreeJsonBean();
			if (null != brandId) {
				
			}
			if (null != assignedCategoryIds) {
				// 菜单是否被选中
				if (assignedCategoryIds.contains(category.getId())) {
					zTreeJsonBean.setChecked(true);
				} else {
					zTreeJsonBean.setChecked(false);
				}
			}
			// 菜单是否顶级菜单
			if (0 == category.getLevel()) {
				zTreeJsonBean.setpId((long)0);
			} else {
				zTreeJsonBean.setpId(category.getParent().getId());
			}
			zTreeJsonBean.setId(category.getId());
			zTreeJsonBean.setName(category.getCategoryName());
			if (null != category.getIconUrl()) {
				// zTreeJsonBean.setIcon(getRequest().getContextPath() + category.getIconUrl());
			} else {
				zTreeJsonBean.setIcon(category.getIconUrl());
			}
			
			zTreeJsonBeans.add(zTreeJsonBean);
		}
		
		// JsonUtil.responseJson(JsonUtil.writeListToJson(zTreeJsonBeans));
	}
	

	
	public List<Category> getList() {
		return list;
	}

	public void setList(List<Category> list) {
		this.list = list;
	}

	public List<Brand> getBrandList() {
		return brandList;
	}

	public void setBrandList(List<Brand> brandList) {
		this.brandList = brandList;
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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

}
