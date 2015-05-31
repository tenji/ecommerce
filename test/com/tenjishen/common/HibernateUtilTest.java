package com.tenjishen.common;

import org.junit.Test;

import com.tenjishen.common.util.HibernateUtil;

/**
 * HibernateUtil测试
 * 
 * @author TENJI
 * 
 */
public class HibernateUtilTest {
	
	@Test
	public void generateCountHqlTest() {
		HibernateUtil.generateCountHql("SELECT　* FROM RamAccount");
	}

}
