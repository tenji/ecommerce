<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>
<!-- 查看Modal -->
<script type="text/javascript">
$(function () { 
    $('#container').highcharts({
        chart: {
            type: 'bar'
        },
        title: {
            text: 'Fruit Consumption'
        },
        xAxis: {
            categories: ['Apples', 'Bananas', 'Oranges']
        },
        yAxis: {
            title: {
                text: 'Fruit eaten'
            }
        },
        series: [{
            name: 'Jane',
            data: [1, 0, 4]
        }, {
            name: 'John',
            data: [5, 7, 3]
        }]
    });
});
</script>

<div class="modal fade" id="modal-read">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h4 class="modal-title" id="myModalLabel">后台登陆日志详情</h4>
			</div>
			<div class="modal-body">
				
				<div class="tabbable">
					<ul class="nav nav-tabs padding-12 tab-color-blue background-blue">
						<li class="active"><a href="#panel-basic" data-toggle="tab">登陆日志</a></li>
						<li><a href="#panel-analysis" data-toggle="tab">日志图形分析</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="panel-basic">
							<form class="form-horizontal">
								<div class="form-group">
									<label for="loginName" class="col-sm-3 control-label">用户名</label>
									<div class="col-sm-9">
										<p class="form-control loginName"><s:property value="model.sysUser.loginName" /></p>
									</div>
								</div>
								<div class="form-group">
									<label for="loginTime" class="col-sm-3 control-label">登陆时间</label>
									<div class="col-sm-9">
										<p class="form-control loginTime"><s:date name="model.loginTime" format="yyyy-MM-dd HH:mm:ss" /></p>
									</div>
								</div>
								<div class="form-group">
									<label for="logoutTime" class="col-sm-3 control-label">退出时间</label>
									<div class="col-sm-9">
										<p class="form-control logoutTime"><s:date name="model.logoutTime" format="yyyy-MM-dd HH:mm:ss" /></p>
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