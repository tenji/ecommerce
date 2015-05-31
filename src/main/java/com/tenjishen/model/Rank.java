package com.tenjishen.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * TRank entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_rank", catalog = "aca")
public class Rank implements java.io.Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6966960050682547358L;
	// Fields
	
	private Long rankId;
	private String name;
	private String description;
	private Long lowestGrowthValue;
	private Integer validityPeriod;
	private String remark;

	// Property accessors
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "identity")
	@GeneratedValue(generator = "idGenerator")
	public Long getRankId() {
		return this.rankId;
	}

	public void setRankId(Long rankId) {
		this.rankId = rankId;
	}

	@Column(name = "NAME", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DESCRIPTION", length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "LOWESTGROWTHVALUE")
	public Long getLowestGrowthValue() {
		return this.lowestGrowthValue;
	}

	public void setLowestGrowthValue(Long lowestGrowthValue) {
		this.lowestGrowthValue = lowestGrowthValue;
	}

	@Column(name = "VALIDITYPERIOD")
	public Integer getValidityPeriod() {
		return this.validityPeriod;
	}

	public void setValidityPeriod(Integer validityPeriod) {
		this.validityPeriod = validityPeriod;
	}

	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}