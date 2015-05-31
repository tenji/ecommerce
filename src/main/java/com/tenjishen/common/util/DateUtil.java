package com.tenjishen.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * 时间处理工具类
 * 
 * @author TENJI
 * @since
 * @date 2014-8-15
 */
public class DateUtil {
	
	private static Logger logger = Logger.getLogger(DateUtil.class);

	/**
	 * 得到几天前的时间
	 * 
	 * @param date 基准日期
	 * @param day 多少天前
	 * @return
	 */
	public static Date getDateBefore(Date date, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		return now.getTime();
	}
	
	/**
	 * 得到昨天的时间
	 * 
	 * @date 2014-8-20
	 * @return
	 */
	public static Date getYesterday() {
		return getDateBefore(new Date(), 1);
	}

	/**
	 * 得到几天后的时间
	 * 
	 * @param date 基准日期
	 * @param day 多少天后
	 * @return
	 */
	public static Date getDateAfter(Date date, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}
	
	/**
	 * 得到明天的时间
	 * 
	 * @date 2014-8-20
	 * @return
	 */
	public static Date getTomorrow() {
		return getDateAfter(new Date(), 1);
	}
	
	/**   
     * 计算两个日期之间相差的天数
     * 
     * @param dateBefore
     * @param dateAfter
     * @return
     */    
    public static int daysBetween(Date dateBefore, Date dateAfter) {  
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateBefore);
        long timeBefore = cal.getTimeInMillis();
        cal.setTime(dateAfter);
        long timeAfter = cal.getTimeInMillis();
        long between_days = (timeAfter - timeBefore) / (1000 * 3600 * 24);
        
       return Integer.parseInt(String.valueOf(between_days));
    }
    
    /**
     * Date格式转String格式
     * 
     * @date 2014-8-15
     * @param date 日期
     * @param strFormat 待转化的格式，比如'yyyy-MM-dd'或'yyyy-MM-dd HH:mm:ss'
     * @return
     */
    public static String dateToStr(Date date, String strFormat) {
    	SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
    	
    	return sdf.format(date);
    }
    
    /**
     * String格式转Date格式
     * 
     * @date 2014-8-15
     * @param str 字符串
     * @param dateFormat 带转化的格式，比如'yyyy-MM-dd'或'yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static Date strToDate(String str, String dateFormat) {
    	DateFormat df = new SimpleDateFormat(dateFormat);
    	Date date = null;
    	try {
    		date = df.parse(str);
		} catch (ParseException e) {
			logger.error("---------- 格式转化出错！ ----------");
			e.printStackTrace();
		}
    	
		return date;
    }
    
    /**
     * 获取指定日期的年份
     * 
     * @date 2014-09-12
     * @param date 指定日期
     * @return
     */
    public static int getYear(Date date) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
		int year = cal.get(Calendar.YEAR);

		return year;
    }
    
    /**
     * 获取指定日期的月份
     * 
     * @date 2014-09-12
     * @param date 指定日期
     * @return
     */
    public static int getMonth(Date date) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
		int month = cal.get(Calendar.MONTH) + 1;

		return month;
    }
    
    // 测试
    public static void main(String[] args) {
		Date date = new Date();
		Date dateBefore = DateUtil.getDateBefore(date, 14);
		System.out.println(date);
		System.out.println(dateBefore);
		System.out.println(DateUtil.daysBetween(dateBefore, date));
		
	}
    
}
