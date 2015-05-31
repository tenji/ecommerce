package com.tenjishen.service.affiliate;

import java.util.List;

import com.tenjishen.model.affiliate.City;
import com.tenjishen.model.affiliate.State;
import com.tenjishen.service.BaseService;

public interface CityService extends BaseService<City, Long> {
	
	/**
	 * Get cities of specific state
	 * 
	 * @param state
	 * @return
	 */
	public List<City> getCityList(State state);
	
}
