package com.tenjishen.dao;

import java.util.Date;
import java.util.List;

import com.tenjishen.model.log.LogAdminRamAccount;

public interface LogAdminRamAccountDao extends
		BaseDao<LogAdminRamAccount, Long> {

	/**
	 * Get Ram Account Log List by Ram Account ID
	 * 
	 * @param ramAccountId
	 *            Ram Account ID
	 * @return
	 */
	public List<LogAdminRamAccount> getRamAccountList(Long ramAccountId);

	/**
	 * Get Ram Account Log List by Ram Account ID
	 * 
	 * @param ramAccountId
	 *            Ram Account ID
	 * @param nWeeks
	 *            how many weeks?
	 * @return
	 */
	public List<LogAdminRamAccount> getRamAccountList(Long ramAccountId,
			Integer nWeeks);

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
	
	/**
	 * Get Ram Account Log before Specific Day by Ram Account ID
	 * 
	 * @date 2014-10-20
	 * @param date 指定日期
	 * @param ramAccountId Ram Account ID
	 * @return
	 */
	public LogAdminRamAccount getRecentLogBeforeDate(Date date, Long ramAccountId);

}
