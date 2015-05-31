package com.tenjishen.controller.admin.product;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tenjishen.common.constants.Constants;
import com.tenjishen.model.PropertyName;
import com.tenjishen.model.PropertyValue;
import com.tenjishen.service.product.PropertyValueService;
import com.tenjishen.vo.json.DefaultJsonBean;

import com.tenjishen.vo.PageBean;

/**
 * Controller - 属性值管理
 * 
 * @author tenji
 *
 */
@Controller
@Scope("prototype")
public class PropertyValueController {

	PropertyValue propertyValue= new PropertyValue();
	
	@Resource
	private PropertyValueService propertyValueService;
	
	private List<PropertyValue> list;
	private List<PropertyName> propertyNameList;
	
	private int page;	// 第几页
	private PageBean pageBean;	// 包含发布信息的Bean
	
	// 查看列表
	public String list() {
		if (0 != page) {
			pageBean = new PageBean();
			pageBean.setCurrentPage(page);
		}
		this.pageBean = propertyValueService.findByPageBean(pageBean);
		
		return "list";
	}
	
	// 查看页面入口，Ajax请求
	public String readEntrance() {
		propertyValue = propertyValueService.getById(propertyValue.getId());
		
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
		
		propertyValue.setCreateTime(new Date());
		propertyValue.setModifyTime(new Date());
		propertyValueService.save(propertyValue);
		
		// 新增成功后返回新增的权限
		// JsonUtil.responseJson(JsonUtil.writeListToJson(propertyValue));
	}
	
	// 更新页面入口，Ajax请求
	public String updateEntrance() {
		propertyValue = propertyValueService.getById(propertyValue.getId());
		
		return "updateEntrance";
	}
	
	// 更新，Ajax请求
	public void update() {
		propertyValueService.update(propertyValue);
		
		// JsonUtil.responseJson(JsonUtil.writeListToJson(propertyValue));
	}
	
	// 删除页面入口，Ajax请求
	public String deleteEntrance() {
		
		return "deleteEntrance";
	}
	
	// 删除，Ajax请求
	public void delete() {
		propertyValue = propertyValueService.getById(propertyValue.getId());
		DefaultJsonBean defaultJsonBean = null;
		
		// 存在需要删除的菜单
		if (null != propertyValue) {
			propertyValueService.deleteById(propertyValue.getId());
			// 返回正确提示
			defaultJsonBean = new DefaultJsonBean(Constants.JSON_SUCCESS, Constants.JSON_SUCCESS_VALUE);
				
		} else {
			// 返回未知错误提示
			defaultJsonBean = new DefaultJsonBean(Constants.JSON_UNDEFINED_ERROR, Constants.JSON_UNDEFINED_ERROR_VALUE);
		}
		
		// JsonUtil.responseJson(JsonUtil.writeListToJson(defaultJsonBean));
	}
	
	

	public List<PropertyValue> getList() {
		return list;
	}

	public void setList(List<PropertyValue> list) {
		this.list = list;
	}

	public List<PropertyName> getPropertyNameList() {
		return propertyNameList;
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

}
