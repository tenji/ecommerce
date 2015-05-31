package com.tenjishen.service.product.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenjishen.dao.ProductImgDao;
import com.tenjishen.model.ProductImg;
import com.tenjishen.service.BaseServiceImpl;
import com.tenjishen.service.product.ProductImgService;


@Service
@Transactional
public class ProductImgServiceImpl extends BaseServiceImpl<ProductImg, Long> implements ProductImgService {
	@Resource
	private ProductImgDao produImgDao;

	@Resource
	public void setBaseDao(ProductImgDao produImgDao) {
		super.setBaseDao(produImgDao);
	}

}
