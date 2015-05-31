package com.tenjishen.service.affiliate;

import com.tenjishen.model.affiliate.EvansDataAccount;
import com.tenjishen.service.BaseService;

public interface EvansDataAccountService extends
		BaseService<EvansDataAccount, Long> {

	/**
	 * Answer the surveys of specific account
	 * 
	 * @param evansDataAccount
	 *            Evans Data Account
	 */
	public EvansDataAccount answerSurvey(EvansDataAccount evansDataAccount)
			throws Exception;
}
