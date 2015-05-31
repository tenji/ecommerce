<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>

<!DOCTYPE HTML>
<?xml version="1.0" encoding="utf-8" ?>
<html>
  <head>
    <title>Ram Sites Lists - ${projectName }</title>

<script type="text/javascript">
$(function() {
	// Register a new 'Ram Account'
	$(".btn-register").click(function() {
		crud.operatedObj = $(this);
		EC.createSpinner(); // 创建JS加载插件
		var url = EC.getContextPath() + $("#ajaxPathPrefix").val() + "/regEntrance";
		// 动态加载
		$("#regModal").load(url, {"id": crud.operatedObj.val()}, function(responseText, status, req) {
			if (!crud.initCallBack("regModal", responseText)) {return ;} 
			// 显示查看Modal
  			$("#modal-reg").modal({
		  		backdrop: 'static'
			});
			
			EC.deleteSpinner();
		});
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
			try{ace.settings.check('main-container' , 'fixed');}catch(e){}
		</script>

		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#">
				<span class="menu-text"></span>
			</a>
			
			<!-- 左侧菜单 -->
			<!-- 当前页面对应的二级菜单以及所属顶级菜单 -->
			<c:set var="topMenuName" value="affiliate"></c:set>
			<c:set var="subMenuName" value="ram"></c:set>
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
							<a href="#">扩展功能</a>
						</li>
						<li class="active">Ram Sites</li>
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
								Ram Sites
								<small>
									<i class="icon-double-angle-right"></i>
									Sites Lists
								</small>
							</h1>
						</div>
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<div class="table-header">
								Results for "Ram Sites"
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
										<th style="">No</th>
										<th style="">Name</th>
										<th style="">Rewards Star</th>
										<th style="">Surveys Star</th>
										<th style="">Country</th>
										<th style="">State</th>
										<th style="">City</th>
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
											<td>${st.count }</td>
											<td><c:out value="${model.name }"></c:out></td>
											<td>
												<c:forEach begin="1" end="${model.rewardsRate }">
													<i class="icon-star bigger-110"></i>
												</c:forEach>
											</td>
											<td>
												<c:forEach begin="1" end="${model.surveysRate }">
													<i class="icon-star bigger-110"></i>
												</c:forEach>
											</td>
											<td><c:out value="${model.country }"></c:out></td>
											<td><c:out value="${model.state }"></c:out></td>
											<td><c:out value="${model.city }"></c:out></td>
											<td>
											<button type="button" class="btn btn-primary btn-xs btn-read" value='${model.id }'>Detail</button>
											<button type="button" class="btn btn-primary btn-xs btn-delete"
												value='${model.id }'>Delete</button>
											<button type="button" class="btn btn-primary btn-xs btn-register"
												value='${model.id }'>Register</button>
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

							<!-- 注册Modal -->
							<div id="regModal"></div>
							
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
