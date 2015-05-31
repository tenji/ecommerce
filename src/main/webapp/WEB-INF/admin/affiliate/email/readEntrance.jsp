<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>
<!-- 查看Modal -->

<div class="modal fade" id="modal-read">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h4 class="modal-title" id="myModalLabel">Email Account Detail</h4>
			</div>
			<div class="modal-body">
				
				<form class="form-horizontal">
					<div class="form-group">
						<label for="email" class="col-sm-3 control-label">Email</label>
						<div class="col-sm-9">
							<p class="form-control email"><c:out value="${model.email }"></c:out></p>
						</div>
					</div>
					<div class="form-group">
						<label for="state" class="col-sm-3 control-label">State</label>
						<div class="col-sm-9">
							<p class="form-control state"><c:out value="${model.state.name }"></c:out></p>
						</div>
					</div>
					<div class="form-group">
						<label for="city" class="col-sm-3 control-label">City</label>
						<div class="col-sm-9">
							<p class="form-control city"><c:out value="${model.city.name }"></c:out></p>
						</div>
					</div>
					<div class="form-group">
						<label for="averageYearIncome" class="col-sm-3 control-label">Year Income</label>
						<div class="col-sm-9">
							<p class="form-control averageYearIncome"><c:out value="${model.averageYearIncome }"></c:out></p>
						</div>
					</div>
					<div class="form-group">
						<label for="address" class="col-sm-3 control-label">Address</label>
						<div class="col-sm-9">
							<p class="form-control address"><c:out value="${model.address }"></c:out></p>
						</div>
					</div>
					<div class="form-group">
						<label for="postalCode" class="col-sm-3 control-label">Postal Code</label>
						<div class="col-sm-9">
							<p class="form-control postalCode"><c:out value="${model.postalCode }"></c:out></p>
						</div>
					</div>
					<div class="form-group">
						<label for="birthday" class="col-sm-3 control-label">Birthday</label>
						<div class="col-sm-9">
							<p class="form-control birthday">
								<fmt:formatDate value="${model.birthday }" pattern="yy-MM-dd HH:mm:ss" />
							</p>
						</div>
					</div>
					<div class="form-group">
						<label for="firstName" class="col-sm-3 control-label">First Name</label>
						<div class="col-sm-9">
							<p class="form-control firstName"><c:out value="${model.firstName }"></c:out></p>
						</div>
					</div>
					<div class="form-group">
						<label for="lastName" class="col-sm-3 control-label">Last Name</label>
						<div class="col-sm-9">
							<p class="form-control lastName"><c:out value="${model.lastName }"></c:out></p>
						</div>
					</div>
					<div class="form-group">
						<label for="company" class="col-sm-3 control-label">Company</label>
						<div class="col-sm-9">
							<p class="form-control company"><c:out value="${model.company }"></c:out></p>
						</div>
					</div>
					<div class="form-group">
						<label for="seniorityLevelId" class="col-sm-3 control-label">Seniority Level ID</label>
						<div class="col-sm-9">
							<p class="form-control seniorityLevelId"><c:out value="${model.seniorityLevelId }"></c:out></p>
						</div>
					</div>
					<div class="form-group">
						<label for="jobRoleId" class="col-sm-3 control-label">Job Role ID</label>
						<div class="col-sm-9">
							<p class="form-control jobRoleId"><c:out value="${model.jobRoleId }"></c:out></p>
						</div>
					</div>
					<div class="form-group">
						<label for="industryId" class="col-sm-3 control-label">Industry ID</label>
						<div class="col-sm-9">
							<p class="form-control industryId"><c:out value="${model.industryId }"></c:out></p>
						</div>
					</div>
					<div class="form-group">
						<label for="createTime" class="col-sm-3 control-label">Create Time</label>
						<div class="col-sm-9">
							<p class="form-control createTime">
								<fmt:formatDate value="${model.createTime }" pattern="yy-MM-dd HH:mm:ss" />
							</p>
						</div>
					</div>
					<div class="form-group">
						<label for="modifyTime" class="col-sm-3 control-label">Modify Time</label>
						<div class="col-sm-9">
							<p class="form-control modifyTime">
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