package com.tenjishen.service.system.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import com.tenjishen.dao.SysGroupDao;
import com.tenjishen.dao.SysUserDao;
import com.tenjishen.model.SysGroup;
import com.tenjishen.model.SysUser;
import com.tenjishen.service.BaseServiceImpl;
import com.tenjishen.service.system.SysGroupService;


@Service
public class SysGroupServiceImpl extends BaseServiceImpl<SysGroup, Long> implements SysGroupService {
	
	@Resource
	private SysGroupDao sysGroupDao;
	@Resource
	private SysUserDao sysUserDao;
	
	@Resource
	public void setBaseDao(SysGroupDao sysGroupDao) {
		super.setBaseDao(sysGroupDao);
	}

	@Override
	public List<Long> getAssignedGroupIds(SysUser sysUser) {
		return sysGroupDao.getAssignedGroupIds(sysUser);
	}

	@Override
	public void saveAssignGroups(SysUser sysUser, Long[] itemIds) {
		/*----------------------------基本算法------------------------------------*/
		/* 先遍历旧的用户组编号集合，如果有旧的用户组不在新的用户组集合中，则将此对应的用户用户组关联删除
		 * 然后遍历新的用户组编号集合，如果有新的用户组不在旧的用户组集合中，则添加此对应的用户用户组关联
		 */
		/*----------------------------------------------------------------------*/
		
		sysUser = sysUserDao.getById(sysUser.getId());
		Set<SysGroup> sysGroups = sysUser.getSysGroups();
		
		// 第一趟遍历，删除未分配用户组
		Iterator<SysGroup> iterator = sysGroups.iterator();
		while (iterator.hasNext()) {
			SysGroup sysGroup = iterator.next();
			if (!ArrayUtils.contains(itemIds, sysGroup.getId())) {
				sysGroup.getSysUsers().remove(sysUser);
				iterator.remove();
			}
			
		}
		
		// 第二趟遍历，添加新分配用户组
		List<Long> assignedMenuIds = getAssignedGroupIds(sysUser); // 已分配用户组编号集合
		for (int i = 0; i < itemIds.length; i++) {
			if (!assignedMenuIds.contains(itemIds[i])) {
				SysGroup sysGroup = sysGroupDao.getById(itemIds[i]);
				sysGroup.getSysUsers().add(sysUser);
				sysUser.getSysGroups().add(sysGroup);
			}
		}
		
		sysUserDao.update(sysUser);
		
	}
	
}
