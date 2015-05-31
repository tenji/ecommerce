package com.tenjishen.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "t_category")
public class Category extends BaseEntity {
	
	private static final long serialVersionUID = 8195054702874802537L;

	public static final String PATH_SEPARATOR = ","; // 树路径分隔符
	
	private String categoryName; // 类目名称
	private String iconUrl; // 类目图标地址
	private Integer isTop;
	private Long orderList;
	private String path; // 树路径
	
	@JsonIgnore
	private Category parent; // 父类目
	@JsonIgnore
	private Set<Category> children; // 子类目
	@JsonIgnore
	private Set<Brand> brands;
	@JsonIgnore
	private Set<Product> products;
	
	public Category() {
	}
	
	public Category(Long categoryId) {
		super.setId(categoryId);
	}

	@Column(name = "categoryName", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Column(name = "iconUrl", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	@Column(name = "isTop", unique = false, nullable = true, insertable = true, updatable = true, length = 2)
	public Integer getIsTop() {
		return isTop;
	}

	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

	@Column(name = "orderList", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public Long getOrderList() {
		return orderList;
	}

	public void setOrderList(Long orderList) {
		this.orderList = orderList;
	}

	@Column(name = "path", nullable = true, length = 10000)
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@JsonIgnore
	@ManyToOne(targetEntity = Category.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "parentId")
	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	@JsonIgnore
	@OneToMany(targetEntity = Category.class, cascade = CascadeType.ALL, mappedBy = "parent", fetch = FetchType.LAZY)
	public Set<Category> getChildren() {
		return children;
	}

	public void setChildren(Set<Category> children) {
		this.children = children;
	}

	@JsonIgnore
	@ManyToMany(mappedBy = "categories")
	public Set<Brand> getBrands() {
		return brands;
	}

	public void setBrands(Set<Brand> brands) {
		this.brands = brands;
	}

	@JsonIgnore
	@OneToMany(targetEntity = Product.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId")
	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
	// 获取分类层级（顶级分类：0）
	@Transient
	@JsonIgnore
	public Integer getLevel() {
		return path.split(PATH_SEPARATOR).length - 1;
	}
	
}
