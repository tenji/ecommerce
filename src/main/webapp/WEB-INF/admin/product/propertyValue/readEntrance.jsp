<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>
<!-- 查看Modal -->

<div class="modal fade" id="modal-read">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h4 class="modal-title" id="myModalLabel">查看属性值</h4>
			</div>
			<div class="modal-body">
				
				<form class="form-horizontal">
					<div class="form-group">
						<label for="name" class="col-sm-3 control-label">属性值</label>
						<div class="col-sm-9">
							<p class="form-control name"><s:property value="model.name" /></p>
						</div>
					</div>
					<div class="form-group">
						<label for="propertyName.name" class="col-sm-3 control-label">属性名</label>
						<div class="col-sm-9">
							<p class="form-control propertyName.name"><s:property value="model.propertyName.name" /></p>
						</div>
					</div>
					<div class="form-group">
						<label for="category.categoryName" class="col-sm-3 control-label">所属类目</label>
						<div class="col-sm-9">
							<p class="form-control category.categoryName"><s:property value="model.category.categoryName" /></p>
						</div>
					</div>
					<div class="form-group">
						<label for="createTime" class="col-sm-3 control-label">Create Time</label>
						<div class="col-sm-9">
							<p class="form-control createTime"><s:date name="model.createTime" format="yyyy-MM-dd HH:mm:ss" /></p>
						</div>
					</div>
					<div class="form-group">
						<label for="modifyTime" class="col-sm-3 control-label">Modify Time</label>
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