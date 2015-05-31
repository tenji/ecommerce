package com.tenjishen.service.log;

import java.util.Date;
import java.util.List;

import com.tenjishen.model.log.LogAdminRamAccount;
import com.tenjishen.service.BaseService;

public interface LogAdminRamAccountService extends
		BaseService<LogAdminRamAccount, Long> {

	/**
	 * 根据Ram Account ID返回最近（默认是两周）账户积分集合
	 * 
	 * @param ramAccountId
	 *            Ram Account ID
	 * @return
	 */
	public List<Integer> getPointList(Long ramAccountId);

	/**
	 * 根据Ram Account ID返回最近n周账户积分集合
	 * 
	 * @param ramAccountId
	 *            Ram Account ID
	 * @param nWeeks
	 *            多少周
	 * @return
	 */
	public List<Integer> getPointList(Long ramAccountId, Integer nWeeks);

	/**
	 * Get Ram Account Log of Specific Day by Ram Account ID
	 * 
	 * @date 2014-8-15
	 * @param date
	 *            指定日期
	 * @param ramAccountId
	 *            Ram Account ID
	 * @return
	 */
	public LogAdminRamAccount getLogByDate(Date date, Long ramAccountId);

}
