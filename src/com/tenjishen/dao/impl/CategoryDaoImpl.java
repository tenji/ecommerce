package com.tenjishen.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tenjishen.dao.CategoryDao;
import com.tenjishen.model.Category;

@Repository
public class CategoryDaoImpl extends BaseDaoImpl<Category, Long> implements CategoryDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getAssignedCategoryIds(Long brandId) {
		
		String hql = "SELECT category.id FROM Category category JOIN category.brands brand WHERE brand.id = ?";
		
		return getSession().createQuery(hql).setParameter(0, brandId).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getRootCategoryList() {
		String hql = "FROM Category category WHERE category.parent is null ORDER BY category.orderList ASC";
		return getSession().createQuery(hql).list();
	}

	@Override
	public List<Category> getParentCategoryList(Category category) {
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAncestorCategoryList(Category category) {
		String hql = "FROM Category category WHERE category != ? AND category.id in(:ids) ORDER BY category.orderList ASC";
		String[] ids = category.getPath().split(Category.PATH_SEPARATOR);
		return getSession().createQuery(hql).setParameter(0, category).setParameterList("ids", ids).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getChildrenCategoryList(Category category) {
		String hql = "SELECT children FROM Category category JOIN category.children children WHERE children != ? AND children.parent = ? ORDER BY category.orderList ASC";
		
		return getSession().createQuery(hql).setParameter(0, category).setParameter(1, category).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getDescendanceCategoryList(Category category) {
		String hql = "FROM Category category WHERE category != ? AND category.path like ? ORDER BY category.orderList ASC";
		return getSession().createQuery(hql).setParameter(0, category).setParameter(1, category.getPath() + "%").list();
	}
	
	// 重写方法，保存时计算path值
	@Override
	public Long save(Category category) {
		Long id = super.save(category);
		Category parent = category.getParent();
		if (parent != null) {
			String parentPath = parent.getPath();
			category.setPath(parentPath + Category.PATH_SEPARATOR + id);
		} else {
			category.setPath(String.valueOf(id));
		}
		super.update(category);
		return id;
	}
		
	// 重写方法，更新时计算path值
	@Override
	public void update(Category category) {
		Category parent = category.getParent();
		if (parent != null) {
			String parentPath = parent.getPath();
			category.setPath(parentPath + Category.PATH_SEPARATOR + category.getId());
		} else {
			category.setPath(String.valueOf(category.getId()));
		}
		super.update(category);
	}

}
