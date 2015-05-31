<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>
<!-- 编辑Modal -->
<script type="text/javascript">
// zTree配置
zTree.setting = {
	check: { 
		enable: true,
		chkStyle: "checkbox",
		autoCheckTrigger: true,
		chkboxType: {"Y":"", "N":""} // 是否关联父子节点
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

$(function() {
	// AJAX请求，获取操作权限树
	var url = "${path}" + "/admin/product/category/getAssignedCategoriesTree.htm";
  	$.ajax({url: url, data: {"brandId": crud.operatedObj.val()}, dataType: 'json', type: 'POST', async: false, // 同步
  		success: function(treeNodes) {
	  			if (!handleAjaxResults(treeNodes)) {
				return ;
			} 
			
			zTreeCategoryObj =  $.fn.zTree.init($("#update-categoryTree"), zTree.setting, treeNodes);
  		}, 
  		error: function() {
  			createNoty("error", "操作失败，请重试！");
  			return ;
  		}
  	});
  	
  	// 点击确认按钮
  	$("#modal-edit").find(".btn-edit").on('click', function() {
  		// 验证表单是否合法
  		if (!$("#modal-edit .form-horizontal").valid()) {
			return ;
		}
  		
  		var selectedCategoryIds = []; // 所属类目集合
  		var selectedCategoryNodes = zTree.zTreeObj.getCheckedNodes(true);
  		if (isEmptyObj(selectedCategoryNodes)) {
  			// 未选择所属类目
			createNoty("error", "请选择所属类目！");
			return ;
		} else {
			for (var i = 0; i < selectedCategoryNodes.length; i++) {
				selectedCategoryIds.push(selectedCategoryNodes[i].id);
			}
		}
		var items = {categoryIds: selectedCategoryIds}; // 将数组转化为对象才能使用$.param(obj, [traditional])方法
		
  		// 发送AJAX请求
  		var url = "${path}" + "/admin/product/brand/update.htm";
  		var data = $("#modal-edit .form-horizontal").serialize() + "&" + $.param(items, true);
	  	$.ajax({url: url, async: false, data: data, dataType: 'json', type: 'POST',
	  		success: function(list) {
	  			$("#modal-edit").modal('hide');
	  			createNoty(); // 操作成功提醒框
	  			
	  			// 先删除修改之前的表格行
	  			setTimeout(function() {
					crud.operatedObj.closest('tr').fadeOut("slow", function() {
						$(this).remove();
					});
				}, 1000);
	  			
	  			// 表格最后一行插入tr
	  			var newTr = $('<tr class="active success">'
	  				+ '<td><input type="checkbox" value="<s:property value="id"/>" /></td>'
	  				+ '<td>' + list.brandId + '</td>'
	  				+ '<td>' + list.chineseName + '</td>'
	  				+ '<td>' + list.englishName + '</td>'
	  				+ '<td>' + list.description + '</td>'
	  				+ '<td>' + list.officialAddress + '</td>'
	  				+ '<td>'
	  					+ '<button type="button" class="btn btn-success btn-sm btn-read" value="' + list.id + '">查看</button>'
	  					+ '<button type="button" class="btn btn-success btn-sm btn-edit" value="' + list.id + '">编辑</button>'
	  					+ '<button type="button" class="btn btn-success btn-sm btn-delete" value="' + list.id + '">删除</button>'
	  				+ '</td>'
	  				+ '</tr>').hide().slideDown(1500);
	  			$('.table tbody').append(newTr);
	  			
	  			// 清空添加框内容
	  			EC.clearFormData($("#modal-edit .form-horizontal")); // 清空表单
	  		},
	  		error: function() {
	  			createNoty("error", "操作失败，请重试！"); // 操作失败提醒框
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
			chineseName: "required",
		},
		messages: {
		}
	});
});
	
</script>

<div class="modal fade" tabindex="-1" id="modal-edit">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h4 class="modal-title" id="myModalLabel">编辑品牌</h4>
			</div>
			<div class="modal-body">

				<form class="form-horizontal">
					<!-- 品牌编号隐藏表单 -->
					<input type="hidden" name="id" value='<s:property value="model.id" />' />
					<div class="form-group">
						<label for="chineseName" class="col-sm-4 control-label">品牌中文名</label>
						<div class="col-sm-8">
							<input type="text" class="form-control chineseName"
								name="chineseName" value='<s:property value="model.chineseName" />' placeholder="chineseName">
						</div>
					</div>
					<div class="form-group">
						<label for="englishName" class="col-sm-4 control-label">品牌英文名</label>
						<div class="col-sm-8">
							<input type="text" class="form-control englishName"
								name="englishName" value='<s:property value="model.englishName" />' placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label for="description" class="col-sm-4 control-label">品牌描述</label>
						<div class="col-sm-8">
							<input type="text" class="form-control description"
								name="description" value='<s:property value="model.description" />' placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label for="officialAddress" class="col-sm-4 control-label">品牌官方地址</label>
						<div class="col-sm-8">
							<input type="text" class="form-control officialAddress"
								name="officialAddress" value='<s:property value="model.officialAddress" />' placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label for="story" class="col-sm-4 control-label">品牌故事</label>
						<div class="col-sm-8">
							<input type="text" class="form-control story"
								name="story" value='<s:property value="model.story" />' placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label for="parentOperationId" class="col-sm-4 control-label">所属操作(不选则为顶级)</label>
						<div class="col-sm-8">
							<div id="update-categoryTree" class="ztree"></div>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary btn-edit">确定</button>
			</div>
		</div>

	</div>

</div>