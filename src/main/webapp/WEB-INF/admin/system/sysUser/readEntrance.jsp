<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>
<!-- 查看Modal -->
<script type="text/javascript">
</script>

<div class="modal fade" tabindex="-1" id="modal-read">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h4 class="modal-title" id="myModalLabel">查看用户</h4>
			</div>
			<div id="spin" align="center"></div>
			<div class="modal-body">
				
				<form class="form-horizontal">
					<div class="form-group">
						<label for="loginName" class="col-sm-3 control-label">系统登录名</label>
						<div class="col-sm-9">
							<p class="form-control loginName"><c:out value="${model.loginName }"></c:out></p>
						</div>
					</div>
					<div class="form-group">
						<label for="name" class="col-sm-3 control-label">姓名</label>
						<div class="col-sm-9">
							<p class="form-control name"><c:out value="${model.firstName }"></c:out>&nbsp;<c:out value="${model.lastName }"></c:out></p>
						</div>
					</div>
					<div class="form-group">
						<label for="sex" class="col-sm-3 control-label">性别</label>
						<div class="col-sm-9">
							<p class="form-control sex">
								<c:choose>
								<c:when test="${model.sex == 0 }">男</c:when>
								<c:otherwise>女</c:otherwise>
								</c:choose>
							</p>
						</div>
					</div>
					<div class="form-group">
						<label for="birthday" class="col-sm-3 control-label">生日</label>
						<div class="col-sm-9">
							<p class="form-control birthday">
								<fmt:formatDate value="${model.birthday }" pattern="yy-MM-dd HH:mm:ss" />
							</p>
						</div>
					</div>
					<div class="form-group">
						<label for="phone" class="col-sm-3 control-label">电话</label>
						<div class="col-sm-9">
							<p class="form-control phone"><c:out value="${model.phone }"></c:out></p>
						</div>
					</div>
					<div class="form-group">
						<label for="email" class="col-sm-3 control-label">邮箱</label>
						<div class="col-sm-9">
							<p class="form-control email"><c:out value="${model.email }"></c:out></p>
						</div>
					</div>
					<div class="form-group">
						<label for="website" class="col-sm-3 control-label">网站</label>
						<div class="col-sm-9">
							<p class="form-control website"><c:out value="${model.website }"></c:out></p>
						</div>
					</div>
					<div class="form-group">
						<label for="lastLoginTime" class="col-sm-3 control-label">最后登陆时间</label>
						<div class="col-sm-9">
							<p class="form-control lastLoginTime">
								<fmt:formatDate value="${model.lastLoginTime }" pattern="yy-MM-dd HH:mm:ss" />
							</p>
						</div>
					</div>
					<div class="form-group">
						<label for="lastLoginIp" class="col-sm-3 control-label">最后登陆IP</label>
						<div class="col-sm-9">
							<p class="form-control lastLoginIp"><c:out value="${model.lastLoginIp }"></c:out></p>
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