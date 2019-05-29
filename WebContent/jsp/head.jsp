<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="/store/js/jquery-1.11.3.min.js" type="text/javascript"></script>
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
		$(function(){
			var url="/store/categroy";
			var params="method=findCategroy";
			
			$.post(url,params,function(d){
				$(d).each(function(a,b){
					$("#ul").append("<li><a href='/store/product?method=findProduct&pageNumber=1&cid="+b.cid+"'>"+b.cname+"</a></li>");
				})
			},"json")
		})
	</script>

	<div class="container-fluid">

			<!--
            	时间：2015-12-30
            	描述：菜单栏
            -->
			<div class="container-fluid">
				<div class="col-md-4">
					<img src="/store/img/logo2.png" />
				</div>
				<div class="col-md-5">
					<img src="/store/img/header.png" />
				</div>
				<div class="col-md-3" style="padding-top:20px">
					<c:if test="${not empty sessionScope.user}">
						<ol class="list-inline">
							<li>欢迎你:${user.name}用户</li>
							<!-- 先访问servlet 通过servlet在转发给web-inf下 -->
							<!-- 以后最好不要jsp访问jsp了 要先访问servlet 通过servlet在访问jsp -->
							<li><a href="/store/order?method=findOrder&pageNumber=1">我的订单</a></li>
							<li><a href="/store/jsp/cart.jsp">购物车</a></li>
							<li><a href="/store/user?method=quit">退出</a></li>
						</ol>
					</c:if>
					
					<c:if test="${empty sessionScope.user}">
						<ol class="list-inline">
							<li><a href="/store/user?method=loginUI">登录</a></li>
							<!-- 先访问servlet 通过servlet在转发给web-inf下 -->
							<!-- 以后最好不要jsp访问jsp了 要先访问servlet 通过servlet在访问jsp -->
							<li><a href="/store/user?method=registerUI">注册</a></li>
							<li><a href="/store/jsp/cart.jsp">购物车</a></li>
						</ol>
					</c:if>
				</div>
			</div>
  <!--导航条-->
	<div class="container-fluid">
				<nav class="navbar navbar-inverse">
					<div class="container-fluid">
						<!-- Brand and toggle get grouped for better mobile display -->
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>
							<a class="navbar-brand" href="#">首页</a>
						</div>

						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
							<ul id="ul" class="nav navbar-nav">
								<!-- <li class="active"><a href="product_list.htm">手机数码<span class="sr-only">(current)</span></a></li>
								<li><a href="#">电脑办公</a></li>
								<li><a href="#">电脑办公</a></li>
								<li><a href="#">电脑办公</a></li> -->
							</ul>
							<form class="navbar-form navbar-right" role="search">
								<div class="form-group">
									<input type="text" class="form-control" placeholder="Search">
								</div>
								<button type="submit" class="btn btn-default">Submit</button>
							</form>

						</div>
						<!-- /.navbar-collapse -->
					</div>
					<!-- /.container-fluid -->
				</nav>
			</div>
</body>
</html>