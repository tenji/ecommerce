<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>
<!-- 添加Modal -->

<script type="text/javascript">
	var zTreeCategoryObj; // 树形产品类目对象
	// zTree配置
	var setting = {
		check: { /** 复选框 **/
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
	 		onCheck: zTreeOnCheck
	 	}
	};
	
	var addEntrance = { // 命名空间
		addItem: function() {
			// 发送AJAX请求，获取产品类目树
			var url = "${path}" + "/admin/product/category/getAssignedCategoriesTree.htm";
		  	$.ajax({url: url, dataType: 'json', type: 'POST', async: false, // 同步
		  		success: addEntrance.initCategoryZTree, 
		  		error: function() {
		  			EC.createNoty("error", "操作失败，请重试！");
		  			return ;
		  		}
		  	});
			
		}, 
		
		// 初始化CategoryZTree
		initCategoryZTree: function(sysCategoryList) {
			if (!handleAjaxResults(sysCategoryList)) {
				return ;
			} 
			
			var treeNodes = sysCategoryList;
			zTreeCategoryObj =  $.fn.zTree.init($("#categoryTree"), setting, treeNodes);
			
			// 弹出Modal
			$("#modal-add").modal({
				backdrop : 'static',
			});
			deleteSpin(); // 删除JS加载插件
		}, 
		
	};
	
	// 节点选中事件
	function zTreeOnCheck(event, treeId, treeNode) {
		createSpin();
		// 发送AJAX请求，获取特定类目下的品牌
		var url = "${path}" + "/admin/product/brand/getBrandsByCategoryId.htm";
	  	$.ajax({url: url, dataType: 'json', data: {categoryId: treeNode.id}, type: 'POST', async: false, 
	  		success: function(brandList) {
	  			var brandSelect = $("#modal-add").find(".brandId");
	  			brandSelect.html(""); // 先清空select框内容
	  			for ( var i = 0; i < brandList.length; i++) {
					$("<option value=" + brandList[i].brandId + ">" + brandList[i].chineseName + "</option>").appendTo(brandSelect);
				}
	  			deleteSpin();
	  		}, 
	  		error: function() {
	  			EC.createNoty("error", "操作失败，请重试！");
	  			return ;
	  		}
	  	});
	}
	
	$(document).ready(function() {
	// 点击确认按钮
  	$("#modal-add").find(".btn-add").on('click', function() {
  		// 验证表单是否合法
  		if (!$("#modal-add .form-horizontal").valid()) {
			return ;
		}
  		var modalAdd = $("#modal-add"); // 获取id为modal-add的jQuery对象
  		var productName = modalAdd.find(".productName").val(); // 产品名称
  		var listPrice = modalAdd.find(".listPrice").val(); // 列表价
  		var categoryId;	// 类目编号
  		
  		var selectedCategoryNodes = zTreeCategoryObj.getCheckedNodes(true);
  		if (isEmptyObj(selectedCategoryNodes)) {
  			// 未选择所属类目
  			createNoty("error", "请选择所属类目！");
			return ;
		} else {
			categoryId = selectedCategoryNodes[0].id;
		}
		var brandId = modalAdd.find(".brandId option:selected").val();
		if (!brandId) {
			createNoty("error", "请选择所属品牌！");
			return ;
		}
  		// 发送AJAX请求
	  	$.ajax({
	  		url: "${path}" + "/admin/product/product/add.htm",
	  		async: false, 
	  		data: {"productName": productName, "listPrice": listPrice, "categoryId": categoryId, "brandId": brandId},
	  		dataType: 'json',
	  		type: 'POST',
	  		success: function(list) {
	  			$("#modal-add").modal('hide');
	  			
	  			createNoty(); // 操作成功提醒框
	  			
	  			// 表格最后一行插入tr
	  			var newTr = $('<tr class="active success">'
	  				+ '<td><input type="checkbox" value="<s:property value="productId"/>" /></td>'
	  				+ '<td>' + list.productId + '</td>'
	  				+ '<td>' + list.productName + '</td>'
	  				+ '<td>' + list.listPrice + '</td>'
	  				+ '<td>' + list.createDate + '</td>'
	  				+ '<td>'
	  					+ '<button type="button" class="btn btn-success btn-sm" value="' + list.productId + '" onclick="readInit(this.value)">查看</button>'
	  					+ ' <button type="button" class="btn btn-success btn-sm" value="' + list.productId + '" onclick="editItem(this.value)">编辑</button>'
	  					+ ' <a href="#modal-delete" data-toggle="modal" data-backdrop="static">' 
	  						+ ' <button type="button" class="btn btn-success btn-sm" value="' + list.productId + '" onclick="updateItemId(this.value);updateTrObj(this)">删除</button>'
	  					+ ' </a>'
	  				+ '</td>'
	  				+ '</tr>').hide().slideDown(1000);
	  			$('.table tbody').append(newTr);
	  			
	  			// 清空添加框内容
	  			modalAdd.find(".productName").val("");
	  			modalAdd.find(".parentId").get(0).selectedIndex = 0; // 设置Select索引值为0的项选中
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
			productName: "required",
			listPrice: {required: true, number: true},
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
				<h4 class="modal-title" id="myModalLabel">添加产品</h4>
			</div>
			<!-- JS加载样式div -->
			<div id="spin" align="center"></div>
			
			<div class="modal-body">

				<form class="form-horizontal">
					<div class="form-group">
						<label for="productName" class="col-sm-4 control-label">产品名称</label>
						<div class="col-sm-8">
							<input type="text" class="form-control productName" 
								name="productName" value="" placeholder="请输入产品名称" >
						</div>
					</div>
					<div class="form-group">
						<label for="listPrice" class="col-sm-4 control-label">列表价</label>
						<div class="col-sm-8">
							<input type="text" class="form-control listPrice" 
								name="listPrice" value="" placeholder="请输入列表价" >
						</div>
					</div>
					<div class="form-group">
						<label for="categoryId" class="col-sm-4 control-label">所属类目(必选)</label>
						<div class="col-sm-8">
							<div id="categoryTree" class="ztree"></div>
						</div>
					</div>
					<div class="form-group">
						<label for="brandId" class="col-sm-4 control-label">所属品牌</label>
						<div class="col-sm-8">
							<select multiple class="form-control brandId">
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