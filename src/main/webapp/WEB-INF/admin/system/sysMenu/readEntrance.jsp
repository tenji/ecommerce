<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>
<!-- 查看Modal -->
<script type="text/javascript">
</script>

<div class="modal fade" tabindex="-1" id="modal-read" >
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h4 class="modal-title" id="myModalLabel">查看菜单</h4>
			</div>
			<div class="modal-body">

				<form class="form-horizontal">
					<div class="form-group">
						<label for="id" class="col-sm-3 control-label">菜单编号</label>
						<div class="col-sm-9">
							<p class="form-control id">${model.id }</p>
						</div>
					</div>
					<div class="form-group">
						<label for="menuName" class="col-sm-3 control-label">菜单名称</label>
						<div class="col-sm-9">
							<p class="form-control menuName">${model.menuName }</p>
						</div>
					</div>
					<div class="form-group">
						<label for="menuDescription" class="col-sm-3 control-label">菜单描述</label>
						<div class="col-sm-9">
							<p class="form-control menuDescription">${model.menuDescription }</p>
						</div>
					</div>
					<div class="form-group">
						<label for="url" class="col-sm-3 control-label">菜单URL</label>
						<div class="col-sm-9">
							<p class="form-control url">${model.url }</p>
						</div>
					</div>
				</form>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
			</div>
		</div>

	</div>

</div>