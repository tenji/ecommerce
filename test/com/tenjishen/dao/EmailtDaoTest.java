package com.tenjishen.dao;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;

import com.tenjishen.BaseJUnitTest;
import com.tenjishen.model.affiliate.Ram;

public class EmailtDaoTest extends BaseJUnitTest {
	
	@Resource
	EmailDao emailDao;
	@Resource
	RamDao ramDao;
	
	@Test
	public void getUnregisteredEmailListTest() {
		Ram ram = ramDao.getById((long) 1); 
		assertEquals(null, emailDao.getUnregisteredEmailList(ram));
	}

}
