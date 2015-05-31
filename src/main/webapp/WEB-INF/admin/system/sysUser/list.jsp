<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>

<!DOCTYPE HTML>
<?xml version="1.0" encoding="utf-8" ?>
<html>
  <head>
	<title>用户列表 - ${projectName }</title>

<script type="text/javascript">

// 分配角色初始化
function assignRolesInit(operatedObj) {
	crud.operatedObj = $(operatedObj);
	spin.createSpin(); // 创建JS加载插件
	// 如果没有加载过查看Modal
	if ("" == $("#assignRolesModal").html()) {
		// 动态加载
		$("#assignRolesModal").load("${path}" + "/admin/system/sysUser/assignRolesEntrance.htm", {"id": crud.operatedObj.val()}, function(responseText, status, req) {
			if (!crud.initCallBack("assignRolesModal", responseText, status)) {return ;} 
			displayAssignRolesModal();	// 弹出分配角色Modal
		});
	} else {
		displayAssignRolesModal();
	}
}

// 分配用户组初始化
function assignGroupsInit(obj) {
	spin.createSpin(); // 创建JS加载插件
	crud.operatedObj = $(obj);
	// 动态加载
	$("#assignGroupsModal").load("${path}" + "/admin/system/sysUser/assignGroupsEntrance.htm", {"id": obj.value}, function(responseText, status, req) {
		if (!crud.initCallBack("assignGroupsModal", responseText, status)) {return ;} 
	});
}
	
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
			<c:set var="topMenuName" value="system"></c:set>
			<c:set var="subMenuName" value="sysUser"></c:set>
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
							<a href="#">系统管理</a>
						</li>
						<li class="active">用户管理</li>
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
								用户管理
								<small>
									<i class="icon-double-angle-right"></i>
									用户列表
								</small>
							</h1>
						</div>
						
						
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<div class="table-header">
								Results for "系统用户"
							</div>
							<div class="table-responsive">
							<div class="dataTables_wrapper">
								<div class="row">
									<div class="col-sm-6">
										<input type="hidden" id="ajaxPathPrefix" value="/admin/${topMenuName }/${subMenuName }" />
										<button type="button" class="btn btn-primary btn-xs btn-add">新建</button>
										<button type="button" class="btn btn-primary btn-xs btn-batchDelete batch" value="batchDelete" disabled>Batch Delete</button>
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
												<th style="">系统登录名</th>
												<th style="">姓名</th>
												<th style="">最后登陆时间</th>
												<th style="">最后登陆IP</th>
												<th style="">是否启用</th>
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
													<td>${model.loginName }</td>
													<td>${model.firstName }&nbsp;${model.lastName }</td>
													<td>
														<fmt:formatDate value="${model.lastLoginTime }" pattern="yy-MM-dd HH:mm:ss" />
													</td>
													<td>${model.lastLoginIp }</td>
													<td><i class="<c:choose><c:when test="${model.isActivated == 1 }">icon-ok</c:when><c:otherwise>icon-remove</c:otherwise></c:choose> bigger-110"></i></td>
													<td>
													<button type="button" class="btn btn-primary btn-xs btn-read"
														value='${model.id }'>查看</button>
													<button type="button" class="btn btn-primary btn-xs btn-edit"
														value='${model.id }'>编辑</button>
													<button type="button" class="btn btn-primary btn-xs btn-delete"
														value='${model.id }'>删除</button>
													<button type="button" class="btn btn-primary btn-xs"
														value='${model.id }'
														onclick="assignRolesInit(this)">分配角色</button>
													<button type="button" class="btn btn-primary btn-xs"
														value='${model.id }'
														onclick="assignGroupsInit(this)">分配用户组</button>
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

							<!-- 分配角色Modal -->
							<div id="assignRolesModal"></div>
							
							<!-- 分配用户组Modal -->
							<div id="assignGroupsModal"></div>
							
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
