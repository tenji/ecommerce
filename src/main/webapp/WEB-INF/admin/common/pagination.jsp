<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>
<!-- 分页标签 -->

<!-- ---------------------------------Pagination Variable--------------------------------------- -->
<!-- ------------------------------------------------------------------------------------------- -->

<!-- 
	分页方案：（参考淘宝分页）
		1. 总页数小于等于5页，直接显示
		2. 总页数大于5页
			a. 当前页数大于等于7，对于当前页前的分页，只显示第1页，第2页以及当前页前的两个分页
			b. 当前页数小于7，对于当前页前的分页，全部显示
			c. 剩余页数大于等于5，对于剩余页数（包括当前页），只显示剩余页数的后4页
			d. 剩余页数小于5，对于剩余页数（包括当前页），全部显示
-->

<div class="col-sm-6">
	<div class="dataTables_info" id="sample-table-2_info">
		显示第 ${(pageBean.currentPage - 1) * pageBean.pageSize + 1 } 到 ${pageBean.currentPage == pageBean.totalPage ? pageBean.totalCount : pageBean.currentPage * pageBean.pageSize } 条，共 ${pageBean.totalCount } 条
	</div>
</div>

<div class="col-sm-6">
	<div class="dataTables_paginate paging_bootstrap">
		<ul class="pagination">
			
			<!-- 首页 -->
			<li class='<c:if test="${pageBean.currentPage == 1 }">disabled</c:if>'>
				<c:choose>
					<c:when test="${pageBean.currentPage == 1 }">
						<span>首页</span>
					</c:when>
					<c:otherwise>
						<a href="${pageBean.searchUrl }page=1">首页</a>
					</c:otherwise>
				</c:choose>
			</li>
			<!-- end -->
			
			<c:if test="${pageBean.totalPage <= 5 }"> <!-- 总页数小于等于5页 -->
				<c:forEach begin="1" end="${pageBean.totalPage }" varStatus="pagination">
					<li class='<c:if test="${pageBean.currentPage == pagination.count }">active</c:if>'>
						<a href="${pageBean.searchUrl }page=${pagination.count }">${pagination.count }</a>
					</li>
				</c:forEach>
			</c:if>
			<c:if test="${pageBean.totalPage > 5 }"> <!-- 总页数大于5页 -->
				<c:if test="${pageBean.currentPage >= 7 }"> <!-- 当前页数大于等于7 -->
		    		<c:forEach begin="1" end="2" varStatus="pagination"> <!-- 显示第一和第二页 -->
		    			<li>
						<a href="${pageBean.searchUrl }page=${pagination.count }">
							${pagination.count }
						</a>
						</li>
		    		</c:forEach>
		    		<li>
						<a>...</a>
					</li>
		    		<c:forEach begin="1" end="2" varStatus="pagination"> <!-- 显示当前页前两个分页 -->
		    			<li>
						<a href="${pageBean.searchUrl }page=${pageBean.currentPage + pagination.count - 3 }">
							${pageBean.currentPage + pagination.count - 3 }
						</a>
						</li>
		    		</c:forEach>
		    	</c:if>
		    	<c:if test="${pageBean.currentPage < 7 }"> <!-- 当前页数小于7 -->
		    		<c:forEach begin="1" end="${pageBean.currentPage - 1 }" varStatus="pagination"> <!-- 显示当前页前所有分页 -->
		    			<li>
							<a href="${pageBean.searchUrl }page=${pagination.count }">
								${pagination.count }
							</a>
						</li>
		    		</c:forEach>
		    	</c:if>
		    	<c:if test="${pageBean.totalPage - pageBean.currentPage >= 5 }"> <!-- 剩余页数大于等于5页 -->
		    		<c:forEach begin="${pageBean.currentPage }" end="${pageBean.currentPage + 4 }" varStatus="pagination"> <!-- 显示当前页后四个分页 -->
		    			<li class='<c:if test="${pagination.count == 1 }">active</c:if>'>
							<a href="${pageBean.searchUrl }page=${pageBean.currentPage + pagination.count - 1 }">
								${pageBean.currentPage + pagination.count - 1 }
							</a>
						</li>
		    		</c:forEach>
		    		<li>
						<a>...</a>
					</li>
		    	</c:if>
		    	<c:if test="${pageBean.totalPage - pageBean.currentPage < 5 }"> <!-- 剩余页数小于5页 -->
		    		<c:forEach begin="${pageBean.currentPage }" end="${pageBean.totalPage }" varStatus="pagination"> <!-- 显示剩余分页 -->
		    			<li class='<c:if test="${pagination.count == 1 }">active</c:if>'>
							<a href="${pageBean.searchUrl }page=${pageBean.currentPage + pagination.count - 1 }">
								${pageBean.currentPage + pagination.count - 1 }
							</a>
						</li>
		    		</c:forEach>
		    	</c:if>
			</c:if>

			<!-- 尾页 -->
			<li class='<c:if test="${pageBean.currentPage == pageBean.totalPage }">disabled</c:if>'>
				<c:choose>
					<c:when test="${pageBean.currentPage == pageBean.totalPage }">
						<span>尾页</span>
					</c:when>
					<c:otherwise>
						<a href="${pageBean.searchUrl }page=${pageBean.totalPage }">尾页</a>
					</c:otherwise>
				</c:choose>
			</li>
			<!-- end -->

			<li><span>共${pageBean.totalPage }页</span></li>

		</ul>
	</div>
</div>
