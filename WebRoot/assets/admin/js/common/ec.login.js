/*
 * 登陆部分JS
 */

(function( ec_login, $, undefined ) {
	
	"use strict"; // 使用严格模式
	
	$(function() {
		var $loginForm = $('#loginForm');
		// 回车登陆
	    $('input').keypress(function (e) {
	        if (e.which == 13) {
	        	if (ec_login.validForm($loginForm)) 
					$loginForm.submit();
	        }
	    });
	    
	    // 点击登陆
		$(".btn-login").click(function() {
			if (ec_login.validForm($loginForm)) 
				$loginForm.submit();
		});
		
		// 监听表单提交
		$loginForm.submit(function() {
			// 验证表单是否合法
	  		if (!$loginForm.find("#loginName").valid() || 
	  				!$loginForm.find("#loginPassword").valid()) {
	  			return ;
	  		}
	  		$loginForm.find("#chkRememberMe").is(":checked") ? $("#chkRememberMe").val(1) : $("#chkRememberMe").val(0); // 是否自动登陆
	  		$loginForm.find(".btn-login b").html("登陆中...");
		});
		
		// 表单验证
		$("#loginForm").validate({
			errorPlacement : function(error, element) {
				var $fieldset = element.closest("fieldset");
				$fieldset.find(".alert").remove();
				error.addClass("alert").addClass("alert-danger");
				$fieldset.prepend(error);	
			},
			errorElement : "div",
			errorClass : "error",
			// 焦点离开
	  		onfocusout: function(event) {
	  			// $(event).valid();
	  		}, 
			rules : {
				loginName : {
					required : true,
					remote : {
						url : EC.getContextPath() + "/admin/isUserExist",
						type : "post",
						date : {
							username : function() {
								return $("#username").val();
							}
						},
						async : false
					}, 
				},
				loginPassword : "required",
				validateCode : {
					required : true, 
					remote: {
						url : EC.getContextPath() + "/admin/isValidateCodeRight",
						type : "post",
						date : {
							username : function() {
								return $("#validateCode").val();
							}
						}, 
						async : false
					}
				}
			},
			messages : {
				loginName : {
					required : "请输入用户名", 
					remote: "此用户不存在"
				},
				loginPassword : "请输入登陆密码",
				validateCode : {
					required : "请输入验证码",
					remote : "验证码不正确"
				}
			}
		});
	});
	
	/**
	 * 切换操作框
	 * 
	 * @param id {String} 操作框ID
	 */
	ec_login.show_box = function(id) {
		$('.widget-box.visible').removeClass('visible');
		$('#'+id).addClass('visible');
	};
	
	// 表单验证
	ec_login.validForm = function($loginForm) {
		if ($loginForm.find("#loginName").valid() && 
  				$loginForm.find("#loginPassword").valid() &&
  				$loginForm.find("#validateCode").valid()) {
			$loginForm.find("#chkRememberMe").is(":checked") ? $("#chkRememberMe").val(1) : $("#chkRememberMe").val(0); // 是否自动登陆
	  		$loginForm.find(".btn-login b").html("登陆中...");
	  		return true;
  		}
		
		return false;
	};

}( window.ec_login = window.ec_login || {}, jQuery ));
