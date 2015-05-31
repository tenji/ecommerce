package com.tenjishen.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tenjishen.dao.SysGroupDao;
import com.tenjishen.model.SysGroup;
import com.tenjishen.model.SysUser;

@Repository
public class SysGroupDaoImpl extends BaseDaoImpl<SysGroup, Long> implements SysGroupDao {
	
	// 重写方法，保存时计算path值
	@Override
	public Long save(SysGroup sysGroup) {
		Long id = super.save(sysGroup);
		SysGroup parent = sysGroup.getParent();
		if (parent != null) {
			String parentPath = parent.getPath();
			String parentPathName = parent.getPathName();
			sysGroup.setPath(parentPath + SysGroup.PATH_SEPARATOR + id); // 设置树路径
			sysGroup.setPathName(parentPathName + SysGroup.PATH_SEPARATOR + sysGroup.getName()); // 设置树名称
		} else {
			sysGroup.setPath(String.valueOf(id)); // 设置树路径
			sysGroup.setPathName(sysGroup.getName()); // 设置树名称
		}
		super.update(sysGroup);
		return id;
	}
			
	// 重写方法，更新时计算path值
	@Override
	public void update(SysGroup sysGroup) {
		SysGroup parent = sysGroup.getParent();
		if (parent != null) {
			String parentPath = parent.getPath();
			String parentPathName = parent.getPathName();
			sysGroup.setPath(parentPath + SysGroup.PATH_SEPARATOR + sysGroup.getId()); // 设置树路径
			sysGroup.setPathName(parentPathName + SysGroup.PATH_SEPARATOR + sysGroup.getName()); // 设置树名称
		} else {
			sysGroup.setPath(String.valueOf(sysGroup.getId())); // 设置树路径
			sysGroup.setPathName(sysGroup.getName()); // 设置树名称
		}
		super.update(sysGroup);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getAssignedGroupIds(SysUser sysUser) {
		String hql = "SELECT sysGroup.id FROM SysGroup sysGroup JOIN sysGroup.sysUsers sysUser WHERE sysUser.id = ?";
		
		return getSession().createQuery(hql).setParameter(0, sysUser.getId()).list();
	}

}
