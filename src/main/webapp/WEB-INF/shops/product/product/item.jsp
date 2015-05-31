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
			<ul class="breadcrumb">
				<li><a href="index.html">Home</a> 
				</li>
				<li><a href="products.html">Items</a> 
				</li>
				<li class="active">Preview</li>
			</ul>
			<div class="well well-sm">
				<div class="row">
					<div class="col-sm-5 col-md-5">
						<div class="carousel slide" id="myCarousel">
							<div class="carousel-inner">
								<s:iterator value="model.productImgs">
									<div class="item active">
										<a href="#"> <img style="width:100%" alt="" src='${path }/<s:property value="url" />'></a>
									</div>
								</s:iterator>
								<div class="item">
									<a href="#"> <img style="width:100%" alt="" src="${path }/assets/shops/img/e.jpg">
									</a>
								</div>
							</div>
							<a data-slide="prev" href="#myCarousel" class="left carousel-control">‹</a> <a data-slide="next" href="#myCarousel" class="right carousel-control">›</a>
						</div>
					</div>
					<div class="col-sm-7 col-md-7">
						<h3>Name of the Item [$140.00]</h3>
						<hr class="soft">
						<form class="form-horizontal qtyFrm">
							<div class="form-group">
								<label class="control-label col-sm-3"> $140.00 </label>
								<div class="col-sm-9">
									<input type="number" placeholder="Qty" class="form-control">
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-sm-3"> Color </label>
								<div class="col-sm-9">
									<select class="form-control">
										<option>Red</option>
										<option>Purple</option>
										<option>Pink</option>
										<option>Red</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3"> Materials </label>
								<div class="col-sm-9">
									<select class="form-control">
										<option>Material 1</option>
										<option>Material 2</option>
										<option>Material 3</option>
										<option>Material 4</option>
									</select>
								</div>
							</div>
							<h4>100 items in stock</h4>
							<p>Nowadays the lingerie industry is one of the most
								successful business spheres. Nowadays the lingerie industry is
								one of ...</p>
							<p>
								<button class="shopBtn" type="submit">
									<span class=" icon-shopping-cart"></span> Add to cart
								</button>
							</p>
						</form>
					</div>
				</div>
				<hr class="softn clr">
				<ul class="nav nav-tabs" id="productDetail">
					<li class="active"><a data-toggle="tab" href="#home">Product
							Details</a>
					</li>
					<li class=""><a data-toggle="tab" href="#profile">Related
							Products </a>
					</li>
					<li class="dropdown"><a data-toggle="dropdown" class="dropdown-toggle" href="#">Acceseries <b class="caret"></b>
					</a>
						<ul class="dropdown-menu">
							<li><a data-toggle="tab" href="#cat1">Category one</a>
							</li>
							<li><a data-toggle="tab" href="#cat2">Category two</a>
							</li>
						</ul></li>
				</ul>
				<div class="tab-content tabWrapper" id="myTabContent">
					<div id="home" class="tab-pane fade active in">
						<h4>Product Information</h4>
						<table class="table table-striped">
							<tbody>
								<tr class="techSpecRow">
									<td class="techSpecTD1">Color:</td>
									<td class="techSpecTD2">Black</td>
								</tr>
								<tr class="techSpecRow">
									<td class="techSpecTD1">Style:</td>
									<td class="techSpecTD2">Apparel,Sports</td>
								</tr>
								<tr class="techSpecRow">
									<td class="techSpecTD1">Season:</td>
									<td class="techSpecTD2">spring/summer</td>
								</tr>
								<tr class="techSpecRow">
									<td class="techSpecTD1">Usage:</td>
									<td class="techSpecTD2">fitness</td>
								</tr>
								<tr class="techSpecRow">
									<td class="techSpecTD1">Sport:</td>
									<td class="techSpecTD2">122855031</td>
								</tr>
								<tr class="techSpecRow">
									<td class="techSpecTD1">Brand:</td>
									<td class="techSpecTD2">Shock Absorber</td>
								</tr>
							</tbody>
						</table>
						<!-- Product Details Begin -->
						<s:property value="model.detail" escape="false"></s:property>
						<!-- Product Details End -->

					</div>
					<div id="profile" class="tab-pane fade">
						<div class="row">
							<div class="col-sm-2 col-md-2">
								<img alt="" src="assets/img/d.jpg">
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
										<a class="defaultBtn" href="product_details.html"><span class=" icon-shopping-cart"></span> Add to cart</a> <a class="shopBtn" href="product_details.html">VIEW</a>
									</div>
								</form>
							</div>
						</div>
						<hr class="soft">
						<div class="row">
							<div class="col-sm-2 col-md-2">
								<img alt="" src="assets/img/d.jpg">
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
										<a class="defaultBtn" href="product_details.html"><span class=" icon-shopping-cart"></span> Add to cart</a> <a class="shopBtn" href="product_details.html">VIEW</a>
									</div>
								</form>
							</div>
						</div>
						<hr class="soft">
						<div class="row">
							<div class="col-sm-2 col-md-2">
								<img alt="" src="assets/img/d.jpg">
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
										<a class="defaultBtn" href="product_details.html"><span class=" icon-shopping-cart"></span> Add to cart</a> <a class="shopBtn" href="product_details.html">VIEW</a>
									</div>
								</form>
							</div>
						</div>
						<hr class="soft">
						<div class="row">
							<div class="col-sm-2 col-md-2">
								<img alt="" src="assets/img/d.jpg">
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
										<a class="defaultBtn" href="product_details.html"><span class=" icon-shopping-cart"></span> Add to cart</a> <a class="shopBtn" href="product_details.html">VIEW</a>
									</div>
								</form>
							</div>
						</div>
						<hr class="soften">
						<div class="row">
							<div class="col-sm-2 col-md-2">
								<img alt="" src="assets/img/d.jpg">
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
										<a class="defaultBtn" href="product_details.html"><span class=" icon-shopping-cart"></span> Add to cart</a> <a class="shopBtn" href="product_details.html">VIEW</a>
									</div>
								</form>
							</div>
						</div>
					</div>
					<div id="cat1" class="tab-pane fade">
						<p>Etsy mixtape wayfarers, ethical wes anderson tofu before
							they sold out mcsweeney's organic lomo retro fanny pack lo-fi
							farm-to-table readymade. Messenger bag gentrify pitchfork
							tattooed craft beer, iphone skateboard locavore carles etsy
							salvia banksy hoodie helvetica. DIY synth PBR banksy irony.
							Leggings gentrify squid 8-bit cred pitchfork. Williamsburg banh
							mi whatever gluten-free, carles pitchfork biodiesel fixie etsy
							retro mlkshk vice blog. Scenester cred you probably haven't
							heard of them, vinyl craft beer blog stumptown. Pitchfork
							sustainable tofu synth chambray yr.</p>
						<br>
						<br>
						<div class="row">
							<div class="col-sm-2 col-md-2">
								<img alt="" src="assets/img/b.jpg">
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
										<a class="defaultBtn" href="product_details.html"><span class=" icon-shopping-cart"></span> Add to cart</a> <a class="shopBtn" href="product_details.html">VIEW</a>
									</div>
								</form>
							</div>
						</div>
						<hr class="soften">
						<div class="row">
							<div class="col-sm-2 col-md-2">
								<img alt="" src="assets/img/a.jpg">
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
										<a class="defaultBtn" href="product_details.html"><span class=" icon-shopping-cart"></span> Add to cart</a> <a class="shopBtn" href="product_details.html">VIEW</a>
									</div>
								</form>
							</div>
						</div>
						<hr class="soften">
					</div>
					<div id="cat2" class="tab-pane fade">

						<div class="row">
							<div class="col-sm-2 col-md-2">
								<img alt="" src="assets/img/d.jpg">
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
										<a class="defaultBtn" href="product_details.html"><span class=" icon-shopping-cart"></span> Add to cart</a> <a class="shopBtn" href="product_details.html">VIEW</a>
									</div>
								</form>
							</div>
						</div>
						<hr class="soften">
						<div class="row">
							<div class="col-sm-2 col-md-2">
								<img alt="" src="assets/img/d.jpg">
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
										<a class="defaultBtn" href="product_details.html"><span class=" icon-shopping-cart"></span> Add to cart</a> <a class="shopBtn" href="product_details.html">VIEW</a>
									</div>
								</form>
							</div>
						</div>
						<hr class="soften">
						<div class="row">
							<div class="col-sm-2 col-md-2">
								<img alt="" src="assets/img/d.jpg">
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
										<a class="defaultBtn" href="product_details.html"><span class=" icon-shopping-cart"></span> Add to cart</a> <a class="shopBtn" href="product_details.html">VIEW</a>
									</div>
								</form>
							</div>
						</div>
						<hr class="soften">
						<div class="row">
							<div class="col-sm-2 col-md-2">
								<img alt="" src="assets/img/d.jpg">
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
										<a class="defaultBtn" href="product_details.html"><span class=" icon-shopping-cart"></span> Add to cart</a> <a class="shopBtn" href="product_details.html">VIEW</a>
									</div>
								</form>
							</div>
						</div>
						<hr class="soften">
					</div>
				</div>

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
