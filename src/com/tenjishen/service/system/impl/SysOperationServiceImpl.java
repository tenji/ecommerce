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

import com.tenjishen.dao.SysOperationDao;
import com.tenjishen.dao.SysRightDao;
import com.tenjishen.dao.SysRoleDao;
import com.tenjishen.model.SysOperation;
import com.tenjishen.model.SysRight;
import com.tenjishen.model.SysRole;
import com.tenjishen.model.SysUser;
import com.tenjishen.service.BaseServiceImpl;
import com.tenjishen.service.system.SysOperationService;


@Service
@Transactional
public class SysOperationServiceImpl extends BaseServiceImpl<SysOperation, Long> implements SysOperationService {
	@Resource
	private SysOperationDao sysOperationDao;
	@Resource
	private SysRightDao sysRightDao;
	@Resource
	private SysRoleDao sysRoleDao;

	@Resource
	public void setBaseDao(SysOperationDao sysOperationDao) {
		super.setBaseDao(sysOperationDao);
	}

	@Override
	public SysOperation saveNewOperation(SysOperation sysOperation, Long parentId) {
		SysRight sysRight = new SysRight();
		sysRight.setRightType(1);
		sysRightDao.save(sysRight);
		
		// 如果选择添加的是顶级操作
		if (0 == parentId) {
			sysOperation.setIsTop(1);
			sysOperation.setParent(null);
		} else {
			sysOperation.setIsTop(0);
			SysOperation parentOperation = sysOperationDao.loadById(parentId);
			sysOperation.setParent(parentOperation);
		}
		sysOperation.setOrderList(1); // 排序号
		sysOperation.setCreateTime(new Date()); // 创建时间
		sysOperation.setSysRight(sysRight);
		
		sysOperationDao.save(sysOperation);
		
		return sysOperation;
	}
	
	@Override
	public List<Long> getAssignedOperationIds(SysRole sysRole) {
		List<Long> assignedItems  = new ArrayList<Long>();
		sysRole = sysRoleDao.getById(sysRole.getId());
		Set<SysRight> sysRights = sysRole.getSysRights();
		if (0 != sysRights.size()) {
			for (SysRight sysRight : sysRights) {
				if (sysRight.getRightType() == 1) {
					assignedItems.add(sysRight.getSysOperation().getId());
				}
			}
		}
		
		return assignedItems;
	}
	
	@Override
	public void saveAssignOperations(SysRole sysRole, Long[] itemIds) {
		/*----------------------------基本算法------------------------------------*/
		/* 先遍历旧的菜单权限编号集合，如果有旧的权限不在新的权限集合中，则将此对应的角色权限关联删除
		 * 然后遍历新的菜单权限编号集合，如果有新的权限不在旧的权限集合中，则添加此对应的角色权限关联
		 */
		/*----------------------------------------------------------------------*/
		
		sysRole = sysRoleDao.getById(sysRole.getId());
		Set<SysRight> sysRights = sysRole.getSysRights();
		
		// 第一趟遍历，删除未分配操作
		Iterator<SysRight> iterator = sysRights.iterator();
		while (iterator.hasNext()) {
			SysRight sysRight = iterator.next();
			if (1 == sysRight.getRightType() && !ArrayUtils.contains(itemIds, sysRight.getSysOperation().getId())) {
				sysRight.getSysRoles().remove(sysRole);
				iterator.remove();
			}
			
		}
		
		// 第二趟遍历，添加新分配菜单
		List<Long> assignedOperationIds = getAssignedOperationIds(sysRole); // 已分配操作编号集合
		for (int i = 0; i < itemIds.length; i++) {
			SysRight sysRight = sysOperationDao.getById(itemIds[i]).getSysRight();
			if (!assignedOperationIds.contains(sysRight.getRightId())) {
				sysRight.getSysRoles().add(sysRole);
				sysRole.getSysRights().add(sysRight);
			}
		}
		
		sysRoleDao.update(sysRole);
		
	}
	
	@Override
	public List<SysOperation> getOperationList(SysUser sysUser) {
		
		return sysOperationDao.getOperationList(sysUser);
	}

	@Override
	public List<SysOperation> getOperationList(SysRole sysRole) {
		
		return sysOperationDao.getOperationList(sysRole);
	}

}
