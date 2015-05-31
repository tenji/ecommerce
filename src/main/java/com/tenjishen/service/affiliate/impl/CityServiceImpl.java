package com.tenjishen.service.affiliate.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenjishen.dao.CityDao;
import com.tenjishen.model.affiliate.City;
import com.tenjishen.model.affiliate.State;
import com.tenjishen.service.BaseServiceImpl;
import com.tenjishen.service.affiliate.CityService;

@Service
@Transactional
public class CityServiceImpl extends BaseServiceImpl<City, Long> implements CityService {
	@Resource
	private CityDao cityDao;

	@Resource
	public void setBaseDao(CityDao cityDao) {
		super.setBaseDao(cityDao);
	}

	@Override
	public List<City> getCityList(State state) {
		
		return cityDao.getCityList(state);
	}

}
