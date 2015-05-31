package com.tenjishen.service.affiliate.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenjishen.dao.StateDao;
import com.tenjishen.model.affiliate.State;
import com.tenjishen.service.BaseServiceImpl;
import com.tenjishen.service.affiliate.StateService;

@Service
@Transactional
public class StateServiceImpl extends BaseServiceImpl<State, Long> implements StateService {
	@Resource
	private StateDao stateDao;

	@Resource
	public void setBaseDao(StateDao stateDao) {
		super.setBaseDao(stateDao);
	}

}
