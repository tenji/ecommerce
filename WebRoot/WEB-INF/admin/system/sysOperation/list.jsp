<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>

<!DOCTYPE HTML>
<?xml version="1.0" encoding="utf-8" ?>
<html>
  <head>
    <title>操作权限列表 - ${projectName }</title>

<script type="text/javascript">
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
			<c:set var="topMenuName" value="system"></c:set>
			<c:set var="subMenuName" value="sysOperation"></c:set>
			<%@ include file="/WEB-INF/admin/common/admin-sidebar.jsp" %>

			<!-- 內容 -->
			<div class="main-content">
				<!-- 面包屑导航 -->
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
					</script>

					<ul class="breadcrumb">
						<li>
							<i class="icon-home home-icon"></i>
							<a href="#">Home</a>
						</li>

						<li>
							<a href="#">系统管理</a>
						</li>
						<li class="active">操作管理</li>
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
								操作管理
								<small>
									<i class="icon-double-angle-right"></i>
									操作列表
								</small>
							</h1>
						</div>
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<div class="table-header">
								Results for "操作权限"
							</div>
							<div class="table-responsive">
							<div class="dataTables_wrapper">
								<div class="row">
									<div class="col-sm-6">
										<input type="hidden" id="ajaxPathPrefix" value="/admin/${topMenuName }/${subMenuName }" />
										<button type="button" class="btn btn-primary btn-xs btn-add">新建</button>
										<button type="button" class="btn btn-primary btn-xs batch" value="batchDelete" disabled onclick="batch(this)">Batch Delete</button>
									</div>
									<div class="col-sm-6">
										<div class="dataTables_length pull-right">
											<label>Display <select name="sample-table-2_length" size="1"><option
														value="10" selected="selected">10</option>
													<option value="25">25</option>
													<option value="50">50</option>
													<option value="100">100</option>
											</select> records</label>
										</div>
									</div>
								</div>
									<table class="table table-striped table-bordered table-hover dataTable" contenteditable="false" style="">
										<thead>
											<tr>
												<th class="center">
												<label>
													<input type="checkbox" class="ace" />
													<span class="lbl"></span>
												</label>
												</th>
												<th style="">操作编号</th>
												<th style="">操作名称</th>
												<th style="">操作URL</th>
												<th style="">其他</th>
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
													<td>${model.id }</td>
													<td><c:out value="${model.operationName }"></c:out></td>
													<td><c:out value="${model.url }"></c:out></td>
													<td>
													<button type="button" class="btn btn-primary btn-xs btn-read" data-opr="read" value='${model.id }'>查看</button>
													<button type="button" class="btn btn-primary btn-xs btn-edit" data-opr="edit" value='${model.id }'>编辑</button>
													<button type="button" class="btn btn-primary btn-xs btn-delete" data-opr="delete" value='${model.id }'>删除</button>
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

			<!-- /#ace-settings-container -->
			<%@ include file="/WEB-INF/admin/common/setting-container.jsp" %>
			
		</div><!-- /.main-container-inner -->

		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
			<i class="icon-double-angle-up icon-only bigger-110"></i>
		</a>
	</div><!-- /.main-container -->
</body>
</html>
