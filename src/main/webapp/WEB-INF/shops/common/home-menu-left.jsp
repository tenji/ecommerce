<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 网站左侧边栏 -->

<script type="text/javascript">

</script>
<div class="col-md-3 column metro">
	<br/>
	<nav class="sidebar light">
    <ul>
        <li class="title">我的主页</li>
        <li class="stick bg-green"><a href="#">账户中心</a>
        	<ul class="sub-menu" style="display: block">
        		<li><a href="${path }/shops/home/member/memberInfo.htm">账户信息</a></li>
        		<li><a href="">账户安全</a></li>
        		<li><a href="">账户余额</a></li>
        		<li><a href="">我的积分</a></li>
        		<li><a href="">收货地址</a></li>
        	</ul>
        </li>
        <li class="stick bg-red active"><a href="#">客户服务</a></li>
        <li class="stick bg-yellow">
            <a class="dropdown-toggle" href="#">社区中心</a>
            <ul class="dropdown-menu" data-role="dropdown">
                <li><a href="">商品评价/晒单</a></li>
                <li class="divider"></li>
                <li><a href="">购物咨询</a></li>
            </ul>
        </li>
        <li class="stick bg-green"><a href="#">订单中心</a>
        	<ul class="sub-menu" style="display: block">
        		<li><a href="">我的订单</a></li>
        		<li><a href="">我的关注</a></li>
        		<li><a href="">浏览历史</a></li>
        		<li><a href="">为我推荐</a></li>
        		<li><a href="">邮件订阅</a></li>
        	</ul>
        </li>
        <li class="disabled"><a href="#">Disabled item</a></li>
 
    </ul>
</nav>
<br/>

</div>