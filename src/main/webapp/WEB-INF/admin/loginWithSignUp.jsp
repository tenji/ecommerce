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

<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="keywords" content="Ecommerce, Open Ecommerce">
<meta http-equiv="description" content="Open Ecommerce">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="shortcut icon" href="${path }/assets/shops/ico/favicon.ico">

<link href="${path }/assets/admin/css/bootstrap.min.css" rel="stylesheet" />
<link href="${path }/assets/admin/css/font-awesome.min.css" rel="stylesheet" />
<link href="${path }/assets/admin/css/ace-fonts.css" rel="stylesheet" />
<link href="${path }/assets/admin/css/ace.min.css" rel="stylesheet" />
<link href="${path }/assets/admin/js/plugin/jquery/jquery.validation/css/jquery.validation.css" rel="stylesheet" />

<script src="${path }/assets/admin/js/plugin/jquery/jquery-2.0.3.js"></script>
<script src="${path }/assets/admin/js/plugin/jquery/jquery.validation/jquery.validate.js"></script>
<script src="${path }/assets/admin/js/plugin/jquery/jquery.validation/additional-methods.js"></script>
<script src="${path }/assets/admin/js/common/ec.lib.js"></script>
<script src="${path }/assets/admin/js/common/ec.login.js"></script>
</head>

