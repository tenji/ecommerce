package com.tenjishen.service.log.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenjishen.common.util.DateUtil;
import com.tenjishen.dao.LogAdminRamAccountDao;
import com.tenjishen.dao.RamAccountDao;
import com.tenjishen.model.log.LogAdminRamAccount;
import com.tenjishen.service.BaseServiceImpl;
import com.tenjishen.service.log.LogAdminRamAccountService;

@Service
@Transactional
public class LogAdminRamAccountServiceImpl extends BaseServiceImpl<LogAdminRamAccount, Long> implements LogAdminRamAccountService {
	@Resource
	private LogAdminRamAccountDao logAdminRamAccountDao;
	@Resource
	private RamAccountDao ramAccountDao;

	@Resource
	public void setBaseDao(LogAdminRamAccountDao logAdminRamAccountDao) {
		super.setBaseDao(logAdminRamAccountDao);
	}
	
	@Override
	public List<Integer> getPointList(Long ramAccountId) {
		return getPointList(ramAccountId, 2); // 默认返回最近两周账户集合
	}

	@Override
	public List<Integer> getPointList(Long ramAccountId, Integer nWeeks) {
		/*
		 * 处理思路：
		 * 1. 获取起始日期积分。起始日期之前如果有操作日志，那么日志中的积分就是起始日期积分。
		 * 2. 组合积分记录索引集合。获取最近N周操作日志，遍历操作日志，根据日志时间获取生成
		 * 账户积分集合的索引，并和此操作日志的积分组合成Map集合。
		 * 3. 生成账户积分集合。遍历最近N周，如果某天有操作日志，则积分为此日志的积分。否则的
		 * 话，积分和昨天的积分相等。
		 * 4. 将此账户积分集合传回前台，由HightCharts插件生成积分变化趋势表。
		 * 
		 */
		// 1. 获取起始日期积，分并将账户积分集合初始化为起始日期积分
		Date beginDate = DateUtil.getDateBefore(new Date(), nWeeks * 7);
		LogAdminRamAccount recentLog = logAdminRamAccountDao.getRecentLogBeforeDate(beginDate, ramAccountId);
		int recentPoint;
		if (null == recentLog) {
			recentPoint = 0;
		} else 
			recentPoint = recentLog.getPoints();
		
		List<LogAdminRamAccount> logAdminRamAccounts = logAdminRamAccountDao.getRamAccountList(ramAccountId, nWeeks);
		
		List<Integer> pointList = new ArrayList<Integer>(nWeeks * 7); // 账户积分集合
		// 积分集合初始化为起始日期积分
		for (int i = 0; i < nWeeks * 7; i++) {
			pointList.add(recentPoint);
		}
		
		// 2. 组合积分记录索引集合
		Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>(); //  积分记录索引集合
		Calendar calendar = Calendar.getInstance();
		for (LogAdminRamAccount logAdminRamAccount : logAdminRamAccounts) {
			int index = (nWeeks * 7 - 1) - DateUtil.daysBetween(logAdminRamAccount.getEndTime(), calendar.getTime());
			indexMap.put(index, logAdminRamAccount.getPoints());
		}
		
		// 3. 生成账户积分集合
		if (pointList.size() > 0) { // 在最近N周内有积分变更记录
			for (int i = 0; i <= nWeeks * 7 - 1; i++) {
				// 若当天有积分变更记录，则更新当天积分；若当天没有变更记录，则当天积分等于明天的积分；
				if (indexMap.containsKey(i)) {
					pointList.set(i, indexMap.get(i));
					recentPoint = indexMap.get(i);
				} else {
					pointList.set(i, recentPoint);
				}
			}
		}
		
		return pointList;
	}

	@Override
	public LogAdminRamAccount getLogByDate(Date date, Long ramAccountId) {
		return logAdminRamAccountDao.getLogByDate(date, ramAccountId);
	}

}
