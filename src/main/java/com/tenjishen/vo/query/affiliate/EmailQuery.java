package com.tenjishen.vo.query.affiliate;

import java.util.Date;

import com.tenjishen.model.affiliate.City;
import com.tenjishen.model.affiliate.State;

public class EmailQuery {

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAverageYearIncome() {
		return averageYearIncome;
	}

	public void setAverageYearIncome(String averageYearIncome) {
		this.averageYearIncome = averageYearIncome;
	}

	public Integer getSeniorityLevelId() {
		return seniorityLevelId;
	}

	public void setSeniorityLevelId(Integer seniorityLevelId) {
		this.seniorityLevelId = seniorityLevelId;
	}

	public Integer getJobRoleId() {
		return jobRoleId;
	}

	public void setJobRoleId(Integer jobRoleId) {
		this.jobRoleId = jobRoleId;
	}

	public Integer getIndustryId() {
		return industryId;
	}

	public void setIndustryId(Integer industryId) {
		this.industryId = industryId;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

}
