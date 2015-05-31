<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ include file="/common/customTaglibs.jsp" %>
<%@ page trimDirectiveWhitespaces="true"%>

<!DOCTYPE HTML>
<?xml version="1.0" encoding="utf-8" ?>
<html>
<head>
	<!-- 引入页面meta元信息以及公用的CSS和JS -->
	<%@ include file="/WEB-INF/shops/common/shops-meta.jsp" %>
	<title>
		<decorator:title></decorator:title>
	</title>
	<decorator:head></decorator:head>

</head>

<body>
	<decorator:body></decorator:body>
</body>
</html>