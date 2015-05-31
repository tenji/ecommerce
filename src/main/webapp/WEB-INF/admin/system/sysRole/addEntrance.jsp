<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>
<!-- 添加Modal -->
<script type="text/javascript">
	// 点击添加按钮
	function addItem() {
		$("#modal-add").modal({
			backdrop : 'static'
		});
		
		EC.deleteSpinner();
	}
	
	$(function() {
	// 点击确认按钮
  	$("#modal-add").find(".btn-add").on('click', function() {
  		var modalRead = $("#modal-add");	// 获取id为modal-add的jQuery对象
  		var roleName = modalRead.find(".roleName").val();		// 获取输入的权限名称
  		var description = modalRead.find(".description").val();	// 获取输入的权限描述
  		var roleLevel = modalRead.find(".roleLevel").val();		// 获取输入的权限等级
  		var createDate = modalRead.find(".createDate").val();	// 获取输入的创建时间
  		// 发送AJAX请求
	  	$.ajax({
	  		url: "${path}" + "/admin/system/sysRole/add",
	  		data: {"roleName": roleName, "description": description, "roleLevel": roleLevel, "createDate": createDate},
	  		dataType: 'json',
	  		type: 'POST',
	  		success: function(list) {
	  			if (!handleAjaxResults(list)) {
					return ;
				}
	  			$("#modal-add").modal('hide');
	  			// 表格最后一行插入tr
	  			var newTr = $('<tr class="active success">'
	  				+ '<td><input type="checkbox" value="<s:property value="roleId"/>" /></td>'
	  				+ '<td>' + list.roleName + '</td>'
	  				+ '<td>' + list.description + '</td>'
	  				+ '<td>' + list.roleLevel + '</td>'
	  				+ '<td>' + list.createDate + '</td>'
	  				+ '<td>'
	  					+ '<button type="button" class="btn btn-success btn-xs" value="' + list.roleId + '" onclick="readInit(this.value)">查看</button>'
	  					+ ' <button type="button" class="btn btn-success btn-xs" value="' + list.roleId + '" onclick="editItem(this.value)">编辑</button>'
	  					+ ' <a href="#modal-delete" data-toggle="modal" data-backdrop="static">' 
	  						+ ' <button type="button" class="btn btn-success btn-xs" value="' + list.roleId + '" onclick="updateItemId(this.value);updateTrObj(this);deleteInit()">删除</button>'
	  					+ ' </a>'
	  					+ '<button type="button" class="btn btn-success btn-xs" value="' + list.roleId + '" onclick="updateItemId(this.value);assignRightsInit()">分配权限</button>'
	  				+ '</td>'
	  				+ '</tr>').hide().slideDown(1000);
	  			$('.table tbody').append(newTr);
	  			
	  			// 清空添加框内容
	  			modalRead.find(".roleName").val("");
	  			modalRead.find(".description").val("");
	  			modalRead.find(".roleLevel").val("");
	  			modalRead.find(".createDate").val("");
	  		},
	  		error: function() {
	  			alert("AJAX请求失败，请重试！");
	  			$("#modal-add").modal('hide');
	  		}
	  	});
  	});
	});
</script>

<div class="modal fade" tabindex="-1" id="modal-add" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h4 class="modal-title" id="myModalLabel">添加权限</h4>
			</div>
			<div class="modal-body">

				<form class="form-horizontal" role="form">
					<div class="form-group">
						<label for="roleName" class="col-sm-2 control-label">权限名称</label>
						<div class="col-sm-10">
							<input type="text" class="form-control roleName" 
								name="roleName" value="" placeholder="请输入权限名称" >
						</div>
					</div>
					<div class="form-group">
						<label for="description" class="col-sm-2 control-label">权限描述</label>
						<div class="col-sm-10">
							<input type="text" class="form-control description" 
								name="description" value="" placeholder="请输入权限描述">
						</div>
					</div>
					<div class="form-group">
						<label for="roleLevel" class="col-sm-2 control-label">权限等级</label>
						<div class="col-sm-10">
							<input type="text" class="form-control roleLevel" 
								name="roleLevel" value="" placeholder="请输入权限等级">
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