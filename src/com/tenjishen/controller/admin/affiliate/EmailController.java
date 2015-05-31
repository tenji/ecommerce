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
import com.tenjishen.model.affiliate.City;
import com.tenjishen.model.affiliate.Email;
import com.tenjishen.model.affiliate.State;
import com.tenjishen.service.affiliate.CityService;
import com.tenjishen.service.affiliate.EmailService;
import com.tenjishen.service.affiliate.StateService;
import com.tenjishen.vo.PageBean;
import com.tenjishen.vo.json.DefaultJsonBean;

/**
 * Controller - Email
 * 
 * @author tenji
 */
@Controller
@RequestMapping("/admin/affiliate/email")
public class EmailController {

	// VIEW
	protected static final String LIST_VIEW = "/admin/affiliate/email/list";
	protected static final String READ_ENTRANCE_VIEW = "/admin/affiliate/email/readEntrance";
	protected static final String UPDATE_ENTRANCE_VIEW = "/admin/affiliate/email/updateEntrance";
	protected static final String DELETE_ENTRANCE_VIEW = "/admin/affiliate/email/deleteEntrance";
	protected static final String ADD_ENTRANCE_VIEW = "/admin/affiliate/email/addEntrance";

	@Resource
	private EmailService emailService;
	@Resource
	private StateService stateService;
	@Resource
	private CityService cityService;

	// 查看列表
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			Email email, Model model, HttpServletRequest request) {
		PageBean pageBean = new PageBean();
		if (null != page && 0 != page) {
			pageBean.setCurrentPage(page);
		}
		if (null != pageSize && 0 != pageSize) {
			pageBean.setPageSize(pageSize);
		}
		pageBean.generateSearchUrl(request); // 生成分页URL参数
		
		if (null == email) {
			pageBean = emailService.findByPageBean(pageBean);
		} else {
			pageBean = emailService.findByPageBean(pageBean, email);
		}
		List<State> stateList = stateService.getAll();
		model.addAttribute("model", email);
		model.addAttribute("stateList", stateList);
		model.addAttribute("pageBean", pageBean);

		return new ModelAndView(LIST_VIEW);
	}

	// 查看页面入口，Ajax请求
	@RequestMapping("/readEntrance")
	public ModelAndView readEntrance(Long id, Model model) {
		Email email = emailService.getById(id);
		model.addAttribute("model", email);

		return new ModelAndView(READ_ENTRANCE_VIEW);
	}

	// 查看，Ajax请求
	public void read() {
	}

	// 添加页面入口，Ajax请求
	@RequestMapping("/addEntrance")
	public ModelAndView addEntrance(Model model) {
		List<State> stateList = stateService.getAll(); // Get all the states
		model.addAttribute("stateList", stateList);

		return new ModelAndView(ADD_ENTRANCE_VIEW);
	}

	// 添加，Ajax请求
	@RequestMapping("/add")
	@ResponseBody
	public Email add(Email email) {
		email.setCreateTime(new Date());
		emailService.save(email);

		// 新增成功后返回新增的权限
		return email;
	}

	// 删除页面入口，Ajax请求
	@RequestMapping("/deleteEntrance")
	@ResponseBody
	public ModelAndView deleteEntrance() {

		return new ModelAndView(DELETE_ENTRANCE_VIEW);
	}

	// 删除，Ajax请求
	@RequestMapping("/delete")
	@ResponseBody
	public DefaultJsonBean delete(Long id) {
		Email email = emailService.getById(id);
		DefaultJsonBean defaultJsonBean = null;

		// 存在需要删除的菜单
		if (null != email) {
			emailService.deleteById(email.getId());
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

	// 更新界面入口
	@RequestMapping("/updateEntrance")
	public ModelAndView updateEntrance(Long id, Model model) {
		Email email = emailService.getById(id);
		List<State> stateList = stateService.getAll(); // List of states
		// List of cities of specific state
		List<City> cityList = cityService.getCityList(email.getState()); 
		model.addAttribute("stateList", stateList);
		model.addAttribute("cityList", cityList);
		model.addAttribute("model", email);

		return new ModelAndView(UPDATE_ENTRANCE_VIEW);
	}

	// 更新
	@RequestMapping("/update")
	@ResponseBody
	public Email update(Email email) {
		Email oldEmail = emailService.getById(email.getId());
		oldEmail.setEmail(email.getEmail());
		oldEmail.setAddress(email.getAddress());
		oldEmail.setBirthday(email.getBirthday());
		oldEmail.setCity(email.getCity());
		oldEmail.setFirstName(email.getFirstName());
		oldEmail.setLastName(email.getLastName());
		oldEmail.setPostalCode(email.getPostalCode());
		oldEmail.setState(email.getState());
		oldEmail.setAverageYearIncome(email.getAverageYearIncome());
		oldEmail.setCompany(email.getCompany());
		oldEmail.setSeniorityLevelId(email.getSeniorityLevelId());
		oldEmail.setJobRoleId(email.getJobRoleId());
		oldEmail.setIndustryId(email.getIndustryId());
		oldEmail.setModifyTime(new Date());
		emailService.update(oldEmail);

		return email;
	}

	// Get cities of specific state
	@RequestMapping("/getCities")
	@ResponseBody
	public List<City> getCities(Email email, Model model) {
		List<City> cityList = cityService.getCityList(email.getState());

		return cityList;
	}
	
}
