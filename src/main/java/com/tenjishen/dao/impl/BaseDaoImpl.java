package com.tenjishen.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.tenjishen.dao.BaseDao;
import com.tenjishen.vo.PageBean;
import com.tenjishen.vo.PageBean.OrderType;

/**
 * Dao实现类 - Dao实现类基类
 * 
 * @param <E>
 * @param <PK>
 * @author TENJI
 */
@Repository
public class BaseDaoImpl<E, PK extends Serializable> implements BaseDao<E, PK> {

	private Class<E> entityClass;
	protected SessionFactory sessionFactory;

	// 获取超类的泛型参数的实际类型，构造函数
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BaseDaoImpl() {
		this.entityClass = null;
		Class c = getClass();
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type)
					.getActualTypeArguments();
			this.entityClass = (Class<E>) parameterizedType[0];
		}
	}

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	@SuppressWarnings("unchecked")
	public E getById(PK id) {
		Assert.notNull(id, "id is required");
		return (E) getSession().get(entityClass, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public E loadById(PK id) {
		Assert.notNull(id, "id is required");
		return (E) getSession().load(entityClass, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<E> getByIds(PK[] ids) {
		Assert.notEmpty(ids, "ids must not be empty");
		String hql = "from " + entityClass.getName()
				+ " as model where model.id in(:ids)";
		return getSession().createQuery(hql).setParameterList("ids", ids)
				.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public E getByPropertyName(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		String hql = "from " + entityClass.getName() + " as model where model."
				+ propertyName + " = ?";
		return (E) getSession().createQuery(hql).setParameter(0, value)
				.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<E> getListByPropertyName(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		String hql = "from " + entityClass.getName() + " as model where model."
				+ propertyName + " = ?";
		return (List<E>) getSession().createQuery(hql).setParameter(0, value)
				.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<E> getAll() {
		String hql = "from " + entityClass.getName();
		return getSession().createQuery(hql).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<E> getAll(String orderBy, boolean isAsc) {
		String hql = isAsc ? "FROM " + entityClass.getName() + " ORDER BY "
				+ orderBy + " ASC" : "FROM " + entityClass.getName()
				+ " ORDER BY " + orderBy + " DESC";
		return getSession().createQuery(hql).list();
	}

	@Override
	public Long getTotalCount() {
		String hql = "select count(*) from " + entityClass.getName();
		return (Long) getSession().createQuery(hql).uniqueResult();
	}

	@Override
	public boolean isUnique(String propertyName, Object oldValue,
			Object newValue) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(newValue, "newValue is required");
		if (newValue == oldValue || newValue.equals(oldValue)) {
			return true;
		}
		if (newValue instanceof String) {
			if (oldValue != null
					&& StringUtils.equalsIgnoreCase((String) oldValue,
							(String) newValue)) {
				return true;
			}
		}
		E object = getByPropertyName(propertyName, newValue);
		return (object == null);
	}

	@Override
	public boolean isExist(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		E object = getByPropertyName(propertyName, value);
		return (object != null);
	}

	@Override
	@SuppressWarnings("unchecked")
	public PK save(E entity) {
		Assert.notNull(entity, "entity is required");
		return (PK) getSession().save(entity);
	}

	@Override
	public void batchSave(final List<E> entities) {
		for (E entity : entities) {
			this.save(entity);
		}
	}

	@Override
	public void update(E entity) {
		Assert.notNull(entity, "entity is required");
		getSession().update(entity);
	}

	@Override
	public void batchUpdate(final List<E> entities) {
		for (E entity : entities) {
			this.update(entity);
		}
	}

	@Override
	public void delete(E entity) {
		Assert.notNull(entity, "entity is required");
		getSession().delete(entity);
	}

	@Override
	public void deleteById(PK id) {
		Assert.notNull(id, "id is required");
		E entity = loadById(id);
		getSession().delete(entity);
	}

	@Override
	public void deleteByIds(PK[] ids) {
		Assert.notEmpty(ids, "ids must not be empty");
		for (PK id : ids) {
			E entity = loadById(id);
			getSession().delete(entity);
		}
	}

	@Override
	public void flush() {
		getSession().flush();
	}

	@Override
	public void clear() {
		getSession().clear();
	}

	@Override
	public void evict(Object object) {
		Assert.notNull(object, "object is required");
		getSession().evict(object);
	}

	@Override
	public PageBean findByPageBean(PageBean pageBean) {
		if (pageBean == null) {
			pageBean = new PageBean();
		}
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(entityClass);
		return findByPageBean(pageBean, detachedCriteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageBean findByPageBean(PageBean pageBean,
			DetachedCriteria detachedCriteria) {
		if (pageBean == null) {
			pageBean = new PageBean();
		}
		Integer currentPage = pageBean.getCurrentPage();
		Integer pageSize = pageBean.getPageSize();
		String orderBy = pageBean.getOrderBy();
		OrderType orderType = pageBean.getOrderType();

		Criteria criteria = detachedCriteria
				.getExecutableCriteria(getSession());

		// int totalCount = ((Integer)
		// criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		// // 总记录数
		long totalCount = getTotalCount();

		criteria.setProjection(null);
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		criteria.setFirstResult((currentPage - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		if (StringUtils.isNotEmpty(orderBy) && orderType != null) {
			if (orderType == OrderType.asc) {
				criteria.addOrder(Order.asc(orderBy));
			} else {
				criteria.addOrder(Order.desc(orderBy));
			}
		}

		pageBean.setTotalCount(totalCount); // 总记录数
		pageBean.setTotalPage(PageBean.countTotalPage(pageSize,
				(int) totalCount)); // 总页数
		pageBean.setList(criteria.list());
		pageBean.init(); // 初始化分页Bean

		return pageBean;
	}

	@Override
	public PageBean findByPageBean(PageBean pageBean, List<String> columns,
			List<?> values) {
		// TODO 根据PageBean对象进行查询(带参数).
		/*
		 * 思路：遍历属性名集合和属性值集合，拼接查询结果数和结果集的HQL（两个）
		 * 
		 * 难点：不是所有的查询都是完全匹配查询，怎么解决模糊查询问题？再添加一个属性名和属性值的链接符号集合吗（比如=, like等）？
		 */
		
		return null;
	}

}
