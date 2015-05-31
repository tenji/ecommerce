package com.tenjishen.vo.session;

import java.util.List;

import com.tenjishen.model.SysOperation;

/**
 * 用于在session中保存会员登录信息的Bean
 * 
 * Created On: 2014-1-24
 * @author TENJI
 *
 */
public class MemberSessionBean {
	
	private Long memberId; // 会员编号
	
	private String memberName;	// 用户名
	
	private String uri;			// 用户访问的uri
	
	private List<SysOperation> sysOperationList; // 用户可执行的操作权限集合
	
	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}
	
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public List<SysOperation> getSysOperationList() {
		return sysOperationList;
	}

	public void setSysOperationList(List<SysOperation> sysOperationList) {
		this.sysOperationList = sysOperationList;
	}
	
}
