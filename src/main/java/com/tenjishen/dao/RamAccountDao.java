package com.tenjishen.dao;

import com.tenjishen.model.affiliate.RamAccount;
import com.tenjishen.vo.PageBean;
import com.tenjishen.vo.query.affiliate.RamAccountQuery;


public interface RamAccountDao extends BaseDao<RamAccount, Long> {
	
	/**
	 * 根据PageBean和RamAccount对象进行查询(提供分页、查找、排序功能).
	 * 
	 * @param pageBean
	 *            PageBean对象
	 * @return PageBean对象
	 */
	public PageBean findByPageBean(PageBean pageBean, RamAccountQuery ramAccountQuery);

}
