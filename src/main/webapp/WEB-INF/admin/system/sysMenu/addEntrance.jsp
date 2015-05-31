<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>
<!-- 添加Modal -->

<script type="text/javascript">
$(document).ready(function() {
	// 点击确认按钮
  	$("#modal-add .btn-add").on('click', function() {
  		// 验证表单是否合法
  		if (!$("#modal-add .form-horizontal").valid()) {
			return ;
		}
		
  		// 发送AJAX请求
  		var url = "${path}" + "/admin/system/sysMenu/add";
  		var data = $("#modal-add .form-horizontal").serialize();
	  	$.ajax({url: url, data: data, dataType: 'json', async: false, type: 'POST',
	  		success: function(list) {
	  			$("#modal-add").modal('hide');
	  			createNoty(); // 操作成功提醒框
	  			
	  			// 表格最后一行插入tr
	  			var newTr = $('<tr class="active success">'
	  				+ '<td><input type="checkbox" value="<s:property value="id"/>" /></td>'
	  				+ '<td>' + list.id + '</td>'
	  				+ '<td>' + list.menuName + '</td>'
	  				+ '<td>' + list.menuDescription + '</td>'
	  				+ '<td>' + list.url + '</td>'
	  				+ '<td>'
	  					+ '<button type="button" class="btn btn-success btn-xs btn-read" value="' + list.id + '">查看</button>'
	  					+ ' <button type="button" class="btn btn-success btn-xs btn-edit" value="' + list.id + '">编辑</button>'
	  					+ ' <button type="button" class="btn btn-success btn-xs btn-delete" value="' + list.id + '">删除</button>'
	  				+ '</td>'
	  				+ '</tr>').hide().slideDown(1000);
	  			$('.table tbody').append(newTr);
	  			
	  			EC.clearFormData($("#modal-add .form-horizontal")); // 清空表单
	  		},
	  		error: function() {
	  			EC.createNoty("error", "操作失败，请重试！"); // 操作失败提醒框
	  			$("#modal-add").modal('hide');
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
			parentId: "required", 
			url: {
				required: true, 
				url: false
			}
		},
		messages: {
		}
	});
});
</script>

<div class="modal fade" id="modal-add">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h4 class="modal-title" id="myModalLabel">添加菜单</h4>
			</div>
			
			<div class="modal-body">

				<form class="form-horizontal">
					<div class="form-group">
						<label for="menuName" class="col-sm-3 control-label">菜单名称</label>
						<div class="col-sm-9">
							<input type="text" class="form-control menuName" 
								name="menuName" value="" placeholder="请输入菜单名称" >
						</div>
					</div>
					<div class="form-group">
						<label for="menuDescription" class="col-sm-3 control-label">菜单描述</label>
						<div class="col-sm-9">
							<input type="text" class="form-control menuDescription" 
								name="menuDescription" value="" placeholder="请输入菜单描述" >
						</div>
					</div>
					<div class="form-group">
						<label for="url" class="col-sm-3 control-label">菜单URL</label>
						<div class="col-sm-9">
							<input type="text" class="form-control url" 
								name="url" placeholder="请输入菜单URL">
						</div>
					</div>
					<div class="form-group">
						<label for="parentId" class="col-sm-3 control-label">所属菜单</label>
						<div class="col-sm-9">
							<select class="form-control parentId" name="parentId">
								<option value="">请选择所属菜单</option>
								<option value="0">顶级菜单</option>
								<c:forEach items="${list }" var="sysMenu">
									<option value="${sysMenu.id }">${sysMenu.menuDescription }</option>
								</c:forEach>
							</select>
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