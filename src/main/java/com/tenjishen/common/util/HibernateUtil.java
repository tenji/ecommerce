package com.tenjishen.common.util;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Hibernate相关工具类
 * 
 * @author TENJI
 *
 */
public class HibernateUtil {
	
	private static Validator validator; // 它是线程安全的
	
	static {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	public static <T> void validate(T t) {
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
		
		if (constraintViolations.size() > 0) {
			String validateError = "";
			for (ConstraintViolation<T> constraintViolation: constraintViolations) {
				validateError += constraintViolation.getMessage() + ";";
			}
			
			throw new ValidationException(validateError); // 验证不通过直接抛异常
		}
	}
	
	/**
	 * 将查询记录集合的HQL转成查询记录总数的HQL
	 * 
	 * @param hql
	 * @return
	 */
	public static String generateCountHql(String hql) {
		int beginIndex = -1;
		// 支持全大写和全小写
		beginIndex = ((-1 == hql.indexOf("FROM")) ?  hql.indexOf("from") : hql.indexOf("FROM"));
		
		if (-1 != beginIndex) {
			hql = "SELECT COUNT(*) " + hql.substring(beginIndex);
		}
		System.out.println(hql);
		return hql;
	}

}
