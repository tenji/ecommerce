<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>
<!-- 查看Modal -->
<script type="text/javascript">
$(function () {
	var pointList = []; // 最近积分集合，用于分析最近的积分变化趋势
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
            pointStart: Date.UTC((new Date()).getYear(), (new Date()).getMonth(), (new Date()).getDate() - 13),
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
				<h4 class="modal-title" id="myModalLabel">I-SAY Account Detail</h4>
			</div>
			<div class="modal-body">
				
				<div class="tabbable">
					<ul class="nav nav-tabs padding-12 tab-color-blue background-blue">
						<li class="active"><a href="#panel-basic" data-toggle="tab">I-SAY Account Basis</a></li>
						<li><a href="#panel-ram" data-toggle="tab">Email Account Detail</a></li>
						<li><a href="#panel-analysis" data-toggle="tab">I-SAY Account Analysis</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="panel-basic">
							<form class="form-horizontal">
								<div class="form-group">
									<label for="email" class="col-sm-3 control-label">Email</label>
									<div class="col-sm-9">
										<p class="form-control email"><c:out value="${model.email.email }"></c:out></p>
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
									<label for="pointsAfterOpr" class="col-sm-3 control-label">Points</label>
									<div class="col-sm-9">
										<p class="form-control pointsAfterOpr"><c:out value="${model.points }"></c:out></p>
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
						<div class="tab-pane" id="panel-ram">
							<form class="form-horizontal">
								<div class="form-group">
									<label for="email" class="col-sm-3 control-label">Email</label>
									<div class="col-sm-9">
										<p class="form-control email"><c:out value="${model.email.email }"></c:out></p>
									</div>
								</div>
							</form>
						</div>
						<div class="tab-pane" id="panel-analysis">
							<div id="container" style="width:500px; height:300px;"></div>
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