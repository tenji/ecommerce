package com.tenjishen.service.system;

import java.util.List;

import com.tenjishen.model.SysMenu;
import com.tenjishen.model.SysRole;
import com.tenjishen.model.SysUser;
import com.tenjishen.service.BaseService;
import com.tenjishen.vo.MenusTreeBean;


public interface SysMenuService extends BaseService<SysMenu, Long> {
	
	/**
	 * 保存新添加的菜单权限
	 * 
	 * @param sysMenu
	 * @param parentId 父菜单编号
	 */
	public SysMenu saveNewMenu(SysMenu sysMenu, Long parentId);
	
	/**
	 * 根据用户角色获取已分配菜单权限编号集合
	 * 
	 * @param roleId 角色编号
	 */
	public List<Long> getAssignedMenuIds(SysRole sysRole);
	
	/**
	 * 保存新分配的菜单权限
	 * 
	 * @param roleId
	 * @param items
	 */
	public void saveAssignMenus(SysRole sysRole, Long[] itemIds);
	
	/**
	 * 获取指定用户菜单权限集合
	 * 
	 * @param sysUser 用户
	 * @return
	 */
	public List<SysMenu> getMenuList(SysUser sysUser);
	
	/**
	 * 用户加载菜单权限树
	 * 
	 * @param sysUser
	 * @return
	 */
	public List<MenusTreeBean> getMenusTreeByUser(SysUser sysUser);
	
	/**
	 * 获取所有顶级菜单集合;
	 * 
	 * @return 所有顶级菜单集合
	 * 
	 */
	public List<SysMenu> getRootMenuList();
	
	/**
	 * 获取菜单树集合;
	 * 
	 * @return 菜单树集合
	 * 
	 */
	public List<SysMenu> getMenuTreeList();
	
	/**
	 * 获取指定角色菜单权限集合
	 * 
	 * @param sysRole
	 *            角色
	 * @return 菜单权限列表
	 */
	public List<SysMenu> getMenuList(SysRole sysRole);
}
