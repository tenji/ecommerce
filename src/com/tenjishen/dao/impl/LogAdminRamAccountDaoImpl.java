package com.tenjishen.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.tenjishen.common.util.DateUtil;
import com.tenjishen.dao.LogAdminRamAccountDao;
import com.tenjishen.model.log.LogAdminRamAccount;

@Repository
public class LogAdminRamAccountDaoImpl extends BaseDaoImpl<LogAdminRamAccount, Long> implements LogAdminRamAccountDao {

	@Override
	public List<LogAdminRamAccount> getRamAccountList(Long ramAccountId) {
		return getRamAccountList(ramAccountId, 2);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LogAdminRamAccount> getRamAccountList(Long ramAccountId, Integer nWeeks) {
		Date endDate = new Date();
		Date beginDate = DateUtil.getDateBefore(endDate, nWeeks * 7);
		
		String hql = "FROM LogAdminRamAccount logAdminRamAccount WHERE " +
				"logAdminRamAccount.ramAccount.id = ? AND logAdminRamAccount.endTime > ? AND " +
				"logAdminRamAccount.endTime < ? ORDER BY logAdminRamAccount.endTime ASC";
		
		return getSession().createQuery(hql).setParameter(0, ramAccountId).setParameter(1, beginDate).
				setParameter(2, endDate).list();
	}

	@Override
	public LogAdminRamAccount getLogByDate(Date date, Long ramAccountId) {
		String dateStr = DateUtil.dateToStr(date, "yyyy-MM-dd");
		
		// 1. hql for MySQL
		String hqlForMySql = "FROM LogAdminRamAccount logAdminRamAccount WHERE " +
				"logAdminRamAccount.ramAccount.id = ? AND DATE_FORMAT(logAdminRamAccount.endTime, '%Y-%m-%d') = ?";
		
		// 2. hql for Oralce
		/*
		String hqlForOracle = "FROM LogAdminRamAccount logAdminRamAccount WHERE " +
				"logAdminRamAccount.ramAccount.id = ? AND TO_CHAR(logAdminRamAccount.endTime, 'yyyy-MM-dd') = ?";
		*/
		
		return (LogAdminRamAccount) getSession().createQuery(hqlForMySql).setParameter(0, ramAccountId).
				setParameter(1, dateStr).uniqueResult();
	}

	@Override
	public LogAdminRamAccount getRecentLogBeforeDate(Date date,
			Long ramAccountId) {
		String dateStr = DateUtil.dateToStr(date, "yyyy-MM-dd");
		
		// 1. hql for MySQL
		String hqlForMySql = "FROM LogAdminRamAccount logAdminRamAccount WHERE " +
				"logAdminRamAccount.ramAccount.id = ? AND DATE_FORMAT(logAdminRamAccount.endTime, '%Y-%m-%d') < ? " +
				"ORDER BY logAdminRamAccount.endTime DESC";
		
		return (LogAdminRamAccount) getSession().createQuery(hqlForMySql).setParameter(0, ramAccountId).
				setParameter(1, dateStr).setMaxResults(1).uniqueResult();
	}

}
