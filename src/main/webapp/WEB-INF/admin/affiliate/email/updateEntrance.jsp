<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>
<!-- 编辑Modal -->

<script type="text/javascript">
var updateEntrance = { // 命名空间
	// 读取需要编辑的数据之后的操作
	editAfterAjax: function(list) {
	} 
};
	
$(function() {
	// 选择时间
	$(".date-picker").datepicker().next().on(ace.click_event, function(){
		$(this).prev().focus();
	});
	
	// 点击确认按钮
  	$("#modal-edit").find(".btn-edit").on('click', function() {
  		// 验证表单是否合法
  		if (!$("#modal-edit .form-horizontal").valid()) {
			return ;
		}
		
  		// 发送AJAX请求
  		var url = "${path}" + "/admin/affiliate/email/update";
  		var data = $("#modal-edit .form-horizontal").serialize();
	  	$.ajax({
	  		url: url, async: false, data: data, dataType: 'json', type: 'POST',
	  		success: function(list) {
	  			$("#modal-edit").modal('hide');
	  			
	  			EC.createNoty(); // 操作成功提醒框
	  			
	  			setTimeout(function() {
	  				location.reload(); // 刷新页面
	  			}, 1000);
	  		},
	  		error: function() {
	  			EC.createNoty("error", "操作失败，请重试！"); // 操作失败提醒框
	  			$("#modal-edit").modal('hide');
	  		}
	  	});
  	});
  	
  	// jquery validate验证
  	$("#modal-edit .form-horizontal").validate({
  		// 焦点离开
  		onfocusout: function(event) {
  			$(event).valid();
  		}, 
		rules: {
			"email": "required", 
			"ram.id": "required",
		},
		messages: {
			"email": "Email is required.", 
			"ram.id": "Ram site is required."
		}
	});
  	
});
	
</script>

