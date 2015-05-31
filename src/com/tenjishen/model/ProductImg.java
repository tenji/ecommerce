package com.tenjishen.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "t_product_img")
public class ProductImg extends BaseEntity {
	
	private static final long serialVersionUID = 1603310614344008670L;
	
	private String url; 
	private Integer position;
	private Integer isMainImg;
	
	private Product product;

	@Column(name = "url", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "position", unique = false, nullable = true, insertable = true, updatable = true, length = 5)
	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	@Column(name = "isMainImg", unique = false, nullable = true, insertable = true, updatable = true, length = 2)
	public Integer getIsMainImg() {
		return isMainImg;
	}

	public void setIsMainImg(Integer isMainImg) {
		this.isMainImg = isMainImg;
	}

	@JsonIgnore
	@ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "productId")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
