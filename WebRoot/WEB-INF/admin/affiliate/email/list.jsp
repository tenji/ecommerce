<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>

<!DOCTYPE HTML>
<?xml version="1.0" encoding="utf-8" ?>
<html>
  <head>
    <title>Email Accounts Lists - ${projectName }</title>
<script type="text/javascript">
$(function() {
	$("#pageSize").change(function() {
		var href = location.href;
		var searchUrl = location.search; // 获取url中"?"符后的字串
		var pageSize = $(this).val();
		if (searchUrl.length > 0) {
			if (searchUrl.indexOf("pageSize") == -1) {
				window.location.href = href + "&pageSize=" + pageSize;
			} else {
				var regExp = /pageSize=[0-9]*/;
				href = href.replace(regExp, "pageSize=" + pageSize);
				window.location.href = href;
			}
		} else {
			window.location.href = href + "?pageSize=" + pageSize;
		}
	});
});
</script>
</head>
<body>
	<!-- 顶部导航 -->
	<%@ include file="/WEB-INF/admin/common/adminnavbar.jsp"%>
	
	<!-- 主容器 -->
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try{ace.settings.check('main-container' , 'fixed')}catch(e){}
		</script>

		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#">
				<span class="menu-text"></span>
			</a>
			
			<!-- 左侧菜单 -->
			<!-- 当前页面对应的二级菜单以及所属顶级菜单 -->
			<c:set var="topMenuName" value="affiliate"></c:set>
			<c:set var="subMenuName" value="email"></c:set>
			<%@ include file="/WEB-INF/admin/common/admin-sidebar.jsp" %>

			<!-- 內容 -->
			<div class="main-content">
				<!-- 面包屑导航 -->
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try{ace.settings.check('breadcrumbs' , 'fixed');}catch(e){}
					</script>

					<ul class="breadcrumb">
						<li>
							<i class="icon-home home-icon"></i>
							<a href="#">Home</a>
						</li>

						<li>
							<a href="#">营销管理</a>
						</li>
						<li class="active">Email Accounts</li>
					</ul><!-- .breadcrumb -->

					<div class="nav-search" id="nav-search">
						<form class="form-search">
							<span class="input-icon">
								<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
								<i class="icon-search nav-search-icon"></i>
							</span>
						</form>
					</div><!-- #nav-search -->
				</div><!-- 面包屑导航结束 -->

				<!-- 内容主体 -->
				<div class="page-content">
					<div class="row">
						<div class="page-header">
							<h1>
								Email Accounts
								<small>
									<i class="icon-double-angle-right"></i>
									Accounts List
								</small>
							</h1>
						</div>
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<div class="widget-box">
								<div class="widget-header">
									<h4>Advanced Search</h4>
									<span class="widget-toolbar">
										<a data-action="settings" href="#">
											<i class="icon-cog"></i>
										</a>

										<a data-action="reload" href="#">
											<i class="icon-refresh"></i>
										</a>

										<a data-action="collapse" href="#">
											<i class="icon-chevron-up"></i>
										</a>

										<a data-action="close" href="#">
											<i class="icon-remove"></i>
										</a>
									</span>
								</div>
								
								<div class="widget-body">
									<div class="widget-main">
										<form class="form-inline" action="${path }/admin/affiliate/email/list">
											<label class="" for="email">Email: </label>
											<div class="form-group">
												<input type="text" class="" name="email" placeholder="Email" value="<c:out value="${model.email }"></c:out>">
											</div>&nbsp;&nbsp;&nbsp;
											<label class="" for="state">State: </label>
											<div class="form-group">
													<select class="form-control" name="state.id" id="state" size="1">
														<option value="">Please select specific state</option>
														<c:forEach items="${stateList }" var="state">
															<option value="${state.id }" <c:if test="${state.id == model.state.id }">selected</c:if>>${state.name }</option>
														</c:forEach>
													</select>
											</div>&nbsp;&nbsp;&nbsp;
											<br/><br/>
											<button class="btn btn-purple btn-sm" type="submit">
												Search
												<i class="icon-search icon-on-right bigger-110"></i>
											</button>
										</form>
									</div>
								</div>
							</div><!-- Advanced Search Widget End -->
							<div class="hr hr-18 dotted hr-double"></div>
							<div class="table-header">
								Results for "Email Accounts"
							</div>
							<div class="table-responsive">
							<div class="dataTables_wrapper">
								<div class="row">
									<div class="col-sm-6">
										<input type="hidden" id="ajaxPathPrefix" value="/admin/${topMenuName }/${subMenuName }" />
										<button type="button" class="btn btn-primary btn-xs btn-add"> New </button>
										<button type="button" class="btn btn-primary btn-xs batch" value="batchDelete" disabled onclick="batch(this)">Batch Delete</button>
									</div>
									<div class="col-sm-6">
										<div class="dataTables_length pull-right">
											<label>Display
											<select name="sample-table-2_length" size="1" id="pageSize">
												<option value="15" <c:if test="${param.pageSize == 15 }">selected</c:if>>15</option>
												<option value="25" <c:if test="${param.pageSize == 25 }">selected</c:if>>25</option>
												<option value="50" <c:if test="${param.pageSize == 50 }">selected</c:if>>50</option>
												<option value="100" <c:if test="${param.pageSize == 100 }">selected</c:if>>100</option>
											</select> records</label>
										</div>
									</div>
								</div>
								<table class="table table-striped table-bordered table-hover dataTable" contenteditable="false" style="">
								<thead>
									<tr>
										<th class="center sorting_disabled">
											<label>
												<input type="checkbox" class="ace" />
												<span class="lbl"></span>
											</label>
										</th>
										<th style="">Email</th>
										<th style="">State</th>
										<th style="">City</th>
										<th>Year Income</th>
										<th style="">First Name</th>
										<th style="">Last Name</th>
										<th style="">CreateTime</th>
										<th style="">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${pageBean.list }" var="model" varStatus="st">
										<tr class="active">
											<td class="center sorting_disabled">
											<label>
												<input type="checkbox" class="ace" value="${model.id }" />
												<span class="lbl"></span>
											</label>
											</td>
											<td><c:out value="${model.email }"></c:out></td>
											<td><c:out value="${model.state.name }"></c:out></td>
											<td><c:out value="${model.city.name }"></c:out></td>
											<td><c:out value="${model.averageYearIncome }"></c:out></td>
											<td><c:out value="${model.firstName }"></c:out></td>
											<td><c:out value="${model.lastName }"></c:out></td>
											<td><fmt:formatDate value="${model.createTime }" pattern="yy-MM-dd HH:mm:ss" /></td>
											<td>
											<button type="button" class="btn btn-primary btn-xs btn-read"
													value='${model.id }'>Detail</button>
											<button type="button" class="btn btn-primary btn-xs btn-delete"
												value='${model.id }'>Delete</button>
											<button type="button" class="btn btn-primary btn-xs btn-edit"
												value='${model.id }'>Edit</button>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<div class="row">
							<!-- 引入分页标签 -->
							<%@ include file="/WEB-INF/admin/common/pagination.jsp"%>
							</div>
							</div>
							</div>
							

							<!-- 添加Modal -->
							<div id="addModal"></div>

							<!-- 删除Modal -->
							<div id="deleteModal"></div>

							<!-- 查看Modal -->
							<div id="readModal"></div>

							<!-- 编辑Modal -->
							<div id="editModal"></div>
							
							<!-- PAGE CONTENT ENDS -->
						</div><!-- /.col -->
					</div><!-- /.row -->
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->

			<div class="ace-settings-container" id="ace-settings-container">
				<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
					<i class="icon-cog bigger-150"></i>
				</div>

				<div class="ace-settings-box" id="ace-settings-box">
					<div>
						<div class="pull-left">
							<select id="skin-colorpicker" class="hide">
								<option data-skin="default" value="#438EB9">#438EB9</option>
								<option data-skin="skin-1" value="#222A2D">#222A2D</option>
								<option data-skin="skin-2" value="#C6487E">#C6487E</option>
								<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
							</select>
						</div>
						<span>&nbsp; Choose Skin</span>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-navbar" />
						<label class="lbl" for="ace-settings-navbar"> Fixed Navbar</label>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-sidebar" />
						<label class="lbl" for="ace-settings-sidebar"> Fixed Sidebar</label>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-breadcrumbs" />
						<label class="lbl" for="ace-settings-breadcrumbs"> Fixed Breadcrumbs</label>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl" />
						<label class="lbl" for="ace-settings-rtl"> Right To Left (rtl)</label>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container" />
						<label class="lbl" for="ace-settings-add-container">
							Inside
							<b>.container</b>
						</label>
					</div>
				</div>
			</div><!-- /#ace-settings-container -->
		</div><!-- /.main-container-inner -->

		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
			<i class="icon-double-angle-up icon-only bigger-110"></i>
		</a>
	</div><!-- /.main-container -->
	
</body>
</html>
