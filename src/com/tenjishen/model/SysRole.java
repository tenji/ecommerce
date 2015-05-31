package com.tenjishen.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "sysrole")
public class SysRole extends BaseEntity {
	
	private static final long serialVersionUID = -2140330641037845745L;

	private String roleName;
	private String description;
	private Integer roleLevel;
	private Integer deletable;
	
	@JsonIgnore
	private Set<SysUser> sysUsers;
	@JsonIgnore
	private Set<SysRight> sysRights;

	@Column(name = "roleName", unique = false, nullable = true, insertable = true, updatable = true, length = 4000)
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "description", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "roleLevel", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public Integer getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(Integer roleLevel) {
		this.roleLevel = roleLevel;
	}

	@Column(name = "deletable", unique = false, nullable = true, insertable = true, updatable = true, length = 2)
	public Integer getDeletable() {
		return deletable;
	}

	public void setDeletable(Integer deletable) {
		this.deletable = deletable;
	}

	@JsonIgnore
	@ManyToMany(mappedBy = "sysRoles")
	public Set<SysUser> getSysUsers() {
		return sysUsers;
	}

	public void setSysUsers(Set<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "sysrole_right",
	joinColumns = @JoinColumn(name = "roleId"),
	inverseJoinColumns = @JoinColumn(name = "rightId"))
	public Set<SysRight> getSysRights() {
		return sysRights;
	}

	public void setSysRights(Set<SysRight> sysRights) {
		this.sysRights = sysRights;
	}

}
