package com.tenjishen.service.shops.member.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenjishen.dao.DeliveryAddressDao;
import com.tenjishen.model.DeliveryAddress;
import com.tenjishen.service.BaseServiceImpl;
import com.tenjishen.service.shops.member.DeliveryAddressService;


@Service
@Transactional
public class DeliveryAddressServiceImpl extends BaseServiceImpl<DeliveryAddress, Long> implements DeliveryAddressService {
	@Resource
	private DeliveryAddressDao deliveryAddressDao;

	@Resource
	public void setBaseDao(DeliveryAddressDao deliveryAddressDao) {
		super.setBaseDao(deliveryAddressDao);
	}

}
