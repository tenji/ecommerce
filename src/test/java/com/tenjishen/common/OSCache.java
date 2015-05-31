package com.tenjishen.common;

import java.util.Vector;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Ignore;
import org.junit.Test;

import com.opensymphony.oscache.general.GeneralCacheAdministrator;

/**
 * OSCache性能测试
 * 
 * @author TENJI
 * 
 */
public class OSCache {
	/**
	 * 测试方法：
	 * 1. 在cache中存放了20万个简单的Form对象
	 * 2. 从cache中取出任意2000个对象
	 * 3. 从cache中移除任意2000个对象
	 */
	GeneralCacheAdministrator cache = new GeneralCacheAdministrator();
	Vector keys = new Vector();

	@Test
	@Ignore
	public void load() {
		int LEN = 200 * 1000;

		for (int i = 0; i < LEN; i++) {
			DataForm form = new DataForm();
			form.setKey(RandomStringUtils.randomAlphanumeric(20));
			form.setName(i + "kkkk");
			cache.putInCache(form.getKey(), form);
			if (i % 100 == 0) {
				keys.add(form.getKey());
			}
		}
	}

	@Test
	@Ignore
	public void testFetchTime() {
		long beginTime = System.currentTimeMillis();
		for (int i = 0; i < keys.size(); i++) {
			long startTime = System.currentTimeMillis();
			String key = (String) keys.get(i);
			DataForm form = null;
			try {
				form = (DataForm) cache.getFromCache(key);
			} catch (Exception e) {

			}
			System.out.println("**" + i + ":"
					+ (System.currentTimeMillis() - startTime) + ","
					+ form.getName());
		}

		System.out.println("serachSumTime="
				+ (System.currentTimeMillis() - beginTime));

		beginTime = System.currentTimeMillis();

		for (int i = 0; i < keys.size(); i++) {

			String key = (String) keys.get(i);
			try {
				cache.removeEntry(key);
			} catch (Exception e) {

			}
		}

		System.out.println("removeSumTime="
				+ (System.currentTimeMillis() - beginTime));
	}
	
	@Test
	public void test() {
		long beginTime = System.currentTimeMillis();
		this.load();
		this.testFetchTime();
		System.out.println("sumTime="
				+ (System.currentTimeMillis() - beginTime));
	}

}

class DataForm {
	private String name = "";
	private String value = "";
	private String key = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
