package com.tenjishen.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单权限树Bean，用于显示菜单栏
 * 
 * @author tenji
 * Created On: Feb 22, 2014
 *
 */
public class MenusTreeBean {
	
	private Long id;
	private String menuName; // 菜单名称
	private String menuDescription; // 菜单描述
	private String url;
	private String iconClass; // 菜单图标
	
	private List<MenusTreeBean> subMenus = new ArrayList<MenusTreeBean>();
	
	// 构造函数
	public MenusTreeBean(Long id, String menuName, String menuDescription, String url, String iconClass) {
		this.menuName = menuName;
		this.menuDescription = menuDescription;
		this.url = url;
		this.iconClass = iconClass;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuDescription() {
		return menuDescription;
	}

	public void setMenuDescription(String menuDescription) {
		this.menuDescription = menuDescription;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIconClass() {
		return iconClass;
	}

	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}

	public List<MenusTreeBean> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(List<MenusTreeBean> subMenus) {
		this.subMenus = subMenus;
	}

}
