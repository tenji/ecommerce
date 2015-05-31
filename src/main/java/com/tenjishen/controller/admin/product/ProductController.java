package com.tenjishen.controller.admin.product;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tenjishen.common.constants.Constants;
import com.tenjishen.common.util.FileUtil;
import com.tenjishen.common.util.HttpUtil;
import com.tenjishen.model.Brand;
import com.tenjishen.model.Category;
import com.tenjishen.model.Product;
import com.tenjishen.service.product.ProductService;
import com.tenjishen.vo.json.DefaultJsonBean;

import com.tenjishen.vo.PageBean;

/**
 * Controller - 产品管理
 * 
 * @author tenji
 *
 */
@Controller(value = "admin.ProductController")
@Scope("prototype")
public class ProductController {

	Product product = new Product();
	
	@Resource
	private ProductService productService;
	private List<Product> list;
	
	private int page;	// 第几页
	private PageBean pageBean;	// 包含发布信息的Bean
	
	private Long categoryId; // 所属类目编号
	private Long brandId; // 所属品牌编号
	
    private File mainImg; 
    private String mainImgContentType; // 上传的文件的数据类型 
    private String mainImgFileName; // 上传的文件的名称 
	
	// 查看列表
	public String list() {
		if (0 != page) {
			pageBean = new PageBean();
			pageBean.setCurrentPage(page);
		}
		this.pageBean = productService.findByPageBean(pageBean);
		
		return "list";
	}
	
	// 查看页面入口，Ajax请求
	public String readEntrance() {
		product = productService.getById(product.getId());
		
		return "readEntrance";
	}
	
	// 查看，Ajax请求
	public void read() {
		product = productService.getById(product.getId());
		
		// JsonUtil.responseJson(JsonUtil.writeListToJson(product));
	}
	
	// 添加页面入口，Ajax请求
	public String addEntrance() {
		
		return "addEntrance";
	}
	
	// 进入新增页面
	public String create() {
		
		return "create";
	}
	
	// 添加，Ajax请求
	public void add() {
		Category category = new Category(categoryId);
		Brand brand = new Brand(brandId);
		product.setCategory(category);
		product.setBrand(brand);
		product.setCreateTime(new Date());
		productService.save(product); // 保存新的产品
		
		// 新增成功后返回新增的权限
		// JsonUtil.responseJson(JsonUtil.writeListToJson(product));
	}
	
	// 删除页面入口，异步请求
	public String deleteEntrance() {
		
		return "deleteEntrance";
	}
	
	// 删除
	public void delete() {
		product = productService.getById(product.getId());
		DefaultJsonBean defaultJsonBean = null;
		
		// 存在需要删除的菜单
		if (null != product) {
			productService.deleteById(product.getId());
			// 返回正确提示
			defaultJsonBean = new DefaultJsonBean(Constants.JSON_SUCCESS, Constants.JSON_SUCCESS_VALUE);
		} else {
			// 返回未知错误提示
			defaultJsonBean = new DefaultJsonBean(Constants.JSON_UNDEFINED_ERROR, Constants.JSON_UNDEFINED_ERROR_VALUE);
		}
		
		// JsonUtil.responseJson(JsonUtil.writeListToJson(defaultJsonBean));
	}
	
	// 进入编辑页面
	public String edit() {
		product = productService.getById(product.getId());
		
		return "edit";
	}
	
	// 更新，Ajax请求
	public void update() {
		Product existProduct = productService.getById(product.getId());
		existProduct.setBrand(new Brand(brandId));
		existProduct.setCategory(new Category(categoryId));
		existProduct.setDescription(product.getDescription());
		existProduct.setDetail(product.getDetail());
		existProduct.setListPrice(product.getListPrice());
		existProduct.setName(product.getName());
		
		// String path = getRequest().getSession().getServletContext().getRealPath("/");
		String path ="";
		
		// 图片处理
		String filePath = path + "\\images\\products\\";
		try {
			FileUtil.copyFile(mainImg, filePath + existProduct.getId() + ".jpg");
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		
		// 每次更新产品，都重新生成产品静态页
		path += "\\static\\product\\"; // 静态页面存放目录
		String staticPageUrl = "/static/product/product" + existProduct.getId() + ".html";
		
		try {
			HttpUtil.createHTML("http://localhost:8080/ECommerce/shops/product/product/item.htm?id=" + existProduct.getId(), 
					path + "product" + existProduct.getId() + ".html");
		} catch (IOException e) {
		}
		
		existProduct.setStaticPageUrl(staticPageUrl);
		productService.update(existProduct);
		
		// JsonUtil.responseJson(JsonUtil.writeListToJson(existProduct));
	}
	
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Product> getList() {
		return list;
	}

	public void setList(List<Product> list) {
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

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public File getMainImg() {
		return mainImg;
	}

	public void setMainImg(File mainImg) {
		this.mainImg = mainImg;
	}

	public String getMainImgContentType() {
		return mainImgContentType;
	}

	public void setMainImgContentType(String mainImgContentType) {
		this.mainImgContentType = mainImgContentType;
	}

	public String getMainImgFileName() {
		return mainImgFileName;
	}

	public void setMainImgFileName(String mainImgFileName) {
		this.mainImgFileName = mainImgFileName;
	}
	
}
