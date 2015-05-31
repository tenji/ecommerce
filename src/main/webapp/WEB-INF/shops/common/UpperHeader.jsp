<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 网站头部 -->

<style>
.view-signup, .view-signin {
	border-right: 1px solid;
}
</style>

<script type="text/javascript">
$(document).ready(function() {
    $('.nav li.dropdown').hover(function() {
        $(this).addClass('open');
    }, function() {
        $(this).removeClass('open');
    });
    
    $("#modal-sign").find(".btn-signup").on('click', function() {
    	var currentForm = $("#modal-sign .form-signup");
    	// 验证表单是否合法
  		if (!currentForm.valid()) {
			return ;
		}
    	var memberName = currentForm.find(".memberName").val();
    	var password = currentForm.find(".password").val();
    	var email = currentForm.find(".email").val();
    	$.ajax({
	  		url: "${path}" + "/shops/common/member/register.htm",
	  		data: {"memberName": memberName, "password": password, "email": email}, 
	  		dataType: 'json',
	  		type: 'POST',
	  		success: function(list) {
	  			currentForm.find(".memberName").empty();
	  			currentForm.find(".password").empty();
	  			handleAjaxResults(list);
	  		},
	  		error: function() {
	  			createNoty("error", "註冊失敗！");
	  			$("#modal-add").modal('hide');
	  		}
	  	});
    });
    
    $("#modal-sign").find(".btn-signin").on('click', function() {
    	var currentForm = $("#modal-sign .form-signin");
    	// 验证表单是否合法
  		if (!currentForm.valid()) {
			return ;
		}
    	var memberName = currentForm.find(".memberName").val();
    	var password = currentForm.find(".password").val();
    	$.ajax({
	  		url: "${path}" + "/shops/common/member/login.htm",
	  		data: {"memberName": memberName, "password": password}, 
	  		dataType: 'json',
	  		type: 'POST',
	  		success: function(list) {
	  			if (!handleAjaxResults(list)) {return ;}
	  			setTimeout(function() {
	  				location.reload();
	  			}, 1000);
	  		},
	  		error: function() {
	  			createNoty("error", "登陆失敗！");
	  			$("#modal-add").modal('hide');
	  		}
	  	});
    });
    
    // jquery validate验证
  	$("#modal-sign .form-signup").validate({
  		// 焦点离开
  		onfocusout: function(event) {
  			$(event).valid();
  		}, 
		rules: {
			memberName: "required",
			password: "required",
			email: {required: true, email: true}
		},
		messages: {
		}
	});
	
	$("#modal-sign .form-signin").validate({
  		// 焦点离开
  		onfocusout: function(event) {
  			$(event).valid();
  		}, 
		rules: {
			memberName: "required",
			password: "required",
		},
	});
});
</script>

	<!-- 
		Upper Header Section 
	-->
	<div class="navbar navbar-fixed-top">
		<div class="topNav">
			<div class="container">
				<div class="alignR">
					<div class="pull-left socialNw">
						<a href="#"><span class="icon-twitter"></span></a>
						<a href="#"><span class="icon-facebook"></span></a>
						<a href="#"><span class="icon-youtube"></span></a>
						<a href="#"><span class="icon-tumblr"></span></a>
					</div>
					<a class="active" href="${path }"> <span class="icon-home"></span> Home</a> 
					<a href="#"><span class="icon-user"></span> My Account</a> 
					<a href="register.html"><span class="icon-edit"></span> Free Register </a> 
					<a href="contact.html"><span class="icon-envelope"></span> Contact us</a>
					<a href="cart.html"><span class="icon-shopping-cart"></span> 2 Item(s) - <span class="badge badge-warning"> $448.42</span></a>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 註冊或者登陆Modal开始 -->
	<div class="modal" id="modal-sign" style="display: none">
		
		<div class="modal-dialog">
			<div class="modal-content">
			
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">×</button>
					<h4 class="modal-title" id="myModalLabel">註冊或登陸</h4>
				</div>
				
				<div class="modal-body row">
					<div class="col-md-7 view-signup" style="display: none">
					<form class="form-horizontal form-signup" role="form">
						<div class="form-group">
							<label for="" class="col-sm-3 control-label"></label>
							<div class="col-sm-9">快速注册<div class="signin-switch pull-right"><a class="js-signin" href="javascript:void(0)">登录</a></div></div>
						</div>
						<div class="form-group">
							<label for="memberName" class="col-sm-3 control-label">用戶名</label>
							<div class="col-sm-9">
								<input type="text" class="form-control memberName" 
									name="memberName" value="" placeholder="请输入用戶名" >
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="col-sm-3 control-label">密碼</label>
							<div class="col-sm-9">
								<input type="password" class="form-control password" 
									name="password" value="" placeholder="请输入密碼" >
							</div>
						</div>
						<div class="form-group">
							<label for="email" class="col-sm-3 control-label">郵箱</label>
							<div class="col-sm-9">
								<input type="text" class="form-control email" 
									name="email" value="" placeholder="请输入郵箱" >
							</div>
						</div>
					</form>
					</div>
					<div class="col-md-7 view-signin" style="display: none">
					<form class="form-horizontal form-signin" role="form">
						<div class="form-group">
							<label for="" class="col-sm-3 control-label"></label>
							<div class="col-sm-9">登录Open ECommerce<div class="signup-switch pull-right"><a class="js-signup" href="javascript:void(0)">注册</a></div></div>
						</div>
						<div class="form-group">
							<label for="memberName" class="col-sm-3 control-label">用戶名</label>
							<div class="col-sm-9">
								<input type="text" class="form-control memberName" 
									name="memberName" value="" placeholder="请输入用戶名或邮箱" >
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="col-sm-3 control-label">密碼</label>
							<div class="col-sm-9">
								<input type="password" class="form-control password" 
									name="password" value="" placeholder="请输入密碼" >
							</div>
						</div>
					</form>
					</div>
					
					<div class="col-md-5 view-sina">
						<p>或者使用</p>
						<a style="margin-bottom: 15px;min-width: 143px;" href="javascript:;" class="zg-btn-red js-bindweibo">
<span class="icon-big-white-sina"></span>新浪微博帐号登录</a>
						<a style="min-width: 143px;" href="javascript:;" class="zg-btn-blue js-bindqq">
<span class="icon-big-white-qq"></span>QQ 帐号直接登录</a>
					</div>
				</div>
				
				<div class="modal-footer view-signup">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary btn-signup">注册</button>
				</div>
				<div class="modal-footer view-signin">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary btn-signin">登录</button>
				</div>
			</div>

		</div>

	</div>
	<!-- 退出登陆Modal结束 -->
