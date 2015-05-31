<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/customTaglibs.jsp" %>

<!DOCTYPE HTML>
<?xml version="1.0" encoding="utf-8" ?>
<html>
  <head>
    <title>产品类目列表 - ${projectName }</title>
	<script type="text/javascript" src="${path }/ckeditor/ckeditor.js"></script>
	
<script type="text/javascript">
	var zTreeCategoryObj; // 树形产品类目对象
	// zTree配置
	var setting = {
		check: { /** 复选框 **/
			enable: true,
			chkStyle: "radio",
			autoCheckTrigger: true,
			radioType: "all"
		},  
		view: { 
			expandSpeed: 300, // 设置树展开的动画速度，IE6下面没效果，  
			showIcon: false
		},                     
		data: { 
			simpleData: {   // 简单的数据源
				enable: true,  
				idKey: "id",  //id和pid，这里不用多说了吧，树的目录级别  
				pIdKey: "pId", 
				rootPId: 0   //根节点  
			} 
	 	}, 
	 	callback: {
	 		onCheck: zTreeOnCheck
	 	}
	};
	
	var zTree = { // 命名空间
		getCategoryZTree: function() {
			// 发送AJAX请求，获取产品类目树
			var url = "${path}" + "/admin/product/category/getAssignedCategoriesTree.htm";
		  	$.ajax({url: url, dataType: 'json', type: 'POST', async: false, // 同步
		  		success: zTree.initCategoryZTree, 
		  		error: function() {
		  			EC.createNoty("error", "操作失败，请重试！");
		  			return ;
		  		}
		  	});
			
		}, 
		
		// 初始化CategoryZTree
		initCategoryZTree: function(sysCategoryList) {
			if (!handleAjaxResults(sysCategoryList)) {
				return ;
			} 
			
			var treeNodes = sysCategoryList;
			zTreeCategoryObj =  $.fn.zTree.init($("#categoryTree"), setting, treeNodes);
		}
	};
	
	// 节点选中事件
	function zTreeOnCheck(event, treeId, treeNode) {
		createSpin();
		// 发送AJAX请求，获取特定类目下的品牌
		var url = "${path}" + "/admin/product/brand/getBrandsByCategoryId.htm";
	  	$.ajax({url: url, dataType: 'json', data: {categoryId: treeNode.id}, type: 'POST', async: false, 
	  		success: function(brandList) {
	  			var brandSelect = $(".brandId");
	  			brandSelect.html(""); // 先清空select框内容
	  			for ( var i = 0; i < brandList.length; i++) {
					$("<option value=" + brandList[i].id + ">" + brandList[i].chineseName + "</option>").appendTo(brandSelect);
				}
	  			deleteSpin();
	  		}, 
	  		error: function() {
	  			EC.createNoty("error", "操作失败，请重试！");
	  			return ;
	  		}
	  	});
	}
	
	$(function() {
		CKEDITOR.replace( 'detailEditor' );
		
		zTree.getCategoryZTree(); // 生成类目树
		
		// jquery validate验证
	  	$("#form-basic").validate({
	  		// 焦点离开
	  		onfocusout: function(event) {
	  			$(event).valid();
	  		}, 
			rules: {
				name: "required",
				listPrice: {required: true, number: true},
				description: {required: true} 
			},
			messages: {
			}
		});
		
		// 提交产品基本信息
		$("#form-basic .btn-save").on("click", function() {
			// 验证表单是否合法
	  		if (!$("#form-basic").valid()) {
				return ;
			}
			var categoryId;	// 类目编号
	  		var selectedCategoryNodes = zTreeCategoryObj.getCheckedNodes(true);
	  		if (isEmptyObj(selectedCategoryNodes)) {
	  			// 未选择所属类目
	  			createNoty("error", "请选择所属类目！");
				return ;
			} else {
				categoryId = selectedCategoryNodes[0].id;
			}
			if (!$("#brandId option:selected").val()) {
				createNoty("error", "请选择所属品牌！");
				return ;
			}
			var url = "${path}" + "/admin/product/product/add.htm";
	  		var data = $("#form-basic").serialize() + "&categoryId=" + categoryId;
	  		// 发送AJAX请求
		  	$.ajax({url: url, data: data, dataType: 'json', async: false, type: 'POST',
		  		success: function(list) {
		  			if (!handleAjaxResults(list)) {
						return ;
					}
					createNoty("success", "操作成功"); // 操作成功提醒框
		  		},
		  		error: function() {
		  			createNoty("error", "操作失败，请重试！"); // 操作失败提醒框
		  		}
		  	});
		});
	});
