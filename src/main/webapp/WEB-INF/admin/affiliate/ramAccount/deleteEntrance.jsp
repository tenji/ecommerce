<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>
<!-- 删除Modal -->

<script type="text/javascript">
$(function() {
	// 点击删除确认按钮，删除指定权限
	$("#modal-delete .btn-delete").on('click', function() {
		// 发送AJAX请求
		var url = "${path}" + "/admin/affiliate/ramAccount/delete";
		$.ajax({url : url, data : {"id" : crud.operatedObj.val()}, dataType : 'json', type : 'POST', async: false, 
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
				EC.createNoty("error", "操作失败，请重试！");
			}
		});
	});
	
});
</script>

<div class="modal" tabindex="-1" id="modal-delete">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				<button class="bootbox-close-button close" type="button" data-dismiss="modal"
					style="margin-top: -10px;">×</button>
				<div class="bootbox-body">Are you sure?</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				<button type="button" class="btn btn-warning btn-delete">OK</button>
			</div>
		</div>

	</div>

</div>