package com.tenjishen.service.shops.member.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenjishen.common.util.encryption.MD5Util;
import com.tenjishen.dao.MemberDao;
import com.tenjishen.model.Member;
import com.tenjishen.service.BaseServiceImpl;
import com.tenjishen.service.shops.member.MemberService;
import com.tenjishen.vo.session.MemberSessionBean;


@Service("shops.memberService")
@Transactional
public class MemberServiceImpl extends BaseServiceImpl<Member, Long> implements MemberService {
	@Resource
	private MemberDao memberDao;

	@Resource
	public void setBaseDao(MemberDao memberDao) {
		super.setBaseDao(memberDao);
	}

	@Override
	public boolean verifyLogin(Member member) {
		Member legalMember = memberDao.getByPropertyName("memberName", member.getMemberName()); // 合法用户
		if (null != legalMember && 
				legalMember.getPassword().equals(MD5Util.getMD5(member.getPassword()))) {
			// 设置会员Session
			MemberSessionBean memberSessionBean = new MemberSessionBean();
			memberSessionBean.setMemberId(legalMember.getMemberId());
			memberSessionBean.setMemberName(member.getMemberName()); 
			// ServletActionContext.getRequest().getSession().setAttribute(Constants.MEMBER_SESSION_BEAN, 
			//		memberSessionBean);
			
			// 设置客户端Cookie
			// HttpServletResponse response = ServletActionContext.getResponse();
			// HttpUtil.setCookie(response, "member", legalMember.getPassword(), 3600*24*365, "/");
			
			return true;
		}
		
		return false;
	}

}
