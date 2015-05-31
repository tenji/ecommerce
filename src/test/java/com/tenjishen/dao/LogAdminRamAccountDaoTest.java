package com.tenjishen.dao;

import static org.junit.Assert.*;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;

import com.tenjishen.BaseJUnitTest;

public class LogAdminRamAccountDaoTest extends BaseJUnitTest {
	
	@Resource
	LogAdminRamAccountDao logAdminRamAccountDao;
	
	@Test
	@Ignore
	public void getLogByDateTest() {
		Date date = new Date();
		assertEquals(null, logAdminRamAccountDao.getLogByDate(date, (long)1));
	}
	
	@Test
	public void getRecentLogBeforeDateTest() {
		Date date = new Date();
		System.out.println(logAdminRamAccountDao.getRecentLogBeforeDate(date, 1L).getEndTime());
		assertEquals(null, logAdminRamAccountDao.getRecentLogBeforeDate(date, (long)1));
	}

}
