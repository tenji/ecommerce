package com.tenjishen.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.tenjishen.dao.BaseDao;
import com.tenjishen.vo.PageBean;

/**
 * Service实现类 - Service实现类基类，泛型类
 * 
 * @author tenji
 * Created On: Nov 25, 2013
 *
 */
public class BaseServiceImpl<E, PK extends Serializable> implements BaseService<E, PK> {

	private BaseDao<E, PK> baseDao;

	public BaseDao<E, PK> getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao<E, PK> baseDao) {
		this.baseDao = baseDao;
	}
	
	@Override
	public E getById(PK id) {
		return baseDao.getById(id);
	}

	@Override
	public E loadById(PK id) {
		return baseDao.loadById(id);
	}

	@Override
	public List<E> getByIds(PK[] ids) {
		return baseDao.getByIds(ids);
	}

	@Override
	public E getByPropertyName(String propertyName, Object value) {
		return baseDao.getByPropertyName(propertyName, value);
	}
	
	@Override
	public List<E> getListByPropertyName(String propertyName, Object value) {
		return baseDao.getListByPropertyName(propertyName, value);
	}

	@Override
	public List<E> getAll() {
		return baseDao.getAll();
	}
	
	@Override
	public List<E> getAll(String orderBy, boolean isAsc) {
		return baseDao.getAll(orderBy, isAsc);
	}

	@Override
	public Long getTotalCount() {
		return baseDao.getTotalCount();
	}

	@Override
	public boolean isUnique(String propertyName, Object oldValue, Object newValue) {
		return baseDao.isUnique(propertyName, oldValue, newValue);
	}

	@Override
	public boolean isExist(String propertyName, Object value) {
		return baseDao.isExist(propertyName, value);
	}

	@Override
	public PK save(E entity) {
		return baseDao.save(entity);
	}

	@Override
	public void update(E entity) {
		baseDao.update(entity);
		
	}

	@Override
	public void delete(E entity) {
		baseDao.delete(entity);
		
	}

	@Override
	public void deleteById(PK id) {
		baseDao.deleteById(id);
		
	}

	@Override
	public void deleteByIds(PK[] ids) {
		baseDao.deleteByIds(ids);
		
	}

	@Override
	public void flush() {
		baseDao.flush();
		
	}

	@Override
	public void clear() {
		baseDao.clear();
		
	}

	@Override
	public void evict(Object object) {
		baseDao.evict(object);
		
	}

	@Override
	public PageBean findByPageBean(PageBean pageBean) {
		return baseDao.findByPageBean(pageBean);
	}

	@Override
	public PageBean findByPageBean(PageBean pageBean, DetachedCriteria detachedCriteria) {
		return baseDao.findByPageBean(pageBean, detachedCriteria);
	}

}
