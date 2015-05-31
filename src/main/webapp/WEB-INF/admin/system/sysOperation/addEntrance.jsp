<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>
<!-- 添加Modal -->

<script type="text/javascript">
$(function() {

	// AJAX请求，获取操作权限树
	var url = "${path}" + "/admin/system/sysOperation/getAssignedOperationsTree";
  	$.ajax({url: url, dataType: 'json', type: 'POST', async: false, 
  		success: function(treeNodes) {
  			if (!handleAjaxResults(treeNodes)) {
				return ;
			} 
		
			zTree.zTreeObj = $.fn.zTree.init($("#operationTree"), zTree.setting, treeNodes); // 初始化ZTree(默认配置)
  		}
  	});
  	
	// 点击确认按钮
  	$("#modal-add").find(".btn-add").on('click', function() {
  		var parentId;	// 父操作权限编号
  		
  		var selectedOperationNodes = zTree.zTreeObj.getCheckedNodes(true);
  		if (EC.isEmptyObj(selectedOperationNodes)) {
  			// 没有选择所属父操作，则默认为顶级
			parentId = 0;
		} else {
			parentId = selectedOperationNodes[0].id;
		}
  		// 发送AJAX请求
  		var url = "${path}" + "/admin/system/sysOperation/add";
  		var data = $("#modal-add .form-horizontal").serialize() + "&parentId=" + parentId;
	  	$.ajax({url: url, async: false, data: data, dataType: 'json', type: 'POST',
	  		success: function(list) {
	  			$("#modal-add").modal('hide');
	  			if (!handleAjaxResults(list)) {
					return ;
				}
				EC.createNoty(); // 操作成功提醒框
	  			
	  			// 表格最后一行插入tr
	  			var newTr = $('<tr class="active success">'
	  				+ '<td><label><input class="ace" type="checkbox" value="" /><span class="lbl"></span></label></td>'
	  				+ '<td>' + list.id + '</td>'
	  				+ '<td>' + list.operationName + '</td>'
	  				+ '<td>' + list.url + '</td>'
	  				+ '<td>'
	  					+ '<button type="button" class="btn btn-success btn-xs" value="' + list.id + '" onclick="readInit(this.value)">查看</button>'
	  					+ ' <button type="button" class="btn btn-success btn-xs" value="' + list.id + '" onclick="editItem(this.value)">编辑</button>'
	  					+ ' <a href="#modal-delete" data-toggle="modal" data-backdrop="static">' 
	  						+ ' <button type="button" class="btn btn-success btn-xs" value="' + list.id + '" onclick="updateItemId(this.value);updateTrObj(this)">删除</button>'
	  					+ ' </a>'
	  				+ '</td>'
	  				+ '</tr>').hide().slideDown(1000);
	  			$('.table tbody').append(newTr);
	  			
	  			EC.clearFormData($("#modal-add .form-horizontal")); // 清空表单内容
	  		},
	  		error: function() {
	  			EC.createNoty("error", "操作失败，请重试！"); // 操作失败提醒框
	  			$("#modal-add").modal('hide');
	  		}
	  	});
  	});
  	
});
</script>

<div class="modal fade" tabindex="-1" id="modal-add">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h4 class="modal-title" id="myModalLabel">添加权限</h4>
			</div>
			
			<div class="modal-body">

				<form class="form-horizontal">
					<div class="form-group">
						<label for="operationName" class="col-sm-4 control-label">操作名称</label>
						<div class="col-sm-8">
							<input type="text" class="form-control operationName" 
								name="operationName" value="" placeholder="请输入操作名称" >
						</div>
					</div>
					<div class="form-group">
						<label for="url" class="col-sm-4 control-label">操作URL</label>
						<div class="col-sm-8">
							<input type="text" class="form-control url" id="tags"
								name="url" value="" placeholder="请输入操作URL">
						</div>
					</div>
					<div class="form-group">
						<label for="parentId" class="col-sm-4 control-label">所属操作(不选则为顶级)</label>
						<div class="col-sm-8">
							<div id="operationTree" class="ztree"></div>
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