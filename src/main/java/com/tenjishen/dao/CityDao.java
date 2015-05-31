package com.tenjishen.dao;

import java.util.List;

import com.tenjishen.model.affiliate.City;
import com.tenjishen.model.affiliate.State;


public interface CityDao extends BaseDao<City, Long> {
	
	/**
	 * Get cities of specific state
	 * 
	 * @param state
	 * @return
	 */
	public List<City> getCityList(State state);

}
