package com.tenjishen.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "sysright")
public class SysRight {
	
	private Long rightId;
	private int rightType; // 权限类型，0表示菜单权限，1表示操作权限，可扩展；
	
	@JsonIgnore
	private SysMenu sysMenu;
	@JsonIgnore
	private SysOperation sysOperation;
	@JsonIgnore
	private Set<SysRole> sysRoles;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "identity")
	@GeneratedValue(generator = "idGenerator")
	public Long getRightId() {
		return rightId;
	}

	public void setRightId(Long rightId) {
		this.rightId = rightId;
	}

	@Column(name = "rightType", unique = false, nullable = true, insertable = true, updatable = true, length = 2)
	public int getRightType() {
		return rightType;
	}

	public void setRightType(int rightType) {
		this.rightType = rightType;
	}

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "sysRight")
	public SysMenu getSysMenu() {
		return sysMenu;
	}

	@JsonIgnore
	public void setSysMenu(SysMenu sysMenu) {
		this.sysMenu = sysMenu;
	}

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "sysRight")
	public SysOperation getSysOperation() {
		return sysOperation;
	}

	@JsonIgnore
	public void setSysOperation(SysOperation sysOperation) {
		this.sysOperation = sysOperation;
	}

	@JsonIgnore
	@ManyToMany(mappedBy = "sysRights", cascade = CascadeType.ALL)
	public Set<SysRole> getSysRoles() {
		return sysRoles;
	}

	public void setSysRoles(Set<SysRole> sysRoles) {
		this.sysRoles = sysRoles;
	}


}
