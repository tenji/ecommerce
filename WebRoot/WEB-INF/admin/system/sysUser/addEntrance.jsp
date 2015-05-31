<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>
<!-- 添加Modal -->

<script type="text/javascript">
$(function() {
	// 点击确认按钮
  	$("#modal-add").find(".btn-add").on('click', function() {
  		// 验证表单是否合法
  		if (!$("#modal-add .form-horizontal").valid()) {
			return ;
		}
  		// 发送AJAX请求
  		var url = "${path}" + "/admin/system/sysUser/add.htm";
  		var data = $("#modal-add .form-horizontal").serialize();
	  	$.ajax({url: url, data: data, dataType: 'json', type: 'POST', async: false, 
	  		success: function(list) {
	  			if (!handleAjaxResults(list)) {
					return ;
				}
	  			
	  			$("#modal-add").modal('hide');
	  			createNoty(); // 操作提示
	  			// 表格最后一行插入tr
	  			var newTr = $('<tr class="active success">'
	  				+ '<td><input type="checkbox" value="<s:property value="id"/>" /></td>'
	  				+ '<td>' + list.name + '</td>'
	  				+ '<td>' + '' + '</td>'
	  				+ '<td>' + '' + '</td>'
	  				+ '<td>' + '' + '</td>'
	  				+ '<td>' + '' + '</td>'
	  				+ '<td>'
	  					+ '<button type="button" class="btn btn-success btn-xs btn-read" value="' + list.id + '">查看</button>'
	  					+ ' <button type="button" class="btn btn-success btn-xs btn-edit" value="' + list.id + '">编辑</button>'
	  					+ ' <button type="button" class="btn btn-success btn-xs btn-delete" value="' + list.id + '">删除</button>'
	  					+ ' <button type="button" class="btn btn-success btn-xs" value="' + list.id + '" onclick="assignRolesInit(this)">分配角色</button>'
	  				+ '</td>'
	  				+ '</tr>').hide().slideDown(1000);
	  			$('.table tbody').append(newTr);
	  			
	  			// 清空添加框内容
	  			EC.clearFormData($("#modal-add .form-horizontal"));
	  			
	  		}
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
			"confirmLoginPassword": {
				equalTo: "#loginPassword"
			}
		},
		messages: {
			"confirmLoginPassword": "密码不匹配"
		}
	});
});
</script>

<div class="modal fade" tabindex="-1" id="modal-add">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" >×</button>
				<h4 class="modal-title" id="myModalLabel">添加用户</h4>
			</div>
			<!-- JS加载样式div -->
			
			<div class="modal-body">
				<form class="form-horizontal">
					<div class="form-group">
						<label for="loginName" class="col-sm-4 control-label">系统登录名</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="loginName" placeholder="请输入系统登录名" >
						</div>
					</div>
					<div class="form-group">
						<label for="loginPassword" class="col-sm-4 control-label">登录密码</label>
						<div class="col-sm-8">
							<input type="password" class="form-control" id="loginPassword" name="loginPassword" placeholder="请输入登录密码">
						</div>
					</div>
					<div class="form-group">
						<label for="confirmLoginPassword" class="col-sm-4 control-label">再次输入登录密码</label>
						<div class="col-sm-8">
							<input type="password" class="form-control" id="confirmLoginPassword" name="confirmLoginPassword" placeholder="请再次输入登录密码">
						</div>
					</div>
					<div class="form-group">
						<label for="firstName" class="col-sm-4 control-label">First Name</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="firstName" placeholder="请输入系统用户名">
						</div>
					</div>
					<div class="form-group">
						<label for="lastName" class="col-sm-4 control-label">Last Name</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="lastName" placeholder="请输入系统用户名">
						</div>
					</div>
				</form>

			</div>
			<div class="modal-footer">
			<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary btn-add">确定</button>
			</div>
		</div>

	</div>

</div>