<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>
<!-- 添加Modal -->

<script type="text/javascript">
$(function() {
	// AJAX请求，获取产品类目树
	var url = "${path}" + "/admin/product/category/getAssignedCategoriesTree.htm";
  	$.ajax({url: url, dataType: 'json', type: 'POST', async: false, 
  		success: function(treeNodes) {
  			if (!handleAjaxResults(treeNodes)) {
				return ;
			} 
		
			zTree.zTreeObj = $.fn.zTree.init($("#categoryTree"), zTree.setting, treeNodes); // 初始化ZTree(默认配置)
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
		
  		var parentId;	// 父类目编号
  		var selectedCategoryNodes = zTree.zTreeObj.getCheckedNodes(true);
  		if (EC.isEmptyObj(selectedCategoryNodes)) {
  			// 没有选择所属父操作，则默认为顶级
			parentId = 0;
		} else {
			parentId = selectedCategoryNodes[0].id;
		}
  		// 发送AJAX请求
  		var url = "${path}" + "/admin/product/category/add.htm";
  		var data = $("#modal-add .form-horizontal").serialize() + "&parentId=" + parentId;
	  	$.ajax({url: url, async: false, data: data,	dataType: 'json', type: 'POST',
	  		success: function(list) {
	  			$("#modal-add").modal('hide');
	  			createNoty(); // 操作成功提醒框
	  			
	  			// 表格最后一行插入tr
	  			var newTr = $('<tr class="active success">'
	  				+ '<td><input type="checkbox" value="<s:property value="id"/>" /></td>'
	  				+ '<td>' + list.id + '</td>'
	  				+ '<td>' + list.categoryName + '</td>'
	  				+ '<td>' + list.categoryName + '</td>'
	  				+ '<td>' + list.createTime + '</td>'
	  				+ '<td>'
	  					+ '<button type="button" class="btn btn-success btn-xs btn-read" value="' + list.id + '">查看</button>'
	  					+ ' <button type="button" class="btn btn-success btn-xs btn-edit" value="' + list.id + '">编辑</button>'
	  					+ ' <button type="button" class="btn btn-success btn-xs btn-delete" value="' + list.id + '">删除</button>'
	  				+ '</td>'
	  				+ '</tr>').hide().slideDown(1000);
	  			$('.table tbody').append(newTr);
	  			
	  			// 清空添加框内容
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
			categoryName: "required"
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
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h4 class="modal-title" id="myModalLabel">添加产品类目</h4>
			</div>
			<!-- JS加载样式div -->
			<div id="spin" align="center"></div>
			
			<div class="modal-body">

				<form class="form-horizontal">
					<div class="form-group">
						<label for="categoryName" class="col-sm-4 control-label">产品类目名称</label>
						<div class="col-sm-8">
							<input type="text" class="form-control categoryName"
								name="categoryName" value="" placeholder="请输入产品类目名称" >
						</div>
					</div>
					<div class="form-group">
						<label for="parentId" class="col-sm-4 control-label">所属类目(不选则为顶级)</label>
						<div class="col-sm-8">
							<input type="text" class="form-control filter" placeholder="请输入过滤关键词" >
							<div id="categoryTree" class="ztree"></div>
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