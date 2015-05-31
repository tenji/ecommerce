<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>

<!DOCTYPE HTML>
<?xml version="1.0" encoding="utf-8" ?>
<html>
  <head>
    <title>后台登陆日志 - ${projectName }</title>
    
<script src="<c:url value="/script/highcharts/highcharts.js"></c:url>"></script>
<script type="text/javascript">
</script>
</head>
<body>
	<!-- 顶部导航 -->
	<%@ include file="/WEB-INF/admin/common/adminnavbar.jsp"%>
	
	<!-- 主容器 -->
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try{ace.settings.check('main-container' , 'fixed');}catch(e){}
		</script>

		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#">
				<span class="menu-text"></span>
			</a>
			
			<!-- 左侧菜单 -->
			<!-- 当前页面对应的二级菜单以及所属顶级菜单 -->
			<c:set var="topMenuName" value="log"></c:set>
			<c:set var="subMenuName" value="adminLogin"></c:set>
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
							<a href="#">日志管理</a>
						</li>
						<li class="active">后台登陆日志</li>
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
								后台登陆日志
								<small>
									<i class="icon-double-angle-right"></i>
									日志列表
								</small>
							</h1>
						</div>
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<div class="widget-box">
								<div class="widget-header">
									<h4>高级搜索</h4>
								</div>
								
								<div class="widget-body">
									<div class="widget-main">
										<form class="form-inline" action="${path }/admin/log/adminLogin/list.htm">
											<div class="form-group">
												<label class="" for="loginName">Login Name: </label>
												<input type="text" class="" name="loginName" placeholder="Login Name" value="${model.loginName }">
											</div>
											<div class="form-group">
													<label class="" for="pageSize">Display: </label>
														<select name="pageSize" id="pageSize" size="1">
															<option value="10" <c:if test="${pageSize==10 }"></c:if>>10</option>
															<option value="25" <c:if test="${pageSize==25 }"></c:if>>25</option>
															<option value="50" <c:if test="${pageSize==50 }"></c:if>>50</option>
															<option value="100" <c:if test="${pageSize==100 }"></c:if>>100</option>
														</select>
											</div>
											<button class="btn btn-purple btn-sm" type="submit">
												搜索
												<i class="icon-search icon-on-right bigger-110"></i>
											</button>
										</form>
									</div>
								</div>
							</div><!-- Advanced Search Widget End -->
							<div class="hr hr-18 dotted hr-double"></div>
										
							<div class="table-header">
								Results for "后台登陆日志"
							</div>
							<div class="table-responsive">
							<div class="dataTables_wrapper">
								<div class="row">
									<div class="col-sm-6">
										<input type="hidden" id="ajaxPathPrefix" value="/admin/<s:property value='#topMenuName' />/<s:property value='#subMenuName' />" />
										<button type="button" class="btn btn-primary btn-xs batch" value="batchDelete" disabled onclick="batch(this)">Batch Delete</button>
									</div>
									<div class="col-sm-6">
										
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
										<th style="">编号</th>
										<th style="">用户名</th>
										<th style="">登陆时间</th>
										<th style="">退出时间</th>
										<th style="">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${pageBean.list }" var="model" varStatus="st">
										<tr class="active">
											<td class="center sorting_disabled">
											<label>
												<input type="checkbox" class="ace item" value="${model.id }" />
												<span class="lbl"></span>
											</label>
											</td>
											<td>${st.count }</td>
											<td>${model.sysUser.loginName }</td>
											<td>
												<fmt:formatDate value="${model.loginTime }" pattern="yy-MM-dd HH:mm:ss" />
											</td>
											<td>
												<fmt:formatDate value="${model.logoutTime }" pattern="yy-MM-dd HH:mm:ss" />
											</td>
											<td>
											<button type="button" class="btn btn-primary btn-xs btn-read" value='${model.id }'>Detail</button>
											<button type="button" class="btn btn-primary btn-xs btn-delete" value='${model.id }'>Delete</button>
											<button type="button" class="btn btn-primary btn-xs btn-edit" value='${model.id }'>Edit</button>
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

							<!-- Redeem Modal -->
							<div id="redeeModal"></div>
							
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
