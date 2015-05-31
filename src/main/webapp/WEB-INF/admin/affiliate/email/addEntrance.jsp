<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>
<!-- 添加Modal -->

<script type="text/javascript">
$(document).ready(function() {
	// 选择时间
	$(".date-picker").datepicker().next().on(ace.click_event, function(){
		$(this).prev().focus();
	});
	
	// 点击确认按钮
  	$("#modal-add").find(".btn-add").on('click', function() {
  		// 验证表单是否合法
  		if (!$("#modal-add .form-horizontal").valid()) {
			return ;
		}
		
  		var url = "${path}" + "/admin/affiliate/email/add";
  		var data = $("#modal-add .form-horizontal").serialize();
  		// 发送AJAX请求
	  	$.ajax({url: url, data: data, dataType: 'json', async: false, type: 'POST',
	  		success: function(list) {
	  			$("#modal-add").modal('hide');
	  			if (!handleAjaxResults(list)) {
					return ;
				} 
	  			EC.createNoty();
	  			
	  			// 表格最后一行插入tr
	  			$table = $(".table");
	  			var newTr = $('<tr class="active success">'
	  				+ '<td><label><input class="ace" type="checkbox" value="" /><span class="lbl"></span></label></td>'
	  				+ '<td>' + list.email + '</td>'
	  				+ '<td>' + list.state.name + '</td>'
	  				+ '<td>' + list.city.name + '</td>'
	  				+ '<td>' + list.averageYearIncome + '</td>'
	  				+ '<td>' + list.firstName + '</td>'
	  				+ '<td>' + list.lastName + '</td>'
	  				+ '<td>' + list.createTime + '</td>'
	  				+ '<td>'
	  					+ '<button type="button" class="btn btn-success btn-xs" value="' + list.Id + '" onclick="readInit(this.value)">Detail</button>'
	  					+ ' <button type="button" class="btn btn-success btn-xs" value="' + list.Id + '" onclick="updateItemId(this.value);updateTrObj(this);deleteInit()">Delete</button>'
	  					+ ' <button type="button" class="btn btn-success btn-xs" value="' + list.Id + '" onclick="checkInit(this.value)">Edit</button>'
	  				+ '</td>'
	  				+ '</tr>').hide().slideDown(1000);
	  			$($table.find("tbody")).append(newTr);
	  			
	  			EC.clearFormData($("#modal-add .form-horizontal")); // 清空表单内容
	  		},
	  		error: function() {
	  			EC.createNoty("error", "操作失败，请重试！"); // 操作失败提醒框
	  			$("#modal-add").modal('hide');
	  		}
	  	});
  	});
  	
  	// Select State
  	$("#modal-add .state").change(function() {
  		var stateId = $(this).find("option:selected").val();
  		var select = $("#modal-add .city");
	  	select.empty().append($("<option value=''>Please Select Specific City</option>"));
  		if ("" == stateId) { return ; } // if state is not selected, return.
  		
  		var url = "${path}" + "/admin/affiliate/email/getCities";	
  		// 发送AJAX请求
	  	$.ajax({url: url, data: {"state.id": stateId}, dataType: 'json', async: false, type: 'POST',
	  		success: function(list) {
	  			for ( var i = 0; i < list.length; i++) {
					$("<option value='" + list[i].id + "'>" + list[i].name + "</option>").appendTo(select);
				}
	  		},
	  		error: function() {
	  			createNoty("error", "操作失败，请重试！"); // 操作失败提醒框
	  			$("#modal-add").modal('hide');
	  		}
	  	});
  	});
  	
  	// jquery validate验证
  	$("#modal-add .form-horizontal").validate({
  		// 焦点离开
  		onfocusout: function(event) {
  			$(event).valid();
  		}, 
		rules: {
			"email": "required", 
			"city.id": "required",
			"seniorityLevelId": "required",
			"jobRoleId": "required",
			"industryId": "required"
		},
		messages: {
			"email": "Email is required.", 
			"city.id": "City is required.",
			"seniorityLevelId": "Seniority Level Id is required.",
			"jobRoleId": "Job Role Id is required.",
			"industryId": "Industry Id is required."
		}
	});
});
</script>

