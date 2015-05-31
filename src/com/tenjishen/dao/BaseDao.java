package com.tenjishen.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.tenjishen.vo.PageBean;

/**
 * Dao接口 - Dao基本接口
 * 
 * @author TENJI
 */
public interface BaseDao<E, PK extends Serializable> {

	/**
	 * 根据ID获取实体对象.
	 * 
	 * @param id
	 *            记录ID
	 * @return 实体对象
	 */
	public E getById(PK id);

	/**
	 * 根据ID获取实体对象.
	 * 
	 * @param id
	 *            记录ID
	 * @return 实体对象
	 */
	public E loadById(PK id);

	/**
	 * 根据ID数组获取实体对象集合.
	 * 
	 * @param ids
	 *            ID对象数组
	 * 
	 * @return 实体对象集合
	 */
	public List<E> getByIds(PK[] ids);

	/**
	 * 根据属性名和属性值获取实体对象.
	 * 
	 * @param propertyName
	 *            属性名称
	 * @param value
	 *            属性值
	 * @return 实体对象
	 */
	public E getByPropertyName(String propertyName, Object value);

	/**
	 * 根据属性名和属性值获取实体对象集合
	 * 
	 * @param propertyName
	 *            属性名称
	 * @param value
	 *            属性值
	 * @return 实体对象集合
	 */
	public List<E> getListByPropertyName(String propertyName, Object value);

	/**
	 * 获取所有实体对象集合.
	 * 
	 * @return 实体对象集合
	 */
	public List<E> getAll();

	/**
	 * 获取所有实体对象集合，带排序字段与升降序参数
	 * 
	 * @param orderBy
	 *            排序字段
	 * @param isAsc
	 *            是否升序
	 * @return 实体对象集合
	 */
	public List<E> getAll(String orderBy, boolean isAsc);

	/**
	 * 获取所有实体对象总数.
	 * 
	 * @return 实体对象总数
	 */
	public Long getTotalCount();

	/**
	 * 根据属性名、修改前后属性值判断在数据库中是否唯一(若新修改的值与原来值相等则直接返回true).
	 * 
	 * @param propertyName
	 *            属性名称
	 * @param oldValue
	 *            修改前的属性值
	 * @param oldValue
	 *            修改后的属性值
	 * @return boolean
	 */
	public boolean isUnique(String propertyName, Object oldValue,
			Object newValue);

	/**
	 * 根据属性名判断数据是否已存在.
	 * 
	 * @param propertyName
	 *            属性名称
	 * @param value
	 *            值
	 * @return boolean
	 */
	public boolean isExist(String propertyName, Object value);

	/**
	 * 保存实体对象.
	 * 
	 * @param entity
	 *            对象
	 * @return ID
	 */
	public PK save(E entity);

	/**
	 * 批量保存实体对象
	 * 
	 * @param entities
	 */
	public void batchSave(final List<E> entities);

	/**
	 * 更新实体对象.
	 * 
	 * @param entity
	 *            对象
	 */
	public void update(E entity);

	/**
	 * 批量更新实体对象
	 * 
	 * @param objs
	 *            需要更新的对象集合
	 */
	public void batchUpdate(final List<E> entities);

	/**
	 * 删除实体对象.
	 * 
	 * @param entity
	 *            对象
	 * @return
	 */
	public void delete(E entity);

	/**
	 * 根据ID删除实体对象.
	 * 
	 * @param id
	 *            记录ID
	 */
	public void deleteById(PK id);

	/**
	 * 根据ID数组删除实体对象.
	 * 
	 * @param ids
	 *            ID数组
	 */
	public void deleteByIds(PK[] ids);

	/**
	 * 刷新session.
	 * 
	 */
	public void flush();

	/**
	 * 清除Session.
	 * 
	 */
	public void clear();

	/**
	 * 清除某一对象.
	 * 
	 * @param object
	 *            需要清除的对象
	 */
	public void evict(Object object);

	/**
	 * 根据PageBean对象进行查询(提供分页、查找、排序功能).
	 * 
	 * @param pageBean
	 *            PageBean对象
	 * @return PageBean对象
	 */
	public PageBean findByPageBean(PageBean pageBean);

	/**
	 * 根据Pager和DetachedCriteria对象进行查询(提供分页、查找、排序功能).
	 * 
	 * @param pageBean
	 *            PageBean对象
	 * @return PageBean对象
	 */
	public PageBean findByPageBean(PageBean pageBean,
			DetachedCriteria detachedCriteria);

	/**
	 * 根据PageBean对象进行查询(带参数).
	 * 
	 * @param pageBean
	 *            PageBean对象
	 * @param columns
	 *            查询属性名集合
	 * @param args
	 *            查询属性值集合
	 * @return PageBean对象
	 */
	public PageBean findByPageBean(PageBean pageBean,
			final List<String> columns, final List<?> values);

}
