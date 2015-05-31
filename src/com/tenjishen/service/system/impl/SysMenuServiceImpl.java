package com.tenjishen.service.system.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenjishen.dao.SysMenuDao;
import com.tenjishen.dao.SysRightDao;
import com.tenjishen.dao.SysRoleDao;
import com.tenjishen.model.SysMenu;
import com.tenjishen.model.SysRight;
import com.tenjishen.model.SysRole;
import com.tenjishen.model.SysUser;
import com.tenjishen.service.BaseServiceImpl;
import com.tenjishen.service.system.SysMenuService;
import com.tenjishen.vo.MenusTreeBean;


@Service
@Transactional
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu, Long> implements SysMenuService {
	@Resource
	private SysMenuDao sysMenuDao;
	@Resource
	private SysRightDao sysRightDao;
	@Resource
	private SysRoleDao sysRoleDao;

	@Resource
	public void setBaseDao(SysMenuDao sysMenuDao) {
		super.setBaseDao(sysMenuDao);
	}

	@Override
	public SysMenu saveNewMenu(SysMenu sysMenu, Long parentId) {
		SysRight sysRight = new SysRight();
		sysRight.setRightType(0);
		sysRightDao.save(sysRight);
		
		// 如果选择添加的是顶级菜单
		if (0 == parentId) {
			sysMenu.setIsTop(1);
			sysMenu.setParent(null);
		} else {
			sysMenu.setIsTop(0);
			SysMenu parentMenu = sysMenuDao.loadById(parentId);
			sysMenu.setParent(parentMenu);
		}
		sysMenu.setOrderList(1);
		sysMenu.setCreateTime(new Date()); // 创建时间
		sysMenu.setSysRight(sysRight);
		
		sysMenuDao.save(sysMenu);
		
		return sysMenu;
	}

	@Override
	public List<Long> getAssignedMenuIds(SysRole sysRole) {
		List<Long> assignedItems  = new ArrayList<Long>();
		sysRole = sysRoleDao.getById(sysRole.getId());
		Set<SysRight> sysRights = sysRole.getSysRights();
		if (0 != sysRights.size()) {
			for (SysRight sysRight : sysRights) {
				if (sysRight.getRightType() == 0) {
					assignedItems.add(sysRight.getSysMenu().getId());
				}
			}
		}
		
		return assignedItems;
	} 
	
	@Override
	public void saveAssignMenus(SysRole sysRole, Long[] itemIds) {
		/*------------------------------基本算法----------------------------------*/
		/* 先遍历旧的菜单权限编号集合，如果有旧的权限不在新的权限集合中，则将此对应的角色权限关联删除
		 * 然后遍历新的菜单权限编号集合，如果有新的权限不在旧的权限集合中，则添加此对应的角色权限关联
		 */
		/*----------------------------------------------------------------------*/
		
		sysRole = sysRoleDao.getById(sysRole.getId());
		Set<SysRight> sysRights = sysRole.getSysRights();
		
		// 第一趟遍历，删除未分配菜单
		Iterator<SysRight> iterator = sysRights.iterator();
		while (iterator.hasNext()) {
			SysRight sysRight = iterator.next();
			if (0 == sysRight.getRightType() && !ArrayUtils.contains(itemIds, sysRight.getSysMenu().getId())) {
				sysRight.getSysRoles().remove(sysRole);
				iterator.remove();
			}
			
		}
		
		// 第二趟遍历，添加新分配菜单
		List<Long> assignedMenuIds = getAssignedMenuIds(sysRole); // 已分配菜单编号集合
		for (int i = 0; i < itemIds.length; i++) {
			SysRight sysRight = sysMenuDao.getById(itemIds[i]).getSysRight();
			if (!assignedMenuIds.contains(sysRight.getRightId())) {
				sysRight.getSysRoles().add(sysRole);
				sysRole.getSysRights().add(sysRight);
			}
		}
		
		sysRoleDao.update(sysRole);
		
	}

	@Override
	public List<SysMenu> getMenuList(SysUser sysUser) {
		List<SysMenu> sysMenus = sysMenuDao.getMenuList(sysUser);
		
		return sysMenus;
	}
	
	@Override
	public List<MenusTreeBean> getMenusTreeByUser(SysUser sysUser) {
		/*------------------------------基本算法----------------------------------*/
		/* 先获取用户拥有的顶级菜单权限，然后遍历顶级菜单，获取二级菜单并生成菜单权限树，可扩展
		 */
		/*----------------------------------------------------------------------*/
		List<MenusTreeBean> menusTreeBeans = new ArrayList<MenusTreeBean>();
		List<SysMenu> sysMenus = getMenuList(sysUser);
		for (SysMenu topMenu : sysMenus) {
			// 顶级菜单
			if (0 == topMenu.getLevel()) {
				MenusTreeBean menusTreeBean = new MenusTreeBean(topMenu.getId(), topMenu.getMenuName(), topMenu.getMenuDescription(),
						topMenu.getUrl(), topMenu.getIconClass());
				menusTreeBeans.add(menusTreeBean);
				
				List<SysMenu> sysSubMenus = sysMenuDao.getChildrenMenuList(topMenu); // 子菜单
				
				for (SysMenu subMenu : sysSubMenus) {
					menusTreeBean.getSubMenus().add(new MenusTreeBean(subMenu.getId(), subMenu.getMenuName(), 
							subMenu.getMenuDescription(), subMenu.getUrl(), subMenu.getIconClass()));
				}
			}
		}
		
		return menusTreeBeans;
	}

	@Override
	public List<SysMenu> getRootMenuList() {
		
		return sysMenuDao.getRootMenuList();
	}

	@Override
	public List<SysMenu> getMenuTreeList() {
		List<SysMenu> allMenuList = this.getAll();
		return recursivMenuTreeList(allMenuList, null, null);
	}
	
	// 递归父类排序分类树
	private List<SysMenu> recursivMenuTreeList(List<SysMenu> allMenuList, SysMenu s, List<SysMenu> temp) {
		if (temp == null) {
			temp = new ArrayList<SysMenu>();
		}
		for (SysMenu sysMenu : allMenuList) {
			SysMenu parent = sysMenu.getParent();
			if ((s == null && parent == null) || (sysMenu != null && parent == s)) {
				temp.add(sysMenu);
				if (sysMenu.getChildren() != null && sysMenu.getChildren().size() > 0) {
					recursivMenuTreeList(allMenuList, sysMenu, temp);
				}
			}
		}
		return temp;
	}

	@Override
	public List<SysMenu> getMenuList(SysRole sysRole) {
		
		return sysMenuDao.getMenuList(sysRole);
	}

}
