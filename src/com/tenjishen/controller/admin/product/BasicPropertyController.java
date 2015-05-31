package com.tenjishen.controller.admin.product;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tenjishen.common.constants.Constants;
import com.tenjishen.model.BasicProperty;
import com.tenjishen.service.product.BasicPropertyService;
import com.tenjishen.vo.json.DefaultJsonBean;

import com.tenjishen.vo.PageBean;

/**
 * Controller - 基本属性管理
 * 
 * @author tenji
 *
 */
@Controller
@Scope("prototype")
public class BasicPropertyController {

	BasicProperty basicProperty = new BasicProperty();
	
	@Resource
	private BasicPropertyService basicPropertyService;
	private List<BasicProperty> list;
	
	private int page;	// 第几页
	private PageBean pageBean;	// 包含发布信息的Bean
	
	// 查看列表
	public String list() {
		if (0 != page) {
			pageBean = new PageBean();
			pageBean.setCurrentPage(page);
		}
		this.pageBean = basicPropertyService.findByPageBean(pageBean);
		
		return "list";
	}
	
	// 查看页面入口，Ajax请求
	public String readEntrance() {
		basicProperty = basicPropertyService.getById(basicProperty.getId());
		
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
		
		basicProperty.setCreateTime(new Date());
		basicProperty.setModifyTime(new Date());
		basicPropertyService.save(basicProperty);
		
		// 新增成功后返回新增的权限
		// JsonUtil.responseJson(JsonUtil.writeListToJson(basicProperty));
	}
	
	// 更新页面入口，Ajax请求
	public String updateEntrance() {
		basicProperty = basicPropertyService.getById(basicProperty.getId());
		
		return "updateEntrance";
	}
	
	// 更新，Ajax请求
	public void update() {
		basicPropertyService.update(basicProperty);
		
		// JsonUtil.responseJson(JsonUtil.writeListToJson(basicProperty));
	}
	
	// 删除页面入口，Ajax请求
	public String deleteEntrance() {
		
		return "deleteEntrance";
	}
	
	// 删除，Ajax请求
	public void delete() {
		basicProperty = basicPropertyService.getById(basicProperty.getId());
		DefaultJsonBean defaultJsonBean = null;
		
		// 存在需要删除的菜单
		if (null != basicProperty) {
			basicPropertyService.deleteById(basicProperty.getId());
			// 返回正确提示
			defaultJsonBean = new DefaultJsonBean(Constants.JSON_SUCCESS, Constants.JSON_SUCCESS_VALUE);
				
		} else {
			// 返回未知错误提示
			defaultJsonBean = new DefaultJsonBean(Constants.JSON_UNDEFINED_ERROR, Constants.JSON_UNDEFINED_ERROR_VALUE);
		}
		
		// JsonUtil.responseJson(JsonUtil.writeListToJson(defaultJsonBean));
	}
	
	
	public List<BasicProperty> getList() {
		return list;
	}

	public void setList(List<BasicProperty> list) {
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

}
