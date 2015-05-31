<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>

<!DOCTYPE HTML>
<?xml version="1.0" encoding="utf-8" ?>
<html>
<head>
	<title>个人资料 - ${projectName }</title>
		
<script type="text/javascript">
$(function() {
	// jquery validate验证
  	$("#form-password").validate({
  		// 焦点离开
  		onfocusout: function(event) {
  			$(event).valid();
  		}, 
		rules: {
			"loginPassword": "required",
			"confirmLoginPassword": {
				equalTo: "#loginPassword"
			}
		},
		messages: {
			"loginPassword": "请输入新密码",
			"confirmLoginPassword": "密码不匹配"
		}
	});
	
	// 更新个人资料
	$("#form-password .btn-save").on("click", function() {
		// 验证表单是否合法
  		if (!$("#form-password").valid()) {
			return ;
		}
		var url = "${path}" + "/admin/account/profile/update.htm";
  		var data = $("#form-password").serialize();
  		// 发送AJAX请求
	  	$.ajax({url: url, data: data, dataType: 'json', async: false, type: 'POST',
	  		success: function(list) {
	  			if (!handleAjaxResults(list)) {
					return ;
				}
				createNoty("success", "操作成功"); // 操作成功提醒框
	  		},
	  		error: function() {
	  			createNoty("error", "操作失败，请重试！"); // 操作失败提醒框
	  		}
	  	});
	});
	
	// 选择时间
	$(".date-picker").datepicker().next().on(ace.click_event, function(){
		$(this).prev().focus();
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
				<c:set var="topMenuName" value="account"></c:set>
				<c:set var="subMenuName" value="profile"></c:set>
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
								<a href="#">个人中心</a>
							</li>
							<li class="active">个人资料</li>
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
						<div class="page-header">
							<h1>
								个人资料
								<small>
									<i class="icon-double-angle-right"></i>&nbsp;&nbsp;基本信息
								</small>
							</h1>
						</div>
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<div class="">
									<div class="user-profile row" id="user-profile-3">
										<div class="col-sm-offset-1 col-sm-10">
											<!-- /well -->

											<div class="space"></div>

											<div class="tabbable">
												<ul class="nav nav-tabs padding-16">
													<li class="active">
														<a href="#edit-basic" data-toggle="tab">
															<i class="green icon-edit bigger-125"></i>
															基本信息
														</a>
													</li>
													<li>
														<a href="${path }/admin/account/profile/avatar">
															<i class="green icon-picture bigger-125"></i>
															头像设置
														</a>
													</li>
													<li class="">
														<a href="#edit-settings" data-toggle="tab">
															<i class="purple icon-cog bigger-125"></i>
															账户设置
														</a>
													</li>
													<li class="">
														<a href="${path }/admin/account/profile/resetPwd">
															<i class="blue icon-key bigger-125"></i>
															重置密码
														</a>
													</li>
												</ul>

												<div class="tab-content profile-edit-tab-content">
													<div class="tab-pane active" id="edit-basic">
														<form class="form-horizontal" id="form-basic">
														<h4 class="header blue bolder smaller">General</h4>
														
														<div class="form-group">
															<label for="loginName" class="col-sm-3 control-label no-padding-right">Username</label>

															<div class="col-sm-9">
																<input type="text" value="${model.loginName }" placeholder="Username" id="loginName" class="col-xs-12 col-sm-10">
															</div>
														</div>

														<div class="space-4"></div>

														<div class="form-group">
															<label for="name" class="col-sm-3 control-label no-padding-right">Name</label>

															<div class="col-sm-9">
																<input type="text" value='${model.firstName }' placeholder="First Name" id="firstName" class="input-small">
																<input type="text" value='${model.lastName }' placeholder="Last Name" id="lastName" class="input-small">
															</div>
														</div>

														<div class="form-group">
															<label for="form-field-date" class="col-sm-3 control-label no-padding-right">Birth Date</label>

															<div class="col-sm-9">
																<div class="input-medium">
																	<div class="input-group">
																		<input type="text" value='<fmt:formatDate value="${model.birthday }" pattern="dd-mm-yyyy" />' placeholder="dd-mm-yyyy" data-date-format="dd-mm-yyyy" id="birthday" class="input-medium date-picker">
																		<span class="input-group-addon">
																			<i class="icon-calendar"></i>
																		</span>
																	</div>
																</div>
															</div>
														</div>

														<div class="space-4"></div>

														<div class="form-group">
															<label class="col-sm-3 control-label no-padding-right">Gender</label>

															<div class="col-sm-9">
																<label class="inline">
																	<input type="radio" class="ace" name="sex">
																	<span class="lbl"> Male</span>
																</label>

																&nbsp; &nbsp; &nbsp;
																<label class="inline">
																	<input type="radio" class="ace" name="sex">
																	<span class="lbl"> Female</span>
																</label>
															</div>
														</div>

														<div class="space-4"></div>

														<div class="form-group">
															<label for="form-field-comment" class="col-sm-3 control-label no-padding-right">Comment</label>

															<div class="col-sm-9">
																<textarea id="form-field-comment"></textarea>
															</div>
														</div>

														<div class="space"></div>
														<h4 class="header blue bolder smaller">Contact</h4>

														<div class="form-group">
															<label for="form-field-email" class="col-sm-3 control-label no-padding-right">Email</label>

															<div class="col-sm-9">
																<span class="input-icon input-icon-right">
																	<input type="email" value="${model.email }" id="email" name="email">
																	<i class="icon-envelope"></i>
																</span>
															</div>
														</div>

														<div class="space-4"></div>

														<div class="form-group">
															<label for="form-field-website" class="col-sm-3 control-label no-padding-right">Website</label>

															<div class="col-sm-9">
																<span class="input-icon input-icon-right">
																	<input type="url" value="${model.website }" id="website" name="website">
																	<i class="icon-globe"></i>
																</span>
															</div>
														</div>

														<div class="space-4"></div>

														<div class="form-group">
															<label for="form-field-phone" class="col-sm-3 control-label no-padding-right">Phone</label>

															<div class="col-sm-9">
																<span class="input-icon input-icon-right">
																	<input type="text" id="form-field-phone" class="input-medium input-mask-phone">
																	<i class="icon-phone icon-flip-horizontal"></i>
																</span>
															</div>
														</div>

														<div class="space"></div>
														<h4 class="header blue bolder smaller">Social</h4>

														<div class="form-group">
															<label for="form-field-facebook" class="col-sm-3 control-label no-padding-right">Facebook</label>

															<div class="col-sm-9">
																<span class="input-icon">
																	<input type="text" id="form-field-facebook" value="">
																	<i class="icon-facebook blue"></i>
																</span>
															</div>
														</div>

														<div class="space-4"></div>

														<div class="form-group">
															<label for="form-field-twitter" class="col-sm-3 control-label no-padding-right">Twitter</label>

															<div class="col-sm-9">
																<span class="input-icon">
																	<input type="text" id="form-field-twitter" value="">
																	<i class="icon-twitter light-blue"></i>
																</span>
															</div>
														</div>

														<div class="space-4"></div>

														<div class="form-group">
															<label for="form-field-gplus" class="col-sm-3 control-label no-padding-right">Google+</label>

															<div class="col-sm-9">
																<span class="input-icon">
																	<input type="text" id="form-field-gplus" value="">
																	<i class="icon-google-plus red"></i>
																</span>
															</div>
														</div>
														<div class="clearfix form-actions">
															<div class="col-md-offset-3 col-md-9">
																<button type="button" class="btn btn-info btn-save">
																	<i class="icon-ok bigger-110"></i>
																	Save
																</button>
		
																&nbsp; &nbsp;
																<button type="reset" class="btn">
																	<i class="icon-undo bigger-110 btn-reset"></i>
																	Reset
																</button>
															</div>
														</div>
														</form>
													</div>
													
													<!-- 头像设置 -->
													<div class="tab-pane" id="edit-portrait">
													</div>
													
													<!-- 账户设置 -->
													<div class="tab-pane" id="edit-settings">
													</div>

													<!-- 密码设置 -->
													<div class="tab-pane" id="edit-password">
													</div>
													
												</div>
											</div>

										</div><!-- /span -->
									</div><!-- /user-profile -->
								</div>
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
