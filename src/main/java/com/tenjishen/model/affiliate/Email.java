package com.tenjishen.model.affiliate;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.tenjishen.model.BaseEntity;

/**
 * Email entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_affiliate_email")
public class Email extends BaseEntity {

	private static final long serialVersionUID = -7053068144647373686L;

	private String email;
	private String address;
	private String postalCode;
	private Date birthday;
	private String firstName;
	private String lastName;
	private String company; // Working company
	private String averageYearIncome; // 家庭年收入
	private Integer seniorityLevelId; // 资历级别编号（Ram）
	private Integer jobRoleId; // 工作角色编号（Ram）
	private Integer industryId; // 行业编号（Ram）
	
	private State state;
	private City city;

	// Constructors

	/** default constructor */
	public Email() {
	}

	/** full constructor */
	public Email(String email, Integer stateId, Integer cityId, String address,
			String postalCode, Date birthday, String firstName,
			String lastName) {
		this.email = email;
		this.address = address;
		this.postalCode = postalCode;
		this.birthday = birthday;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "Address", length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "postalCode", length = 20)
	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Column(name = "birthday", length = 0)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "firstName", length = 50)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "lastName", length = 50)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name = "company", length = 100)
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "averageYearIncome", length = 20)
	public String getAverageYearIncome() {
		return averageYearIncome;
	}

	public void setAverageYearIncome(String averageYearIncome) {
		this.averageYearIncome = averageYearIncome;
	}

	@Column(name = "seniorityLevelId")
	public Integer getSeniorityLevelId() {
		return seniorityLevelId;
	}

	public void setSeniorityLevelId(Integer seniorityLevelId) {
		this.seniorityLevelId = seniorityLevelId;
	}

	@Column(name = "jobRoleId")
	public Integer getJobRoleId() {
		return jobRoleId;
	}

	public void setJobRoleId(Integer jobRoleId) {
		this.jobRoleId = jobRoleId;
	}

	@Column(name = "industryId")
	public Integer getIndustryId() {
		return industryId;
	}

	public void setIndustryId(Integer industryId) {
		this.industryId = industryId;
	}

	@OneToOne(targetEntity = State.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "stateId")
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@OneToOne(targetEntity = City.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "cityId")
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

}