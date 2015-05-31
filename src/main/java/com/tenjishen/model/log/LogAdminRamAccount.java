package com.tenjishen.model.log;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenjishen.model.affiliate.RamAccount;

@Entity
@Table(name = "t_log_admin_ram_account")
public class LogAdminRamAccount implements java.io.Serializable {
	
	private static final long serialVersionUID = 772127553805905998L;
	
	private Long id;
	private Integer points;
	private Date beginTime;
	private Date endTime;
	
	private RamAccount ramAccount;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "identity")
	@GeneratedValue(generator = "idGenerator")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "points")
	public Integer getPoints() {
		return this.points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	@Column(name = "beginTime", length = 0)
	public Date getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	@Column(name = "endTime", length = 0)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@OneToOne()
	@JoinColumn(name = "ramAccountId")
	public RamAccount getRamAccount() {
		return ramAccount;
	}

	public void setRamAccount(RamAccount ramAccount) {
		this.ramAccount = ramAccount;
	}

}