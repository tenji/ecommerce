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
@Table(name = "sysgroup")
public class SysGroup extends BaseEntity {

	private static final long serialVersionUID = 3078659808145683403L;
	
	public static final String PATH_SEPARATOR = ","; // 树路径分隔符
	
	private String name; // 组名称
	private String description; // 组描述
	private String path;
	private String pathName;
	private Integer orderList;
	private Integer status; // 是否启用
	private Integer deletable; // 是否可删除
	
	@JsonIgnore
	private SysGroup parent; // 父用户组
	@JsonIgnore
	private Set<SysGroup> children; // 子用户组
	@JsonIgnore
	private Set<SysUser> sysUsers;
	
	public SysGroup() {
		super();
	}
	
	public SysGroup(Long id) {
		super(id);
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 100)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "path", length = 65535)
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "pathName", length = 65535)
	public String getPathName() {
		return this.pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

	@Column(name = "orderList")
	public Integer getOrderList() {
		return this.orderList;
	}

	public void setOrderList(Integer orderList) {
		this.orderList = orderList;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "deletable")
	public Integer getDeletable() {
		return deletable;
	}

	public void setDeletable(Integer deletable) {
		this.deletable = deletable;
	}

	@JsonIgnore
	@ManyToOne(targetEntity = SysGroup.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "parentId")
	public SysGroup getParent() {
		return parent;
	}

	public void setParent(SysGroup parent) {
		this.parent = parent;
	}

	@JsonIgnore
	@OneToMany(targetEntity = SysGroup.class, cascade = CascadeType.ALL, mappedBy = "parent", fetch = FetchType.LAZY)
	public Set<SysGroup> getChildren() {
		return children;
	}

	public void setChildren(Set<SysGroup> children) {
		this.children = children;
	}

	@JsonIgnore
	@ManyToMany(mappedBy = "sysGroups")
	public Set<SysUser> getSysUsers() {
		return sysUsers;
	}

	public void setSysUsers(Set<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}
	
	// 获取分类层级（顶级分类：0）
	@Transient
	@JsonIgnore
	public Integer getLevel() {
		return path.split(PATH_SEPARATOR).length - 1;
	}

}