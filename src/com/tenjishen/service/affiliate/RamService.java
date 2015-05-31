package com.tenjishen.service.affiliate;

import com.tenjishen.model.affiliate.Ram;
import com.tenjishen.service.BaseService;

public interface RamService extends BaseService<Ram, Long> {
	
	/**
	 * Register a new 'Ram Accont' with specific 'Email Account'
	 * 
	 * @date 2014-9-12
	 * @param ramId
	 * @param emailId
	 */
	public boolean regRamAccount(Long ramId, Long emailId) throws Exception;
	
}
