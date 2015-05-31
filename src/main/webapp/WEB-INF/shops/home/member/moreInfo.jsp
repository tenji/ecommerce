<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<?xml version="1.0" encoding="utf-8" ?>
<html>
  <head>
    <title>${projectName } - 更多个人信息</title>
    <!-- 引入页面meta元信息以及公用的CSS和JS -->
	<%@ include file="/common/meta.jsp" %>
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
						<li><a href="${path }/shops/home/member/memberInfo.htm">账户信息</a></li>
						<li class="active"><a href="${path }/shops/home/member/moreInfo.htm">更多个人信息</a></li>
						<li><a href="${path }/shops/home/member/memberRank.htm">我的级别</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active">
						<br/>
						<h4 style="color: red">教育信息</h4>
						<hr/>
						<form class="form-horizontal" role="form">
						  <div class="form-group">
						    <label for="educationInfo.type" class="col-sm-2 control-label">学校类型：</label>
						    <div class="col-sm-10">
						    	<select class="form-control" name="educationInfo.type">
						    		<option>大学</option>
						    		<option>职高</option>
						    		<option>高中</option>
						    	</select>
						    </div>
						  </div>
						  <div class="form-group">
						    <label for="educationInfo.name" class="col-sm-2 control-label">学校名称：</label>
						    <div class="col-sm-10">
						      <input type="text" class="form-control educationInfo" name="educationInfo.name" placeholder="请选择学校">
						    </div>
						  </div>
						  <div class="form-group">
						    <label for="educationInfo.school" class="col-sm-2 control-label">院系：</label>
						    <div class="col-sm-10">
						    	<select class="form-control" name="educationInfo.school">
						    		<option>院系</option>
						    	</select>
						    </div>
						  </div>
						  <div class="form-group">
						    <label for="educationInfo.enrollmentTime" class="col-sm-2 control-label">入学时间：</label>
						    <div class="col-sm-10">
						    	<select class="form-control" name="educationInfo.enrollmentTime">
						    		<s:iterator begin="1935" end="2014" var="year">
						    			<option>
						    				<s:property value="#year" />
						    			</option>
						    		</s:iterator>
						    	</select>
						    </div>
						  </div>
						  <div class="form-group">
						    <div class="col-sm-offset-2 col-sm-10">
						      <button type="button" class="btn btn-default">提交</button>
						    </div>
						  </div>
						</form>
						<hr/>
						<h4 style="color: red">职业信息</h4>
						<hr/>
						<form class="form-horizontal" role="form">
						  <div class="form-group">
						    <label for="occupationInfo.name" class="col-sm-2 control-label">单位名称：</label>
						    <div class="col-sm-10">
						    	<input type="text" class="form-control occupationInfo.name" name="occupationInfo.name" placeholder="请输入单位名称">
						    </div>
						  </div>
						  <div class="form-group">
						    <label for="income" class="col-sm-2 control-label">工作时间：</label>
						    <div class="col-sm-4">
						      <select class="form-control" name="occupationInfo.startTime">
						    		<s:iterator begin="1935" end="2014" var="year">
						    			<option>
						    				<s:property value="#year" />
						    			</option>
						    		</s:iterator>
						      </select>
						    </div>
						    <div class="col-sm-1"><p class="form-control">至</p></div>
						    <div class="col-sm-4">
						      <select class="form-control" name="occupationInfo.startTime">
						    		<s:iterator begin="1935" end="2014" var="year">
						    			<option>
						    				<s:property value="#year" />
						    			</option>
						    		</s:iterator>
						      </select>
						    </div>
						  </div>
						  <div class="form-group">
						    <div class="col-sm-offset-2 col-sm-10">
						      <button type="button" class="btn btn-default">提交</button>
						    </div>
						  </div>
						</form>
						<hr/>
							
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
