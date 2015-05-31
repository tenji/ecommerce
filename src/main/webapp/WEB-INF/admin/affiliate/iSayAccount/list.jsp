<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>

<!DOCTYPE HTML>
<?xml version="1.0" encoding="utf-8" ?>
<html>
  <head>
    <title>ISay Accounts Lists - ${projectName }</title>
    
<script src="<c:url value="/assets/admin/js/plugin/highcharts/highcharts.js"></c:url>"></script>
<script type="text/javascript">

	// 初始化函数的回调处理
	function initCallBack(modalId, responseText) {
		// 加载页面失败
		if ("error" == status) {
			EC.createNoty("error", "页面加载失败！");
			return false;
		}
		// 如果responseText是JSON格式
		if (isJsonFormat(responseText)) {
			responseText = eval ("(" + responseText + ")");
			
			var result = handleAjaxResults(responseText);
			if (result) {
			
			} else {
				$("#" + modalId).html(""); // 清空Modal内容
				
			}
			return result;
		}
		
		return true;
	}
	
	// Batch Opr
	function batch(batchButton) {
		EC.showLoadingLayer(); // 添加加载层
		
		var ids = ""; // id串
		var $checkedInputs = $(batchButton).closest('.table-responsive').find("input[type='checkbox'][class='ace item']:checked");
		$checkedInputs.each(function(index) {
			if (0 == index) { ids = this.value; return true; }
			ids += (',' + this.value);
		});
		
		var interval = setInterval(function() {
			var url = "${path}" + "/admin/affiliate/ramAccount/getOprProgress";
		  	$.ajax({
		  		url: url, dataType: 'json', data: {},	type: 'POST', async: false, 
		  		success: function(progress) {
		  			EC.changeLoadingProgress(progress);
		  		}
		  	});
		}, 120000); // 每2分钟获取一次
		if ("batchDelete" == batchButton.value) {
			// 发送AJAX请求
			var url = "${path}" + "/admin/affiliate/ramAccount/batchDelete";
		  	$.ajax({
		  		url: url, dataType: 'json', data: {"ids" : ids},	type: 'POST', async: true, 
		  		success: function(list) {
		  			EC.createNoty("success", "Successfully!");
		  			EC.delLoadingLayer(); // 删除加载层
		  		},
		  		error: function() {
		  			EC.createNoty("error", "操作失败，请重试！");
		  			return ;
		  		}
		  	});
		} else if ("batchAnswer" == batchButton.value) {
			// 发送AJAX请求
			var url = "${path}" + "/admin/affiliate/ramAccount/batchAnswer";
		  	$.ajax({
		  		url: url, dataType: 'json', data: {"ids" : ids}, type: 'POST', async: true, 
		  		success: function(list) {
		  			EC.createNoty("success", "Successfully!");
		  			EC.delLoadingLayer(); // 删除加载层
		  			clearInterval(interval);
		  			setTimeout(function() { location.reload(true); }, 10000); // Reload after 10 seconds
		  		}
		  	});
		}
		
	}
	
	// Answer survey of specific account
	function answerInit(buttonObj) {
		EC.showLoadingLayer(); // 添加加载层
		// 发送AJAX请求
		var url = "${path}" + "/admin/affiliate/ramAccount/answer";
	  	$.ajax({
	  		url: url, dataType: 'json',	data: {"id": buttonObj.value}, type: 'POST', async: false, 
	  		success: function(list) {
	  			EC.createNoty("success", "更新成功");
	  			$(buttonObj).closest("tr").find(".unanswered").html(list.unansweredNums); // Change unanswered nums
	  			$(buttonObj).closest("tr").find(".pointsBeforeOpr").html(list.pointsBeforeOpr); // Change unanswered nums
	  			$(buttonObj).closest("tr").find(".pointsAfterOpr").html(list.pointsAfterOpr); // Change unanswered nums
	  			
	  			EC.delLoadingLayer(); // 删除加载层
	  		},
	  		error: function() {
	  			EC.createNoty("error", "检测失败，请重试！");
	  			EC.delLoadingLayer(); // 删除加载层
	  			return ;
	  		}
	  	});
	}
	
	// Redeem points of specific account
	function redeemInit(buttonObj) {
		EC.showLoadingLayer(); // 添加加载层
		var url = "${path}" + "/admin/affiliate/ramAccount/redeem";
	  	$.ajax({
	  		url: url, dataType: 'json',	data: {"id": buttonObj.value}, type: 'POST', async: false, 
	  		success: function(list) {
	  			EC.createNoty("success", "更新成功");
	  			$(buttonObj).closest("tr").find(".redeemStatus").html("Redeeming"); // Change unanswered nums
	  			
	  			EC.delLoadingLayer(); // 删除加载层
	  		},
	  		error: function() {
	  			EC.createNoty("error", "检测失败，请重试！");
	  			EC.delLoadingLayer(); // 删除加载层
	  			return ;
	  		}
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
			<c:set var="topMenuName" value="affiliate"></c:set>
			<c:set var="subMenuName" value="iSayAccount"></c:set>
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
						<li class="active">ISay Accounts</li>
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
								ISay Accounts
								<small>
									<i class="icon-double-angle-right"></i>
									Accounts Lists
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
										<form class="form-inline" action="${path }/admin/affiliate/ramAccount/list">
											<label class="" for="email">Email: </label>
											<div class="form-group">
												<input type="text" class="" name="email" placeholder="Email" value="<c:out value="${model.email }"></c:out>">
											</div>&nbsp;&nbsp;&nbsp;
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
								Results for "ISay Accounts"
							</div>
							<div class="table-responsive">
							<div class="dataTables_wrapper">
								<div class="row">
									<div class="col-sm-6">
										<input type="hidden" id="ajaxPathPrefix" value="/admin/${topMenuName }/${subMenuName }" />
										<button type="button" class="btn btn-primary btn-xs btn-add">New</button>
										<button type="button" class="btn btn-primary btn-xs btn-batchDelete batch" value="batchDelete" disabled onclick="batch(this)">Batch Delete</button>
										<button type="button" class="btn btn-primary btn-xs batch" value="batchAnswer" disabled onclick="batch(this)">Batch Answer</button>
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
										<th style="">EMail</th>
										<th>Login Name</th>
										<th>Login Password</th>
										<th style="">Points</th>
										<th style="">Latest Redeem Time</th>
										<th style="">Latest Opr Time</th>
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
											<td>${model.email.email }</td>
											<td>${model.loginName }</td>
											<td>${model.loginPassword }</td>
											<td class="points">${model.points }</td>
											<td><fmt:formatDate value="${model.latestRedeemTime }" pattern="yy-MM-dd HH:mm:ss" /></td>
											<td><fmt:formatDate value="${model.latestOprTime }" pattern="yy-MM-dd HH:mm:ss" /></td>
											<td>
											<button type="button" class="btn btn-primary btn-xs btn-read" value='${model.id }'>Detail</button>
											<button type="button" class="btn btn-primary btn-xs btn-delete" value='${model.id }'>Delete</button>
											<button type="button" class="btn btn-primary btn-xs btn-edit" value='${model.id }'>Edit</button>
											<button type="button" class="btn btn-primary btn-xs"
												value='${model.id }'
												onclick="answerInit(this)">Answer</button>
											<button type="button" class="btn btn-primary btn-xs"
												value='${model.id }'
												onclick="redeemInit(this)">Redeem</button></td>
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
