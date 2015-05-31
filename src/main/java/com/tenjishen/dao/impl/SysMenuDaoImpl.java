package com.tenjishen.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tenjishen.dao.SysMenuDao;
import com.tenjishen.model.SysMenu;
import com.tenjishen.model.SysRole;
import com.tenjishen.model.SysUser;

@Repository
public class SysMenuDaoImpl extends BaseDaoImpl<SysMenu, Long> implements SysMenuDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<SysMenu> getMenuList(SysUser sysUser) {
		String hql = "SELECT sysMenu FROM SysMenu sysMenu JOIN sysMenu.sysRight sysRight JOIN sysRight.sysRoles sysRole JOIN" +
				" sysRole.sysUsers sysUser WHERE sysUser = ?";
		
		return getSession().createQuery(hql).setParameter(0, sysUser).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SysMenu> getMenuList(SysRole sysRole) {
		String hql = "SELECT sysMenu FROM SysMenu sysMenu JOIN sysMenu.sysRight sysRight JOIN sysRight.sysRoles sysRole" +
				" WHERE sysRole = ?";
		
		return getSession().createQuery(hql).setParameter(0, sysRole).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SysMenu> getRootMenuList() {
		String hql = "FROM SysMenu sysMenu WHERE sysMenu.parent IS NULL ORDER BY sysMenu.orderList ASC";
		return getSession().createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SysMenu> getParentMenuList(SysMenu sysMenu) {
		String hql = "from SysMenu sysMenu where sysMenu != ? and sysMenu.id in(:ids) order by sysMenu.orderList asc";
		String[] ids = sysMenu.getPath().split(SysMenu.PATH_SEPARATOR);
		return getSession().createQuery(hql).setParameter(0, sysMenu).setParameterList("ids", ids).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SysMenu> getChildrenMenuList(SysMenu sysMenu) {
		String hql = "from SysMenu sysMenu where sysMenu != ? and sysMenu.path like ? order by sysMenu.orderList asc";
		return getSession().createQuery(hql).setParameter(0, sysMenu).setParameter(1, sysMenu.getPath() + SysMenu.PATH_SEPARATOR + "%").list();
	}
	
	// 重写方法，保存时计算path值
	@Override
	public Long save(SysMenu sysMenu) {
		Long id = super.save(sysMenu);
		SysMenu parent = sysMenu.getParent();
		if (parent != null) {
			String parentPath = parent.getPath();
			sysMenu.setPath(parentPath + SysMenu.PATH_SEPARATOR + id);
		} else {
			sysMenu.setPath(String.valueOf(id));
		}
		super.update(sysMenu);
		return id;
	}
		
	// 重写方法，更新时计算path值
	@Override
	public void update(SysMenu sysMenu) {
		SysMenu parent = sysMenu.getParent();
		if (parent != null) {
			String parentPath = parent.getPath();
			sysMenu.setPath(parentPath + SysMenu.PATH_SEPARATOR + sysMenu.getId());
		} else {
			sysMenu.setPath(String.valueOf(sysMenu.getId()));
		}
		super.update(sysMenu);
	}

}
