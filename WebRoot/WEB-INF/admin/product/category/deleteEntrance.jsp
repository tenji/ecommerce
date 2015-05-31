<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>
<!-- 删除Modal -->

<script type="text/javascript">
$(document).ready(function() {
	// 点击删除确认按钮，删除指定权限
	$("#modal-delete .btn-delete").on('click', function() {
		var url = "${path}" + "/admin/product/category/delete.htm";
		// 发送AJAX请求
		$.ajax({url : url, data : {"id": crud.operatedObj.val()}, dataType : 'json', type : 'POST',
			success : function(list) {
				if (!handleAjaxResults(list)) {
					return ;
				}
				
				$("#modal-delete").modal('hide');
				// 如果数据库删除成功，则删除表格对应行
				setTimeout(function() {
					crud.operatedObj.closest('tr').fadeOut("slow", function() {
						$(this).remove();
					});
				}, 1000);
			},
			error : function() {
				createNoty("error", "操作失败，请重试！");
			}
		});
	});
	
});
</script>

<div class="modal fade" tabindex="-1" id="modal-delete">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
			</div>
			<div class="modal-body">您确定要删除这条记录吗？</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-warning btn-delete">确定</button>
			</div>
		</div>

	</div>

</div>