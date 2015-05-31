package com.tenjishen.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tenjishen.dao.CityDao;
import com.tenjishen.model.affiliate.City;
import com.tenjishen.model.affiliate.State;

@Repository
public class CityDaoImpl extends BaseDaoImpl<City, Long> implements CityDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<City> getCityList(State state) {
		String hql = "SELECT city FROM City city JOIN city.state state WHERE state.id = ?";
		
		return getSession().createQuery(hql).setParameter(0, state.getId()).list();
	}

}
