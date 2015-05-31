package com.tenjishen.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.tenjishen.dao.EmailDao;
import com.tenjishen.model.affiliate.Email;
import com.tenjishen.model.affiliate.Ram;
import com.tenjishen.vo.PageBean;

@Repository
public class EmailDaoImpl extends BaseDaoImpl<Email, Long> implements EmailDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Email> getUnregisteredEmailList(Ram ram) {
		/*
		 * 原生SQL解决方案（记得使用addEntity方法绑定实体）
		 */
		String sql = "SELECT * FROM t_affiliate_email e WHERE e.email NOT IN ( " +
							"SELECT ara.email FROM t_affiliate_ram_account ara WHERE ara.ramId=? UNION ( " +
							"SELECT t.email FROM t_affiliate_ram_account t GROUP BY t.email HAVING COUNT(t.email)>=5 " +
						") " +
					")";
		Query query = getSession().createSQLQuery(sql).addEntity(Email.class);
		List<Email> list = query.setParameter(0, ram.getId()).list(); // 查询结果返回的是一个Object的数组
		/*
		 * HQL解决方案
		 */
		/*
		String hql = "SELECT email FROM Email email WHERE email.email NOT IN" +
				" (SELECT ramAccount.email FROM RamAccount ramAccount WHERE ramAccount.ram = ?)";
		Query query = getSession().createQuery(hql);
		List<Email> list = query.setParameter(0, ram).list();
		*/
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageBean findByPageBean(PageBean pageBean, Email email) {
		if (pageBean == null) {
			pageBean = new PageBean();
		}
		Integer currentPage = pageBean.getCurrentPage();
		Integer pageSize = pageBean.getPageSize();

		StringBuilder hql = new StringBuilder(
				"SELECT email FROM Email email Left JOIN email.state state WHERE 1=1");
		StringBuilder countHql = new StringBuilder(
				"SELECT COUNT(*) FROM Email email Left JOIN email.state state WHERE 1=1");

		// 拼接HQL，添加参数
		if (null != email.getState() && null != email.getState().getId()) {
			hql.append(" AND email.state.id= :stateId");
			countHql.append(" AND email.state.id= :stateId");
		}
		if (!StringUtils.isBlank(email.getEmail())) {
			hql.append(" AND email.email like :email");
			countHql.append(" AND email.email like :email");
		}

		Query query = getSession().createQuery(hql.toString());
		Query countQuery = getSession().createQuery(countHql.toString());
		if (null != email.getState() && null != email.getState().getId()) {
			query.setParameter("stateId", email.getState().getId());
			countQuery.setParameter("stateId", email.getState().getId());
		}
		if (!StringUtils.isBlank(email.getEmail())) {
			query.setParameter("email", "%" + email.getEmail() + "%");
			countQuery.setParameter("email", "%" + email.getEmail() + "%");
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
	};
	
}
