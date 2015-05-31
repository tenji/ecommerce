<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>
<!-- 编辑Modal -->
<script type="text/javascript">
	var zTreeOperationObj; // 树形操作权限对象
	// zTree配置
	var setting = {
		check: { 
			enable: true,
			chkStyle: "radio",
			autoCheckTrigger: true,
			radioType: "all"
		},  
		view: { 
			expandSpeed: 300, // 设置树展开的动画速度，IE6下面没效果，  
			showIcon: false
		},                     
		data: { 
			simpleData: {   // 简单的数据源
				enable: true,  
				idKey: "id",  //id和pid，这里不用多说了吧，树的目录级别  
				pIdKey: "pId", 
				rootPId: 0   //根节点  
			} 
	 	}, 
	 	callback: {
	 	}
	};

var updateEntrance = { // 命名空间
	// 点击编辑按钮
	editItem: function(itemId) {
		// 发送AJAX请求
	  	$.ajax({
	  		url: "${path}" + "/admin/system/sysOperation/read.htm",
	  		data: {"id": itemId},
	  		dataType: 'json',
	  		async: false, // 同步
	  		type: 'POST',
	  		success: updateEntrance.editAfterAjax,
	  		error: function() {
	  			deleteSpin(); // 删除JS加载插件
	  			EC.createNoty("error", "操作失败，请重试！");
	  			return ;
	  		}
	  	});
	  	
	},
	
	// 读取需要编辑的数据之后的操作
	editAfterAjax: function(list) {
		var modalEdit = $("#modal-edit");	// 获取id为modal-edit的jQuery对象
		modalEdit.find(".operationName").val(list.operationName);// 设置操作名称
		modalEdit.find(".url").val(list.url);	// 设置操作URL
		modalEdit.find("#parentOperationId").val(list.parentOperationId); // 设置父操作编号隐藏表单
		// 发送AJAX请求，获取操作权限树
	  	$.ajax({
	  		url: "${path}" + "/admin/system/sysOperation/getAssignedOperationsTree.htm",
	  		dataType: 'json',
	  		type: 'POST',
	  		async: false, // 同步
	  		success: updateEntrance.initOperationZTree,
	  		error: function() {
	  			EC.createNoty("error", "操作失败，请重试！");
	  			return ;
	  		}
	  	});
	}, 
	
	// 初始化OperationZTree
	initOperationZTree: function(sysOperationList) {
		if (!handleAjaxResults(sysOperationList)) {
			return ;
		} 
		
		var treeNodes = sysOperationList;
		zTreeOperationObj =  $.fn.zTree.init($("#update-operationTree"), setting, treeNodes);
		
		var parentOperationId = $("#parentOperationId").val();
		// 找到父操作编号
		var selectedOperationNode = zTreeOperationObj.getNodesByParam("id", parentOperationId, null);
		zTreeOperationObj.checkNode(selectedOperationNode[0], true, true); // 选中
		
		// 弹出Modal
		$("#modal-edit").modal({
			backdrop : 'static',
		});
		deleteSpin(); // 删除JS加载插件
	},
};
	
	$(document).ready(function() {
	// 点击确认按钮
  	$("#modal-edit").find(".btn-edit").on('click', function() {
  		var modalEdit = $("#modal-edit"); // 获取id为modal-update的jQuery对象
  		var operationName = modalEdit.find(".operationName").val(); // 获取输入的菜单名称
  		var url = modalEdit.find(".url").val();	// 获取输入的菜单URL
  		var parentOperationId;	// 父操作权限编号
  		
  		var selectedOperationNodes = zTreeOperationObj.getCheckedNodes(true);
  		if (isEmptyObj(selectedOperationNodes)) {
  			// 没有选择所属父操作，则默认为顶级
			parentOperationId = 0;
		} else {
			parentOperationId = selectedOperationNodes[0].id;
		}
  		// 发送AJAX请求
	  	$.ajax({
	  		url: "${path}" + "/admin/system/sysOperation/update.htm",
	  		async: false, 
	  		data: {"id": itemId, "operationName": operationName, "url": url, "parentOperationId": parentOperationId},
	  		dataType: 'json',
	  		type: 'POST',
	  		success: function(list) {
	  			$("#modal-edit").modal('hide');
	  			
	  			createNoty(); // 操作成功提醒框
	  			
	  			// 先删除修改之前的表格行
	  			setTimeout(function() {
					$(trObj).closest('tr').fadeOut("slow", function() {
						$(this).remove();
					});
				}, 1000);
	  			
	  			// 表格最后一行插入tr
	  			var newTr = $('<tr class="active success">'
	  				+ '<td><input type="checkbox" value="<s:property value="id"/>" /></td>'
	  				+ '<td>' + list.id + '</td>'
	  				+ '<td>' + list.operationName + '</td>'
	  				+ '<td>' + list.url + '</td>'
	  				+ '<td>'
	  					+ '<button type="button" class="btn btn-success btn-sm" value="' + list.id + '" onclick="readInit(this.value)">查看</button>'
	  					+ ' <button type="button" class="btn btn-success btn-sm" value="' + list.id + '" onclick="updateItemId(this.value);updateTrObj(this);editItem(this.value)">编辑</button>'
	  					+ ' <a href="#modal-delete" data-toggle="modal" data-backdrop="static">' 
	  						+ ' <button type="button" class="btn btn-success btn-sm" value="' + list.id + '" onclick="updateItemId(this.value);updateTrObj(this)">删除</button>'
	  					+ ' </a>'
	  				+ '</td>'
	  				+ '</tr>').hide().slideDown(1500);
	  			$('.table tbody').append(newTr);
	  			
	  			// 清空添加框内容
	  			modalEdit.find(".operationName").val("");
	  			modalEdit.find(".url").val("");
	  			modalEdit.find(".parentOperationId").get(0).selectedIndex = 0; // 设置Select索引值为0的项选中
	  		},
	  		error: function() {
	  			createNoty("error", "操作失败，请重试！"); // 操作失败提醒框
	  			$("#modal-edit").modal('hide');
	  		}
	  	});
  	});
  	
	});
	
</script>

<div class="modal fade" tabindex="-1" id="modal-edit">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h4 class="modal-title" id="myModalLabel">编辑操作权限</h4>
			</div>
			<div id="spin" align="center"></div>
			<div class="modal-body">

				<form class="form-horizontal">
					<div class="form-group">
						<label for="operationName" class="col-sm-4 control-label">操作名称</label>
						<div class="col-sm-8">
							<input type="text" class="form-control operationName"
								name="operationName" value="" placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label for="url" class="col-sm-4 control-label">操作URL</label>
						<div class="col-sm-8">
							<input type="text" class="form-control url"
								name="url" placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label for="parentOperationId" class="col-sm-4 control-label">所属操作(不选则为顶级)</label>
						<div class="col-sm-8">
							<div id="update-operationTree" class="ztree"></div>
						</div>
					</div>
				</form>
				<!-- 父操作编号隐藏表单 -->
				<input type="hidden" id="parentOperationId" value="" />
				<!-- end -->
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary btn-edit" data-dismiss="modal">确定</button>
			</div>
		</div>

	</div>

</div>