package com.tenjishen.service.shops.member.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenjishen.dao.RankDao;
import com.tenjishen.model.Rank;
import com.tenjishen.service.BaseServiceImpl;
import com.tenjishen.service.shops.member.RankService;


@Service
@Transactional
public class RankServiceImpl extends BaseServiceImpl<Rank, Long> implements RankService {
	@Resource
	private RankDao rankDao;

	@Resource
	public void setBaseDao(RankDao rankDao) {
		super.setBaseDao(rankDao);
	}

}
