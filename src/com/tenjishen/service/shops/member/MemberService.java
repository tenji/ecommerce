package com.tenjishen.service.shops.member;

import com.tenjishen.model.Member;
import com.tenjishen.service.BaseService;


public interface MemberService extends BaseService<Member, Long> {
	
	/**
	 * 验证登陆
	 * 
	 * Created On: 2014-1-24
	 * @param member 会员对象
	 */
	public boolean verifyLogin(Member member);
	
}
