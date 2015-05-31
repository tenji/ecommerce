<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>
<!-- 分配权限Modal -->

<script type="text/javascript">
	var zTreeMenuObj; // 树形菜单对象
	var zTreeOperationObj; // 树形操作权限对象
	// zTree配置
	var setting = {
		check: { /** 复选框 **/
			enable: true,
			chkStyle: "checkbox",
			autoCheckTrigger: true,
			chkboxType: {"Y":"ps", "N":"ps"}
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
	
	function displayAssignRightsModal(itemId) {
		var menusTreeUrl = "${path}" + "/admin/system/sysMenu/getAssignedMenusTree";
		// 获取已分配菜单权限
	  	$.ajax({url: menusTreeUrl, async: false, data: {"roleId": itemId}, dataType: 'json', type: 'POST',
	  		success: initMenuZTree,
	  		error: function() {
	  			spin.deleteSpin(); // 删除JS加载插件
	  			EC.createNoty("error", "操作失败，请重试！");
	  			return ;
	  		}
	  	});
	  	
	  	var operationsTreeUrl = "${path}" + "/admin/system/sysOperation/getAssignedOperationsTree";
	  	// 获取已分配操作权限
	  	$.ajax({
	  		url: operationsTreeUrl, async: false, data: {"roleId": itemId}, dataType: 'json', type: 'POST',
	  		success: initOperationZTree,
	  		error: function() {
	  			EC.deleteSpinner(); // 删除JS加载插件
	  			EC.createNoty("error", "操作失败，请重试！");
	  			return ;
	  		}
	  	});
	  	
	}
	
	// 初始化MenuZTree
	function initMenuZTree(sysMenuList) {
		if (sysMenuList.key == "error") {
			EC.deleteSpinner();
			EC.createNoty("error", sysMenuList.value);
			return ;
		}
		
 		var treeNodes = sysMenuList;
		zTreeMenuObj =  $.fn.zTree.init($("#menuTree"), setting, treeNodes);
	}
	
	// 初始化OperationZTree
	function initOperationZTree(sysOperationList) {
		if (sysOperationList.key == "error") {
			spin.deleteSpin();
			EC.createNoty("error", sysOperationList.value);
			return ;
		}
		
		var treeNodes = sysOperationList;
		zTreeOperationObj =  $.fn.zTree.init($("#operationTree"), setting, treeNodes);
		
		// 弹出Modal
		$("#modal-assignRights").modal({
			backdrop : 'static',
		});
		EC.deleteSpinner(); // 删除JS加载插件
	}
	
	// ZTree被勾选或者取消勾选
	function zTreeOnCheck() {
		var modal = $("#modal-assignRights");
		selectZTree(zTreeMenuObj, modal.find(".selectedNums"), modal.find(".selectedTips"), modal.find(".btn-assignRights"));
	}
	
	$(function() {
		// 点击确认分配
		$(".btn-assignRights").on('click', function() {
			$("#modal-assignRights").modal('hide');
			
			var selectedMenuNodes = []; 
			var selectedOperationNodes = [];
			var selectedMenuIds = ""; // 菜单权限编号串
			var selectedOperationIds = ""; // 操作权限编号串
			
			selectedMenuNodes = zTreeMenuObj.getCheckedNodes(true);
			selectedOperationNodes = zTreeOperationObj.getCheckedNodes(true);
			for (var i = 0; i < selectedMenuNodes.length; i++) {
				if (0 == i) { selectedMenuIds = selectedMenuNodes[i].id; continue ; }
				selectedMenuIds += ("," + selectedMenuNodes[i].id);
			}
			for (var i = 0; i < selectedOperationNodes.length; i++) {
				if (0 == i) { selectedOperationIds = selectedOperationNodes[i].id; continue ; }
				selectedOperationIds += ("," + selectedOperationNodes[i].id);
			}
			
			// 分配权限
		  	$.ajax({
		  		url: "${path}" + "/admin/system/sysRole/assignRights",
		  		data: {"id" : crud.operatedObj.val(), "menuIds" : selectedMenuIds, "operationIds" : selectedOperationIds},
		  		dataType: 'json',
		  		aysnc: false,
		  		type: 'POST',
		  		success: function(list) {
		  			EC.createNoty(); // 成功提示
		  		},
		  		error: function() {
		  			EC.createNoty("error", "分配权限失败，请重试！"); // 失败提示
		  		}
		  	});
		  	
		});
		
	});
	
</script>

<div class="modal fade" tabindex="-1" id="modal-assignRights" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h4 class="modal-title">分配权限</h4>
			</div>
			<!-- modal-body start -->
			<div class="modal-body">

				<div class="tabbable">
					<ul class="nav nav-tabs padding-12 tab-color-blue background-blue">
						<li class="active"><a href="#panel-menus" data-toggle="tab">菜单权限</a></li>
						<li><a href="#panel-operations" data-toggle="tab">操作权限</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="panel-menus">
						
						<!-- zTree菜单权限树 start-->
							<div id="menuTree" class="ztree"></div>
						<!-- zTree end -->
							
						</div>
						
						<div class="tab-pane" id="panel-operations">
						
						<!-- zTree操作权限树 start-->
							<div id="operationTree" class="ztree"></div>
						<!-- zTree end -->	
						
						</div>
						
					</div>
				</div>

			</div><!-- modal-body end -->
			
			<div class="modal-footer">
			<span class="selectedTips" style="display: none">已选中<span class="selectedNums"></span>项&nbsp;&nbsp;</span>
			<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary btn-assignRights" disabled="disabled">确认分配</button>
			</div>
		</div>

	</div>

</div>