<body class="login-layout">
<div class="main-container">
	<div class="main-content">
		<div class="row">
			<div class="col-sm-10 col-sm-offset-1">
				<div class="login-container">
					<div class="center">
						<h1>
							<i class="icon-leaf green"></i>
							<span class="red">ECommerce</span>
						</h1>
						<h4 class="blue">&copy; TENJI LIMITED</h4>
					</div>

					<div class="space-6"></div>

					<div class="position-relative">
						<div id="login-box" class="login-box visible widget-box no-border">
							<div class="widget-body">
								<div class="widget-main">
									<h4 class="header blue lighter bigger">
										<i class="icon-coffee green"></i>
										请输入登陆信息
									</h4>

									<div class="space-6"></div>

									<form id="loginForm" name="loginForm" action="${path}/admin/loginProcess" method="post">
										<fieldset>
											<label class="block clearfix">
												<span class="block input-icon input-icon-right">
													<input type="text" class="form-control" id="loginName" name="loginName" placeholder="用户名" />
													<i class="icon-user"></i>
												</span>
											</label>

											<label class="block clearfix">
												<span class="block input-icon input-icon-right">
													<input type="password" class="form-control" id="loginPassword" name="loginPassword" placeholder="密码" />
													<i class="icon-lock"></i>
												</span>
											</label>
											
											<div class="input-group">
												<input type="text" class="form-control input-lg" name="validateCode" id="validateCode"
													class="input-lg login_validateText" placeholder="验证码" />
												<span class="input-group-addon">
													<img src="${path }/admin/validateCode?code=" onclick="this.src=this.src+Math.random()" alt="验证码" title="验证码"
														class="login_validate" style="width:80px;height:30px;" />
												</span>
											</div>

											<div class="space"></div>

											<div class="clearfix">
												<label class="inline">
													<input type="checkbox" class="ace" id="chkRememberMe" checked name="chkRememberMe" value="0" />
													<span class="lbl"> 下次自动登陆</span>
												</label>

												<button type="button" class="width-35 pull-right btn btn-sm btn-primary btn-login">
													<i class="icon-key"></i><b>登陆</b>
												</button>
											</div>

											<div class="space-4"></div>
										</fieldset>
									</form>

									<div class="social-or-login center">
										<span class="bigger-110">Or Login Using</span>
									</div>

									<div class="social-login center">
										<a class="btn btn-primary">
											<i class="icon-facebook"></i>
										</a>

										<a class="btn btn-info">
											<i class="icon-twitter"></i>
										</a>

										<a class="btn btn-danger">
											<i class="icon-google-plus"></i>
										</a>
									</div>
								</div><!-- /widget-main -->

								<div class="toolbar clearfix">
									<div>
										<a href="#" onclick="ec_login.show_box('forgot-box'); return false;" class="forgot-password-link">
											<i class="icon-arrow-left"></i>
											忘记密码
										</a>
									</div>

									<div>
										<a href="#" onclick="ec_login.show_box('signup-box'); return false;" class="user-signup-link">
											注册
											<i class="icon-arrow-right"></i>
										</a>
									</div>
								</div>
							</div><!-- /widget-body -->
						</div><!-- /login-box -->

						<div id="forgot-box" class="forgot-box widget-box no-border">
							<div class="widget-body">
								<div class="widget-main">
									<h4 class="header red lighter bigger">
										<i class="icon-key"></i>
										Retrieve Password
									</h4>

									<div class="space-6"></div>
									<p>
										Enter your email and to receive instructions
									</p>

									<form>
										<fieldset>
											<label class="block clearfix">
												<span class="block input-icon input-icon-right">
													<input type="email" class="form-control" placeholder="Email" />
													<i class="icon-envelope"></i>
												</span>
											</label>

											<div class="clearfix">
												<button type="button" class="width-35 pull-right btn btn-sm btn-danger">
													<i class="icon-lightbulb"></i>
													Send Me!
												</button>
											</div>
										</fieldset>
									</form>
								</div><!-- /widget-main -->

								<div class="toolbar center">
									<a href="#" onclick="ec_login.show_box('login-box'); return false;" class="back-to-login-link">
										Back to login
										<i class="icon-arrow-right"></i>
									</a>
								</div>
							</div><!-- /widget-body -->
						</div><!-- /forgot-box -->

						<div id="signup-box" class="signup-box widget-box no-border">
							<div class="widget-body">
								<div class="widget-main">
									<h4 class="header green lighter bigger">
										<i class="icon-group blue"></i>
										New User Registration
									</h4>

									<div class="space-6"></div>
									<p> Enter your details to begin: </p>

									<form>
										<fieldset>
											<label class="block clearfix">
												<span class="block input-icon input-icon-right">
													<input type="email" class="form-control" placeholder="Email" />
													<i class="icon-envelope"></i>
												</span>
											</label>

											<label class="block clearfix">
												<span class="block input-icon input-icon-right">
													<input type="text" class="form-control" placeholder="Username" />
													<i class="icon-user"></i>
												</span>
											</label>

											<label class="block clearfix">
												<span class="block input-icon input-icon-right">
													<input type="password" class="form-control" placeholder="Password" />
													<i class="icon-lock"></i>
												</span>
											</label>

											<label class="block clearfix">
												<span class="block input-icon input-icon-right">
													<input type="password" class="form-control" placeholder="Repeat password" />
													<i class="icon-retweet"></i>
												</span>
											</label>

											<label class="block">
												<input type="checkbox" class="ace" />
												<span class="lbl">
													I accept the
													<a href="#">User Agreement</a>
												</span>
											</label>

											<div class="space-24"></div>

											<div class="clearfix">
												<button type="reset" class="width-30 pull-left btn btn-sm">
													<i class="icon-refresh"></i>
													Reset
												</button>

												<button type="button" class="width-65 pull-right btn btn-sm btn-success">
													Register
													<i class="icon-arrow-right icon-on-right"></i>
												</button>
											</div>
										</fieldset>
									</form>
								</div>

								<div class="toolbar center">
									<a href="#" onclick="ec_login.show_box('login-box'); return false;" class="back-to-login-link">
										<i class="icon-arrow-left"></i>
										Back to login
									</a>
								</div>
							</div><!-- /widget-body -->
						</div><!-- /signup-box -->
					</div><!-- /position-relative -->
				</div>
			</div><!-- /.col -->
		</div><!-- /.row -->
	</div>
</div><!-- /.main-container -->
</body>
</html>
