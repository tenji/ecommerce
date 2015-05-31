package com.tenjishen.controller.admin.affiliate;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tenjishen.common.constants.Constants;
import com.tenjishen.model.affiliate.Email;
import com.tenjishen.model.affiliate.EvansDataAccount;
import com.tenjishen.model.affiliate.RamAccount;
import com.tenjishen.service.affiliate.EmailService;
import com.tenjishen.service.affiliate.EvansDataAccountService;
import com.tenjishen.vo.PageBean;
import com.tenjishen.vo.json.DefaultJsonBean;

/**
 * Controller - Evans Data Account
 * 
 */
@Controller
@RequestMapping("/admin/affiliate/evansDataAccount")
public class EvansDataAccountController {

	// VIEW
	protected static final String LIST_VIEW = "/admin/affiliate/evansDataAccount/list";
	protected static final String READ_ENTRANCE_VIEW = "/admin/affiliate/evansDataAccount/readEntrance";
	protected static final String UPDATE_ENTRANCE_VIEW = "/admin/affiliate/evansDataAccount/updateEntrance";
	protected static final String DELETE_ENTRANCE_VIEW = "/admin/affiliate/evansDataAccount/deleteEntrance";
	protected static final String ADD_ENTRANCE_VIEW = "/admin/affiliate/evansDataAccount/addEntrance";

	@Resource
	private EvansDataAccountService evansDataAccountService;
	@Resource
	private EmailService emailService;
	
	// 查看列表
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			EvansDataAccount evansDataAccount, Model model,
			HttpServletRequest request) {
		PageBean pageBean = new PageBean();
		if (null != page && 0 != page) {
			pageBean.setCurrentPage(page);
		}
		if (null != pageSize && 0 != pageSize) {
			pageBean.setPageSize(pageSize);
		}
		pageBean.generateSearchUrl(request); // 生成分页URL参数
		
		pageBean = evansDataAccountService.findByPageBean(pageBean);
		model.addAttribute("pageBean", pageBean);
		
		return new ModelAndView(LIST_VIEW);
	}

	// 查看页面入口，Ajax请求
	@RequestMapping("/readEntrance")
	public ModelAndView readEntrance(Long id, Model model) {
		EvansDataAccount evansDataAccount = evansDataAccountService.getById(id);
		model.addAttribute("model", evansDataAccount);

		return new ModelAndView(READ_ENTRANCE_VIEW);
	}

	// 查看，Ajax请求
	public void read() {
	}

	// 添加页面入口，Ajax请求
	@RequestMapping("/addEntrance")
	public ModelAndView addEntrance(Model model) {
		List<Email> emailList = emailService.getAll();
		model.addAttribute("emailList", emailList);

		return new ModelAndView(ADD_ENTRANCE_VIEW);
	}

	// 添加，Ajax请求
	@RequestMapping("/add")
	@ResponseBody
	public EvansDataAccount add(EvansDataAccount evansDataAccount) {
		evansDataAccount.setCreateTime(new Date()); // Create Time
		evansDataAccount.setModifyTime(new Date()); // Modify Time
		evansDataAccount.setAvailablePoints(0); // Available Points
		evansDataAccount.setPendingPoints(0); // Pending Points
		evansDataAccount = evansDataAccountService.getById(evansDataAccountService.save(evansDataAccount)); 

		return evansDataAccount;
	}

	// 删除页面入口，Ajax请求
	@RequestMapping("/deleteEntrance")
	public ModelAndView deleteEntrance() {

		return new ModelAndView(DELETE_ENTRANCE_VIEW);
	}

	// 删除，Ajax请求
	@RequestMapping("/delete")
	@ResponseBody
	public DefaultJsonBean delete(Long id) {
		EvansDataAccount evansDataAccount = evansDataAccountService.getById(id);
		DefaultJsonBean defaultJsonBean = null;

		// 存在需要删除的菜单
		if (null != evansDataAccount) {
			evansDataAccountService.deleteById(evansDataAccount.getId());
			// 返回正确提示
			defaultJsonBean = new DefaultJsonBean(Constants.JSON_SUCCESS,
					Constants.JSON_SUCCESS_VALUE);

		} else {
			// 返回未知错误提示
			defaultJsonBean = new DefaultJsonBean(
					Constants.JSON_UNDEFINED_ERROR,
					Constants.JSON_UNDEFINED_ERROR_VALUE);
		}

		return defaultJsonBean;
	}

	// 更新页面入口，Ajax请求
	@RequestMapping("/updateEntrance")
	public ModelAndView updateEntrance(Long id, Model model) {

		return new ModelAndView(UPDATE_ENTRANCE_VIEW);
	}

	// 更新，Ajax请求
	@RequestMapping("/update")
	@ResponseBody
	public RamAccount update(RamAccount ramAccount) {

		return null;
	}

	// Batch delete
	@RequestMapping("/batchDelete")
	@ResponseBody
	public DefaultJsonBean batchDelete(Long[] ids) {
		DefaultJsonBean defaultJsonBean = new DefaultJsonBean(
				Constants.JSON_SUCCESS, Constants.JSON_SUCCESS_VALUE);
		evansDataAccountService.deleteByIds(ids);

		return defaultJsonBean;
	}

	// Answer the surveys of specific account
	@RequestMapping("/answer")
	@ResponseBody
	public EvansDataAccount answer(Long id) throws Exception {
		EvansDataAccount evansDataAccount = evansDataAccountService.getById(id);
		evansDataAccountService.answerSurvey(evansDataAccount);
		
		return evansDataAccount;
	}

	// Batch answer the surveys of several accounts
	@RequestMapping("/batchAnswer")
	@ResponseBody
	public DefaultJsonBean batchAnswer(String ids) throws Exception {
		DefaultJsonBean defaultJsonBean = new DefaultJsonBean(
				Constants.JSON_SUCCESS, Constants.JSON_SUCCESS_VALUE);

		return defaultJsonBean;
	}

}
