package com.tenjishen.controller.admin.affiliate;

import java.util.Date;
import java.util.List;
import java.util.Random;

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
import com.tenjishen.common.util.ArrayUtil;
import com.tenjishen.model.affiliate.Ram;
import com.tenjishen.model.affiliate.RamAccount;
import com.tenjishen.service.affiliate.RamAccountService;
import com.tenjishen.service.affiliate.RamService;
import com.tenjishen.vo.json.DefaultJsonBean;
import com.tenjishen.vo.query.affiliate.RamAccountQuery;

import com.tenjishen.vo.PageBean;

/**
 * Controller - Rampanel Account
 * 
 */
@Controller
@RequestMapping("/admin/affiliate/ramAccount")
public class RamAccountController {

	// VIEW
	protected static final String LIST_VIEW = "/admin/affiliate/ramAccount/list";
	protected static final String READ_ENTRANCE_VIEW = "/admin/affiliate/ramAccount/readEntrance";
	protected static final String UPDATE_ENTRANCE_VIEW = "/admin/affiliate/ramAccount/updateEntrance";
	protected static final String DELETE_ENTRANCE_VIEW = "/admin/affiliate/ramAccount/deleteEntrance";
	protected static final String ADD_ENTRANCE_VIEW = "/admin/affiliate/ramAccount/addEntrance";

	@Resource
	private RamAccountService ramAccountService;
	@Resource
	private RamService ramService;
	
	private int oprProgress; // 操作进度

