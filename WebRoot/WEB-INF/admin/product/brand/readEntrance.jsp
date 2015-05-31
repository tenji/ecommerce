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
				<h4 class="modal-title" id="myModalLabel">品牌详情</h4>
			</div>
			<div class="modal-body">

				<form class="form-horizontal">
					<div class="form-group">
						<label for="chineseName" class="col-sm-3 control-label">品牌中文名</label>
						<div class="col-sm-9">
							<p class="form-control chineseName"><s:property value="model.chineseName" /></p>
						</div>
					</div>
					<div class="form-group">
						<label for="englishName" class="col-sm-3 control-label">品牌英文名</label>
						<div class="col-sm-9">
							<p class="form-control englishName"><s:property value="model.englishName" /></p>
						</div>
					</div>
					<div class="form-group">
						<label for="description" class="col-sm-3 control-label">品牌描述</label>
						<div class="col-sm-9">
							<p class="form-control description"><s:property value="model.description" /></p>
						</div>
					</div>
					<div class="form-group">
						<label for="officialAddress" class="col-sm-3 control-label">品牌官方地址</label>
						<div class="col-sm-9">
							<p class="form-control officialAddress"><s:property value="model.officialAddress" /></p>
						</div>
					</div>
					<div class="form-group">
						<label for="story" class="col-sm-3 control-label">品牌故事</label>
						<div class="col-sm-9">
							<textarea rows="" cols="" class="form-control story" name="story"><s:property value="model.story" /></textarea>
						</div>
					</div>
					<div class="form-group">
						<label for="createTime" class="col-sm-3 control-label">创建时间</label>
						<div class="col-sm-9">
							<p class="form-control createTime"><s:date name="model.createTime" format="yyyy-MM-dd HH:mm:ss" /></p>
						</div>
					</div>
					<div class="form-group">
						<label for="modifyTime" class="col-sm-3 control-label">修改时间</label>
						<div class="col-sm-9">
							<p class="form-control modifyTime"><s:date name="model.modifyTime" format="yyyy-MM-dd HH:mm:ss" /></p>
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