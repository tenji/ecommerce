package com.tenjishen.common.util.oscache;

import javax.servlet.ServletContext;

import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;
import com.opensymphony.oscache.web.ServletCacheAdministrator;

/*******************************************************************************
 * OScahe 缓存服务类
 * 
 * @author zengxiangtao
 ******************************************************************************/
public class CacheUtils {

	// 缓存源
	private static final GeneralCacheAdministrator cache = new GeneralCacheAdministrator();

	// 不允许实例化
	private CacheUtils() {

	}

	/**
	 * 缓存数据
	 * 
	 * @param key
	 * @param value
	 * */
	public static void putToCache(String key, Object value) {
		cache.putInCache(key, value);
	}

	/**
	 * 清除所有缓存数据
	 * */
	public static void flushAllCache() {
		cache.flushAll();
	}

	/**
	 * 清除key缓存数据
	 * 
	 * @param key
	 * */
	public static void flushCache(String key) {
		cache.flushEntry(key);
	}

	/**
	 * 获取缓存数据
	 * 
	 * @param key
	 * */
	public static Object getFormCache(String key) {
		Object obj = null;
		try {
			obj = cache.getFromCache(key);
		} catch (NeedsRefreshException nre) {
			cache.cancelUpdate(key);
		}
		return obj;
	}
    
	/**
	 * 放入缓存数据
	 * 
	 * @param key
	 * */
	public static void putToCache(String key, Object obj, int second) {
		cache.putInCache(key, obj, new EntryRefreshPolicyImpl(second));
	}
	
	/**
	 * 获得页面缓存源
	 * 
	 * @param key
	 * */
	public static ServletCacheAdministrator getA(ServletContext context){
		return ServletCacheAdministrator.getInstance(context);
	}
}
