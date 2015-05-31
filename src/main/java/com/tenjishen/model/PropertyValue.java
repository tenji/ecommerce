package com.tenjishen.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * PropertyValue entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_property_value")
public class PropertyValue extends BaseEntity {

	private static final long serialVersionUID = -954067803229947967L;
	
	private String name;
	private Integer status;
	private Integer orderList;
	
	private PropertyName propertyName;
	private Category category;

	// Constructors

	/** default constructor */
	public PropertyValue() {
	}

	/** full constructor */
	public PropertyValue(String name, Integer status,
			Integer orderList) {
		this.name = name;
		this.status = status;
		this.orderList = orderList;
	}

	@Column(name = "name", length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "orderList")
	public Integer getOrderList() {
		return this.orderList;
	}

	public void setOrderList(Integer orderList) {
		this.orderList = orderList;
	}

	@ManyToOne(targetEntity = PropertyName.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "propertyNameId")
	public PropertyName getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(PropertyName propertyName) {
		this.propertyName = propertyName;
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