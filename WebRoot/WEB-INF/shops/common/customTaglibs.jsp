<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- 保存项目路径和项目名 -->

<%-- 项目相对路径 --%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<%-- 项目绝对路径 --%>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<c:set var="basePath" value="<%=basePath %>"></c:set>

<%-- 取得请求的参数字符串 --%>
<c:set var="queryString" value="${pageContext.request.queryString}"></c:set>

<%-- 项目名 --%>
<c:set var="projectName" value="Open Ecommerce" />
