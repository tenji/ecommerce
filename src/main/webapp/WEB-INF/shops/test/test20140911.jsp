<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
    <%@ include file="/common/customTaglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${path }/assets/admin/js/plugin/jquery/jquery-2.0.3.js" type="text/javascript"></script>
<script type="text/javascript">
	function add() {
		$("<p>added</p>").appendTo($("#add"));
	}
</script>
</head>
<body>
<p>HAHA, this is a test file!</p>
<form name="test">
男：<input type="radio" name="sex" value="0" onclick="add();" />
女：<input type="radio" name="sex" value="1" />
</form>
<div id="add">
	
</div>
</body>
</html>