package com.tenjishen.controller.shops;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tenjishen.model.Product;
import com.tenjishen.service.product.ProductImgService;
import com.tenjishen.service.product.ProductService;
import com.tenjishen.service.shops.member.MemberService;

/**
 * Controller - 首页
 * 
 * @author tenji
 *
 */
@Controller
public class IndexController {
	
	// VIEW
	protected static final String INDEX_VIEW = "/shops/index";

	@Resource
	private MemberService memberService;
	@Resource
	private ProductService productService;
	@Resource
	private ProductImgService productImgService;

	// 首页
	@RequestMapping("/index")
	public ModelAndView index(Model model) {
		List<Product> productList = productService.getAll();
		model.addAttribute("productList", productList);
		
		return new ModelAndView(INDEX_VIEW);
	}
	
}
