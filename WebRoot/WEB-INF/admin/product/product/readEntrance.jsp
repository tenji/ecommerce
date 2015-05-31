<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>
<!-- 查看Modal -->
<script type="text/javascript">
</script>

<div class="modal fade" tabindex="-1" id="modal-read">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h4 class="modal-title" id="myModalLabel">产品详情</h4>
			</div>
			<div id="spin" align="center"></div>
			<div class="modal-body">
				
				<form class="form-horizontal">
					<div class="form-group">
						<label for="name" class="col-sm-3 control-label">产品名称</label>
						<div class="col-sm-9">
							<p class="form-control name"><s:property value="model.name"/></p>
						</div>
					</div>
					<div class="form-group">
						<label for="description" class="col-sm-3 control-label">产品描述</label>
						<div class="col-sm-9">
							<textarea rows="4" class="form-control loginUrl"><s:property value="model.description" /></textarea>
						</div>
					</div>
					<div class="form-group">
						<label for="listPrice" class="col-sm-3 control-label">标价</label>
						<div class="col-sm-9">
							<p class="form-control listPrice"><s:property value="model.listPrice"/>&nbsp;<s:property value="model.lastName"/></p>
						</div>
					</div>
					<div class="form-group">
						<label for="createTime" class="col-sm-3 control-label">创建时间</label>
						<div class="col-sm-9">
							<p class="form-control createTime"><s:date name="model.createTime" format="yyyy-MM-dd HH:mm:ss" /></p>
						</div>
					</div>
					<div class="form-group">
						<label for="modifyTime" class="col-sm-3 control-label">修改时间</label>
						<div class="col-sm-9">
							<p class="form-control modifyTime"><s:date name="model.modifyTime" format="yyyy-MM-dd HH:mm:ss" /></p>
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