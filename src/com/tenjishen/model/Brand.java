package com.tenjishen.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "t_brand")
public class Brand extends BaseEntity {
	
	private static final long serialVersionUID = 673002419193320491L;
	
	private String chineseName; 
	private String englishName;
	private String description;
	private String logo;
	private Integer status;
	private String officialAddress;
	private String story;
	
	@JsonIgnore
	private Set<Category> categories;
	@JsonIgnore
	private Set<Product> products;
	
	public Brand() {
	}
	
	public Brand(Long brandId) {
		super.setId(brandId);
	}

	@Column(name = "chineseName", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	@Column(name = "englishName", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	@Column(name = "description", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "logo", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 5)
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "officialAddress", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getOfficialAddress() {
		return officialAddress;
	}

	public void setOfficialAddress(String officialAddress) {
		this.officialAddress = officialAddress;
	}

	@Column(name = "story", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}

	@JsonIgnore
	@ManyToMany()
	@JoinTable(name = "t_category_brand",
	joinColumns = @JoinColumn(name = "brandId"),
	inverseJoinColumns = @JoinColumn(name = "categoryId"))
	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	@JsonIgnore
	@OneToMany(targetEntity = Product.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "brandId")
	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
	
}
