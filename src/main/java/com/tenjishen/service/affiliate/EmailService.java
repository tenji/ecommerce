package com.tenjishen.service.affiliate;

import java.util.List;

import com.tenjishen.model.affiliate.Email;
import com.tenjishen.model.affiliate.Ram;
import com.tenjishen.service.BaseService;
import com.tenjishen.vo.PageBean;

public interface EmailService extends BaseService<Email, Long> {

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
	 * 搜索查询，根据PageBean和Email对象进行查询(提供分页、查找、排序功能).
	 * 
	 * @param pageBean
	 *            PageBean对象
	 * @param email
	 *            Email对象
	 * @return PageBean对象
	 */
	public PageBean findByPageBean(PageBean pageBean, Email email);

}
