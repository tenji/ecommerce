package com.tenjishen.model.affiliate;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;


/**
 * State entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_affiliate_state")
public class State implements java.io.Serializable {

	private static final long serialVersionUID = -2811831047769335433L;

	private Long id;
	private String name;
	private String chineseName;
	private String abbr;
	private String remark;
	
	@JsonIgnore
	private Set<City> cities;

	// Constructors

	/** default constructor */
	public State() {
	}

	/** full constructor */
	public State(String name, String chineseName, String abbr, String remark) {
		this.name = name;
		this.chineseName = chineseName;
		this.abbr = abbr;
		this.remark = remark;
	}

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "identity")
	@GeneratedValue(generator = "idGenerator")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "chineseName", length = 50)
	public String getChineseName() {
		return this.chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	@Column(name = "abbr", length = 20)
	public String getAbbr() {
		return this.abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	@Column(name = "remark", length = 1000)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@JsonIgnore
	@OneToMany(targetEntity = City.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "stateId")
	public Set<City> getCities() {
		return cities;
	}

	public void setCities(Set<City> cities) {
		this.cities = cities;
	}

}