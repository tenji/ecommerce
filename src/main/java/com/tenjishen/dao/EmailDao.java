package com.tenjishen.dao;

import java.util.List;

import com.tenjishen.model.affiliate.Email;
import com.tenjishen.model.affiliate.Ram;
import com.tenjishen.vo.PageBean;

public interface EmailDao extends BaseDao<Email, Long> {

	/**
	 * Get unregistered email list of specific ram account
	 * 
	 * @date 2014-9-19
	 * @param ram
	 *            Ram Account
	 * @return
	 */
	public List<Email> getUnregisteredEmailList(Ram ram);

	/**
	 * 根据PageBean和Email对象进行查询(提供分页、查找、排序功能).
	 * 
	 * @param pageBean
	 *            PageBean对象
	 * @param email
	 *            Email对象
	 * @return PageBean对象
	 */
	public PageBean findByPageBean(PageBean pageBean, Email email);

}
