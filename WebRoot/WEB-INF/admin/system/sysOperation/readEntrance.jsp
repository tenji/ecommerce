<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>
<!-- 查看Modal -->

<div class="modal fade" tabindex="-1" id="modal-read" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h4 class="modal-title" id="myModalLabel">查看操作权限</h4>
			</div>
			<div id="spin" align="center"></div>
			<div class="modal-body">
				
				<form class="form-horizontal" role="form">
					<div class="form-group">
						<label for="id" class="col-sm-3 control-label">操作编号</label>
						<div class="col-sm-9">
							<p class="form-control">${model.id }</p>
						</div>
					</div>
					<div class="form-group">
						<label for="operationName" class="col-sm-3 control-label">操作名称</label>
						<div class="col-sm-9">
							<p class="form-control"><c:out value="${model.operationName }"></c:out></p>
						</div>
					</div>
					<div class="form-group">
						<label for="url" class="col-sm-3 control-label">操作URL</label>
						<div class="col-sm-9">
							<p class="form-control"><c:out value="${model.url }"></c:out></p>
						</div>
					</div>
					<div class="form-group">
						<label for="createTime" class="col-sm-3 control-label">创建时间</label>
						<div class="col-sm-9">
							<p class="form-control">
								<fmt:formatDate value="${model.createTime }" pattern="yy-MM-dd HH:mm:ss" />
							</p>
						</div>
					</div>
					<div class="form-group">
						<label for="modifyTime" class="col-sm-3 control-label">修改时间</label>
						<div class="col-sm-9">
							<p class="form-control">
								<fmt:formatDate value="${model.modifyTime }" pattern="yy-MM-dd HH:mm:ss" />
							</p>
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