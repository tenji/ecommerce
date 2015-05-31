<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>
<!-- 查看Modal -->
<script type="text/javascript">
$(function () {
	var pointList = []; // 最近积分集合，用于分析最近的积分变化趋势
	var nWeeks = 4; // 分析最近N周的数据
	// 发送AJAX请求
	var url = "${path}/admin/log/logAdminRamAccount/getPointList";
  	$.ajax({url: url, data: {"ramAccountId": crud.operatedObj.val()}, dataType: 'json', async: false, type: 'POST',
  		success: function(list) {
  			pointList = list;
  		},
  		error: function() {
  			createNoty("error", "操作失败，请重试！"); // 操作失败提醒框
  			$("#modal-add").modal('hide');
  		}
  	});
	$('#container').highcharts({
	    chart: {
	        type: 'line'
	    },
	    title: {
	        text: 'Ram Account Analysis'
	    },
	    subtitle: {
	        text: 'Points Changing Trend Monthly'
	    },
	    xAxis: {
	        type: 'datetime',
            dateTimeLabelFormats: {
                day: '%b %e'
            }
	    },
	    yAxis: {
	        title: {
	            text: 'Points'
	        }
	    },
	    plotOptions: {
	        line: {
	            dataLabels: {
	                enabled: true
	            },
	            enableMouseTracking: true
	        }
	    },
	    series: [{
	    	name: "Account", 
	        data: pointList,
            pointStart: Date.UTC((new Date()).getYear(), (new Date()).getMonth(), (new Date()).getDate() - (nWeeks * 7 - 1)),
            pointInterval: 24 * 3600 * 1000 // one day
	    }]
	});
});
</script>

<div class="modal fade" id="modal-read">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h4 class="modal-title" id="myModalLabel">Ram Account Detail</h4>
			</div>
			<div class="modal-body">
				
				<div class="tabbable">
					<ul class="nav nav-tabs padding-12 tab-color-blue background-blue">
						<li class="active"><a href="#panel-basic" data-toggle="tab">Ram Account Basis</a></li>
						<li><a href="#panel-ram" data-toggle="tab">Ram Site Detail</a></li>
						<li><a href="#panel-analysis" data-toggle="tab">Ram Account Analysis</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="panel-basic">
							<form class="form-horizontal">
								<div class="form-group">
									<label for="email" class="col-sm-3 control-label">Email</label>
									<div class="col-sm-9">
										<p class="form-control email"><c:out value="${model.email }"></c:out></p>
									</div>
								</div>
								<div class="form-group">
									<label for="loginName" class="col-sm-3 control-label">User Name</label>
									<div class="col-sm-9">
										<p class="form-control loginName"><c:out value="${model.loginName }"></c:out></p>
									</div>
								</div>
								<div class="form-group">
									<label for="loginPassword" class="col-sm-3 control-label">Password</label>
									<div class="col-sm-9">
										<p class="form-control loginPassword"><c:out value="${model.loginPassword }"></c:out></p>
									</div>
								</div>
								<div class="form-group">
									<label for="loginUrl" class="col-sm-3 control-label">Login Url</label>
									<div class="col-sm-9">
										<textarea rows="4" class="form-control loginUrl"><c:out value="${model.loginUrl }"></c:out></textarea>
									</div>
								</div>
								<div class="form-group">
									<label for="pointsBeforeOpr" class="col-sm-3 control-label">Points Before Operation</label>
									<div class="col-sm-9">
										<p class="form-control pointsBeforeOpr"><c:out value="${model.pointsBeforeOpr }"></c:out></p>
									</div>
								</div>
								<div class="form-group">
									<label for="pointsAfterOpr" class="col-sm-3 control-label">Points After Operation</label>
									<div class="col-sm-9">
										<p class="form-control pointsAfterOpr"><c:out value="${model.pointsAfterOpr }"></c:out></p>
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
								<div class="form-group">
									<label for="ramId" class="col-sm-3 control-label">Ram Site</label>
									<div class="col-sm-9">
										<p class="form-control ramId"><c:out value="${model.ram.name }"></c:out></p>
									</div>
								</div>
							</form>
						</div>
						<div class="tab-pane" id="panel-ram">
							<form class="form-horizontal">
								<div class="form-group">
									<label for="name" class="col-sm-3 control-label">Name</label>
									<div class="col-sm-9">
										<p class="form-control name"><c:out value="${model.ram.name }"></c:out></p>
									</div>
								</div>
								<div class="form-group">
									<label for="star" class="col-sm-3 control-label">Rewards Rate</label>
									<div class="col-sm-9">
										<p class="form-control star">
											<c:forEach begin="1" end="${model.ram.rewardsRate }">
												<i class="icon-star bigger-110"></i>
											</c:forEach>
										</p>
									</div>
								</div>
								<div class="form-group">
									<label for="star" class="col-sm-3 control-label">Surveys Rate</label>
									<div class="col-sm-9">
										<p class="form-control star">
											<c:forEach begin="1" end="${model.ram.surveysRate }">
												<i class="icon-star bigger-110"></i>
											</c:forEach>
										</p>
									</div>
								</div>
								<div class="form-group">
									<label for="rewardDetail" class="col-sm-3 control-label">Reward Detail</label>
									<div class="col-sm-9">
										<textarea rows="4" class="form-control rewardDetail"><c:out value="${model.ram.rewardDetail }"></c:out></textarea>
									</div>
								</div>
								<div class="form-group">
									<label for="remark" class="col-sm-3 control-label">Remark</label>
									<div class="col-sm-9">
										<textarea rows="3" class="form-control remark"><c:out value="${model.ram.remark }"></c:out></textarea>
									</div>
								</div>
							</form>
						</div>
						<div class="tab-pane" id="panel-analysis">
							<div id="container" style="width:1200px; height:500px;"></div>
						</div>
					</div>
				</div>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
			</div>
		</div>

	</div>

</div>