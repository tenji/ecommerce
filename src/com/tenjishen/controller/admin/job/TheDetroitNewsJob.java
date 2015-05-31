package com.tenjishen.controller.admin.job;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

import com.tenjishen.common.util.DateUtil;
import com.tenjishen.model.affiliate.RamAccount;
import com.tenjishen.service.affiliate.RamAccountService;
import com.tenjishen.service.log.LogAdminRamAccountService;

/**
 * Job - Batch answer surveys of The Detroit News on specific days
 * 
 * @author TENJI
 * @since
 * @date 2014-8-15
 */
public class TheDetroitNewsJob implements Job {
	
	Logger logger = Logger.getLogger(TheDetroitNewsJob.class);
	@Resource
	private RamAccountService ramAccountService;
	@Resource
	private LogAdminRamAccountService logAdminRamAccountService;

	@Override
	public void execute(JobExecutionContext jec) throws JobExecutionException {
		logger.error("---------- The Detroit News Timer Task ----------");
		
		ApplicationContext ctx = (ApplicationContext) jec.getJobDetail()
				.getJobDataMap().get("applicationContext");
		if (null == ramAccountService) {
			ramAccountService = ctx.getBean(RamAccountService.class);
		}
		if (null == logAdminRamAccountService) {
			logAdminRamAccountService = ctx.getBean(LogAdminRamAccountService.class);
		}
		// ramId of the Detroit Free Press is 6
		List<RamAccount> ramAccounts = ramAccountService.getListByPropertyName("ram.id", (long) 6);
		Collections.shuffle(ramAccounts); // Randomly permutes the specified 'Ram Account' list
		
		for (RamAccount ramAccount : ramAccounts) {
			if (null != logAdminRamAccountService.getLogByDate(new Date(), ramAccount.getId()) ||
					null != logAdminRamAccountService.getLogByDate(DateUtil.getYesterday(), ramAccount.getId())) {
				// If this account was operated today or yesterday, just continue!
				break ;
			}
			try {
				ramAccount = ramAccountService.checkSurveyNums(ramAccount); // check before answer the survey

				if (0 < ramAccount.getUnansweredNums()) {
					ramAccount = ramAccountService.answerSurvey(ramAccount);

					Thread.sleep(new Random().nextInt(300000)); // 每次点击随机暂停300秒以内
				}
			} catch (Exception e) {
				continue ; // ignore all the exceptions
			}
		}
		
	}

}
