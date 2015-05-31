package com.tenjishen.controller.admin.member;

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

import com.tenjishen.vo.PageBean;


/**
 * Controller - 会员管理
 * 
 * @author tenji
 * Created On: Dec 11, 2013
 *
 */
@Controller(value = "admin.MemberController")
@Scope("prototype")
public class MemberController {
	
	Member member = new Member();
	
	@Resource
	private MemberService memberService;
	private List<Member> list;
	
	private int page;	// 第几页
	private PageBean pageBean;	// 包含发布信息的Bean
	
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

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	/**
	 * 查看列表
	 * 
	 * Created On: Dec 11, 2013
	 */
	public String list() {
		if (0 != page) {
			pageBean = new PageBean();
			pageBean.setCurrentPage(page);
		}
		this.pageBean = memberService.findByPageBean(pageBean);
		
		return "list";
	}
	
	/**
	 * 查看页面入口，Ajax请求
	 * 
	 * Created On: 2013-11-26
	 */
	public String readEntrance() {
		
		return "readEntrance";
	}
	
	/**
	 * 根据编号查看，Ajax请求
	 * 
	 * Created On: Dec 12, 2013
	 */
	public void read() {
		member = memberService.getById(member.getMemberId());
		
		// JsonUtil.responseJson(JsonUtil.writeListToJson(member));
	}
	
	/**
	 * 添加页面入口，Ajax请求
	 * 
	 * Created On: Dec 12, 2013
	 */
	public String addEntrance() {
		
		return "addEntrance";
	}
	
	/**
	 * 添加，异步请求
	 * 
	 * Created On: Dec 12, 2013
	 */
	public void add() {
		
//		memberService.saveNewMember(member, categoryIds); // 保存新的品牌
		
		// 新增成功后返回新增的权限
		// JsonUtil.responseJson(JsonUtil.writeListToJson(member));
	}
	
	/**
	 * 更新页面入口，Ajax请求
	 * 
	 * Created On: Jan 20, 2014
	 */
	public String updateEntrance() {
		
		return "updateEntrance";
	}
	
	/**
	 * 更新，Ajax请求
	 * 
	 * Created On: Jan 20, 2014
	 */
	public void update() {
		
		// JsonUtil.responseJson(JsonUtil.writeListToJson(member));
	}
	
	/**
	 * 删除页面入口，Ajax请求
	 * 
	 * Created On: 2013-12-12
	 */
	public String deleteEntrance() {
		
		return "deleteEntrance";
	}
	
	/**
	 * 删除
	 * 
	 * Created On: 2013-12-12
	 */
	public void delete() {
		member = memberService.getById(member.getMemberId());
		DefaultJsonBean defaultJsonBean = null;
		
		// 存在需要删除的菜单
		if (null != member) {
			memberService.deleteById(member.getMemberId());
			// 返回正确提示
			defaultJsonBean = new DefaultJsonBean(Constants.JSON_SUCCESS, Constants.JSON_SUCCESS_VALUE);
				
		} else {
			// 返回未知错误提示
			defaultJsonBean = new DefaultJsonBean(Constants.JSON_UNDEFINED_ERROR, Constants.JSON_UNDEFINED_ERROR_VALUE);
		}
		
		// JsonUtil.responseJson(JsonUtil.writeListToJson(defaultJsonBean));
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
		
		// JsonUtil.responseJson(JsonUtil.writeListToJson(defaultJsonBean));
	}
	
}
