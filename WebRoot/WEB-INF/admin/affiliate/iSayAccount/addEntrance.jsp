<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>
<!-- 添加Modal -->

<script type="text/javascript">
	
$(function() {
	// 点击确认按钮
  	$("#modal-add .btn-add").on('click', function() {
  		// 验证表单是否合法
  		if (!$("#modal-add .form-horizontal").valid()) {
			return ;
		}
		
  		var url = "${path}" + "/admin/affiliate/iSayAccount/add";
  		var data = $("#modal-add .form-horizontal").serialize();
  		// 发送AJAX请求
	  	$.ajax({url: url, data: data, dataType: 'json', async: false, type: 'POST',
	  		success: function(list) {
	  			$("#modal-add").modal('hide');
	  			if (!handleAjaxResults(list)) {
					return ;
				} 
				EC.createNoty();
	  			
	  			$table = $(".table");
	  			// 表格最后一行插入tr
	  			var newTr = $('<tr class="active success">'
	  				+ '<td><label><input class="ace" type="checkbox" value="" /><span class="lbl"></span></label></td>'
	  				+ '<td>' + ($table.find("tbody tr").length + 1) + '</td>'
	  				+ '<td>' + list.email.email + '</td>'
	  				+ '<td>' + list.loginName + '</td>'
	  				+ '<td>' + list.loginPassword + '</td>'
	  				+ '<td>' + list.points + '</td>'
	  				+ '<td>' + list.latestRedeemTime + '</td>'
	  				+ '<td>' + list.latestOprTime + '</td>'
	  				+ '<td>'
	  					+ '<button type="button" class="btn btn-success btn-xs btn-read" value="' + list.Id + '">Detail</button>'
	  					+ ' <button type="button" class="btn btn-success btn-xs btn-delete" value="' + list.Id + '">Delete</button>'
	  					+ ' <button type="button" class="btn btn-success btn-xs btn-edit" value="' + list.Id + '">Edit</button>'
	  					+ ' <button type="button" class="btn btn-success btn-xs" value="' + list.Id + '">Answer</button>'
	  					+ ' <button type="button" class="btn btn-success btn-xs" value="' + list.Id + '">Redeem</button>'
	  				+ '</td>'
	  				+ '</tr>').hide().slideDown(1000);
	  			$($table.find("tbody")).append(newTr);
	  			
	  			EC.clearFormData($("#modal-add .form-horizontal")); // 清空表单
	  		},
	  		error: function() {
	  			EC.createNoty("error", "操作失败，请重试！"); // 操作失败提醒框
	  			$("#modal-add").modal('hide');
	  		}
	  	});
  	});
	});
	
	// jquery validate验证
  	$("#modal-add .form-horizontal").validate({
  		// 焦点离开
  		onfocusout: function(event) {
  			$(event).valid();
  		}, 
		rules: {
			"loginName": "required", 
			"loginPassword": "required", 
			"email.id": "required",
		},
		messages: {
			"loginName": "Login Name is required.", 
			"loginPassword": "Login Password is required.", 
			"email.id": "Email Account is required."
		}
});
</script>

<div class="modal fade" id="modal-add" >
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h4 class="modal-title" id="myModalLabel">Add a new i-say account</h4>
			</div>
			
			<div class="modal-body">

				<form class="form-horizontal">
					
					<div class="form-group">
						<label for="loginName" class="col-sm-3 control-label">Login Name</label>
						<div class="col-sm-9">
							<input type="text" class="form-control loginName" 
								name="loginName" value="" placeholder="Login Name">
						</div>
					</div>
					<div class="form-group">
						<label for="LoginPassword" class="col-sm-3 control-label">Login Password</label>
						<div class="col-sm-9">
							<input type="text" class="form-control LoginPassword" 
								name="LoginPassword" value="" placeholder="Login Password">
						</div>
					</div>
					<div class="form-group">
						<label for="email.id" class="col-sm-3 control-label">Email Account</label>
						<div class="col-sm-9">
							<select class="form-control email.id" name="email.id">
								<option value="" selected>Please select specific Email account</option>
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
								data-dismiss="modal">Cancel</button>
				<button type="button" class="btn btn-primary btn-add">OK</button>
			</div>
		</div>

	</div>

</div>