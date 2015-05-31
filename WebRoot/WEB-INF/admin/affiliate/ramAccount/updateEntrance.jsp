<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>
<!-- 编辑Modal -->

<script type="text/javascript">
$(document).ready(function() {
	// 点击确认按钮
  	$("#modal-edit .btn-edit").on('click', function() {
  		// 验证表单是否合法
  		if (!$("#modal-edit .form-horizontal").valid()) {
			return ;
		}
		
  		// 发送AJAX请求
  		var url = "${path}" + "/admin/affiliate/ramAccount/update";
  		var data = $("#modal-edit .form-horizontal").serialize();
	  	$.ajax({
	  		url: url, async: false, data: data, dataType: 'json', type: 'POST',
	  		success: function(list) {
	  			$("#modal-edit").modal('hide');
	  			
	  			EC.createNoty(); // 操作成功提醒框
	  			
	  			// 先删除修改之前的表格行
	  			setTimeout(function() {
					crud.operatedObj.closest('tr').fadeOut("slow", function() {
						$(this).remove();
					});
				}, 1000);
	  			
	  			$table = $(".table");
	  			// 表格最后一行插入tr
	  			var newTr = $('<tr class="active success">'
	  				+ '<td><label><input class="ace" type="checkbox" value="" /><span class="lbl"></span></label></td>'
	  				+ '<td>' + ($table.find("tbody tr").length + 1) + '</td>'
	  				+ '<td>' + list.email + '</td>'
	  				+ '<td>' + list.ram.name + '</td>'
	  				+ '<td>' + list.unAnswered + '</td>'
	  				+ '<td>' + list.pointsBeforeOpr + '</td>'
	  				+ '<td>' + list.pointsAfterOpr + '</td>'
	  				+ '<td>' + list.redeemStatus + '</td>'
	  				+ '<td>' + list.latestRedeemTime + '</td>'
	  				+ '<td>' + list.latestCheckTime + '</td>'
	  				+ '<td>' + list.latestOprTime + '</td>'
	  				+ '<td>'
	  					+ '<button type="button" class="btn btn-success btn-xs btn-read" value="' + list.id + '">查看</button>'
	  					+ ' <button type="button" class="btn btn-success btn-xs btn-edit" value="' + list.id + '">编辑</button>'
	  					+ ' <button type="button" class="btn btn-success btn-xs btn-delete" value="' + list.id + '">删除</button>'
	  				+ '</td>'
	  				+ '</tr>').hide().slideDown(1500);
	  			$($table.find("tbody")).append(newTr);
	  			
	  			// 清空添加框内容
	  			EC.clearFormData($("modal-edit .form-horizontal")); // 清空表单
	  		},
	  		error: function() {
	  			EC.createNoty("error", "操作失败，请重试！"); // 操作失败提醒框
	  			$("#modal-edit").modal('hide');
	  		}
	  	});
  	});
  	
  	// jquery validate验证
  	$("#modal-edit .form-horizontal").validate({
  		// 焦点离开
  		onfocusout: function(event) {
  			$(event).valid();
  		}, 
		rules: {
			"email": "required", 
			"ram.id": "required",
		},
		messages: {
			"email": "Email is required.", 
			"ram.id": "Ram site is required."
		}
	});
  	
});
	
</script>

<div class="modal fade" tabindex="-1" id="modal-edit">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h4 class="modal-title" id="myModalLabel">Update Ram Account</h4>
			</div>
			<div class="modal-body">

				<form class="form-horizontal">
					<input type="hidden" name="id" value='${model.id }' />
					<div class="form-group">
						<label for="email" class="col-sm-4 control-label">Email</label>
						<div class="col-sm-8">
							<input type="text" class="form-control email" name="email" value="<c:out value="${model.email }"></c:out>" placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label for="loginUrl" class="col-sm-4 control-label">Login URL</label>
						<div class="col-sm-8">
							<input type="text" class="form-control loginUrl" name="loginUrl" value="<c:out value="${model.loginUrl }"></c:out>" placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label for="loginName" class="col-sm-4 control-label">Login Name</label>
						<div class="col-sm-8">
							<input type="text" class="form-control loginName" name="loginName" value="<c:out value="${model.loginName }"></c:out>" placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label for="loginPassword" class="col-sm-4 control-label">Login Password</label>
						<div class="col-sm-8">
							<input type="text" class="form-control loginPassword" name="loginPassword" value="<c:out value="${model.loginPassword }"></c:out>" placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label for="pointsBeforeOpr" class="col-sm-4 control-label">Points Before Opr</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="pointsBeforeOpr" value="<c:out value="${model.pointsBeforeOpr }"></c:out>" placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label for="pointsAfterOpr" class="col-sm-4 control-label">Points After Opr</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="pointsAfterOpr" value="<c:out value="${model.pointsAfterOpr }"></c:out>" placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label for="remark" class="col-sm-4 control-label">Ram Site</label>
						<div class="col-sm-8">
							<select class="form-control ram.id" name="ram.id" multiple>
								<c:forEach items="${ramList }" var="ram">
									<option value="${ram.id }" <c:if test="${model.ram.id==ram.id }">selected</c:if>>${ram.name }</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary btn-edit" data-dismiss="modal">确定</button>
			</div>
		</div>

	</div>

</div>