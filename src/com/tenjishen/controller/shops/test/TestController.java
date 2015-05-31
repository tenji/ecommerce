package com.tenjishen.controller.shops.test;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller - Email
 * 
 * @author tenji
 */
@Controller
@RequestMapping("/shops/test")
public class TestController {

	// VIEW
	protected static final String LIST_VIEW = "/admin/affiliate/email/list";
	protected static final String READ_ENTRANCE_VIEW = "/admin/affiliate/email/readEntrance";
	protected static final String UPDATE_ENTRANCE_VIEW = "/admin/affiliate/email/updateEntrance";
	protected static final String DELETE_ENTRANCE_VIEW = "/admin/affiliate/email/deleteEntrance";
	protected static final String ADD_ENTRANCE_VIEW = "/admin/affiliate/email/addEntrance";

	@RequestMapping(value = "/test20140911", method = RequestMethod.GET)
	public ModelAndView test20140911(Model model, HttpServletRequest request) {
		
		return new ModelAndView("/shops/test/test20140911");
	}
	
	@RequestMapping(value = "/test2014091101", method = RequestMethod.GET)
	public ModelAndView test2014091101(Model model, HttpServletRequest request) {
		
		return new ModelAndView("/shops/test/test2014091101");
	}
	
	@RequestMapping(value = "/test2014091102", method = RequestMethod.GET)
	public ModelAndView test2014091102(Model model, HttpServletRequest request) {
		
		return new ModelAndView("/shops/test/test2014091102");
	}
	
}
