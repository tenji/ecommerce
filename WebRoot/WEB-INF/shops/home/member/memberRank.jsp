<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<?xml version="1.0" encoding="utf-8" ?>
<html>
  <head>
    <title>${projectName } - 会员等级</title>
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
						<li><a href="${path }/shops/home/member/moreInfo.htm">更多个人信息</a></li>
						<li class="active"><a href="${path }/shops/home/member/memberRank.htm">我的级别</a></li>
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
