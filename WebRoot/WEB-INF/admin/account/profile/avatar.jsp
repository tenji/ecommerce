<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>

<!DOCTYPE HTML>
<?xml version="1.0" encoding="utf-8" ?>
<html>
<head>
	<title>个人资料 - ${projectName }</title>
		<script type="text/javascript" src="${path }/assets/admin/js/common/swfobject.js"></script>
        <script type="text/javascript">
            // For version detection, set to min. required Flash Player version, or 0 (or 0.0.0), for no version detection. 
            var swfVersionStr = "11.1.0";
            // To use express install, set to playerProductInstall.swf, otherwise the empty string. 
            var xiSwfUrlStr = "";
            var flashvars = {};
            var params = {};
            params.quality = "high";
            params.bgcolor = "#ffffff";
            params.allowscriptaccess = "sameDomain";
            params.allowfullscreen = "true";
            var attributes = {};
            attributes.id = "fileUpload";
            attributes.name = "fileUpload";
            attributes.align = "middle";
            swfobject.embedSWF(
                "http://localhost:8080/ecommerce/assets/admin/flash/fileUpload.swf", "flashContent", 
                "100%", "500px", 
                swfVersionStr, xiSwfUrlStr, 
                flashvars, params, attributes);
            // JavaScript enabled so display the flashContent div in case it is not replaced with a swf object.
            swfobject.createCSS("#flashContent", "display:block;text-align:left;");
        </script>

	</head>

	<body>
		<!-- 顶部导航 -->
		<%@ include file="/WEB-INF/admin/common/adminnavbar.jsp"%>
		
		<!-- 主容器 -->
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed');}catch(e){}
			</script>

			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>
				
				<!-- 左侧菜单 -->
				<!-- 当前页面对应的二级菜单以及所属顶级菜单 -->
				<c:set var="topMenuName" value="account"></c:set>
				<c:set var="subMenuName" value="profile"></c:set>
				<%@ include file="/WEB-INF/admin/common/admin-sidebar.jsp" %>

				<!-- 內容 -->
				<div class="main-content">
					<!-- 面包屑导航 -->
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="#">Home</a>
							</li>
							<li>
								<a href="#">个人中心</a>
							</li>
							<li class="active">个人资料</li>
						</ul><!-- .breadcrumb -->

						<div class="nav-search" id="nav-search">
							<form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
									<i class="icon-search nav-search-icon"></i>
								</span>
							</form>
						</div><!-- #nav-search -->
					</div><!-- 面包屑导航结束 -->

					<!-- 内容主体 -->
					<div class="page-content">
						<div class="page-header">
							<h1>
								个人资料
								<small>
									<i class="icon-double-angle-right"></i>&nbsp;&nbsp;头像设置
								</small>
							</h1>
						</div>
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<div class="">
									<div class="user-profile row" id="user-profile-3">
										<div class="col-sm-offset-1 col-sm-10">
											<!-- /well -->

											<div class="space"></div>

											<div class="tabbable">
												<ul class="nav nav-tabs padding-16">
													<li class="">
														<a href="${path }/admin/account/profile/view">
															<i class="green icon-edit bigger-125"></i>
															基本信息
														</a>
													</li>
													
													<li class="active">
														<a href="javascript:void(0)">
															<i class="green icon-picture bigger-125"></i>
															头像设置
														</a>
													</li>

													<li class="">
														<a href="#edit-settings" data-toggle="tab">
															<i class="purple icon-cog bigger-125"></i>
															账户设置
														</a>
													</li>

													<li class="">
														<a href="#edit-password" data-toggle="tab">
															<i class="blue icon-key bigger-125"></i>
															重置密码
														</a>
													</li>
												</ul>

												<div class="tab-content profile-edit-tab-content">
													<!-- 基本信息 -->
													<div class="tab-pane" id="edit-basic">
													</div>
													
													<!-- 头像设置 -->
													<div class="tab-pane active" id="edit-portrait">
														<div id="flashContent">
												            <p>
												                To view this page ensure that Adobe Flash Player version 
												                11.1.0 or greater is installed. 
												            </p>
												            <script type="text/javascript"> 
												                var pageHost = ((document.location.protocol == "https:") ? "https://" : "http://"); 
												                document.write("<a href='http://www.adobe.com/go/getflashplayer'><img src='" 
												                                + pageHost + "www.adobe.com/images/shared/download_buttons/get_flash_player.gif' alt='Get Adobe Flash player' /></a>" ); 
												            </script> 
												        </div>
														<noscript>
												            <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="100%" height="100%" id="fileUpload">
												                <param name="movie" value="fileUpload.swf" />
												                <param name="quality" value="high" />
												                <param name="bgcolor" value="#ffffff" />
												                <param name="allowScriptAccess" value="sameDomain" />
												                <param name="allowFullScreen" value="true" />
												                <!--[if !IE]>-->
												                <object type="application/x-shockwave-flash" data="fileUpload.swf" width="100%" height="100%">
												                    <param name="quality" value="high" />
												                    <param name="bgcolor" value="#ffffff" />
												                    <param name="allowScriptAccess" value="sameDomain" />
												                    <param name="allowFullScreen" value="true" />
												                <!--<![endif]-->
												                <!--[if gte IE 6]>-->
												                    <p> 
												                        Either scripts and active content are not permitted to run or Adobe Flash Player version
												                        11.1.0 or greater is not installed.
												                    </p>
												                <!--<![endif]-->
												                    <a href="http://www.adobe.com/go/getflashplayer">
												                        <img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="Get Adobe Flash Player" />
												                    </a>
												                <!--[if !IE]>-->
												                </object>
												                <!--<![endif]-->
												            </object>
												        </noscript>
													</div>
													
													<!-- 账户设置 -->
													<div class="tab-pane" id="edit-settings">
													</div>

													<!-- 密码设置 -->
													<div class="tab-pane" id="edit-password">
													</div>
													
												</div>
											</div>

										</div><!-- /span -->
									</div><!-- /user-profile -->
								</div>
								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->

				<!-- /#ace-settings-container -->
				<%@ include file="/WEB-INF/admin/common/setting-container.jsp" %>
				
			</div><!-- /.main-container-inner -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='${path }/assets/admin/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>

		<!-- page specific plugin scripts -->

		<!-- inline scripts related to this page -->
	</body>
</html>
