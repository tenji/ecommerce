package com.tenjishen.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.tenjishen.dao.RamAccountDao;
import com.tenjishen.model.affiliate.RamAccount;
import com.tenjishen.vo.PageBean;
import com.tenjishen.vo.query.affiliate.RamAccountQuery;

@Repository
public class RamAccountDaoImpl extends BaseDaoImpl<RamAccount, Long> implements
		RamAccountDao {

	@SuppressWarnings("unchecked")
	@Override
	public PageBean findByPageBean(PageBean pageBean, RamAccountQuery ramAccountQuery) {
		if (pageBean == null) {
			pageBean = new PageBean();
		}
		Integer currentPage = pageBean.getCurrentPage();
		Integer pageSize = pageBean.getPageSize();

		StringBuilder hql = new StringBuilder(
				"SELECT ramAccount FROM RamAccount ramAccount Left JOIN ramAccount.ram ram WHERE 1=1");
		StringBuilder countHql = new StringBuilder(
				"SELECT COUNT(*) FROM RamAccount ramAccount Left JOIN ramAccount.ram ram WHERE 1=1");

		// 拼接HQL，添加参数
		if (null != ramAccountQuery.getRam() && null != ramAccountQuery.getRam().getId()) {
			hql.append(" AND ramAccount.ram.id= :ramId");
			countHql.append(" AND ramAccount.ram.id= :ramId");
		}
		if (StringUtils.isNotBlank(ramAccountQuery.getEmail())) {
			hql.append(" AND ramAccount.email like :email");
			countHql.append(" AND ramAccount.email like :email");
		}
		if (null != ramAccountQuery.getRedeemStatus()) {
			hql.append(" AND ramAccount.redeemStatus= :redeemStatus");
			countHql.append(" AND ramAccount.redeemStatus= :redeemStatus");
		}

		Query query = getSession().createQuery(hql.toString());
		Query countQuery = getSession().createQuery(countHql.toString());
		if (null != ramAccountQuery.getRam() && null != ramAccountQuery.getRam().getId()) {
			query.setParameter("ramId", ramAccountQuery.getRam().getId());
			countQuery.setParameter("ramId", ramAccountQuery.getRam().getId());
		}
		if (!StringUtils.isBlank(ramAccountQuery.getEmail())) {
			query.setParameter("email", "%" + ramAccountQuery.getEmail() + "%");
			countQuery.setParameter("email", "%" + ramAccountQuery.getEmail() + "%");
		}
		if (null != ramAccountQuery.getRedeemStatus()) {
			query.setParameter("redeemStatus", ramAccountQuery.getRedeemStatus());
			countQuery.setParameter("redeemStatus", ramAccountQuery.getRedeemStatus());
		}
		
		// Integer totalCount = query.list().size();
		// 查询总记录数
		Integer totalCount = ((Long) countQuery.uniqueResult()).intValue();

		query.setFirstResult((currentPage - 1) * pageSize);
		query.setMaxResults(pageSize);

		pageBean.setTotalCount(totalCount); // 总记录数
		pageBean.setTotalPage(PageBean.countTotalPage(pageSize,
				totalCount)); // 总页数
		pageBean.setList(query.list());
		pageBean.init(); // 初始化分页Bean

		return pageBean;
	}

}
