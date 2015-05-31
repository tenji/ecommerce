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
@Table(name = "t_affiliate_evans_data_account")
public class EvansDataAccount extends BaseEntity {

	private static final long serialVersionUID = -6559269815977534274L;

	// Fields
	private String displayName;
	private String password;
	private Integer availablePoints;
	private Integer pendingPoints;
	private Date latestRedeemTime; // 最近请款时间
	private Date latestOprTime; // 最近操作时间

	private Email email; // Email账号实体

	// Constructors

	/** default constructor */
	public EvansDataAccount() {
	}

	/** full constructor */
	public EvansDataAccount(String displayName, String password,
			Integer availablePoints, Integer pendingPoints) {
		this.displayName = displayName;
		this.password = password;
		this.availablePoints = availablePoints;
		this.pendingPoints = pendingPoints;
	}

	@Column(name = "displayName", length = 50)
	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Column(name = "password", length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "availablePoints")
	public Integer getAvailablePoints() {
		return this.availablePoints;
	}

	public void setAvailablePoints(Integer availablePoints) {
		this.availablePoints = availablePoints;
	}

	@Column(name = "pendingPoints")
	public Integer getPendingPoints() {
		return this.pendingPoints;
	}

	public void setPendingPoints(Integer pendingPoints) {
		this.pendingPoints = pendingPoints;
	}

	@Column(name = "latestRedeemTime")
	public Date getLatestRedeemTime() {
		return latestRedeemTime;
	}

	public void setLatestRedeemTime(Date latestRedeemTime) {
		this.latestRedeemTime = latestRedeemTime;
	}

	@Column(name = "latestOprTime", length = 0)
	public Date getLatestOprTime() {
		return latestOprTime;
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