	// 查看列表
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			RamAccountQuery ramAccountQuery, Model model,
			HttpServletRequest request) {
		PageBean pageBean = new PageBean();
		if (null != page && 0 != page) {
			pageBean.setCurrentPage(page);
		}
		if (null != pageSize && 0 != pageSize) {
			pageBean.setPageSize(pageSize);
		}
		pageBean.generateSearchUrl(request); // 生成分页URL参数

		if (null == ramAccountQuery) {
			pageBean = ramAccountService.findByPageBean(pageBean);
		} else {
			pageBean = ramAccountService.findByPageBean(pageBean, ramAccountQuery);
		}
		List<Ram> ramList = ramService.getAll("avgRate", false);
		model.addAttribute("model", ramAccountQuery);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("ramList", ramList);

		return new ModelAndView(LIST_VIEW);
	}

	// 查看页面入口，Ajax请求
	@RequestMapping("/readEntrance")
	public ModelAndView readEntrance(Long id, Model model) {
		RamAccount ramAccount = ramAccountService.getById(id);
		model.addAttribute("model", ramAccount);

		return new ModelAndView(READ_ENTRANCE_VIEW);
	}

	// 查看，Ajax请求
	public void read() {
	}

	// 添加页面入口，Ajax请求
	@RequestMapping("/addEntrance")
	public ModelAndView addEntrance(Model model) {
		List<Ram> ramList = ramService.getAll("avgRate", false);
		model.addAttribute("ramList", ramList);

		return new ModelAndView(ADD_ENTRANCE_VIEW);
	}

	// 添加，Ajax请求
	@RequestMapping("/add")
	@ResponseBody
	public RamAccount add(RamAccount ramAccount) {
		ramAccount.setCreateTime(new Date());
		ramAccount.setUnansweredNums(0);
		ramAccount.setPointsBeforeOpr(0);
		ramAccount.setPointsAfterOpr(0);
		ramAccount.setRedeemStatus(0);
		ramAccountService.save(ramAccount);

		// 新增成功后返回新增的权限
		return ramAccount;
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
		RamAccount ramAccount = ramAccountService.getById(id);
		DefaultJsonBean defaultJsonBean = null;

		// 存在需要删除的菜单
		if (null != ramAccount) {
			ramAccountService.deleteById(ramAccount.getId());
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
		RamAccount ramAccount = ramAccountService.getById(id);
		List<Ram> ramList = ramService.getAll();
		model.addAttribute("model", ramAccount);
		model.addAttribute("ramList", ramList);

		return new ModelAndView(UPDATE_ENTRANCE_VIEW);
	}

	// 更新，Ajax请求
	@RequestMapping("/update")
	@ResponseBody
	public RamAccount update(RamAccount ramAccount) {
		RamAccount existRamAccount = ramAccountService.getById(ramAccount
				.getId());
		existRamAccount.setEmail(ramAccount.getEmail());
		existRamAccount.setLoginName(ramAccount.getLoginName());
		existRamAccount.setLoginPassword(ramAccount.getLoginPassword());
		existRamAccount.setLoginUrl(ramAccount.getLoginUrl());
		existRamAccount.setPointsBeforeOpr(ramAccount.getPointsBeforeOpr());
		existRamAccount.setPointsAfterOpr(ramAccount.getPointsAfterOpr());
		existRamAccount.setModifyTime(new Date()); // Modify Time
		if (null != ramAccount.getRam()) {
			existRamAccount.setRam(ramAccount.getRam());
		}
		ramAccountService.update(existRamAccount); // Update Ram Account

		return existRamAccount;
	}

	// Batch delete
	@RequestMapping("/batchDelete")
	@ResponseBody
	public DefaultJsonBean batchDelete(Long[] ids) {
		DefaultJsonBean defaultJsonBean = new DefaultJsonBean(
				Constants.JSON_SUCCESS, Constants.JSON_SUCCESS_VALUE);
		ramAccountService.deleteByIds(ids);

		return defaultJsonBean;
	}

	// Check unanswered surveys of specific account
	@RequestMapping("/check")
	@ResponseBody
	public RamAccount check(Long id) throws Exception {
		RamAccount ramAccount = ramAccountService.getById(id);
		ramAccount = ramAccountService.checkSurveyNums(ramAccount);

		return ramAccount;
	}

	// Batch check surveys of several accounts
	public DefaultJsonBean batchCheck(Long[] ids) throws Exception {
		DefaultJsonBean defaultJsonBean = new DefaultJsonBean(
				Constants.JSON_SUCCESS, Constants.JSON_SUCCESS_VALUE);
		for (int i = 0; i < ids.length; i++) {
			RamAccount ramAccount = ramAccountService.getById(ids[i]);
			ramAccount = ramAccountService.checkSurveyNums(ramAccount);

			Thread.sleep(new Random().nextInt(300000)); // 每次点击随机暂停300秒以内
		}

		return defaultJsonBean;
	}

	// Answer the surveys of specific account
	@RequestMapping("/answer")
	@ResponseBody
	public RamAccount answer(Long id) throws Exception {
		RamAccount ramAccount = ramAccountService.getById(id);
		ramAccount = ramAccountService.answerSurvey(ramAccount); // answer the survey

		return ramAccount;
	}
	
	// Answer entend
	@RequestMapping("/answerExtend")
	@ResponseBody
	public RamAccount answerExtend(Long id) throws Exception {
		RamAccount ramAccount = ramAccountService.getById(id);
		ramAccount = ramAccountService.answerSurveyExtend20141015(ramAccount); // answer the survey

		return ramAccount;
	}

	// Batch answer the surveys of several accounts
	@RequestMapping("/batchAnswer")
	@ResponseBody
	public DefaultJsonBean batchAnswer(String ids) throws Exception {
		DefaultJsonBean defaultJsonBean = new DefaultJsonBean(
				Constants.JSON_SUCCESS, Constants.JSON_SUCCESS_VALUE);
		String[] accountIds = ids.split(",");
		accountIds = ArrayUtil.shuffle(accountIds); // 打乱ID顺序
		
		for (int i = 0; i < accountIds.length; i++) {
			try {
				RamAccount ramAccount = ramAccountService.getById(Long.valueOf(accountIds[i]));

				ramAccount = ramAccountService.answerSurveyExtend20141015(ramAccount); // Answer the survey(Extended)

				Thread.sleep(new Random().nextInt(180000)); // Random stop within 180 seconds(3 minutes)
				this.oprProgress = (int) Math.round(((i + 1)*1.0 / accountIds.length) * 100); // 修改操作进度
			} catch (Exception e) {
				continue ; // ignore all the exceptions
			}
		}

		return defaultJsonBean;
	}
	
	// Redeem points of specific account
	@RequestMapping("/redeem")
	@ResponseBody
	public RamAccount redeem(Long id) {
		RamAccount ramAccount = ramAccountService.getById(id);
		if (ramAccount.getRedeemStatus() == 0) {
			// if 'Redeem', then update redeem time
			ramAccount.setLatestRedeemTime(new Date());
		}
		ramAccount.setRedeemStatus((ramAccount.getRedeemStatus() == 0) ? 1 : 0); // update redeem status
		ramAccountService.update(ramAccount);
		
		return ramAccount;
	}
	
	// Get Operation Progress
	@RequestMapping("/getOprProgress")
	@ResponseBody
	public String getOprProgress() {
		
		return String.valueOf(this.oprProgress);
	}

}
