<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>
<!-- 注册Modal -->

<script type="text/javascript">
$(function() {
	$modalReg = $("#modal-reg"); // jQuery Modal Object
	// 点击确认按钮
  	$modalReg.find(".btn-reg").on('click', function() {
  		// 验证表单是否合法
  		if (!$("#modal-reg .form-horizontal").valid()) {
			return ;
		}
  		
  		EC.createSpinner(); // 创建JS加载插件
  		var url = "${path}" + "/admin/affiliate/ram/reg";
  		var emailId = $("#emailId option:selected").val(); // Get the Email that would register for the new Ram Account
  		// 发送AJAX请求
	  	$.ajax({url: url, data: {"emailId" : emailId, "ramId" : crud.operatedObj.val()}, dataType: 'json', async: false, type: 'POST',
	  		success: function(list) {
	  			EC.deleteSpinner();
	  			$modalReg.modal('hide');
	  			if (!handleAjaxResults(list)) {
					return ;
				} 
	  		},
	  		error: function() {
	  			EC.createNoty("error", "操作失败，请重试！"); // 操作失败提醒框
	  			EC.deleteSpinner();
	  			$modalReg.modal('hide');
	  		}
	  	});
  	});
  	
  	// jquery validate验证
  	$("#modal-reg .form-horizontal").validate({
  		// 焦点离开
  		onfocusout: function(event) {
  			$(event).valid();
  		}, 
		rules: {
			"emailId": "required"
		},
		messages: {
			"emailId": "Email is required."
		}
	});
});
</script>

<div class="modal fade" id="modal-reg" >
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h4 class="modal-title" id="myModalLabel">Add a new ram site</h4>
			</div>
			
			<div class="modal-body">

				<form class="form-horizontal">
					<div class="form-group">
						<label for="state" class="col-sm-4 control-label">Email Account: </label>
						<div class="col-sm-8">
							<select class="form-control state" name="emailId" id="emailId">
								<option value="" selected>Please Select Specific State</option>
								<c:forEach items="${emailList }" var="email">
									<option value="${email.id }">${email.email }</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</form>

			</div>
			<div class="modal-footer">
			<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary btn-reg">确定</button>
			</div>
		</div>

	</div>

</div>