package com.tenjishen.service.affiliate;

import com.tenjishen.model.affiliate.RamAccount;
import com.tenjishen.service.BaseService;
import com.tenjishen.vo.PageBean;
import com.tenjishen.vo.query.affiliate.RamAccountQuery;

public interface RamAccountService extends BaseService<RamAccount, Long> {
	
	/**
	 * 搜索查询，根据PageBean和RamAccount对象进行查询(提供分页、查找、排序功能).
	 * 
	 * @param pageBean
	 *            PageBean对象
	 * @return PageBean对象
	 */
	public PageBean findByPageBean(PageBean pageBean, RamAccountQuery ramAccountQuery);
	
	/**
	 * Answer the surveys of specific account
	 * 
	 * @param ramAccount
	 */
	public RamAccount answerSurvey(RamAccount ramAccount) throws Exception;
	
	/**
	 * Check unanswered surveys of specific account
	 * 
	 * @param ramAccount
	 */
	public RamAccount checkSurveyNums(RamAccount ramAccount) throws Exception;
	
	/**
	 * Answer the surveys of specific account(extended in 2014-9-16)
	 * 
	 * @date 2014-9-16
	 * @param ramAccount
	 * @return
	 * @throws Exception
	 */
	public RamAccount answerSurveyExtend(RamAccount ramAccount) throws Exception;
	
	/**
	 * Answer the surveys of specific account(update in 2014-10-15)
	 * 
	 * @date 2014-10-15
	 * @param ramAccount
	 * @return
	 * @throws Exception
	 */
	public RamAccount answerSurveyExtend20141015(RamAccount ramAccount) throws Exception;
	
}
