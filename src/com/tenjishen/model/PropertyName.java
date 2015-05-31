package com.tenjishen.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * PropertyName entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_property_name")
public class PropertyName extends BaseEntity {

	private static final long serialVersionUID = -3226220911060842261L;
	
	private String name;
	private Integer allowAlias;
	private Integer isColor;
	private Integer isEnumerated;
	private Integer isInput;
	private Integer isKey;
	private Integer isSale;
	private Integer isSearch;
	private Integer isNecessary;
	private Integer isAlternative;
	private Integer status;
	
	private Category category; // 所属类目

	/** default constructor */
	public PropertyName() {
	}

	/** full constructor */
	public PropertyName(String name, Integer categoryId, Integer allowAlias,
			Integer isColor, Integer isEnumerated, Integer isInput,
			Integer isKey, Integer isSale, Integer isSearch,
			Integer isNecessary, Integer isAlternative, Integer status) {
		this.name = name;
		this.allowAlias = allowAlias;
		this.isColor = isColor;
		this.isEnumerated = isEnumerated;
		this.isInput = isInput;
		this.isKey = isKey;
		this.isSale = isSale;
		this.isSearch = isSearch;
		this.isNecessary = isNecessary;
		this.isAlternative = isAlternative;
		this.status = status;
	}

	@Column(name = "name", length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "allowAlias")
	public Integer getAllowAlias() {
		return this.allowAlias;
	}

	public void setAllowAlias(Integer allowAlias) {
		this.allowAlias = allowAlias;
	}

	@Column(name = "isColor")
	public Integer getIsColor() {
		return this.isColor;
	}

	public void setIsColor(Integer isColor) {
		this.isColor = isColor;
	}

	@Column(name = "isEnumerated")
	public Integer getIsEnumerated() {
		return this.isEnumerated;
	}

	public void setIsEnumerated(Integer isEnumerated) {
		this.isEnumerated = isEnumerated;
	}

	@Column(name = "isInput")
	public Integer getIsInput() {
		return this.isInput;
	}

	public void setIsInput(Integer isInput) {
		this.isInput = isInput;
	}

	@Column(name = "isKey")
	public Integer getIsKey() {
		return this.isKey;
	}

	public void setIsKey(Integer isKey) {
		this.isKey = isKey;
	}

	@Column(name = "isSale")
	public Integer getIsSale() {
		return this.isSale;
	}

	public void setIsSale(Integer isSale) {
		this.isSale = isSale;
	}

	@Column(name = "isSearch")
	public Integer getIsSearch() {
		return this.isSearch;
	}

	public void setIsSearch(Integer isSearch) {
		this.isSearch = isSearch;
	}

	@Column(name = "isNecessary")
	public Integer getIsNecessary() {
		return this.isNecessary;
	}

	public void setIsNecessary(Integer isNecessary) {
		this.isNecessary = isNecessary;
	}

	@Column(name = "isAlternative")
	public Integer getIsAlternative() {
		return this.isAlternative;
	}

	public void setIsAlternative(Integer isAlternative) {
		this.isAlternative = isAlternative;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@ManyToOne(targetEntity = Category.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "categoryId")
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}