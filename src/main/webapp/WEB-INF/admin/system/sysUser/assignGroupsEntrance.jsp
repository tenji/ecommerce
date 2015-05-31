<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>
<!-- 分配权限Modal -->

<script type="text/javascript">
	// zTree配置
	zTree.setting = {
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
	
	$(function() {
		var url = "${path}" + "/admin/system/sysGroup/getAssignedGroupsTree.htm";
	  	// 获取用户组树
	  	$.ajax({url: url, async: false, data: {"userId": crud.operatedObj.val()}, dataType: 'json', type: 'POST',
	  		success: function(treeNodes) {
	  			zTree.zTreeObj =  $.fn.zTree.init($("#groupTree"), zTree.setting, treeNodes); // 初始化
		
				// 弹出Modal
				$("#modal-assignGroups").modal({
					backdrop : 'static',
				});
				spin.deleteSpin(); // 删除JS加载插件
	  		},
	  		error: function() {
	  			deleteSpin(); // 删除JS加载插件
	  			createNoty("error", "操作失败，请重试！");
	  			return ;
	  		}
	  	});
	});
	
	// ZTree被勾选或者取消勾选
	function zTreeOnCheck() {
		var modal = $("#modal-assignGroups");
		selectZTree(zTree.zTreeObj, modal.find(".selectedNums"), modal.find(".selectedTips"), modal.find(".btn-assignGroups"));
	}
	
	$(document).ready(function() {
		// 点击确认分配
		$(".btn-assignGroups").on('click', function() {
			$("#modal-assignGroups").modal('hide');
			spin.createSpin();
			
			var selectedGroupNodes = [];
			var selectedGroupIds = []; // 用户组编号数组
			
			selectedGroupNodes = zTree.zTreeObj.getCheckedNodes(true);
			for (var i = 0; i < selectedGroupNodes.length; i++) {
				selectedGroupIds.push(selectedGroupNodes[i].id);
			}
			
			var items = {itemIds: selectedGroupIds}; // 将数组转化为对象才能使用$.param(obj, [traditional])方法
			items.id = crud.operatedObj.val(); // 将需要分配用户组的用户编号加入对象
			
			// 分配用户组
			var url = "${path}" + "/admin/system/sysUser/assignGroups.htm";
		  	$.ajax({url: url, data: $.param(items, true), dataType: 'json', aysnc: false, type: 'POST',
		  		success: function(list) {
		  			spin.deleteSpin();
		  			createNoty(); // 成功提示
		  		},
		  		error: function() {
		  			spin.deleteSpin();
		  			createNoty("error", "分配用户组失败，请重试！"); // 失败提示
		  		}
		  	});
		  	
		});
		
	});
	
</script>

<div class="modal fade" tabindex="-1" id="modal-assignGroups">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h4 class="modal-title">分配用户组</h4>
			</div>
			<!-- modal-body start -->
			<div class="modal-body">

				<div class="tabbable">
					<ul class="nav nav-tabs padding-12 tab-color-blue background-blue">
						<li class="active"><a href="#panel-menus" data-toggle="tab">用户组</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="panel-menus">
						
							<!-- zTree用户组树 start-->
								<div id="groupTree" class="ztree"></div>
							<!-- zTree end -->	
						
						</div>
						
					</div>
				</div>

			</div><!-- modal-body end -->
			
			<div class="modal-footer">
			<span class="selectedTips" style="display: none">已选中<span class="selectedNums"></span>项&nbsp;&nbsp;</span>
			<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary btn-assignGroups" disabled="disabled">确认分配</button>
			</div>
		</div>

	</div>

</div>