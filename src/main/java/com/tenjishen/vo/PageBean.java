package com.tenjishen.vo;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * Bean类 - 分页
 * 
 * @author TENJI
 * 
 */
@SuppressWarnings("unused")
public class PageBean {
	
	// 排序方式
	public enum OrderType {
		asc, desc
	}
	
	private final String PAGE = "page"; // 当前页参数名
	
	private long totalCount = 0; // 总记录数
	private int totalPage = 0; // 总页数
	private int currentPage = 1; // 当前页码
	private int pageSize = 15; // 每页记录数

	private boolean isFirstPage; // 是否为第一页
	private boolean isLastPage; // 是否为最后一页
	private boolean hasPreviousPage; // 是否有前一页
	private boolean hasNextPage; // 是否有下一页
	
	private String orderBy = "id"; // 排序字段，默认是逻辑编号
	private OrderType orderType = OrderType.desc; // 排序方式

	private String searchUrl = ""; // 分页URL参数（不包括当前页参数）
	
	private List<Object> list; // 要返回的某一页的记录列表

	// 初始化分页信息
	public void init() {
		this.isFirstPage = isFirstPage();
		this.isLastPage = isLastPage();
		this.hasPreviousPage = isHasPreviousPage();
		this.hasNextPage = isHasNextPage();
	}

	// 生成分页URL参数
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void generateSearchUrl(HttpServletRequest request) {
		Map map =request.getParameterMap(); // 请求参数和值集合
		
		// 遍历Map集合并拼接分页URL
		Iterator<Object> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>)iterator.next();
			String key = (String)entry.getKey();
			String value = ((String[])entry.getValue())[0];
			if (!PAGE.equals(key)) {
				searchUrl += key + "=" + value + "&";
			}
			
		}

		searchUrl = request.getRequestURI() + "?" + searchUrl;
	}

	/*************************** 以下判断页的信息 **********************************/

	public boolean isFirstPage() {
		return currentPage == 1; // 如是当前页是第1页
	}

	public boolean isLastPage() {
		return currentPage == totalPage; // 如果当前页是最后一页
	}

	public boolean isHasPreviousPage() {
		return currentPage != 1; // 只要当前页不是第1页
	}

	public boolean isHasNextPage() {
		return currentPage != totalPage; // 只要当前页不是最后1页
	}

	/**************************************************************************/

	/******************************** 静态方法 ************************************/
	/**
	 * 计算总页数，供外部直接通过类名调用
	 * 
	 * @param pageSize
	 *            每页记录数
	 * @param allRow
	 *            总记录数
	 * @return 总页数
	 */
	public static int countTotalPage(final int pageSize, final int totalCount) {
		int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize
				: totalCount / pageSize + 1;

		return totalPage;
	}

	/**
	 * 计算当前页开始记录
	 * 
	 * @param pageSize
	 *            每页记录数
	 * @param currentPage
	 *            当前第几页
	 * @return 当前页开始记录数
	 */
	public static int countOffset(final int pageSize, final int currentPage) {
		final int offSet = pageSize * (currentPage - 1);

		return offSet;
	}



	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public String getSearchUrl() {
		return searchUrl;
	}

	public void setSearchUrl(String searchUrl) {
		this.searchUrl = searchUrl;
	}
	
	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}
	
}
