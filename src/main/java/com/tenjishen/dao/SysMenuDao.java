package com.tenjishen.dao;

import java.util.List;

import com.tenjishen.model.SysMenu;
import com.tenjishen.model.SysRole;
import com.tenjishen.model.SysUser;

public interface SysMenuDao extends BaseDao<SysMenu, Long> {

	/**
	 * 获取指定用户菜单权限集合
	 * 
	 * @param sysUser
	 *            用户
	 * @return 菜单权限列表
	 */
	public List<SysMenu> getMenuList(SysUser sysUser);

	/**
	 * 获取指定角色菜单权限集合
	 * 
	 * @param sysRole
	 *            角色
	 * @return 菜单权限列表
	 */
	public List<SysMenu> getMenuList(SysRole sysRole);

	/**
	 * 获取所有顶级菜单集合;
	 * 
	 * @return 所有顶级菜单集合
	 * 
	 */
	public List<SysMenu> getRootMenuList();

	/**
	 * 根据SysMenu对象获取所有父类集合，若无父类则返回null;
	 * 
	 * @return 父类集合
	 * 
	 */
	public List<SysMenu> getParentMenuList(SysMenu sysMenu);

	/**
	 * 根据SysMenu对象获取所有子类集合，若无子类则返回null;
	 * 
	 * @return 子类集合
	 * 
	 */
	public List<SysMenu> getChildrenMenuList(SysMenu sysMenu);

}
