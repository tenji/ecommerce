package com.tenjishen.vo.session;

import java.util.Date;
import java.util.List;

import com.tenjishen.model.SysOperation;
import com.tenjishen.vo.MenusTreeBean;

/**
 * 用于在session中保存用户登录信息的Bean
 * 
 * Created On: 2013-11-3
 * @author TENJI
 *
 */
public class SystemSessionBean {
	
	private Long userId; // 用户编号
	private String userName; // 显示用户名
	private String url;	// 用户访问的url
	private Date loginTime; // 登陆时间
	
	private List<MenusTreeBean> menusTree; // 用户可访问的菜单权限树
	private List<SysOperation> sysOperationList; // 用户可执行的操作权限集合
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public List<MenusTreeBean> getMenusTree() {
		return menusTree;
	}

	public void setMenusTree(List<MenusTreeBean> menusTree) {
		this.menusTree = menusTree;
	}

	public List<SysOperation> getSysOperationList() {
		return sysOperationList;
	}

	public void setSysOperationList(List<SysOperation> sysOperationList) {
		this.sysOperationList = sysOperationList;
	}
	

}
