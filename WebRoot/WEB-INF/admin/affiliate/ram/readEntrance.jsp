<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>
<!-- 查看Modal -->
<script type="text/javascript">
</script>

<div class="modal fade" id="modal-read">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h4 class="modal-title" id="myModalLabel">Ram site detail</h4>
			</div>
			<div id="spin" align="center"></div>
			<div class="modal-body">

				<form class="form-horizontal">
					<div class="form-group">
						<label for="name" class="col-sm-3 control-label">Name</label>
						<div class="col-sm-9">
							<p class="form-control name"><c:out value="${model.name }"></c:out></p>
						</div>
					</div>
					<div class="form-group">
						<label for="officialWebsiteUrl" class="col-sm-3 control-label">Official Website Url</label>
						<div class="col-sm-9">
							<p class="form-control officialWebsiteUrl"><c:out value="${model.officialWebsiteUrl }"></c:out></p>
						</div>
					</div>
					<div class="form-group">
						<label for="ramRegisterUrl" class="col-sm-3 control-label">Ram Register Url</label>
						<div class="col-sm-9">
							<textarea rows="2" class="form-control ramRegisterUrl"><c:out value="${model.ramRegisterUrl }"></c:out></textarea>
						</div>
					</div>
					<div class="form-group">
						<label for="rewardDetail" class="col-sm-3 control-label">Reward Detail</label>
						<div class="col-sm-9">
							<textarea rows="3" class="form-control rewardDetail"><c:out value="${model.rewardDetail }"></c:out></textarea>
						</div>
					</div>
					<div class="form-group">
						<label for="remark" class="col-sm-3 control-label">Remark</label>
						<div class="col-sm-9">
							<textarea rows="3" class="form-control remark"><c:out value="${model.remark }"></c:out></textarea>
						</div>
					</div>
				</form>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal">OK</button>
			</div>
		</div>

	</div>

</div>