<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>
<!-- 添加Modal -->

<script type="text/javascript">
$(document).ready(function() {
	// AJAX请求，获取产品类目树
	var url = "${path}" + "/admin/product/category/getAssignedCategoriesTree.htm";
  	$.ajax({url: url, dataType: 'json', type: 'POST', async: false, 
  		success: function(treeNodes) {
  			if (!handleAjaxResults(treeNodes)) {
				return ;
			} 
		
			zTree.zTreeObj = $.fn.zTree.init($("#categoryTree"), zTree.setting, treeNodes); // 初始化ZTree(默认配置)
  		}, 
  		error: function() {
  			createNoty("error", "操作失败，请重试！");
  			return ;
  		}
  	});
  	
  	// zTree搜索
  	$("#modal-add .filter").keyup(function() {
  		zTree.zTreeFilter('categoryTree', 'name', $(this).val());
  	});
  	
	// 确认创建
  	$("#modal-add .btn-add").on('click', function() {
  		// 验证表单是否合法
  		if (!$("#modal-add .form-horizontal").valid()) {
			return ;
		}
		
  		var selectedCategoryNodes = zTree.zTreeObj.getCheckedNodes(true);
  		if (isEmptyObj(selectedCategoryNodes)) {
  			createNoty("error", "请选择所属类目");
  			return ;
		} else {
			$("#categoryId").val(selectedCategoryNodes[0].id);
		}
		
  		var url = "${path}" + "/admin/product/propertyValue/add.htm";
  		var data = $("#modal-add .form-horizontal").serialize();
  		// 发送AJAX请求
	  	$.ajax({url: url, data: data, dataType: 'json', async: false, type: 'POST',
	  		success: function(list) {
	  			$("#modal-add").modal('hide');
	  			if (!handleAjaxResults(list)) {
					return ;
				}
				createNoty(); // 操作成功提醒框
	  			
	  			// 表格最后一行插入tr
	  			var newTr = $('<tr class="active success">'
	  				+ '<td><input type="checkbox" value="<s:property value="Id"/>" /></td>'
	  				+ '<td>' + list.name + '</td>'
	  				+ '<td>' + list.propertyName.name + '</td>'
	  				+ '<td>' + list.category.categoryName + '</td>'
	  				+ '<td>' + list.createTime + '</td>'
	  				+ '<td>'
	  					+ '<button type="button" class="btn btn-success btn-xs btn-read" value="' + list.Id + '">查看</button>'
	  					+ ' <button type="button" class="btn btn-success btn-xs btn-edit" value="' + list.Id + '">编辑</button>'
	  					+ ' <button type="button" class="btn btn-success btn-xs btn-delete" value="' + list.Id + '">删除</button>'
	  				+ '</td>'
	  				+ '</tr>').hide().slideDown(1000);
	  			$('.table tbody').append(newTr);
	  			
	  			EC.clearFormData($("#modal-add .form-horizontal")); // 清空表单
	  		},
	  		error: function() {
	  			createNoty("error", "操作失败，请重试！"); // 操作失败提醒框
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
			"propertyName.id": "required"
		},
		messages: {
		}
	});
});
	
// 节点选中事件
zTree.zTreeOnCheck = function zTreeOnCheck(event, treeId, treeNode) {
	spin.createSpin();
	// 发送AJAX请求，获取特定类目下的属性名
	var url = "${path}" + "/admin/product/propertyName/getPropertyNamesByCategoryId.htm";
  	$.ajax({url: url, dataType: 'json', data: {categoryId: treeNode.id}, type: 'POST', async: false, 
  		success: function(propertyNameList) {
  			var propertyNameSelect = $(".propertyNameId");
  			propertyNameSelect.html(""); // 先清空select框内容
  			$('<option value="" selected>请选择所属属性名</option>').appendTo(propertyNameSelect);
  			for ( var i = 0; i < propertyNameList.length; i++) {
				$("<option value=" + propertyNameList[i].id + ">" + propertyNameList[i].name + "</option>").appendTo(propertyNameSelect);
			}
  			spin.deleteSpin();
  		}, 
  		error: function() {
  			EC.createNoty("error", "操作失败，请重试！");
  			return ;
  		}
  	});
};
	
</script>

<div class="modal fade" id="modal-add" >
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h4 class="modal-title" id="myModalLabel">添加属性值</h4>
			</div>
			
			<div class="modal-body">

				<form class="form-horizontal">
					
					<div class="form-group">
						<label for="name" class="col-sm-4 control-label">属性值名称</label>
						<div class="col-sm-8">
							<input type="text" class="form-control name" name="name" value="" placeholder="属性值名称" >
						</div>
					</div>
					<div class="form-group">
						<input type="hidden" id="categoryId" name="category.id" value="" />
						<label for="parentId" class="col-sm-4 control-label">所属类目</label>
						<div class="col-sm-8">
							<input type="text" class="form-control filter" placeholder="请输入过滤关键词" >
							<div id="categoryTree" class="ztree"></div>
						</div>
					</div>
					<div class="form-group">
						<label for="propertyName.id" class="col-sm-4 control-label">所属属性名</label>
						<div class="col-sm-8">
							<select class="form-control propertyNameId" name="propertyName.id" id="propertyNameId">
								<option value="" selected>请选择所属属性名</option>
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