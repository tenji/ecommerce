package com.tenjishen.model.affiliate;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tenjishen.model.BaseEntity;

@Entity
@Table(name = "t_affiliate_i_say_account")
public class ISayAccount extends BaseEntity {

	private static final long serialVersionUID = 1292959045202388436L;
	
	private String loginName;
	private String loginPassword;
	private Integer points;
	private Date latestRedeemTime;
	private Date latestOprTime;
	
	private Email email; // Email账号实体

	// Constructors

	/** default constructor */
	public ISayAccount() {
	}

	/** full constructor */
	public ISayAccount(Integer emailId, String loginName,
			String loginPassword, Integer points, Date latestRedeemTime,
			Date latestOprTime) {
		this.loginName = loginName;
		this.loginPassword = loginPassword;
		this.points = points;
		this.latestRedeemTime = latestRedeemTime;
		this.latestOprTime = latestOprTime;
	}

	@Column(name = "loginName", length = 50)
	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "loginPassword", length = 50)
	public String getLoginPassword() {
		return this.loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	@Column(name = "points")
	public Integer getPoints() {
		return this.points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	@Column(name = "latestRedeemTime", length = 0)
	public Date getLatestRedeemTime() {
		return this.latestRedeemTime;
	}

	public void setLatestRedeemTime(Date latestRedeemTime) {
		this.latestRedeemTime = latestRedeemTime;
	}

	@Column(name = "latestOprTime", length = 0)
	public Date getLatestOprTime() {
		return this.latestOprTime;
	}

	public void setLatestOprTime(Date latestOprTime) {
		this.latestOprTime = latestOprTime;
	}

	@ManyToOne(targetEntity = Email.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "emailId")
	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

}