package com.tenjishen.model.affiliate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;


/**
 * City entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_affiliate_city")
public class City implements java.io.Serializable {

	private static final long serialVersionUID = -1702571632667371621L;

	private Long id;
	private String name;
	private String chineseName;
	private String remark;
	
	@JsonIgnore
	private State state;

	// Constructors

	/** default constructor */
	public City() {
	}

	/** full constructor */
	public City(Integer stateId, String name, String chineseName, String remark) {
		this.name = name;
		this.chineseName = chineseName;
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

	@Column(name = "remark", length = 1000)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@JsonIgnore
	@ManyToOne(targetEntity = State.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "stateId")
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

}