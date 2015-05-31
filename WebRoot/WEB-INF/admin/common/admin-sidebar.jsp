<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/oscache" prefix="cache"%>
<%@ include file="/common/customTaglibs.jsp"%>
<!-- 左侧菜单 -->

<div class="sidebar" id="sidebar">
	<script type="text/javascript">
		try{ace.settings.check('sidebar' , 'fixed');}catch(e){}
	</script>

	<cache:cache>
	<div class="sidebar-shortcuts" id="sidebar-shortcuts">
		<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
			<button class="btn btn-success">
				<i class="icon-signal"></i>
			</button>

			<button class="btn btn-info">
				<i class="icon-pencil"></i>
			</button>

			<button class="btn btn-warning">
				<i class="icon-group"></i>
			</button>

			<button class="btn btn-danger">
				<i class="icon-cogs"></i>
			</button>
		</div>

		<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
			<span class="btn btn-success"></span> <span class="btn btn-info"></span>

			<span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
		</div>
	</div>
	<!-- #sidebar-shortcuts -->

	<ul class="nav nav-list">
		<li><a href="${path }/admin/myDashboard"> <i class="icon-dashboard"></i> <span class="menu-text"> Dashboard </span> </a></li>
		
		<c:forEach items="${sessionScope.systemSessionBean.menusTree }" var="topMenu">
			<li class='<c:if test="${topMenu.menuName == topMenuName }">active</c:if>'><a href="#" class="dropdown-toggle"> <i class="${topMenu.iconClass }"></i>
					<span class="menu-text"> ${topMenu.menuDescription } </span> <b class="arrow icon-angle-down"></b> </a>
					
			<ul class="submenu">
				<c:forEach items="${topMenu.subMenus }" var="subMenu">
					<li class='<c:if test="${subMenu.menuName == subMenuName }">active</c:if>'><a href="${path}${subMenu.url }"> <i class="icon-double-angle-right"></i> ${subMenu.menuDescription } </a></li>
				</c:forEach>
			</ul>
			</li>
		</c:forEach>
		
	</ul><!-- /.nav-list -->
	
	<div class="sidebar-collapse" id="sidebar-collapse">
		<i class="icon-double-angle-left" data-icon1="icon-double-angle-left"
			data-icon2="icon-double-angle-right"></i>
	</div>
	</cache:cache>

	<script type="text/javascript">
		try{ace.settings.check('sidebar' , 'collapsed');}catch(e){}
	</script>
</div>