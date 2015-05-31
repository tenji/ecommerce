package com.tenjishen.controller.shops.member;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tenjishen.common.constants.Constants;
import com.tenjishen.common.util.encryption.MD5Util;
import com.tenjishen.model.Member;
import com.tenjishen.service.shops.member.MemberService;
import com.tenjishen.vo.json.DefaultJsonBean;



/**
 * Controller - 会员管理
 * 
 * @author tenji
 *
 */
@Controller(value = "shops.MemberController")
@Scope("prototype")
public class MemberController {

	Member member = new Member();
	
	@Resource
	private MemberService memberService;
	private List<Member> list;
	
	public Member getMember() {
		return member;
	}

	public void setCategory(Member member) {
		this.member = member;
	}

	public List<Member> getList() {
		return list;
	}

	public void setList(List<Member> list) {
		this.list = list;
	}

	// 注册新会员
	public void register() {
		DefaultJsonBean defaultJsonBean = new DefaultJsonBean();
		
		member.setPassword(MD5Util.getMD5(member.getPassword())); // MD5加密
		member.setCreateDate(new Date());
		memberService.save(member);
		
		defaultJsonBean = new DefaultJsonBean(Constants.JSON_SUCCESS, Constants.JSON_SUCCESS_VALUE);
		
		// JsonUtil.responseJson(JsonUtil.writeListToJson(defaultJsonBean));
	}
	
	// 会员登陆
	public void login() {
		DefaultJsonBean defaultJsonBean = new DefaultJsonBean();
		if (memberService.verifyLogin(member)) {
			defaultJsonBean = new DefaultJsonBean(Constants.JSON_SUCCESS, Constants.JSON_SUCCESS_VALUE);
		} else {
			defaultJsonBean = new DefaultJsonBean(Constants.JSON_UNDEFINED_ERROR, Constants.JSON_UNDEFINED_ERROR_VALUE);
		}
		
		// JsonUtil.responseJson(JsonUtil.writeListToJson(defaultJsonBean));
	}
	
	// 会员注销
	public void logout() {
		/*
		getRequest().getSession()
		.removeAttribute(Constants.MEMBER_SESSION_BEAN); // 清除session，退出登陆
		
		try {
			getResponse().sendRedirect(getRequest().getContextPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
	}
	
	// 会员中心
	public String index() {
		
		return "index";
	}
	
	// 账户信息
	public String memberInfo() {
		// member = memberService.getById(getMemberId());
		
		return "memberInfo";
	}
	
	// 更多个人信息
	public String moreInfo() {
		// member = memberService.getById(getMemberId());
		
		return "moreInfo";
	}
	
	// 会员等级
	public String memberRank() {
		// member = memberService.getById(getMemberId());
		
		return "memberRank";	
	}
	
}
