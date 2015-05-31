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
				<h4 class="modal-title" id="myModalLabel">查看产品类目</h4>
			</div>
			<div id="spin" align="center"></div>
			<div class="modal-body">
				<div class="tabbable">
					<ul class="nav nav-tabs padding-12 tab-color-blue background-blue">
						<li class="active"><a href="#panel-category" data-toggle="tab">类目详情</a></li>
						<li><a href="#panel-brandList" data-toggle="tab">包含品牌列表</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="panel-category">
							<form class="form-horizontal">
								<div class="form-group">
									<label for="id" class="col-sm-3 control-label">类目编号</label>
									<div class="col-sm-9">
										<p class="form-control id">
											<s:property value="model.id" />
										</p>
									</div>
								</div>
								<div class="form-group">
									<label for="categoryName" class="col-sm-3 control-label">类目名称</label>
									<div class="col-sm-9">
										<p class="form-control categoryName">
											<s:property value="model.categoryName" />
										</p>
									</div>
								</div>
								<div class="form-group">
									<label for="createTime" class="col-sm-3 control-label">创建时间</label>
									<div class="col-sm-9">
										<p class="form-control createTime">
											<s:date name="model.createTime" format="yyyy-MM-dd HH:mm:ss" />
										</p>
									</div>
								</div>
								<div class="form-group">
									<label for="modifyTime" class="col-sm-3 control-label">修改时间</label>
									<div class="col-sm-9">
										<p class="form-control modifyTime">
											<s:date name="model.modifyTime" format="yyyy-MM-dd HH:mm:ss" />
										</p>
									</div>
								</div>
							</form><!-- Category form -->
						</div>
						
						<div class="tab-pane" id="panel-brandList">
							<table class="table table-striped table-bordered table-hover no-margin-bottom no-border-top menus-table">
								<thead>
									<tr>
										<th>品牌中文名</th>
										<th>品牌英文名</th>
										<th>创建时间</th>
									</tr>
								</thead>

								<tbody>
									<s:iterator value="brandList">
									<tr class="active">
										<td><s:property value="chineseName" /></td>
										<td><s:property value="englishName" /></td>
										<td><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss" /></td>
									</tr>
									</s:iterator>
								</tbody>
							</table>
							
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