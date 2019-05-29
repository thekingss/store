<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>会员登录</title>
		<link rel="stylesheet" href="/store/css/bootstrap.min.css" type="text/css" />
		<script src="/store/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="/store/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="/store/css/style.css" type="text/css" />

		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
				width: 100%;
			}
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
		</style>
	</head>

	<body>
		
			<!--
            	时间：2015-12-30
            	描述：菜单栏
            -->
			<%@include file="/jsp/head.jsp" %>

		<div class="row" style="width:1210px;margin:0 auto;">
			<div class="col-md-12">
				<ol class="breadcrumb">
					<li><a href="#">首页</a></li>
				</ol>
			</div>

			<c:if test="${not empty pb.list }">
				<c:forEach items="${pb.list}" var="product">
					<div class="col-md-3">
						<a href="/store/product?method=findByPid&pid=${product.pid}">
							<img src="/store/${product.pimage}" width="170" height="170" style="display: inline-block;">
						</a>
						<p><a href="/store/product?method=findByPid&pid=${product.pid}" style='color:green'>${product.pname}</a></p>
						<p><font color="#FF0000">商城价：&yen;${product.shop_price}</font></p>
					</div>
				</c:forEach>
			</c:if>
		</div>
			

		<!--分页 -->
		<div style="width:380px;margin:0 auto;margin-top:50px;">
			<ul class="pagination" style="text-align:center; margin-top:10px;">
				<c:if test="${pb.pageNumber==1}">
					<li class="disabled"><a href="#" aria-label="Previous">
					<span aria-hidden="true">
					&laquo;
					</span></a>
				    </li>
				</c:if>
				<c:if test="${pb.pageNumber!=1 }">
					<li >
						<a href="/store/product?method=findProduct&cid=${cid}&pageNumber=${pb.pageNumber-1}" aria-label="Previous">
							<span aria-hidden="true">
							&laquo;
							</span>
						</a>
				    </li>
				</c:if>
				<c:forEach begin="1" end="${pb.totalPage}" var="page">
					<c:if test="${pb.pageNumber==page }">
						<li class="disabled"><a href="#">${page}</a></li>
					</c:if>
					<c:if test="${pb.pageNumber!=page }">
						<li><a href="/store/product?method=findProduct&cid=${cid }&pageNumber=${page}">${page}</a></li>
					</c:if>
				</c:forEach>
				
				<c:if test="${pb.pageNumber==pb.totalPage}">
					<li class="disabled"><a href="#" aria-label="Next">
					<span aria-hidden="ture">
					&raquo;
					</span></a>
				    </li>
				</c:if>
				<c:if test="${pb.pageNumber!=pb.totalPage }">
					<li >
						<a href="/store/product?method=findProduct&cid=${cid}&pageNumber=${pb.pageNumber+1}" aria-label="Next">
							<span aria-hidden="true">
							&raquo;
							</span>
						</a>
				    </li>
				</c:if>
				
			</ul>
		</div>
		<!-- 分页结束=======================        -->

		<!--
       		商品浏览记录:
        -->
		<div style="width:1210px;margin:0 auto; padding: 0 9px;border: 1px solid #ddd;border-top: 2px solid #999;height: 246px;">

			<h4 style="width: 50%;float: left;font: 14px/30px " 微软雅黑 ";">浏览记录</h4>
			<div style="width: 50%;float: right;text-align: right;"><a href="">more</a></div>
			<div style="clear: both;"></div>

			<div style="overflow: hidden;">

				<ul style="list-style: none;">
					<li style="width: 150px;height: 216;float: left;margin: 0 8px 0 0;padding: 0 18px 15px;text-align: center;"><img src="/store/products/1/cs10001.jpg" width="130px" height="130px" /></li>
				</ul>

			</div>
		</div>
		<div style="margin-top:50px;">
			<img src="/store/image/footer.jpg" width="100%" height="78" alt="我们的优势" title="我们的优势" />
		</div>

		<div style="text-align: center;margin-top: 5px;">
			<ul class="list-inline">
				<li><a>关于我们</a></li>
				<li><a>联系我们</a></li>
				<li><a>招贤纳士</a></li>
				<li><a>法律声明</a></li>
				<li><a>友情链接</a></li>
				<li><a target="_blank">支付方式</a></li>
				<li><a target="_blank">配送方式</a></li>
				<li><a>服务声明</a></li>
				<li><a>广告声明</a></li>
			</ul>
		</div>
		<div style="text-align: center;margin-top: 5px;margin-bottom:20px;">
			Copyright &copy; 2005-2016 传智商城 版权所有
		</div>

	</body>

</html>