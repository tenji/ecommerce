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
				<h4 class="modal-title">角色详情</h4>
			</div>
			<div class="modal-body">
			
				<div class="tabbable">
					<ul class="nav nav-tabs padding-12 tab-color-blue background-blue">
						<li class="active"><a href="#panel-role" data-toggle="tab">基本信息</a></li>
						<li><a href="#panel-menus" data-toggle="tab">菜单权限</a></li>
						<li><a href="#panel-operations" data-toggle="tab">操作权限</a></li>
						<li><a href="#panel-users" data-toggle="tab">关联用户</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="panel-role">
							<form class="form-horizontal">
								<div class="form-group">
									<label for="roleName" class="col-sm-3 control-label">权限名称</label>
									<div class="col-sm-9">
										<p class="form-control roleName"><s:property value="model.roleName"/></p>
									</div>
								</div>
								<div class="form-group">
									<label for="description" class="col-sm-3 control-label">权限描述</label>
									<div class="col-sm-9">
										<p class="form-control description"><s:property value="model.description"/></p>
									</div>
								</div>
								<div class="form-group">
									<label for="roleLevel" class="col-sm-3 control-label">权限等级</label>
									<div class="col-sm-9">
										<p class="form-control roleLevel"><s:property value="model.roleLevel"/></p>
									</div>
								</div>
								<div class="form-group">
									<label for="createTime" class="col-sm-3 control-label">创建时间</label>
									<div class="col-sm-9">
										<p class="form-control createTime"><s:date name="model.createTime" format="yyyy-MM-dd HH:ss:mm"/></p>
									</div>
								</div>
								<div class="form-group">
									<label for="modifyTime" class="col-sm-3 control-label">修改时间</label>
									<div class="col-sm-9">
										<p class="form-control modifyTime"><s:date name="model.modifyTime" format="yyyy-MM-dd HH:ss:mm"/></p>
									</div>
								</div>
							</form><!-- sysRole form -->
						</div>
						
						<div class="tab-pane" id="panel-menus">
							<table class="table table-striped table-bordered table-hover no-margin-bottom no-border-top menus-table">
								<thead>
									<tr>
										<th>菜单名称</th>
										<th>菜单描述</th>
										<th>菜单URL</th>
									</tr>
								</thead>

								<tbody>
									<s:iterator value="sysMenuList" var="sysMenu">
										<tr>
											<td><s:property value="menuName" /></td>
											<td><s:property value="menuDescription" /></td>
											<td><s:property value="url" /></td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
							
						</div>
						
						<div class="tab-pane" id="panel-operations">
						<table class="table table-striped table-bordered table-hover no-margin-bottom no-border-top operations-table">
								<thead>
									<tr>
										<th>操作名称</th>
										<th>操作URL</th>
									</tr>
								</thead>

								<tbody>
									<s:iterator value="sysOperationList" var="sysOperatioin">
										<tr>
											<td><s:property value="operationName" /></td>
											<td><s:property value="url" /></td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</div>
						
						<div class="tab-pane" id="panel-users">

							<table class="table table-striped table-bordered table-hover no-margin-bottom no-border-top users-table">
								<thead>
									<tr>
										<th>用户名</th>
										<th>最后登陆时间</th>
										<th>最后登陆IP</th>
									</tr>
								</thead>

								<tbody>
									<s:iterator value="sysUserList" var="sysUser">
										<tr>
											<td><s:property value="loginName" /></td>
											<td><s:date name="lastLoginTime" format="yyyy-MM-dd HH:ss:mm" /></td>
											<td><s:property value="lastLoginIp" /></td>
										</tr>
									</s:iterator>
								</tbody>
							</table>

						</div>
						
					</div>
				</div>

			</div><!-- modal body end -->
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
			</div>
		</div>

	</div>

</div>