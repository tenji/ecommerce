<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<?xml version="1.0" encoding="utf-8" ?>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>${projectName } - 个人中心</title>
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
			<!-- 中部 -->
			<div class="col-md-9 home-content">
				<h4 class="margin-base-vertical text-center">Hot Products</h4>
			</div>
			
		</div>
		<!-- 底部版权 -->
		<div class="row footer">
		</div>
	</div>

</body>
</html>
