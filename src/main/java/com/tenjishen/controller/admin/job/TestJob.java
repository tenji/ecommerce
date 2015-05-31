package com.tenjishen.controller.admin.job;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Job - Job for testing
 * 
 * @author TENJI
 * @since
 * @date 2014-8-20
 */
public class TestJob implements Job {
	
	Logger logger = Logger.getLogger(TestJob.class);

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		logger.info("---------- The Test Timer Task ----------");
		
	}

}
