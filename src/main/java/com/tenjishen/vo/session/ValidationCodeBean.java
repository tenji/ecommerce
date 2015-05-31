package com.tenjishen.vo.session;

import java.util.Date;

import com.tenjishen.common.util.PropertyUtil;


/**
 * 验证码Bean，保存验证码实体信息
 * 
 * @author TENJI
 *
 */
public class ValidationCodeBean {
	private String code;
	private Date expiredTime;

	public ValidationCodeBean(String code) {
		long activeTime = Long.parseLong((String) PropertyUtil
				.get("app.validationCodeActiveTime"));
		this.code = code;
		this.expiredTime = new Date(System.currentTimeMillis() + activeTime);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}

	/**
	 * 判断验证码是否过期
	 * 
	 * @return
	 */
	public boolean isNotExpired() {
		return expiredTime.getTime() > new Date().getTime();
	}

}
