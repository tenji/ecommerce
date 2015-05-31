package com.tenjishen.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import javax.persistence.Table;

/**
 * BasicProperty entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_basic_property")
public class BasicProperty extends BaseEntity {

	private static final long serialVersionUID = 2676086555471782398L;
	
	private Integer isSku;
	private Integer skuid;
	
	private Product product; // 产品
	private PropertyName propertyName; // 属性名
	private PropertyValue propertyValue; // 属性值

	@Column(name = "isSKU")
	public Integer getIsSku() {
		return this.isSku;
	}

	public void setIsSku(Integer isSku) {
		this.isSku = isSku;
	}

	@Column(name = "SKUId")
	public Integer getSkuid() {
		return this.skuid;
	}

	public void setSkuid(Integer skuid) {
		this.skuid = skuid;
	}

	@ManyToOne(targetEntity = Product.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "productId")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@OneToOne()
	@JoinColumn(name = "propertyNameId")
	public PropertyName getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(PropertyName propertyName) {
		this.propertyName = propertyName;
	}

	@OneToOne()
	@JoinColumn(name = "propertyValueId")
	public PropertyValue getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(PropertyValue propertyValue) {
		this.propertyValue = propertyValue;
	}

}