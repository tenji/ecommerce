package com.tenjishen.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

/**
 * TEducationInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_education_info", catalog = "aca")
public class EducationInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1143219482145249374L;
	// Fields

	private Integer id;
	private Integer schoolId;
	private Timestamp enrollmentTime;
	private String remark;
	
	@JsonIgnore
	private Member member;
	@JsonIgnore
	private School school;

	// Property accessors
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "identity")
	@GeneratedValue(generator = "idGenerator")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "SCHOOLID")
	public Integer getSchoolId() {
		return this.schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	@Column(name = "ENROLLMENTTIME", length = 0)
	public Timestamp getEnrollmentTime() {
		return this.enrollmentTime;
	}

	public void setEnrollmentTime(Timestamp enrollmentTime) {
		this.enrollmentTime = enrollmentTime;
	}

	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@JsonIgnore
	@ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "memberId")
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@JsonIgnore
	@OneToOne(targetEntity = School.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "schoolId")
	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

}