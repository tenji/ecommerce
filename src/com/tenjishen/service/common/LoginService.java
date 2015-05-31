package com.tenjishen.service.common;


import com.tenjishen.model.SysUser;
import com.tenjishen.service.BaseService;

public interface LoginService extends BaseService<SysUser, Long> {
	
	/**
	 * 验证登录
	 * 
	 * Created On: Dec 23, 2013
	 * @param sysUser 用户对象
	 * @return
	 */
	public boolean validate(SysUser sysUser);
	
	/**
	 * 根据用户名判断用户是否存在
	 * 
	 * Created On: Dec 23, 2013
	 * @param username
	 * @return
	 */
	public boolean isUserExist(String username);
	
}
