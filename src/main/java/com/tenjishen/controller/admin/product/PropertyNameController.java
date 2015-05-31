package com.tenjishen.controller.admin.product;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tenjishen.common.constants.Constants;
import com.tenjishen.model.Category;
import com.tenjishen.model.PropertyName;
import com.tenjishen.service.product.PropertyNameService;
import com.tenjishen.vo.json.DefaultJsonBean;

import com.tenjishen.vo.PageBean;

/**
 * Controller - 属性名管理
 * 
 * @author tenji
 *
 */
@Controller
@Scope("prototype")
public class PropertyNameController {

	PropertyName propertyName = new PropertyName();
	
	@Resource
	private PropertyNameService propertyNameService;
	private List<PropertyName> list;
	
	private int page;	// 第几页
	private PageBean pageBean;	// 包含发布信息的Bean
	
	private Long categoryId; // 所属类目编号
	
	// 查看列表
	public String list() {
		if (0 != page) {
			pageBean = new PageBean();
			pageBean.setCurrentPage(page);
		}
		this.pageBean = propertyNameService.findByPageBean(pageBean);
		
		return "list";
	}
	
	// 查看页面入口，Ajax请求
	public String readEntrance() {
		propertyName = propertyNameService.getById(propertyName.getId());
		
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
		
		propertyName.setCreateTime(new Date());
		propertyName.setModifyTime(new Date());
		propertyNameService.save(propertyName);
		
		// 新增成功后返回新增的权限
		// JsonUtil.responseJson(JsonUtil.writeListToJson(propertyName));
	}
	
	// 更新页面入口，Ajax请求
	public String updateEntrance() {
		propertyName = propertyNameService.getById(propertyName.getId());
		
		return "updateEntrance";
	}
	
	// 更新，Ajax请求
	public void update() {
		propertyNameService.update(propertyName);
		
		// JsonUtil.responseJson(JsonUtil.writeListToJson(propertyName));
	}
	
	// 删除页面入口，Ajax请求
	public String deleteEntrance() {
		
		return "deleteEntrance";
	}
	
	// 删除，Ajax请求
	public void delete() {
		propertyName = propertyNameService.getById(propertyName.getId());
		DefaultJsonBean defaultJsonBean = null;
		
		// 存在需要删除的菜单
		if (null != propertyName) {
			propertyNameService.deleteById(propertyName.getId());
			// 返回正确提示
			defaultJsonBean = new DefaultJsonBean(Constants.JSON_SUCCESS, Constants.JSON_SUCCESS_VALUE);
				
		} else {
			// 返回未知错误提示
			defaultJsonBean = new DefaultJsonBean(Constants.JSON_UNDEFINED_ERROR, Constants.JSON_UNDEFINED_ERROR_VALUE);
		}
		
		// JsonUtil.responseJson(JsonUtil.writeListToJson(defaultJsonBean));
	}
	
	// 获取指定类目下的属性名
	public void getPropertyNamesByCategoryId() {
		if (null != categoryId) {
			list = propertyNameService.getPropertyNameList(new Category(categoryId));
		}
		
		// JsonUtil.responseJson(JsonUtil.writeListToJson(list));
	}
	
	

	public List<PropertyName> getList() {
		return list;
	}

	public void setList(List<PropertyName> list) {
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

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

}
