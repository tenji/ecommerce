package com.tenjishen.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "t_product")
public class Product extends BaseEntity {
	
	private static final long serialVersionUID = 8470421985070389868L;
	
	private String name; 
	private String description;
	private String detail;
	private Double listPrice;
	private String staticPageUrl;
	
	@JsonIgnore
	private Category category;
	
	@JsonIgnore
	private Brand brand;
	
	@JsonIgnore
	private Set<ProductImg> productImgs;

	@Column(name = "name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 2000)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "detail", length = 10000)
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Column(name = "listPrice", unique = false, nullable = true, insertable = true, updatable = true)
	public Double getListPrice() {
		return listPrice;
	}

	public void setListPrice(Double listPrice) {
		this.listPrice = listPrice;
	}

	@Column(name = "staticPageUrl", length = 100)
	public String getStaticPageUrl() {
		return staticPageUrl;
	}

	public void setStaticPageUrl(String staticPageUrl) {
		this.staticPageUrl = staticPageUrl;
	}

	@JsonIgnore
	@ManyToOne(targetEntity = Category.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "categoryId")
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@JsonIgnore
	@ManyToOne(targetEntity = Brand.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "brandId")
	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	@JsonIgnore
	@OneToMany(targetEntity = ProductImg.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "productId")
	public Set<ProductImg> getProductImgs() {
		return productImgs;
	}

	public void setProductImgs(Set<ProductImg> productImgs) {
		this.productImgs = productImgs;
	}
	

}
