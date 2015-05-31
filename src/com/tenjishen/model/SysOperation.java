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
@Table(name = "sysoperation")
public class SysOperation extends BaseEntity {
	
	private static final long serialVersionUID = 265739862271015794L;
	
	public static final String PATH_SEPARATOR = ","; // 树路径分隔符
	
	private String operationName;
	private String operationCode;
	private String url;
	private Integer isTop;
	private String path; // 树路径
	private Integer orderList; // 排序号
	
	@JsonIgnore
	private SysOperation parent; // 父菜单
	@JsonIgnore
	private Set<SysOperation> children; // 子菜单
	@JsonIgnore
	private SysRight sysRight;

	@Column(name = "operationName", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	@Column(name = "operationCode", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}

	@Column(name = "url", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	@Column(name = "isTop", unique = false, nullable = true, insertable = true, updatable = true, length = 2)
	public Integer getIsTop() {
		return isTop;
	}

	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

	@JsonIgnore
	@ManyToOne(targetEntity = SysOperation.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "parentId")
	public SysOperation getParent() {
		return parent;
	}

	public void setParent(SysOperation parent) {
		this.parent = parent;
	}

	@JsonIgnore
	@OneToMany(targetEntity = SysOperation.class, cascade = CascadeType.ALL, mappedBy = "parent", fetch = FetchType.LAZY)
	public Set<SysOperation> getChildren() {
		return children;
	}

	public void setChildren(Set<SysOperation> children) {
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