</script>
</head>
<body>
	<!-- 顶部导航 -->
	<%@ include file="/WEB-INF/admin/common/adminnavbar.jsp"%>
	
	<!-- 主容器 -->
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try{ace.settings.check('main-container' , 'fixed')}catch(e){}
		</script>

		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#">
				<span class="menu-text"></span>
			</a>
			
			<!-- 左侧菜单 -->
			<!-- 当前页面对应的二级菜单以及所属顶级菜单 -->
			<s:set name="topMenuName">product</s:set>
			<s:set name="subMenuName">product</s:set>
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
							<a href="#">产品管理</a>
						</li>
						<li class="active">产品管理</li>
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
					<div class="row">
						<div class="page-header">
							<h1>
								产品管理
								<small>
									<i class="icon-double-angle-right"></i>
									添加产品
								</small>
							</h1>
						</div>
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<div class="">
									<div class="user-profile row" id="user-profile-3">
										<div class="col-sm-offset-1 col-sm-10">
											<!-- /well -->

											<div class="space"></div>

											<div class="tabbable">
												<ul class="nav nav-tabs padding-16">
													<li class="active">
														<a href="#basic" data-toggle="tab">
															<i class="green icon-edit bigger-125"></i>
															基本信息
														</a>
													</li>
													
													<li class="">
														<a href="#pics" data-toggle="tab">
															<i class="purple icon-picture bigger-125"></i>
															产品图片
														</a>
													</li>

													<li class="">
														<a href="#parameters" data-toggle="tab">
															<i class="blue icon-key bigger-125"></i>
															产品参数
														</a>
													</li>
												</ul>

												<div class="tab-content profile-edit-tab-content">
													<div class="tab-pane active" id="basic">
														<h4 class="header blue bolder smaller">General</h4>
														<form class="form-horizontal" id="form-basic">
															<div class="form-group">
																<label for="form-field-1"
																	class="col-sm-3 control-label no-padding-right">
																	产品名称 </label>
	
																<div class="col-sm-9">
																	<input type="text" class="col-xs-10 col-sm-5" placeholder="Name" name="name" id="name">
																</div>
															</div>
															<div class="space-4"></div>
															<div class="form-group">
																<label for="form-field-2"
																	class="col-sm-3 control-label no-padding-right">
																	标价 </label>
	
																<div class="col-sm-9">
																	<input type="text" class="col-xs-10 col-sm-5"
																		placeholder="ListPrice" name="listPrice" id="listPrice"><span
																		class="help-inline col-xs-12 col-sm-7"> <span
																		class="middle">目标价格，也称标价或定价</span> </span>
																</div>
															</div>
															<div class="space-4"></div>
															<div class="form-group">
																<label for="form-field-2"
																	class="col-sm-3 control-label no-padding-right">
																	所属分类 </label>
	
																<div class="col-sm-9">
																	<div id="categoryTree" class="ztree"></div>
																</div>
															</div>
															<div class="space-4"></div>
															<div class="form-group">
																<label for="form-field-2"
																	class="col-sm-3 control-label no-padding-right">
																	所属品牌 </label>
	
																<div class="col-sm-9">
																	<select multiple class="form-control brandId" id="brandId" name="brandId"></select>
																</div>
															</div>
															<div class="space-4"></div>
															<div class="form-group">
																<label for="form-field-1"
																	class="col-sm-3 control-label no-padding-right">
																	产品描述 </label>
	
																<div class="col-sm-9">
																	<input type="text" class="col-xs-10 col-sm-5"
																		placeholder="Description" name="description" id="description">
																</div>
															</div>
															<h4 class="header blue bolder smaller">Product Details</h4>
															<!-- CKEditor Begin -->
															<textarea id="detailEditor" name="detail">&lt;p&gt;Product Details.&lt;/p&gt;</textarea>
															<!-- CKEditor End -->
															<div class="clearfix form-actions">
																<div class="col-md-offset-3 col-md-9">
																	<button type="button" class="btn btn-info btn-save">
																		<i class="icon-ok bigger-110"></i>
																		Save
																	</button>
			
																	&nbsp; &nbsp;
																	<button type="reset" class="btn">
																		<i class="icon-undo bigger-110 btn-reset"></i>
																		Return
																	</button>
																</div>
															</div>
														</form>
													</div>
													
													<div class="tab-pane" id="pics">
														<form class="form-horizontal" id="form-pics">
														<div class="space-10"></div>

														<div>
															<label class="inline">
																<input type="checkbox" class="ace" name="form-field-checkbox">
																<span class="lbl"> Make my profile public</span>
															</label>
														</div>

														<div class="space-8"></div>

														<div>
															<label class="inline">
																<input type="checkbox" class="ace" name="form-field-checkbox">
																<span class="lbl"> Email me new updates</span>
															</label>
														</div>

														<div class="space-8"></div>

														<div>
															<label class="inline">
																<input type="checkbox" class="ace" name="form-field-checkbox">
																<span class="lbl"> Keep a history of my conversations</span>
															</label>

															<label class="inline">
																<span class="space-2 block"></span>

																for
																<input type="text" maxlength="3" class="input-mini">
																days
															</label>
														</div>
														<div class="clearfix form-actions">
															<div class="col-md-offset-3 col-md-9">
																<button type="button" class="btn btn-info btn-save">
																	<i class="icon-ok bigger-110"></i>
																	Save
																</button>
		
																&nbsp; &nbsp;
																<button type="reset" class="btn">
																	<i class="icon-undo bigger-110 btn-reset"></i>
																	Reset
																</button>
															</div>
														</div>
														</form>
													</div>

													<div class="tab-pane" id="parameters">
														<form class="form-horizontal" id="form-parameters">
														<div class="space-10"></div>

														<div class="form-group">
															<label for="form-field-pass1" class="col-sm-3 control-label no-padding-right">New Password</label>

															<div class="col-sm-9">
																<input type="password" name="loginPassword" id="loginPassword">
															</div>
														</div>

														<div class="space-4"></div>

														<div class="form-group">
															<label for="form-field-pass2" class="col-sm-3 control-label no-padding-right">Confirm Password</label>

															<div class="col-sm-9">
																<input type="password" name="confirmLoginPassword" id="confirmLoginPassword">
															</div>
														</div>
														
														<div class="clearfix form-actions">
															<div class="col-md-offset-3 col-md-9">
																<button type="button" class="btn btn-info btn-save">
																	<i class="icon-ok bigger-110"></i>
																	Save
																</button>
		
																&nbsp; &nbsp;
																<button type="reset" class="btn">
																	<i class="icon-undo bigger-110 btn-reset"></i>
																	Reset
																</button>
															</div>
														</div>
														</form>
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

			<div class="ace-settings-container" id="ace-settings-container">
				<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
					<i class="icon-cog bigger-150"></i>
				</div>

				<div class="ace-settings-box" id="ace-settings-box">
					<div>
						<div class="pull-left">
							<select id="skin-colorpicker" class="hide">
								<option data-skin="default" value="#438EB9">#438EB9</option>
								<option data-skin="skin-1" value="#222A2D">#222A2D</option>
								<option data-skin="skin-2" value="#C6487E">#C6487E</option>
								<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
							</select>
						</div>
						<span>&nbsp; Choose Skin</span>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-navbar" />
						<label class="lbl" for="ace-settings-navbar"> Fixed Navbar</label>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-sidebar" />
						<label class="lbl" for="ace-settings-sidebar"> Fixed Sidebar</label>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-breadcrumbs" />
						<label class="lbl" for="ace-settings-breadcrumbs"> Fixed Breadcrumbs</label>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl" />
						<label class="lbl" for="ace-settings-rtl"> Right To Left (rtl)</label>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container" />
						<label class="lbl" for="ace-settings-add-container">
							Inside
							<b>.container</b>
						</label>
					</div>
				</div>
			</div><!-- /#ace-settings-container -->
		</div><!-- /.main-container-inner -->

		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
			<i class="icon-double-angle-up icon-only bigger-110"></i>
		</a>
	</div><!-- /.main-container -->
</body>
</html>
