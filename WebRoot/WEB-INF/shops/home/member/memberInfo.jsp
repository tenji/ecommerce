<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<?xml version="1.0" encoding="utf-8" ?>
<html>
  <head>
    <title>${projectName } - 个人中心</title>
    <!-- 引入页面meta元信息以及公用的CSS和JS -->
	<%@ include file="/common/meta.jsp" %>

<script type="text/javascript">
$(document).ready(function() {
	$(".submit-memberInfo").on('click', function() {
		createNoty("success", "更新成功");
	});
});
</script>
  </head>
 
<body>
	<div class="container">

		<!-- 网站头部 -->
		<div class="row header">
		</div>
		<!-- 网站后台主体 -->
		<div class="row home-main">
			<!-- 左侧边栏 -->
			<%@ include file="/WEB-INF/shops/common/home-menu-left.jsp"%>
			<!-- 主体内容 -->
			<div class="col-md-9 home-content">
				<h4 class="margin-base-vertical text-center">Hot Products</h4>
				<div class="tabbable" id="tabs-806601">
					<ul class="nav nav-pills">
						<li class="active"><a href="${path }/shops/home/member/memberInfo.htm">账户信息</a></li>
						<li><a href="${path }/shops/home/member/moreInfo.htm">更多个人信息</a></li>
						<li><a href="${path }/shops/home/member/memberRank.htm">我的级别</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active">
						<br/>
						<form class="form-horizontal" role="form">
						  <div class="form-group">
						    <label for="memberName" class="col-sm-2 control-label">用户名：</label>
						    <div class="col-sm-10">
						    	<p class="form-control memberName"><s:property value="member.memberName" /></p>
						    </div>
						  </div>
						  <div class="form-group">
						    <label for="nickName" class="col-sm-2 control-label">昵称：</label>
						    <div class="col-sm-10">
						      <input type="text" class="form-control" id="nickName" placeholder="请输入昵称">
						    </div>
						  </div>
						  <div class="form-group">
						  	<label for="sex" class="col-sm-2 control-label">性别：</label>
						  	<div class="col-sm-10">
						  	<label class="radio-inline">
							  <input type="radio" name="sex" id="inlineCheckbox1" value="option1"> 保密
							</label>
							<label class="radio-inline">
							  <input type="radio" name="sex" id="inlineCheckbox2" value="option2"> 男
							</label>
							<label class="radio-inline">
							  <input type="radio" name="sex" id="inlineCheckbox3" value="option3"> 女
							</label>
							</div>
						  </div>
						  <div class="form-group">
						    <label for="cellPhone" class="col-sm-2 control-label">手机号码：</label>
						    <div class="col-sm-10">
						    	<p class="form-control"><s:property value="member.cellPhone" /></p>
						    </div>
						  </div>
						  <div class="form-group">
						    <label for="email" class="col-sm-2 control-label">邮箱：</label>
						    <div class="col-sm-10">
						    	<p class="form-control"><s:property value="member.email" /></p>
						    </div>
						  </div>
						  <div class="form-group">
						    <label for="IdCardNo" class="col-sm-2 control-label">身份证号码：</label>
						    <div class="col-sm-10">
						      <input type="text" class="form-control" id="IdCardNo" placeholder="请输入身份证号码">
						    </div>
						  </div>
						  <div class="form-group">
						    <label for="Address" class="col-sm-2 control-label">所在地：</label>
						    <div class="col-sm-10">
						      <input type="text" class="form-control" id="Address" placeholder="请输入地址">
						    </div>
						  </div>
						  <br/>
						  <hr/>
						  <div class="form-group">
						    <label for="Birthday" class="col-sm-2 control-label">生日：</label>
						    <div class="col-sm-10">
						      <input type="text" class="form-control" id="Birthday" placeholder="请输入生日">
						    </div>
						  </div>
						  <div class="form-group">
						  	<label for="maritalStatus" class="col-sm-2 control-label">性别：</label>
						  	<div class="col-sm-10">
						  	<label class="radio-inline">
							  <input type="radio" name="maritalStatus" value="option1"> 保密
							</label>
							<label class="radio-inline">
							  <input type="radio" name="maritalStatus" value="option2"> 已婚
							</label>
							<label class="radio-inline">
							  <input type="radio" name="maritalStatus" value="option3"> 未婚
							</label>
							</div>
						  </div>
						  <div class="form-group">
						    <label for="income" class="col-sm-2 control-label">生日：</label>
						    <div class="col-sm-10">
						      <select name="income">
						      	<option>5000以下</option>
						      	<option>8000以上</option>
						      </select>
						    </div>
						  </div>
						  <div class="form-group">
						    <label for="hobbies" class="col-sm-2 control-label">兴趣爱好：</label>
						    <div class="col-sm-10">
						      <textarea class="form-control" rows="3" name="hobbies"></textarea>
						    </div>
						  </div>
						  <div class="form-group">
						    <div class="col-sm-offset-2 col-sm-10">
						      <button type="button" class="btn btn-default submit-memberInfo">提交</button>
						    </div>
						  </div>
						</form>
							
						</div>
						
					</div>
				</div>
			</div>
			
		</div>
		<!-- 底部版权 -->
		<div class="row footer">
		</div>
	</div>

</body>
</html>
