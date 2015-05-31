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
<!-- Upper Header -->
	<%@include file="/WEB-INF/shops/common/LowerHeader.jsp" %>
<!-- Upper Header End -->

<!-- Upper Header -->
	<%@include file="/WEB-INF/shops/common/navBar.jsp" %>
<!-- Upper Header End -->

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
	<div class="well np">
		<div id="myCarousel" class="carousel slide homCar">
            <div class="carousel-inner">
			  <div class="item">
                <img style="width:100%" src="${path }/assets/shops/img/bootstrap_free-ecommerce.png" alt="bootstrap ecommerce templates">
                <div class="carousel-caption">
                      <h4>Bootstrap shopping cart</h4>
                      <p><span>Very clean simple to use</span></p>
                </div>
              </div>
			  <div class="item">
                <img style="width:100%" src="${path }/assets/shops/img/carousel1.png" alt="bootstrap ecommerce templates">
                <div class="carousel-caption">
                      <h4>Bootstrap Ecommerce template</h4>
                      <p><span>Highly Google seo friendly</span></p>
                </div>
              </div>
			  <div class="item active">
                <img style="width:100%" src="${path }/assets/shops/img/carousel3.png" alt="bootstrap ecommerce templates">
                <div class="carousel-caption">
                      <h4>Twitter Bootstrap cart</h4>
                      <p><span>Very easy to integrate and expand.</span></p>
                </div>
              </div>
              <div class="item">
                <img style="width:100%" src="${path }/assets/shops/img/bootstrap-templates.png" alt="bootstrap templates">
                <div class="carousel-caption">
                      <h4>Bootstrap templates integration</h4>
                      <p><span>Compitable to many more opensource cart</span></p>
                </div>
              </div>
            </div>
            <a class="left carousel-control" href="#myCarousel" data-slide="prev">‹</a>
            <a class="right carousel-control" href="#myCarousel" data-slide="next">›</a>
          </div>
        </div>
