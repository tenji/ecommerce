package com.tenjishen.vo.query.affiliate;

import java.util.Date;

import com.tenjishen.model.affiliate.Ram;

/**
 * Query - Ram Account
 * 
 * @author TENJI
 * @since
 * @date 2014-9-19
 */
public class RamAccountQuery {

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
	
	private Date latestOprStartTime; // 最近操作查询起始时间
	private Date latestOprEndTime; // 最近操作查询结束时间

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public Integer getUnansweredNums() {
		return unansweredNums;
	}

	public void setUnansweredNums(Integer unansweredNums) {
		this.unansweredNums = unansweredNums;
	}

	public Integer getPointsBeforeOpr() {
		return pointsBeforeOpr;
	}

	public void setPointsBeforeOpr(Integer pointsBeforeOpr) {
		this.pointsBeforeOpr = pointsBeforeOpr;
	}

	public Integer getPointsAfterOpr() {
		return pointsAfterOpr;
	}

	public void setPointsAfterOpr(Integer pointsAfterOpr) {
		this.pointsAfterOpr = pointsAfterOpr;
	}

	public Integer getRedeemStatus() {
		return redeemStatus;
	}

	public void setRedeemStatus(Integer redeemStatus) {
		this.redeemStatus = redeemStatus;
	}

	public Date getLatestRedeemTime() {
		return latestRedeemTime;
	}

	public void setLatestRedeemTime(Date latestRedeemTime) {
		this.latestRedeemTime = latestRedeemTime;
	}

	public Date getLatestCheckTime() {
		return latestCheckTime;
	}

	public void setLatestCheckTime(Date latestCheckTime) {
		this.latestCheckTime = latestCheckTime;
	}

	public Date getLatestOprTime() {
		return latestOprTime;
	}

	public void setLatestOprTime(Date latestOprTime) {
		this.latestOprTime = latestOprTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Ram getRam() {
		return ram;
	}

	public void setRam(Ram ram) {
		this.ram = ram;
	}

	public Date getLatestOprStartTime() {
		return latestOprStartTime;
	}

	public void setLatestOprStartTime(Date latestOprStartTime) {
		this.latestOprStartTime = latestOprStartTime;
	}

	public Date getLatestOprEndTime() {
		return latestOprEndTime;
	}

	public void setLatestOprEndTime(Date latestOprEndTime) {
		this.latestOprEndTime = latestOprEndTime;
	}

}
