package com.tenjishen.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "sysuser")
public class SysUser extends BaseEntity {
	
	private static final long serialVersionUID = 1652204237595634588L;

	private String loginName;
	private String loginPassword;
	private String firstName;
	private String lastName;
	private Integer sex;
	private java.util.Date birthday;
	private String phone;
	private String email;
	private String website;
	private java.util.Date lastLoginTime;
	private String lastLoginIp;
	private Integer isActivated;
	
	@JsonIgnore
	private Set<SysRole> sysRoles;
	private Set<SysGroup> sysGroups;
	
	public SysUser() {
		super();
	}
	
	public SysUser(Long id) {
		super(id);
	}

	@Column(name = "loginName", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "loginPassword", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	@Column(name = "firstName", length = 50)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "lastName", length = 50)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "sex", length = 2)
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@Column(name = "birthday")
	public java.util.Date getBirthday() {
		return birthday;
	}

	public void setBirthday(java.util.Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "phone", length = 20)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "email", length = 50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "website", length = 100)
	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Column(name = "lastLoginTime", unique = false, nullable = true, insertable = true, updatable = true)
	public java.util.Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(java.util.Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Column(name = "lastLoginIp", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	@Column(name = "isActivated", unique = false, nullable = true, insertable = true, updatable = true, length = 2)
	public Integer getIsActivated() {
		return isActivated;
	}

	public void setIsActivated(Integer isActivated) {
		this.isActivated = isActivated;
	}

	@JsonIgnore
	@ManyToMany()
	@JoinTable(name = "sysuser_role",
	joinColumns = @JoinColumn(name = "userId"),
	inverseJoinColumns = @JoinColumn(name = "roleId"))
	public Set<SysRole> getSysRoles() {
		return sysRoles;
	}

	public void setSysRoles(Set<SysRole> sysRoles) {
		this.sysRoles = sysRoles;
	}

	@ManyToMany()
	@JoinTable(name = "sysuser_group",
	joinColumns = @JoinColumn(name = "userId"),
	inverseJoinColumns = @JoinColumn(name = "groupId"))
	public Set<SysGroup> getSysGroups() {
		return sysGroups;
	}

	public void setSysGroups(Set<SysGroup> sysGroups) {
		this.sysGroups = sysGroups;
	}

}
