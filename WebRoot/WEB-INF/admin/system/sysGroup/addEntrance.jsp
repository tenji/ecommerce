<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>
<!-- 添加Modal -->

<script type="text/javascript">
$(function() {
	// AJAX请求，获取产品类目树
	var url = "${path}" + "/admin/system/sysGroup/getAssignedGroupsTree.htm";
  	$.ajax({url: url, dataType: 'json', type: 'POST', async: false, 
  		success: function(treeNodes) {
  			if (!handleAjaxResults(treeNodes)) {
				return ;
			} 
		
			zTree.zTreeObj = $.fn.zTree.init($("#groupTree"), zTree.setting, treeNodes); // 初始化ZTree(默认配置)
  		}, 
  		error: function() {
  			createNoty("error", "操作失败，请重试！");
  			return ;
  		}
  	});
  	
  	// zTree搜索
  	$("#modal-add .filter").keyup(function() {
  		zTree.zTreeFilter('groupTree', 'name', $(this).val());
  	});
	
	// 点击确认按钮
  	$("#modal-add .btn-add").on('click', function() {
  		// 验证表单是否合法
  		if (!$("#modal-add .form-horizontal").valid()) {
			return ;
		}
		
		var parentId;	// 父类目编号
  		var selectedGroupNodes = zTree.zTreeObj.getCheckedNodes(true);
  		if (isEmptyObj(selectedGroupNodes)) {
  			// 没有选择所属父操作，则默认为顶级
			parentId = 0;
		} else {
			parentId = selectedGroupNodes[0].id;
		}
  		// 发送AJAX请求
  		var url = "${path}" + "/admin/system/sysGroup/add.htm";
  		var data = $("#modal-add .form-horizontal").serialize() + "&parentId=" + parentId;
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
	  				+ '<td>' + list.description + '</td>'
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
	  			
	  		},
	  		error: function() {
	  			createNoty("error", "操作失败，请重试！");
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
			"name": "required", 
			"description": "required"
		},
		messages: {
		}
	});
});
</script>

<div class="modal fade" tabindex="-1" id="modal-add">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" >×</button>
				<h4 class="modal-title" id="myModalLabel">添加用户组</h4>
			</div>
			
			<div class="modal-body">
				<form class="form-horizontal">
					<div class="form-group">
						<label for="name" class="col-sm-4 control-label">组名称</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="name" placeholder="请输入用户组名" >
						</div>
					</div>
					<div class="form-group">
						<label for="description" class="col-sm-4 control-label">组描述</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="description" placeholder="请输入用户组描述" >
						</div>
					</div>
					<div class="form-group">
						<label for="orderList" class="col-sm-4 control-label">排序号</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="orderList" placeholder="请输入排序号" >
						</div>
					</div>
					<div class="form-group">
						<label for="status" class="col-sm-3 control-label">是否启用</label>
						<div class="col-sm-3">
							<label>
								<input name="status" class="ace ace-switch ace-switch-2" type="checkbox" value="1">
								<span class="lbl"></span>
							</label>
						</div>
						<label for="deletable" class="col-sm-3 control-label">是否可删除</label>
						<div class="col-sm-3">
							<label>
								<input name="deletable" class="ace ace-switch ace-switch-2" type="checkbox" value="1">
								<span class="lbl"></span>
							</label>
						</div>
					</div>
					<div class="form-group">
						<label for="parentId" class="col-sm-4 control-label">所属用户组(不选则为顶级)</label>
						<div class="col-sm-8">
							<input type="text" class="form-control filter" placeholder="请输入过滤关键词" >
							<div id="groupTree" class="ztree"></div>
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