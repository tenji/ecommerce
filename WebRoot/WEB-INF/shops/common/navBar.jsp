<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 网站头部 -->

<!--
Navigation Bar Section 
-->
<div class="navbar navbar-default">
	<div class="container">
		<div class="container">
			<div class="navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="${path }">Home </a>
					</li>
					<li class=""><a href="list-view.html">List View</a>
					</li>
					<li class=""><a href="grid-view.html">Grid View</a>
					</li>
					<li class=""><a href="three-col.html">Three Column</a>
					</li>
					<li class=""><a href="four-col.html">Four Column</a>
					</li>
					<li class=""><a href="general.html">General Content</a>
					</li>
				</ul>
				<form action="#" class="pull-left navbar-form">
					<input type="text" placeholder="Search" class="search-query span2 form-control">
				</form>
				<ul class="nav pull-right navbar-nav">
					<li class="dropdown"><a data-toggle="dropdown" class="dropdown-toggle" href="#"><span class="icon-lock"></span>
							Login <b class="caret"></b>
					</a>
						<div class="dropdown-menu">
							<form class="form-horizontal loginFrm">
								<div class="form-group">
									<input type="text" class="span2 form-control" id="inputEmail" placeholder="Email">
								</div>
								<div class="form-group">
									<input type="password" class="span2 form-control" id="inputPassword" placeholder="Password">
								</div>
								<div class="form-group">
									<div class="checkbox"><label class=""><input type="checkbox">
										Remember me </label></div>
									<button type="submit" class="shopBtn btn-block">Sign
										in</button>
								</div>
							</form>
						</div></li>
				</ul>
			</div>
		</div>
	</div>
</div>
