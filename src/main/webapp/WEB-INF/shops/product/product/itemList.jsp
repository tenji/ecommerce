<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/shops/common/customTaglibs.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<?xml version="1.0" encoding="utf-8" ?>
<html>
  <head>
    <title>${projectName }</title>
    <!-- 引入页面meta元信息以及公用的CSS和JS -->
	<%@ include file="/WEB-INF/shops/common/shops-meta.jsp" %>
  </head>
  
<script type="text/javascript">
$(function() {
	$("img.lazy").lazyload({
   		effect : "fadeIn"
	});
});
</script>
 
<body>
<!-- Upper Header -->
	<%@include file="/WEB-INF/shops/common/UpperHeader.jsp" %>
<!-- Upper Header End -->

<div class="container">
<!-- Lower Header -->
	<%@include file="/WEB-INF/shops/common/LowerHeader.jsp" %>
<!-- Lower Header End -->

<!-- Nav Bar -->
	<%@include file="/WEB-INF/shops/common/navBar.jsp" %>
<!-- Nav Bar End -->

	<!-- 
	Body Section 
	-->
	<div class="row">
		<div id="sidebar" class="col-sm-3 col-md-3">
			<!-- sidebar -->
			<%@include file="/WEB-INF/shops/common/sidebar-left.jsp" %>
			<!-- sidebar end -->
		</div>

		<div class="col-sm-9 col-md-9">
			<div class="well well-sm">
				<h3>筛选商品</h3>
				<s:if test="null!=categoryList&&!categoryList.isEmpty()">
				<hr class="soften">
				<div class="categories row">
					<div class="col-sm-1 col-md-1">分类：</div>
					<div class="col-sm-11 col-md-11">
					<s:iterator value="categoryList">
						<a href='${path }/shops/product/product/itemList.htm?categoryId=<s:property value="id" />'><span class="label label-primary"><s:property value="categoryName" /></span></a>
					</s:iterator>
					</div>
				</div>
				</s:if>
				<s:if test="null!=brandList&&!brandList.isEmpty()">
				<hr class="soften">
				<div class="brands row">
					<div class="col-sm-1 col-md-1">品牌：</div>
					<div class="col-sm-11 col-md-11">
					<s:iterator value="brandList">
						<label><input type="checkbox" class="ace"><span class="lbl"><s:property value="chineseName" /></span></label>
					</s:iterator>
					</div>
				</div>
				</s:if>
			</div>
			<div class="well well-sm">
				<div class="row">
					<div class="col-sm-2 col-md-2">
						<img class="img-responsive" src="${path }/assets/shops/img/a.jpg" alt="">
					</div>
					<div class="col-sm-6 col-md-6">
						<h5>Product Name</h5>
						<p>Nowadays the lingerie industry is one of the most
							successful business spheres. We always stay in touch with the
							latest fashion tendencies - that is why our goods are so
							popular..</p>
					</div>
					<div class="alignR col-sm-4 col-md-4">
						<form class="form-horizontal qtyFrm">
							<h3>$140.00</h3>
							<div class="checkbox"><label class=""> <input type="checkbox">
									Adds product to compair 
							</label></div><br>
							<div class="btn-group">
								<a href="product_details.html" class="defaultBtn"><span class=" icon-shopping-cart"></span> Add to cart</a> <a href="product_details.html" class="shopBtn">VIEW</a>
							</div>
						</form>
					</div>
				</div>
				
				<s:iterator value="productList">
				<hr class="soften">
				<div class="row">
					<div class="col-sm-2 col-md-2">
						<img class="img-responsive" src="${path }/images/products/ecommerce.jpg" alt="">
					</div>
					<div class="col-sm-6 col-md-6">
						<h5><s:property value="name"></s:property></h5>
						<p><s:property value="description"></s:property></p>
					</div>
					<div class="alignR col-sm-4 col-md-4">
						<form class="form-horizontal qtyFrm">
							<h3><s:property value="listPrice"></s:property></h3>
							<div class="checkbox"><label class=""> 
							<input type="checkbox"> Adds product to compair </label></div><br>
							<div class="btn-group">
								<a href="product_details.html" class="defaultBtn"><span class=" icon-shopping-cart"></span> Add to cart</a> <a href='${path }<s:property value="staticPageUrl" />' class="shopBtn">VIEW</a>
							</div>
						</form>
					</div>
				</div>
				</s:iterator>
				
			</div>
		</div>

		</div>

<!-- Clients -->
	<%@include file="/WEB-INF/shops/common/manufactures.jsp" %>
<!-- Clients End -->

<!-- Footer -->
	<%@include file="/WEB-INF/shops/common/footer.jsp" %>
<!-- Footer End -->
</div>

<!-- Copyright -->
	<%@include file="/WEB-INF/shops/common/copyright.jsp" %>
<!-- Copyright End -->
</body>
</html>
