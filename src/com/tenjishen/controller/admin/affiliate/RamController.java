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
import com.tenjishen.model.affiliate.Ram;
import com.tenjishen.service.affiliate.EmailService;
import com.tenjishen.service.affiliate.RamService;
import com.tenjishen.vo.PageBean;
import com.tenjishen.vo.json.DefaultJsonBean;

/**
 * Controller - Rampanel Site
 * 
 * @author tenji
 *
 */
@Controller
@RequestMapping("/admin/affiliate/ram")
public class RamController {
	
	// VIEW
	protected static final String LIST_VIEW = "/admin/affiliate/ram/list";
	protected static final String READ_ENTRANCE_VIEW = "/admin/affiliate/ram/readEntrance";
	protected static final String UPDATE_ENTRANCE_VIEW = "/admin/affiliate/ram/updateEntrance";
	protected static final String DELETE_ENTRANCE_VIEW = "/admin/affiliate/ram/deleteEntrance";
	protected static final String ADD_ENTRANCE_VIEW = "/admin/affiliate/ram/addEntrance";
	protected static final String REG_ENTRANCE_VIEW = "/admin/affiliate/ram/regEntrance";

	@Resource
	private RamService ramService;
	@Resource
	private EmailService emailService;
	
	// 查看列表
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			Ram ram, Model model, HttpServletRequest request) {
		PageBean pageBean = new PageBean();
		if (null != page && 0 != page) {
			pageBean.setCurrentPage(page);
		}
		if (null != pageSize && 0 != pageSize) {
			pageBean.setPageSize(pageSize);
		}
		pageBean.setOrderBy("avgRate"); // 按照星级排序
		pageBean.generateSearchUrl(request); // 生成分页URL参数
		
		pageBean = ramService.findByPageBean(pageBean);
		model.addAttribute("pageBean", pageBean);
		
		return new ModelAndView(LIST_VIEW);
	}
	
	// 查看页面入口，Ajax请求
	@RequestMapping("/readEntrance")
	public ModelAndView readEntrance(Long id, Model model) {
		Ram ram = ramService.getById(id);
		model.addAttribute("model", ram);
		
		return new ModelAndView(READ_ENTRANCE_VIEW);
	}
	
	// 查看，Ajax请求
	public void read() {
	}
	
	// 添加页面入口，Ajax请求
	@RequestMapping("/addEntrance")
	public ModelAndView addEntrance() {
		
		return new ModelAndView(ADD_ENTRANCE_VIEW);
	}
	
	// 添加，Ajax请求
	@RequestMapping("/add")
	@ResponseBody
	public Ram add(Ram ram) {
		ram.setAvgRate(ram.getRewardsRate() + ram.getSurveysRate()); // 设置总体评级
		ram.setCreateTime(new Date());
		ram.setModifyTime(new Date());
		ramService.save(ram);
		
		// 新增成功后返回新增的权限
		return ram;
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
		Ram ram = ramService.getById(id);
		DefaultJsonBean defaultJsonBean = null;
		
		// 存在需要删除的菜单
		if (null != ram) {
			ramService.deleteById(ram.getId());
			// 返回正确提示
			defaultJsonBean = new DefaultJsonBean(Constants.JSON_SUCCESS, Constants.JSON_SUCCESS_VALUE);
				
		} else {
			// 返回未知错误提示
			defaultJsonBean = new DefaultJsonBean(Constants.JSON_UNDEFINED_ERROR, Constants.JSON_UNDEFINED_ERROR_VALUE);
		}
		
		return defaultJsonBean;
	}
	
	// 注册Ram Account账号入口
	@RequestMapping("/regEntrance")
	public ModelAndView regEntrance(Long id, Model model) {
		List<Email> emailList = emailService.getUnregisteredEmailList(ramService.getById(id));
		model.addAttribute("emailList", emailList);
		
		return new ModelAndView(REG_ENTRANCE_VIEW);
	}
	
	// 注册Ram Account账号
	@RequestMapping("/reg")
	@ResponseBody
	public DefaultJsonBean register(Long ramId, Long emailId) throws Exception {
		DefaultJsonBean defaultJsonBean = null;
		if (ramService.regRamAccount(ramId, emailId)) {
			defaultJsonBean = new DefaultJsonBean(Constants.JSON_SUCCESS, Constants.JSON_SUCCESS_VALUE);
		} else {
			defaultJsonBean = new DefaultJsonBean(Constants.JSON_UNDEFINED_ERROR, Constants.JSON_UNDEFINED_ERROR_VALUE);
		}
		
		return defaultJsonBean;
	}
	
}
