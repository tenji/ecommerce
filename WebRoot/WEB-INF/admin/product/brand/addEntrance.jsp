<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>
<!-- 添加Modal -->

<script type="text/javascript">
zTree.setting = {
	check: { /** 复选框 **/
		enable: true,
		chkStyle: "checkbox",
		autoCheckTrigger: true,
		chkboxType: {"Y":"", "N":""} // 是否关联父子节点
	},  
	view: { 
		expandSpeed: 300, // 设置树展开的动画速度，IE6下面没效果，  
		showIcon: true, 
		fontCss: zTree.getFontCss
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
	// 发送AJAX请求，获取产品类目树
	var url = "${path}" + "/admin/product/category/getAssignedCategoriesTree.htm";
  	$.ajax({url: url, dataType: 'json', type: 'POST', async: false, 
  		success: function(treeNodes) {
  			if (!handleAjaxResults(treeNodes)) {
				return ;
			} 
			
			zTree.zTreeObj =  $.fn.zTree.init($("#categoryTree"), zTree.setting, treeNodes);
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
  	$("#modal-add .btn-add").on('click', function() {
  		// 验证表单是否合法
  		if (!$("#modal-add .form-horizontal").valid()) {
			return ;
		}
		
  		var selectedCategoryIds = []; // 所属类目数组
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
  		var url = "${path}" + "/admin/product/brand/add.htm";
  		var data = $("#modal-add .form-horizontal").serialize() + "&" + $.param(items, true);
	  	$.ajax({url: url, async: false,  data: data, dataType: 'json', type: 'POST',
	  		success: function(list) {
	  			$("#modal-add").modal('hide');
	  			createNoty(); // 操作成功提醒框
	  			
	  			// 表格最后一行插入tr
	  			var newTr = $('<tr class="active success">'
	  				+ '<td><input type="checkbox" value="<s:property value="id"/>" /></td>'
	  				+ '<td>' + list.chineseName + '</td>'
	  				+ '<td>' + list.englishName + '</td>'
	  				+ '<td>' + list.description + '</td>'
	  				+ '<td>' + list.officialAddress + '</td>'
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
			chineseName: "required",
			englishName: "required",
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
				<h4 class="modal-title" id="myModalLabel">添加品牌</h4>
			</div>
			<!-- JS加载样式div -->
			<div id="spin" align="center"></div>
			
			<div class="modal-body">

				<form class="form-horizontal">
					<div class="form-group">
						<label for="chineseName" class="col-sm-4 control-label">品牌中文名</label>
						<div class="col-sm-8">
							<input type="text" class="form-control chineseName" 
								name="chineseName" id="chineseName" value="" placeholder="请输入品牌中文名" >
						</div>
					</div>
					<div class="form-group">
						<label for="englishName" class="col-sm-4 control-label">品牌英文名</label>
						<div class="col-sm-8">
							<input type="text" class="form-control englishName" 
								name="englishName" id="englishName" value="" placeholder="请输入品牌英文名" >
						</div>
					</div>
					<div class="form-group">
						<label for="description" class="col-sm-4 control-label">品牌描述</label>
						<div class="col-sm-8">
							<input type="text" class="form-control description" 
								name="description" value="" placeholder="请输入品牌描述" >
						</div>
					</div>
					<div class="form-group">
						<label for="officialAddress" class="col-sm-4 control-label">品牌官方地址</label>
						<div class="col-sm-8">
							<input type="text" class="form-control officialAddress" 
								name="officialAddress" value="" placeholder="请输入品牌官方地址" >
						</div>
					</div>
					<div class="form-group">
						<label for="story" class="col-sm-4 control-label">品牌故事</label>
						<div class="col-sm-8">
							<input type="text" class="form-control story" 
								name="story" value="" placeholder="请输入品牌故事" >
						</div>
					</div>
					<div class="form-group">
						<label for="categoryId" class="col-sm-4 control-label">所属类目(必选)</label>
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