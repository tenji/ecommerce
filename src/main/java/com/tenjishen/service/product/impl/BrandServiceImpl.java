package com.tenjishen.service.product.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenjishen.dao.BrandDao;
import com.tenjishen.dao.CategoryDao;
import com.tenjishen.model.Brand;
import com.tenjishen.model.Category;
import com.tenjishen.service.BaseServiceImpl;
import com.tenjishen.service.product.BrandService;


@Service
@Transactional
public class BrandServiceImpl extends BaseServiceImpl<Brand, Long> implements BrandService {
	@Resource
	private BrandDao brandDao;
	@Resource
	private CategoryDao categoryDao;

	@Resource
	public void setBaseDao(BrandDao brandDao) {
		super.setBaseDao(brandDao);
	}

	@Override
	public List<Brand> getBrandList(Category category) {
		
		return brandDao.getBrandList(category);
	}
	
	@Override
	public Brand save(Brand brand, Long[] categoryIds) {
		Set<Category> categories = new HashSet<Category>();
		for (int i = 0; i < categoryIds.length; i++) {
			categories.add(categoryDao.loadById(categoryIds[i]));
		}
		brand.setCategories(categories);
		brandDao.save(brand);
		
		return brand;
	}

	@Override
	public Brand update(Brand brand, Long[] categoryIds) {
		Brand oldBrand = brandDao.getById(brand.getId()); // 旧的品牌信息
		oldBrand.setChineseName(brand.getChineseName());
		oldBrand.setDescription(brand.getDescription());
		oldBrand.setEnglishName(brand.getEnglishName());
		oldBrand.setOfficialAddress(brand.getOfficialAddress());
		oldBrand.setStory(brand.getStory());
		oldBrand.setModifyTime(new Date()); // Modify Time
		
		Set<Category> categories = oldBrand.getCategories(); // 旧的所属类目
		
		// 第一趟遍历，删除更新后不包含的旧的所属类目
		Iterator<Category> iterator = categories.iterator();
		while (iterator.hasNext()) {
			Category category = iterator.next();
			if (!ArrayUtils.contains(categoryIds, category.getId())) {
				category.getBrands().remove(oldBrand);
				iterator.remove();
			}
			
		}
		
		// 第二趟遍历，添加新分配的所属类目
		List<Long> assignedCategoryIds = categoryDao.getAssignedCategoryIds(brand.getId()); // 已分配所属类目集合
		for (int i = 0; i < categoryIds.length; i++) {
			if (!assignedCategoryIds.contains(categoryIds[i])) { // 新分配的所属类目不在旧的所属类目中
				Category category = categoryDao.getById(categoryIds[i]);
				oldBrand.getCategories().add(category);
				category.getBrands().add(oldBrand);
			}
		}
		
		brandDao.update(oldBrand); // 更新品牌
		
		return brand;
	}

	@Override
	public List<Brand> getBrandList(List<Category> categories) {
		
		return brandDao.getBrandList(categories);
	}

}