<!--
New Products
-->
	<div class="well well-sm">
	<h3>New Products </h3>
	<hr class="soften">
		<div class="row">
		<div id="newProductCar" class="carousel slide">
            <div class="carousel-inner">
			<div class="item active">
				<div class="col-sm-3 col-md-3">
				<div class="thumbnail">
					<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
					<a href="#" class="tag"></a>
					<a href="product_details.html"><img src="${path }/assets/shops/img/bootstrap-ring.png" alt="bootstrap-ring"></a>
				</div>
				</div>
				<div class="col-sm-3 col-md-3">
				  <div class="thumbnail">
					<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
					<a href="#" class="tag"></a>
					<a href="product_details.html"><img src="${path }/assets/shops/img/i.jpg" alt=""></a>
				  </div>
				</div>
				<div class="col-sm-3 col-md-3">
				  <div class="thumbnail">
					<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
					<a href="#" class="tag"></a>
					<a href="product_details.html"><img src="${path }/assets/shops/img/g.jpg" alt=""></a>
				  </div>
				</div>
				<div class="col-sm-3 col-md-3">
				  <div class="thumbnail">
					<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
					<a href="product_details.html"><img src="${path }/assets/shops/img/s.png" alt=""></a>
				  </div>
				</div>
			  </div>
		   <div class="item">
			<div class="col-sm-3 col-md-3">
			  <div class="thumbnail">
				<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
				<a href="product_details.html"><img src="${path }/assets/shops/img/i.jpg" alt=""></a>
			  </div>
			</div>
			<div class="col-sm-3 col-md-3">
			  <div class="thumbnail">
				<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
				<a href="product_details.html"><img src="${path }/assets/shops/img/f.jpg" alt=""></a>
			  </div>
			</div>
			<div class="col-sm-3 col-md-3">
			  <div class="thumbnail">
				<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
				<a href="product_details.html"><img src="${path }/assets/shops/img/h.jpg" alt=""></a>
			  </div>
			</div>
			<div class="col-sm-3 col-md-3">
			  <div class="thumbnail">
				<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
				<a href="product_details.html"><img src="${path }/assets/shops/img/j.jpg" alt=""></a>
			  </div>
			</div>
		  </div>
		   </div>
		  <a class="left carousel-control" href="#newProductCar" data-slide="prev">‹</a>
            <a class="right carousel-control" href="#newProductCar" data-slide="next">›</a>
		  </div>
		</div>
		<hr class="soften">
		<div class="row">
			<div class="col-sm-4 col-md-4">
			  <div class="thumbnail">
				 
				<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
				<a href="product_details.html"><img src="${path }/assets/shops/img/b.jpg" alt=""></a>
				<div class="caption cntr">
					<p>Manicure &amp; Pedicure</p>
					<p><strong> $22.00</strong></p>
					<h4><a class="shopBtn" href="#" title="add to cart"> Add to cart </a></h4>
					<div class="actionList">
						<a class="pull-left" href="#">Add to Wish List </a> 
						<a class="pull-left" href="#"> Add to Compare </a>
					</div> 
					<br class="clr">
				</div>
			  </div>
			</div>
			<div class="col-sm-4 col-md-4">
			  <div class="thumbnail">
				<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
				<a href="product_details.html"><img src="${path }/assets/shops/img/c.jpg" alt=""></a>
				<div class="caption cntr">
					<p>Manicure &amp; Pedicure</p>
					<p><strong> $22.00</strong></p>
					<h4><a class="shopBtn" href="#" title="add to cart"> Add to cart </a></h4>
					<div class="actionList">
						<a class="pull-left" href="#">Add to Wish List </a> 
						<a class="pull-left" href="#"> Add to Compare </a>
					</div> 
					<br class="clr">
				</div>
			  </div>
			</div>
			<div class="col-sm-4 col-md-4">
			  <div class="thumbnail">
				<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
				<a href="product_details.html"><img src="${path }/assets/shops/img/a.jpg" alt=""></a>
				<div class="caption cntr">
					<p>Manicure &amp; Pedicure</p>
					<p><strong> $22.00</strong></p>
					<h4><a class="shopBtn" href="#" title="add to cart"> Add to cart </a></h4>
					<div class="actionList">
						<a class="pull-left" href="#">Add to Wish List </a> 
						<a class="pull-left" href="#"> Add to Compare </a>
					</div> 
					<br class="clr">
				</div>
			  </div>
			</div>
		</div>
	</div>
	<!--
	Featured Products
	-->
		<div class="well well-sm">
		  <h3><a class="btn pull-right btn-default btn-xs" href="products.html" title="View more">VIew More<span class="icon-plus"></span></a> Featured Products  </h3>
		  <hr class="soften">
		  <div class="row">
			<div class="col-sm-4 col-md-4">
			  <div class="thumbnail">
				<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
				<a href="product_details.html"><img class="lazy" data-original="${path }/assets/shops/img/d.jpg" alt=""></a>
				<div class="caption">
				  <h5>Manicure &amp; Pedicure</h5>
				  <h4>
					  <a class="defaultBtn" href="product_details.html" title="Click to view"><span class="icon-zoom-in"></span></a>
					  <a class="shopBtn" href="#" title="add to cart"><span class="icon-plus"></span></a>
					  <span class="pull-right">$22.00</span>
				  </h4>
				</div>
			  </div>
			</div>
			<div class="col-sm-4 col-md-4">
			  <div class="thumbnail">
				<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
				<a href="product_details.html"><img class="lazy" data-original="${path }/assets/shops/img/e.jpg" alt=""></a>
				<div class="caption">
				  <h5>Manicure &amp; Pedicure</h5>
				  <h4>
					  <a class="defaultBtn" href="product_details.html" title="Click to view"><span class="icon-zoom-in"></span></a>
					  <a class="shopBtn" href="#" title="add to cart"><span class="icon-plus"></span></a>
					  <span class="pull-right">$22.00</span>
				  </h4>
				</div>
			  </div>
			</div>
			<div class="col-sm-4 col-md-4">
			  <div class="thumbnail">
				<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
				<a href="product_details.html"><img class="lazy" data-original="${path }/assets/shops/img/f.jpg" alt=""></a>
				<div class="caption">
				  <h5>Manicure &amp; Pedicure</h5>
				  <h4>
					  <a class="defaultBtn" href="product_details.html" title="Click to view"><span class="icon-zoom-in"></span></a>
					  <a class="shopBtn" href="#" title="add to cart"><span class="icon-plus"></span></a>
					  <span class="pull-right">$22.00</span>
				  </h4>
				</div>
			  </div>
			</div>
	</div>
	</div>
	
	<div class="well well-sm">
	<a class="btn pull-right btn-default btn-xs" href="#">View more <span class="icon-plus"></span></a>
	Popular Products 
	</div>
	<hr>
	<div class="well well-sm">
	<a class="btn pull-right btn-default btn-xs" href="#">View more <span class="icon-plus"></span></a>
	Best selling Products 
	</div>
	</div>
	</div>

<!-- Clients -->
	<%@include file="/WEB-INF/shops/common/manufactures.jsp" %>
<!-- Clients End -->

<!-- Footer -->
	<%@include file="/WEB-INF/shops/common/footer.jsp" %>
<!-- Footer End -->
</div><!-- /container -->

<!-- Copyright -->
	<%@include file="/WEB-INF/shops/common/copyright.jsp" %>
<!-- Copyright End -->
<a href="#" class="gotop"><i class="icon-double-angle-up"></i></a>
    <!-- Placed at the end of the document so the pages load faster -->

</body>
</html>