<div class="modal fade" tabindex="-1" id="modal-edit">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h4 class="modal-title" id="myModalLabel">Update Ram Account</h4>
			</div>
			<div class="modal-body">

				<form class="form-horizontal">
					<input type="hidden" name="id" value='${model.id }' />
					<div class="form-group">
						<label for="email" class="col-sm-4 control-label">Email</label>
						<div class="col-sm-8">
							<input type="text" class="form-control email" name="email" placeholder="Email" value="${model.email }">
						</div>
					</div>
					<div class="form-group">
						<label for="state" class="col-sm-4 control-label">State</label>
						<div class="col-sm-8">
							<select class="form-control state" name="state.id">
								<option value="" selected>Please Select Specific State</option>
								<c:forEach items="${stateList }" var="state">
									<option value='${state.id }' <c:if test="${state.id == model.state.id }">selected</c:if> >${state.name }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="city" class="col-sm-4 control-label">City</label>
						<div class="col-sm-8">
							<select class="form-control city" name="city.id">
								<option value="" selected>Please Select Specific City</option>
								<c:forEach items="${cityList }" var="city">
									<option value='${city.id }' <c:if test="${city.id == model.city.id }">selected</c:if> >${city.name }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="averageYearIncome" class="col-sm-4 control-label">Average Year Income</label>
						<div class="col-sm-8">
							<input type="text" class="form-control averageYearIncome" name="averageYearIncome" placeholder="Average Year Income" value="${model.averageYearIncome }">
						</div>
					</div>
					<div class="form-group">
						<label for="address" class="col-sm-4 control-label">Address</label>
						<div class="col-sm-8">
							<input type="text" class="form-control address" name="address" placeholder="Address" value='${model.address }'>
						</div>
					</div>
					<div class="form-group">
						<label for="postalCode" class="col-sm-4 control-label">Postal Code</label>
						<div class="col-sm-8">
							<input type="text" class="form-control postalCode" name="postalCode" placeholder="Postal Code" value='${model.postalCode }'>
						</div>
					</div>
					<div class="form-group">
						<label for="birthday" class="col-sm-4 control-label">Birthday</label>
						<div class="col-sm-8">
							<input type="text" placeholder="yyyy-MM-dd" name="birthday"
								data-date-format="yyyy-mm-dd" id="birthday" class="form-control date-picker" 
								value='<fmt:formatDate value="${model.birthday }" pattern="yyyy-MM-dd" />' />
						</div>
					</div>
					<div class="form-group">
						<label for="firstName" class="col-sm-4 control-label">First Name</label>
						<div class="col-sm-8">
							<input type="text" class="form-control firstName" name="firstName" placeholder="First Name" value='${model.firstName }'>
						</div>
					</div>
					<div class="form-group">
						<label for="lastName" class="col-sm-4 control-label">Last Name</label>
						<div class="col-sm-8">
							<input type="text" class="form-control lastName" name="lastName" placeholder="Last Name" value="${model.lastName }">
						</div>
					</div>
					<div class="form-group">
						<label for="company" class="col-sm-4 control-label">Company</label>
						<div class="col-sm-8">
							<input type="text" class="form-control company" name="company" placeholder="Working Company" value="${model.company }">
						</div>
					</div>
					<div class="form-group">
						<label for="seniorityLevelId" class="col-sm-4 control-label">Seniority Level</label>
						<div class="col-sm-8">
							<select class="form-control" name="seniorityLevelId">
								<option value="" selected>Please Select Seniority Level</option>
								<option value="0" <c:if test="${model.seniorityLevelId == 0 }">selected</c:if>>Chief Executive Officer or Corporate Executive Officer or equivalent</option>
								<option value="1" <c:if test="${model.seniorityLevelId == 1 }">selected</c:if>>Managing Director, Regional Director, District Director, or equivalent</option>
								<option value="2" <c:if test="${model.seniorityLevelId == 2 }">selected</c:if>>Department/division/section Director or equivalent</option>
								<option value="3" <c:if test="${model.seniorityLevelId == 3 }">selected</c:if>>Civil Servant with personnel or economic responsibility or equivalent</option>
								<option value="4" <c:if test="${model.seniorityLevelId == 4 }">selected</c:if>>Civil Servant, Handling officer without personnel or economic responsibility, or equivalent</option>
								<option value="5" <c:if test="${model.seniorityLevelId == 5 }">selected</c:if>>Other position/Unemployed</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="jobRoleId" class="col-sm-4 control-label">Job Role</label>
						<div class="col-sm-8">
							<select class="form-control" name="jobRoleId">
								<option value="" selected>Please Select Job Role</option>
								<option value="0" <c:if test="${model.jobRoleId == 0 }">selected</c:if>>Management</option>
								<option value="1" <c:if test="${model.jobRoleId == 1 }">selected</c:if>>Clerical/Administration</option>
								<option value="2" <c:if test="${model.jobRoleId == 2 }">selected</c:if>>Purchasing</option>
								<option value="3" <c:if test="${model.jobRoleId == 3 }">selected</c:if>>Technology/Research and Development/Engineering</option>
								<option value="4" <c:if test="${model.jobRoleId == 4 }">selected</c:if>>Personnel/HR</option>
								<option value="5" <c:if test="${model.jobRoleId == 5 }">selected</c:if>>Marketing</option>
								<option value="6" <c:if test="${model.jobRoleId == 6 }">selected</c:if>>Manufacturing/Factory/Maintenance</option>
								<option value="7" <c:if test="${model.jobRoleId == 7 }">selected</c:if>>Computers/IT</option>
								<option value="8" <c:if test="${model.jobRoleId == 8 }">selected</c:if>>Service/Transport</option>
								<option value="9" <c:if test="${model.jobRoleId == 9 }">selected</c:if>>Education/Schooling</option>
								<option value="10" <c:if test="${model.jobRoleId == 10 }">selected</c:if>>Health/Nutrition</option>
								<option value="11" <c:if test="${model.jobRoleId == 11 }">selected</c:if>>Other</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="industryId" class="col-sm-4 control-label">Industry</label>
						<div class="col-sm-8">
							<select class="form-control" name="industryId">
								<option value="" selected>Please Select Job Role</option>
								<option value="0" <c:if test="${model.industryId == 0 }">selected</c:if>>Farming/Hunting/Forestry/Fisheries</option>
								<option value="1" <c:if test="${model.industryId == 1 }">selected</c:if>>Food and drinks industry</option>
								<option value="2" <c:if test="${model.industryId == 2 }">selected</c:if>>Timber/Pulp and Paper industry</option>
								<option value="3" <c:if test="${model.industryId == 3 }">selected</c:if>>Printing industry</option>
								<option value="4" <c:if test="${model.industryId == 4 }">selected</c:if>>Rubber and plastics industry</option>
								<option value="5" <c:if test="${model.industryId == 5 }">selected</c:if>>Metal/Machinery industry</option>
								<option value="6" <c:if test="${model.industryId == 6 }">selected</c:if>>Car Industry/Motor and Transport Industry</option>
								<option value="7" <c:if test="${model.industryId == 7 }">selected</c:if>>Instrument/Photography and Optical industry</option>
								<option value="8" <c:if test="${model.industryId == 8 }">selected</c:if>>Electronics and telecom manufacturing industry/IT and computer industry</option>
								<option value="9" <c:if test="${model.industryId == 9 }">selected</c:if>>All other manufacturing industries</option>
								<option value="10" <c:if test="${model.industryId == 10 }">selected</c:if>>Electricity-, gas- heat- and water works</option>
								<option value="11" <c:if test="${model.industryId == 11 }">selected</c:if>>Building industry/Construction</option>
								<option value="12" <c:if test="${model.industryId == 12 }">selected</c:if>>Wholesale trade</option>
								<option value="13" <c:if test="${model.industryId == 13 }">selected</c:if>>Retail trade/Shop work</option>
								<option value="14" <c:if test="${model.industryId == 14 }">selected</c:if>>Auto</option>
								<option value="15" <c:if test="${model.industryId == 15 }">selected</c:if>>Hotel and restaurant</option>
								<option value="16" <c:if test="${model.industryId == 16 }">selected</c:if>>Transport, warehouse and communication</option>
								<option value="17" <c:if test="${model.industryId == 17 }">selected</c:if>>Bank, credit agency, insurance company</option>
								<option value="18" <c:if test="${model.industryId == 18 }">selected</c:if>>Real estate</option>
								<option value="19" <c:if test="${model.industryId == 19 }">selected</c:if>>Rental business</option>
								<option value="20" <c:if test="${model.industryId == 20 }">selected</c:if>>Consulting business in computer/IT/Telecommunications industry</option>
								<option value="21" <c:if test="${model.industryId == 21 }">selected</c:if>>All other contract businesses/consulting firms</option>
								<option value="22" <c:if test="${model.industryId == 22 }">selected</c:if>>Media/Newspaper/Publishing/Radio/TV</option>
								<option value="23" <c:if test="${model.industryId == 23 }">selected</c:if>>Education/School</option>
								<option value="24" <c:if test="${model.industryId == 24 }">selected</c:if>>Health and medical service</option>
								<option value="25" <c:if test="${model.industryId == 25 }">selected</c:if>>Cleaning/Sanitation</option>
								<option value="26" <c:if test="${model.industryId == 26 }">selected</c:if>>Recreation/Culture/Sport</option>
								<option value="27" <c:if test="${model.industryId == 27 }">selected</c:if>>All other public services</option>
								<option value="28" <c:if test="${model.industryId == 28 }">selected</c:if>>US Military or Coast Guard</option>
							</select>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary btn-edit" data-dismiss="modal">确定</button>
			</div>
		</div>

	</div>

</div>