<div class="modal fade" id="modal-add" >
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h4 class="modal-title" id="myModalLabel">Add a new email account</h4>
			</div>
			
			<div class="modal-body">

				<form class="form-horizontal">
					<div class="form-group">
						<label for="email" class="col-sm-4 control-label">Email</label>
						<div class="col-sm-8">
							<input type="text" class="form-control email" name="email" placeholder="Email" >
						</div>
					</div>
					<div class="form-group">
						<label for="state" class="col-sm-4 control-label">State</label>
						<div class="col-sm-8">
							<select class="form-control state" name="state.id">
								<option value="" selected>Please Select Specific State</option>
								<c:forEach items="${stateList }" var="state">
									<option value="${state.id }">${state.name }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="city" class="col-sm-4 control-label">City</label>
						<div class="col-sm-8">
							<select class="form-control city" name="city.id">
								<option value="" selected>Please Select Specific City</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="averageYearIncome" class="col-sm-4 control-label">Average Year Income</label>
						<div class="col-sm-8">
							<input type="text" class="form-control averageYearIncome" name="averageYearIncome" placeholder="Average Year Income">
						</div>
					</div>
					<div class="form-group">
						<label for="address" class="col-sm-4 control-label">Address</label>
						<div class="col-sm-8">
							<input type="text" class="form-control address" name="address" placeholder="Address">
						</div>
					</div>
					<div class="form-group">
						<label for="postalCode" class="col-sm-4 control-label">Postal Code</label>
						<div class="col-sm-8">
							<input type="text" class="form-control postalCode" name="postalCode" placeholder="Postal Code">
						</div>
					</div>
					<div class="form-group">
						<label for="birthday" class="col-sm-4 control-label">Birthday</label>
						<div class="col-sm-8">
							<input type="text" placeholder="yyyy-MM-dd" name="birthday"
								data-date-format="yyyy-mm-dd" id="birthday" class="form-control date-picker">
						</div>
					</div>
					<div class="form-group">
						<label for="firstName" class="col-sm-4 control-label">First Name</label>
						<div class="col-sm-8">
							<input type="text" class="form-control firstName" name="firstName" placeholder="First Name">
						</div>
					</div>
					<div class="form-group">
						<label for="lastName" class="col-sm-4 control-label">Last Name</label>
						<div class="col-sm-8">
							<input type="text" class="form-control lastName" name="lastName" placeholder="Last Name">
						</div>
					</div>
					<div class="form-group">
						<label for="company" class="col-sm-4 control-label">Company</label>
						<div class="col-sm-8">
							<input type="text" class="form-control company" name="company" placeholder="Working Company">
						</div>
					</div>
					<div class="form-group">
						<label for="seniorityLevelId" class="col-sm-4 control-label">Seniority Level</label>
						<div class="col-sm-8">
							<select class="form-control" name="seniorityLevelId">
								<option value="" selected>Please Select Seniority Level</option>
								<option value="0">Chief Executive Officer or Corporate Executive Officer or equivalent</option>
								<option value="1">Managing Director, Regional Director, District Director, or equivalent</option>
								<option value="2">Department/division/section Director or equivalent</option>
								<option value="3">Civil Servant with personnel or economic responsibility or equivalent</option>
								<option value="4">Civil Servant, Handling officer without personnel or economic responsibility, or equivalent</option>
								<option value="5">Other position/Unemployed</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="jobRoleId" class="col-sm-4 control-label">Job Role</label>
						<div class="col-sm-8">
							<select class="form-control" name="jobRoleId">
								<option value="" selected>Please Select Job Role</option>
								<option value="0">Management</option>
								<option value="1">Clerical/Administration</option>
								<option value="2">Purchasing</option>
								<option value="3">Technology/Research and Development/Engineering</option>
								<option value="4">Personnel/HR</option>
								<option value="5">Marketing</option>
								<option value="6">Manufacturing/Factory/Maintenance</option>
								<option value="7">Computers/IT</option>
								<option value="8">Service/Transport</option>
								<option value="9">Education/Schooling</option>
								<option value="10">Health/Nutrition</option>
								<option value="11">Other</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="industryId" class="col-sm-4 control-label">Industry</label>
						<div class="col-sm-8">
							<select class="form-control" name="industryId">
								<option value="" selected>Please Select Job Role</option>
								<option value="0">Farming/Hunting/Forestry/Fisheries</option>
								<option value="1">Food and drinks industry</option>
								<option value="2">Timber/Pulp and Paper industry</option>
								<option value="3">Printing industry</option>
								<option value="4">Rubber and plastics industry</option>
								<option value="5">Metal/Machinery industry</option>
								<option value="6">Car Industry/Motor and Transport Industry</option>
								<option value="7">Instrument/Photography and Optical industry</option>
								<option value="8">Electronics and telecom manufacturing industry/IT and computer industry</option>
								<option value="9">All other manufacturing industries</option>
								<option value="10">Electricity-, gas- heat- and water works</option>
								<option value="11">Building industry/Construction</option>
								<option value="12">Wholesale trade</option>
								<option value="13">Retail trade/Shop work</option>
								<option value="14">Auto</option>
								<option value="15">Hotel and restaurant</option>
								<option value="16">Transport, warehouse and communication</option>
								<option value="17">Bank, credit agency, insurance company</option>
								<option value="18">Real estate</option>
								<option value="19">Rental business</option>
								<option value="20">Consulting business in computer/IT/Telecommunications industry</option>
								<option value="21">All other contract businesses/consulting firms</option>
								<option value="22">Media/Newspaper/Publishing/Radio/TV</option>
								<option value="23">Education/School</option>
								<option value="24">Health and medical service</option>
								<option value="25">Cleaning/Sanitation</option>
								<option value="26">Recreation/Culture/Sport</option>
								<option value="27">All other public services</option>
								<option value="28">US Military or Coast Guard</option>
							</select>
						</div>
					</div>
				</form>

			</div>
			<div class="modal-footer">
			<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary btn-add">确定</button>
			</div>
		</div>

	</div>

</div>