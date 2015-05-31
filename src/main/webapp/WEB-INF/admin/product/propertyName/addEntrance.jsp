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
  	
	// 点击确认按钮
  	$("#modal-add").find(".btn-add").on('click', function() {
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
		
  		var url = "${path}" + "/admin/product/propertyName/add.htm";
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
	  				+ '<td>' + list.category.categoryName + '</td>'
	  				+ '<td>' + list.unansweredNums + '</td>'
	  				+ '<td>' + list.pointsBeforeOpr + '</td>'
	  				+ '<td>' + list.pointsAfterOpr + '</td>'
	  				+ '<td>' + list.pointsAfterOpr + '</td>'
	  				+ '<td>' + list.pointsAfterOpr + '</td>'
	  				+ '<td>' + list.pointsAfterOpr + '</td>'
	  				+ '<td>' + list.pointsAfterOpr + '</td>'
	  				+ '<td>' + list.createTime + '</td>'
	  				+ '<td>'
	  					+ '<button type="button" class="btn btn-success btn-xs btn-read" value="' + list.Id + '">查看</button>'
	  					+ ' <button type="button" class="btn btn-success btn-xs btn-edit" value="' + list.Id + '">修改</button>'
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
	});
	
	// jquery validate验证
  	$("#modal-add .form-horizontal").validate({
  		// 焦点离开
  		onfocusout: function(event) {
  			$(event).valid();
  		}, 
		rules: {
			"name": "required", 
			// "category.id": "required",
		},
		messages: {
		}
	});
	
</script>

<div class="modal fade" id="modal-add" >
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h4 class="modal-title" id="myModalLabel">添加属性名</h4>
			</div>
			
			<div class="modal-body">

				<form class="form-horizontal">
					
					<div class="form-group">
						<label for="name" class="col-sm-3 control-label">属性名</label>
						<div class="col-sm-9">
							<input type="text" class="form-control name" name="name" value="" placeholder="属性名" >
						</div>
					</div>
					<div class="form-group">
						<label for="allowAlias" class="col-sm-3 control-label">是否允许别名</label>
						<div class="col-sm-3">
							<label>
								<input name="allowAlias" class="ace ace-switch ace-switch-2" type="checkbox" value="1">
								<span class="lbl"></span>
							</label>
						</div>
						<label for="isColor" class="col-sm-3 control-label">是否颜色属性</label>
						<div class="col-sm-3">
							<label>
								<input name="isColor" class="ace ace-switch ace-switch-2" type="checkbox" value="1">
								<span class="lbl"></span>
							</label>
						</div>
					</div>
					<div class="form-group">
						<label for="isEnumerated" class="col-sm-3 control-label">是否枚举属性</label>
						<div class="col-sm-3">
							<label>
								<input name="isEnumerated" class="ace ace-switch ace-switch-2" type="checkbox" value="1">
								<span class="lbl"></span>
							</label>
						</div>
						<label for="isInput" class="col-sm-3 control-label">是否输入属性</label>
						<div class="col-sm-3">
							<label>
								<input name="isInput" class="ace ace-switch ace-switch-2" type="checkbox" value="1">
								<span class="lbl"></span>
							</label>
						</div>
					</div>
					<div class="form-group">
						<label for="isKey" class="col-sm-3 control-label">是否关键属性</label>
						<div class="col-sm-3">
							<label>
								<input name="isKey" class="ace ace-switch ace-switch-2" type="checkbox" value="1">
								<span class="lbl"></span>
							</label>
						</div>
						<label for="isSale" class="col-sm-3 control-label">是否销售属性</label>
						<div class="col-sm-3">
							<label>
								<input name="isSale" class="ace ace-switch ace-switch-2" type="checkbox" value="1">
								<span class="lbl"></span>
							</label>
						</div>
					</div>
					<div class="form-group">
						<label for="isSearch" class="col-sm-3 control-label">是否搜索属性</label>
						<div class="col-sm-3">
							<label>
								<input name="isSearch" class="ace ace-switch ace-switch-2" type="checkbox" value="1">
								<span class="lbl"></span>
							</label>
						</div>
						<label for="isNecessary" class="col-sm-3 control-label">是否必须</label>
						<div class="col-sm-3">
							<label>
								<input name="isNecessary" class="ace ace-switch ace-switch-2" type="checkbox" value="1">
								<span class="lbl"></span>
							</label>
						</div>
					</div>
					<div class="form-group">
						<label for="isAlternative" class="col-sm-3 control-label">是否多选</label>
						<div class="col-sm-9">
							<label>
								<input name="isAlternative" class="ace ace-switch ace-switch-2" type="checkbox" value="1">
								<span class="lbl"></span>
							</label>
						</div>
					</div>
					<div class="form-group">
						<input type="hidden" id="categoryId" name="category.id" value="" />
						<label for="parentId" class="col-sm-3 control-label">所属类目</label>
						<div class="col-sm-9">
							<input type="text" class="form-control filter" placeholder="请输入过滤关键词" >
							<div id="categoryTree" class="ztree"></div>
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