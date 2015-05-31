package com.tenjishen.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

/**
 * TMember entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_member", catalog = "aca")
public class Member implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6730152671297541359L;
	// Fields
	private Long memberId;
	private String name;
	private String memberName;
	private String password;
	private String nickName;
	private Integer sex;
	private String cellPhone;
	private String email;
	private String address;
	private Date birthday;
	private String IdCardNo;
	private Integer maritalStatus;
	private Double income;
	private String hobbies;
	private Long growthValue;
	private Double accountBalance;
	private Long score;
	private Date createDate;
	private String registerIp;
	private String lastloginIp;
	private String paymentPassword;
	private Integer emailVerification;
	private Integer phoneVerification;
	private String digitalCertificate;
	private Integer status;
	
	@JsonIgnore
	private Area area;
	@JsonIgnore
	private Set<DeliveryAddress> deliveryAddresses;
	@JsonIgnore
	private Rank rank;
	@JsonIgnore
	private Set<EducationInfo> educationInfos;
	@JsonIgnore
	private Set<OccupationInfo> occupationInfos;

	// Property accessors
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "identity")
	@GeneratedValue(generator = "idGenerator")
	public Long getMemberId() {
		return this.memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	@Column(name = "NAME", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "MEMBERNAME", length = 50)
	public String getMemberName() {
		return this.memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	@Column(name = "PASSWORD", length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "NICKNAME", length = 50)
	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Column(name = "SEX")
	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@Column(name = "CELLPHONE", length = 12)
	public String getCellPhone() {
		return this.cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	@Column(name = "EMAIL", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "ADDRESS", length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "BIRTHDAY", length = 0)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "IDCARDNO", length = 50)
	public String getIdCardNo() {
		return IdCardNo;
	}

	public void setIdCardNo(String IdCardNo) {
		this.IdCardNo = IdCardNo;
	}

	@Column(name = "MARITALSTATUS")
	public Integer getMaritalStatus() {
		return this.maritalStatus;
	}

	public void setMaritalStatus(Integer maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	@Column(name = "INCOME", precision = 22, scale = 0)
	public Double getIncome() {
		return this.income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	@Column(name = "HOBBIES", length = 500)
	public String getHobbies() {
		return this.hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	@Column(name = "GROWTHVALUE")
	public Long getGrowthValue() {
		return this.growthValue;
	}

	public void setGrowthValue(Long growthValue) {
		this.growthValue = growthValue;
	}

	@Column(name = "ACCOUNTBALANCE", precision = 22, scale = 0)
	public Double getAccountBalance() {
		return this.accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}

	@Column(name = "SCORE")
	public Long getScore() {
		return this.score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	@Column(name = "CREATEDATE", length = 0)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "REGISTERIP", length = 50)
	public String getRegisterIp() {
		return this.registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

	@Column(name = "LASTLOGINIP", length = 50)
	public String getLastloginIp() {
		return this.lastloginIp;
	}

	public void setLastloginIp(String lastloginIp) {
		this.lastloginIp = lastloginIp;
	}

	@Column(name = "paymentPassword", length = 50)
	public String getPaymentPassword() {
		return paymentPassword;
	}

	public void setPaymentPassword(String paymentPassword) {
		this.paymentPassword = paymentPassword;
	}

	@Column(name = "emailVerification", length = 2)
	public Integer getEmailVerification() {
		return emailVerification;
	}

	public void setEmailVerification(Integer emailVerification) {
		this.emailVerification = emailVerification;
	}

	@Column(name = "phoneVerification", length = 2)
	public Integer getPhoneVerification() {
		return phoneVerification;
	}

	public void setPhoneVerification(Integer phoneVerification) {
		this.phoneVerification = phoneVerification;
	}

	@Column(name = "digitalCertificate", length = 200)
	public String getDigitalCertificate() {
		return digitalCertificate;
	}

	public void setDigitalCertificate(String digitalCertificate) {
		this.digitalCertificate = digitalCertificate;
	}

	@Column(name = "status", length = 2)
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@JsonIgnore
	@OneToOne(targetEntity = Area.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "areaId")
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@JsonIgnore
	@OneToMany(targetEntity = DeliveryAddress.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "memberId")
	public Set<DeliveryAddress> getDeliveryAddresses() {
		return deliveryAddresses;
	}

	public void setDeliveryAddresses(Set<DeliveryAddress> deliveryAddresses) {
		this.deliveryAddresses = deliveryAddresses;
	}

	@JsonIgnore
	@OneToOne(targetEntity = Rank.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "rankId")
	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	@JsonIgnore
	@OneToMany(targetEntity = EducationInfo.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "memberId")
	public Set<EducationInfo> getEducationInfos() {
		return educationInfos;
	}

	public void setEducationInfos(Set<EducationInfo> educationInfos) {
		this.educationInfos = educationInfos;
	}

	@JsonIgnore
	@OneToMany(targetEntity = OccupationInfo.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "memberId")
	public Set<OccupationInfo> getOccupationInfos() {
		return occupationInfos;
	}

	public void setOccupationInfos(Set<OccupationInfo> occupationInfos) {
		this.occupationInfos = occupationInfos;
	}
	

}