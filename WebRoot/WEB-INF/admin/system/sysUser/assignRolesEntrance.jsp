<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>
<!-- 分配角色Modal -->

<script type="text/javascript">
	function displayAssignRolesModal() {
		// 发送AJAX请求
		var url = "${path}" + "/admin/system/sysUser/getAssignedRoles.htm";
	  	$.ajax({url: url, data: {"id": crud.operatedObj.val()}, dataType: 'json', type: 'POST', async: false, 
	  		success: function(list) {
	  			if (!handleAjaxResults(list)) {
					return ;
				}
				
	  			tbody = $("#assignRolesForm").find("tbody");
	  			tbody.find("input:checkbox[name=items]:checked").prop("checked", false);
	  			// 遍历数组
	  			$.each(list, function(index, value) {
	  				tbody.find("input:checkbox[value=" + value + "]").prop("checked", true);
	  			});
	  			// 弹出Modal
				$("#modal-assignRoles").modal({
					backdrop : 'static',
				});
				spin.deleteSpin(); // 删除JS加载插件
	  		},
	  		error: function() {
	  			spin.deleteSpin(); // 删除JS加载插件
	  			alert("请求失败，请联系技术人员！");
	  			return ;
	  		}
	  	});
	  	
	}
	
	$(document).ready(function() {
		// 选择待分配角色
		$(".items").change(function() {
			var modal = $("#modal-assignRoles");
		});
		// 全选和反选
		$(".selectAll").on('click', function() {
			$(this).closest("table").find(":checkbox").prop("checked", this.checked);
			
			$(".items").change();	// 触发items的change事件
		});
		
		// 点击确认分配
		$(".btn-assignRoles").on('click', function() {
			$("#modal-assignRoles").modal('hide');
			// createProgressBar(); // 创建动态进度条
			
			var values = [];
			$("#assignRolesForm").find("tbody").find("input:checkbox[name=items]:checked").each(function() {
				values.push($(this).val());
			});
			var items = {items: values}; // 将数组转化为对象才能使用$.param(obj, [traditional])方法
			items.id = itemId; // 将需要分配权限的用户编号加入对象
			
			getProgress(items);
		});
		
		function getProgress(items) {
			// 发送AJAX请求
		  	$.ajax({
		  		url: "${path}" + "/admin/system/sysUser/assignRoles.htm",
		  		data: $.param(items, true),
		  		dataType: 'json',
		  		type: 'POST',
		  		success: function(list) {
		  			// createNoty(); // 成功提示
		  		},
		  		error: function() {
		  			// createNoty("error", "AJAX请求失败，请重试！"); // 失败提示
		  			alert("操作失败");
		  		}
		  	});
		}
	});
</script>

<div class="modal fade" tabindex="-1" id="modal-assignRoles">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h4 class="modal-title" id="myModalLabel">分配角色</h4>
			</div>

			<div class="modal-body">
				<form id="assignRolesForm" name="assignRolesForm">
				<table class="table table-striped table-bordered table-hover dataTable" contenteditable="false" style="">
					<thead>
						<tr>
							<th class="center">
								<label>
									<input type="checkbox" class="ace" />
									<span class="lbl"></span>
								</label>
							</th>
							<th style="">角色名称</th>
							<th style="">角色描述</th>
						</tr>
					</thead>
					<tbody>
					<s:iterator value="sysRoleList">
						<tr class="active">
							<td class="center sorting_disabled">
								<label>
									<input type="checkbox" class="ace items" name="items" value="<s:property value="id"/>" />
									<span class="lbl"></span>
								</label>
							</td>
							<td><s:property value="roleName" />
							</td>
							<td><s:property value="description" />
							</td>
						</tr>
					</s:iterator>
					</tbody>
				</table>
				</form>

			</div>
			<div class="modal-footer">
			<span class="selectedTips" style="display: none">已选中<span class="selectedNums"></span>项&nbsp;&nbsp;</span>
			<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary btn-assignRoles">确认分配</button>
			</div>
		</div>

	</div>

</div>