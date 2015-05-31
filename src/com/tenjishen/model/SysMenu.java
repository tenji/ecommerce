package com.tenjishen.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "sysmenu")
public class SysMenu extends BaseEntity {
	
	private static final long serialVersionUID = 6397172040496453801L;
	
	public static final String PATH_SEPARATOR = ","; // 树路径分隔符
	
	private String menuName; // 菜单名称
	private String menuDescription; // 菜单描述
	private String url;
	private String iconClass; // 菜单图标
	private Integer isTop;
	private String path; // 树路径
	private Integer orderList; // 排序号
	
	@JsonIgnore
	private SysMenu parent; // 父菜单
	@JsonIgnore
	private Set<SysMenu> children; // 子菜单
	@JsonIgnore
	private SysRight sysRight;
	
	@Column(name = "menuName", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Column(name = "menuDescription", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getMenuDescription() {
		return menuDescription;
	}

	public void setMenuDescription(String menuDescription) {
		this.menuDescription = menuDescription;
	}

	@Column(name = "url", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "iconClass", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getIconClass() {
		return iconClass;
	}

	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}

	@Column(name = "isTop", unique = false, nullable = true, insertable = true, updatable = true, length = 2)
	public Integer getIsTop() {
		return isTop;
	}

	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

	@Column(name = "path", nullable = true, length = 10000)
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "orderList", nullable = true, length = 11)
	public Integer getOrderList() {
		return orderList;
	}

	public void setOrderList(Integer orderList) {
		this.orderList = orderList;
	}

	@JsonIgnore
	@ManyToOne(targetEntity = SysMenu.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "parentId")
	public SysMenu getParent() {
		return parent;
	}

	public void setParent(SysMenu parent) {
		this.parent = parent;
	}

	@JsonIgnore
	@OneToMany(targetEntity = SysMenu.class, cascade = CascadeType.ALL, mappedBy = "parent", fetch = FetchType.LAZY)
	public Set<SysMenu> getChildren() {
		return children;
	}

	public void setChildren(Set<SysMenu> children) {
		this.children = children;
	}

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rightId")
	public SysRight getSysRight() {
		return sysRight;
	}

	@JsonIgnore
	public void setSysRight(SysRight sysRight) {
		this.sysRight = sysRight;
	}
	
	// 获取分类层级（顶级分类：0）
	@Transient
	@JsonIgnore
	public Integer getLevel() {
		return path.split(PATH_SEPARATOR).length - 1;
	}


}
