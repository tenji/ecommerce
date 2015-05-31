package com.tenjishen.model.affiliate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tenjishen.model.BaseEntity;

/**
 * Ram entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_affiliate_ram")
public class Ram extends BaseEntity {

	private static final long serialVersionUID = 1211085559903250671L;

	private String name;
	private String officialWebsiteUrl;
	private String ramRegisterUrl;
	private String ramRegisterCode;
	private String rewardDetail;
	private Integer rewardsRate; // 调查奖品评级；最高5级；最低0级；
	private Integer surveysRate; // 调查数量评级；最高5级；最低0级；
	private Integer avgRate; // 综合评级
	private String country;
	private String state;
	private String city;
	private String remark;

	@Column(name = "name", length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "officialWebsiteUrl", length = 100)
	public String getOfficialWebsiteUrl() {
		return officialWebsiteUrl;
	}

	public void setOfficialWebsiteUrl(String officialWebsiteUrl) {
		this.officialWebsiteUrl = officialWebsiteUrl;
	}

	@Column(name = "ramRegisterUrl", length = 100)
	public String getRamRegisterUrl() {
		return ramRegisterUrl;
	}

	public void setRamRegisterUrl(String ramRegisterUrl) {
		this.ramRegisterUrl = ramRegisterUrl;
	}

	@Column(name = "ramRegisterCode", length = 10)
	public String getRamRegisterCode() {
		return ramRegisterCode;
	}

	public void setRamRegisterCode(String ramRegisterCode) {
		this.ramRegisterCode = ramRegisterCode;
	}

	@Column(name = "rewardDetail", length = 1000)
	public String getRewardDetail() {
		return rewardDetail;
	}

	public void setRewardDetail(String rewardDetail) {
		this.rewardDetail = rewardDetail;
	}

	@Column(name = "rewardsRate", length = 5)
	public Integer getRewardsRate() {
		return rewardsRate;
	}

	public void setRewardsRate(Integer rewardsRate) {
		this.rewardsRate = rewardsRate;
	}

	public Integer getSurveysRate() {
		return surveysRate;
	}

	@Column(name = "surveysRate", length = 5)
	public void setSurveysRate(Integer surveysRate) {
		this.surveysRate = surveysRate;
	}

	@Column(name = "avgRate", length = 5)
	public Integer getAvgRate() {
		return avgRate;
	}

	public void setAvgRate(Integer avgRate) {
		this.avgRate = avgRate;
	}

	@Column(name = "country", length = 50)
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "state", length = 50)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "city", length = 50)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "remark", length = 500)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}