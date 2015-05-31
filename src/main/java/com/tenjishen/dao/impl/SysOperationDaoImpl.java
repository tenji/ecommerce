package com.tenjishen.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tenjishen.dao.SysOperationDao;
import com.tenjishen.model.SysOperation;
import com.tenjishen.model.SysRole;
import com.tenjishen.model.SysUser;

@Repository
public class SysOperationDaoImpl extends BaseDaoImpl<SysOperation, Long> implements SysOperationDao {

	// 重写方法，保存时计算path值
	@Override
	public Long save(SysOperation sysOperation) {
		Long id = super.save(sysOperation);
		SysOperation parent = sysOperation.getParent();
		if (parent != null) {
			String parentPath = parent.getPath();
			sysOperation.setPath(parentPath + SysOperation.PATH_SEPARATOR + id);
		} else {
			sysOperation.setPath(String.valueOf(id));
		}
		super.update(sysOperation);
		return id;
	}
		
	// 重写方法，更新时计算path值
	@Override
	public void update(SysOperation sysOperation) {
		SysOperation parent = sysOperation.getParent();
		if (parent != null) {
			String parentPath = parent.getPath();
			sysOperation.setPath(parentPath + SysOperation.PATH_SEPARATOR + sysOperation.getId());
		} else {
			sysOperation.setPath(String.valueOf(sysOperation.getId()));
		}
		super.update(sysOperation);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<SysOperation> getOperationList(SysUser sysUser) {
		String hql = "SELECT sysOperation FROM SysOperation sysOperation JOIN sysOperation.sysRight sysRight JOIN sysRight.sysRoles sysRole JOIN" +
				" sysRole.sysUsers sysUser WHERE sysUser.id = ?";
		
		return getSession().createQuery(hql).setParameter(0, sysUser.getId()).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SysOperation> getOperationList(SysRole sysRole) {
		String hql = "SELECT sysOperation FROM SysOperation sysOperation JOIN sysOperation.sysRight sysRight JOIN sysRight.sysRoles sysRole" +
				" WHERE sysRole.id = ?";
		
		return getSession().createQuery(hql).setParameter(0, sysRole.getId()).list();
	}

}
