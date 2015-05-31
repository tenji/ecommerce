<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.tenjishen.vo.session.SystemSessionBean"%>
<%@ page import="com.tenjishen.common.constants.Constants"%>
<%@ include file="/common/customTaglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>后台登陆 - ${projectName }</title>
<%
	// 如果已经登录，则直接进入后台，显示系统菜单 
	SystemSessionBean systemSessionBean = (SystemSessionBean) request
			.getSession().getAttribute(Constants.SYSTEM_SESSION_BEAN);
	if (null != systemSessionBean) {
		if (null != systemSessionBean.getUserName())
			response.sendRedirect(request.getContextPath()
					+ "/admin/myDashboard");
	}
%>
<style>
html {
	background: url(${path}/assets/admin/images/background.jpg) no-repeat center center
		fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
}

body {
	padding-top: 20px;
	font-size: 16px;
	font-family: "Open Sans", serif;
	background: transparent;
}

h1 {
	font-family: "Abel", Arial, sans-serif;
	font-weight: 400;
	font-size: 40px;
}

.margin-base-vertical {
	margin: 40px 0;
}

/* Override B3 .panel adding a subtly transparent background */
.panel {
	background-color: rgba(255, 255, 255, 0.9);
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$("#loginForm").validate({
			// 焦点离开
	  		onfocusout: function(event) {
	  			$(event).valid();
	  		}, 
			rules : {
				loginName : {
					required : true,
					remote : {
						url : "${path}" + "/admin/isUserExist",
						type : "post",
						date : {
							username : function() {
								return $("#username").val();
							}
						}
					}, 
				},
				loginPassword : "required",
				validateCode : {
					required : true, 
					remote: {
						url : "${path}" + "/admin/isValidateCodeRight",
						type : "post",
						date : {
							username : function() {
								return $("#validateCode").val();
							}
						}
					}
				}
			},
			messages : {
				username : {
					remote : "用户不存在", 
					validateCode: "验证码不正确"
				}
			}
		});

		$('#loginForm').submit(function() {
			// 验证表单是否合法
	  		if (!$("#loginForm").valid()) {
				return ;
			}
			$("#login-btn").text("登陆中...");
		});
	});
</script>
</head>

<body>
	<div class="container">
		<div class="row">

			<div class="col-md-6 col-md-offset-3 panel panel-default">
				<h1 class="margin-base-vertical">请先登陆后台管理系统</h1>

				<form name="loginForm" id="loginForm" action="${path}/admin/loginProcess" method="post">
					<p class="input-group">
						<span class="input-group-addon">用户名：</span>
						<input type="text" class="form-control input-lg" name="loginName" id="loginName" placeholder="请输入用户名" />
					</p>
					<p class="input-group">
						<span class="input-group-addon">密&#12288;码：</span>
						<input type="password" class="form-control input-lg" name="loginPassword" id="loginPassword" placeholder="请输入密码" />
					</p>
					<div class="input-group">
						<span class="input-group-addon">验证码：</span>
						<input type="text" class="form-control input-lg" name="validateCode"
							class=" input-lg login_validateText" />
						<span class="input-group-addon">
							<img src="${path }/admin/validateCode?code=" onclick="this.src=this.src+Math.random()" alt="验证码" title="验证码"
								class="login_validate" />
						</span>
					</div>
					<p class="text-center">
						<button type="submit" id="login-btn" class="btn btn-success btn-lg">登陆</button>
					</p>
				</form>

			</div>
		</div>
	</div>
</body>
</html>
