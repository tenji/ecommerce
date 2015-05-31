package com.tenjishen.model.affiliate;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tenjishen.model.BaseEntity;

/**
 * RamAccount entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_affiliate_ram_account")
public class RamAccount extends BaseEntity {

	private static final long serialVersionUID = -4153856195390523726L;

	private String email;
	private String loginUrl;
	private String loginName; // 登陆用户名
	private String loginPassword; // 登陆密码
	private Integer unansweredNums;
	private Integer pointsBeforeOpr;
	private Integer pointsAfterOpr;
	private Integer redeemStatus; // 请款状态
	private Date latestRedeemTime; // 最近请款时间
	private Date latestCheckTime;
	private Date latestOprTime;
	private String remark;
	
	private Ram ram;
	
	public RamAccount() {
		
	}
	
	public RamAccount(String loginName, String loginPassword) {
		this.loginName = loginName;
		this.loginPassword = loginPassword;
	}

	@Column(name = "email", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "loginUrl", length = 200)
	public String getLoginUrl() {
		return this.loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
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

	@Column(name = "unansweredNums")
	public Integer getUnansweredNums() {
		return unansweredNums;
	}

	public void setUnansweredNums(Integer unansweredNums) {
		this.unansweredNums = unansweredNums;
	}

	@Column(name = "pointsBeforeOpr")
	public Integer getPointsBeforeOpr() {
		return this.pointsBeforeOpr;
	}

	public void setPointsBeforeOpr(Integer pointsBeforeOpr) {
		this.pointsBeforeOpr = pointsBeforeOpr;
	}

	@Column(name = "pointsAfterOpr")
	public Integer getPointsAfterOpr() {
		return this.pointsAfterOpr;
	}

	public void setPointsAfterOpr(Integer pointsAfterOpr) {
		this.pointsAfterOpr = pointsAfterOpr;
	}

	@Column(name = "redeemStatus")
	public Integer getRedeemStatus() {
		return redeemStatus;
	}

	public void setRedeemStatus(Integer redeemStatus) {
		this.redeemStatus = redeemStatus;
	}

	@Column(name = "latestRedeemTime")
	public Date getLatestRedeemTime() {
		return latestRedeemTime;
	}

	public void setLatestRedeemTime(Date latestRedeemTime) {
		this.latestRedeemTime = latestRedeemTime;
	}

	@Column(name = "latestCheckTime", length = 0)
	public Date getLatestCheckTime() {
		return latestCheckTime;
	}

	public void setLatestCheckTime(Date latestCheckTime) {
		this.latestCheckTime = latestCheckTime;
	}

	@Column(name = "latestOprTime", length = 0)
	public Date getLatestOprTime() {
		return this.latestOprTime;
	}

	public void setLatestOprTime(Date latestOprTime) {
		this.latestOprTime = latestOprTime;
	}

	@Column(name = "remark", length = 100)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@ManyToOne(targetEntity = Ram.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "ramId")
	public Ram getRam() {
		return ram;
	}

	public void setRam(Ram ram) {
		this.ram = ram;
	}

}