<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>
<!-- 添加Modal -->

<script type="text/javascript">
// 点击添加按钮
function addItem() {
	$("#modal-add").modal({
		backdrop : 'static'
	});
	EC.deleteSpin();
}
	
$(function() {
	// 点击确认按钮
  	$("#modal-add").find(".btn-add").on('click', function() {
  		
  		var url = "${path}" + "/admin/affiliate/ram/add";
  		var data = $("#modal-add .form-horizontal").serialize();
  		// 发送AJAX请求
	  	$.ajax({url: url, data: data, dataType: 'json', async: false, type: 'POST',
	  		success: function(list) {
	  			$("#modal-add").modal('hide');
	  			if (!handleAjaxResults(list)) {
					return ;
				}
				EC.createNoty();
	  			
	  			var $table = $(".table");
	  			// 表格最后一行插入tr
	  			var newTr = $('<tr class="active success">'
	  				+ '<td><label><input class="ace" type="checkbox" value="" /><span class="lbl"></span></label></td>'
	  				+ '<td>' + ($table.find("tbody tr").length + 1) + '</td>'
	  				+ '<td>' + list.name + '</td>'
	  				+ '<td>' + list.rewardsRate + '</td>'
	  				+ '<td>' + list.surveysRate + '</td>'
	  				+ '<td>' + list.country + '</td>'
	  				+ '<td>' + list.state + '</td>'
	  				+ '<td>' + list.city + '</td>'
	  				+ '<td>' + list.createTime + '</td>'
	  				+ '<td>'
	  					+ '<button type="button" class="btn btn-success btn-xs btn-read" value="' + list.Id + '">Detail</button>'
	  					+ ' <button type="button" class="btn btn-success btn-xs btn-delete" value="' + list.Id + '">Check</button>'
	  					+ ' <button type="button" class="btn btn-success btn-xs btn-delete" value="' + list.Id + '">Register</button>'
	  				+ '</td>'
	  				+ '</tr>').hide().slideDown(1000);
	  			$('.table tbody').append(newTr);
	  			
	  			EC.clearFormData($("#modal-add .form-horizontal")); // 清空表单内容
	  		},
	  		error: function() {
	  			EC.createNoty("error", "操作失败，请重试！"); // 操作失败提醒框
	  			$("#modal-add").modal('hide');
	  		}
	  	});
  	});
});
</script>

<div class="modal fade" id="modal-add" >
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h4 class="modal-title" id="myModalLabel">Add a new ram site</h4>
			</div>
			
			<div class="modal-body">

				<form class="form-horizontal">
					<div class="form-group">
						<label for="name" class="col-sm-4 control-label">Name</label>
						<div class="col-sm-8">
							<input type="text" class="form-control name" name="name" placeholder="Name" >
						</div>
					</div>
					<div class="form-group">
						<label for="officialWebsiteUrl" class="col-sm-4 control-label">Official Website Url</label>
						<div class="col-sm-8">
							<input type="text" class="form-control officialWebsiteUrl" name="officialWebsiteUrl" placeholder="Official Website Url" >
						</div>
					</div>
					<div class="form-group">
						<label for="ramRegisterUrl" class="col-sm-4 control-label">Ram Register Url</label>
						<div class="col-sm-8">
							<input type="text" class="form-control ramRegisterUrl" name="ramRegisterUrl" placeholder="Ram Register Url">
						</div>
					</div>
					<div class="form-group">
						<label for="country" class="col-sm-4 control-label">Rewards Rate</label>
						<div class="col-sm-8">
							<select class="form-control rewardsRate" name="rewardsRate">
								<option value="0" selected>0</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="country" class="col-sm-4 control-label">Surveys Rate</label>
						<div class="col-sm-8">
							<select class="form-control surveysRate" name="surveysRate">
								<option value="0" selected>0</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="country" class="col-sm-4 control-label">Country</label>
						<div class="col-sm-8">
							<input type="text" class="form-control country" name="country" placeholder="Country">
						</div>
					</div>
					<div class="form-group">
						<label for="country" class="col-sm-4 control-label">State</label>
						<div class="col-sm-8">
							<input type="text" class="form-control state" name="state" placeholder="Country">
						</div>
					</div>
					<div class="form-group">
						<label for="country" class="col-sm-4 control-label">City</label>
						<div class="col-sm-8">
							<input type="text" class="form-control city" name="city" placeholder="Country">
						</div>
					</div>
					<div class="form-group">
						<label for="rewardDetail" class="col-sm-4 control-label">Reward Detail</label>
						<div class="col-sm-8">
							<textarea rows="3" cols="" class="form-control rewardDetail" name="rewardDetail" placeholder="Reward Detail"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label for="remark" class="col-sm-4 control-label">Remark</label>
						<div class="col-sm-8">
							<input type="text" class="form-control remark" name="remark" placeholder="Remark">
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