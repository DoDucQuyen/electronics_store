<%@page import="ultils.Constant"%>
<%@page import="entities.Product"%>
<%@page import="dao.ProductDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Electronics Store</title>
</head>
<body>

	<%
		ProductDao productDao = new ProductDao();
	%>
	<jsp:include page="header.jsp"></jsp:include>

	<jsp:include page="banner.jsp"></jsp:include>

	<div class="container">
		<div class="content">
			<div class="content-top">
				<div class="content-middle">
					<div class="content-middle-in">
						<script type="text/javascript">
							$(window).load(function() {
								$("#flexiselDemo1").flexisel({
									visibleItems : 4,
									animationSpeed : 1000,
									autoPlay : true,
									autoPlaySpeed : 3000,
									pauseOnHover : true,
									enableResponsiveBreakpoints : true,
									responsiveBreakpoints : {
										portrait : {
											changePoint : 480,
											visibleItems : 1
										},
										landscape : {
											changePoint : 640,
											visibleItems : 2
										},
										tablet : {
											changePoint : 768,
											visibleItems : 3
										}
									}
								});

							});
						</script>
						<script type="text/javascript" src="js/jquery.flexisel.js"></script>

					</div>
				</div>
				<!---->
				<div class="content-bottom">
					<h3 class="future">Mới nhất</h3>
					<div class="content-bottom-in">
						<ul id="flexiselDemo2">
							<%
								for (Product product : productDao.getLastProduct(Constant.NUMBER_PRODUCT_PER_PAGE)) {
							%>
							<li><div class="col-md men">
									<a href="single.jsp?product_id=<%=product.getId()%>"
										class="compare-in "><img src="images/pi6.jpg" alt="" /> </a>
									<div class="top-content bag">
										<h5>
											<a href="single.jsp?product_id=<%=product.getId()%>"><%=product.getName() %></a>
										</h5>
										<div class="white">
											<a href="CartController?command=addToCart&product_id=<%=product.getId()%>"
												class="hvr-shutter-in-vertical hvr-shutter-in-vertical2">Mua</a>
											<p class="dollar">
												<span class="in-dollar">VND</span><span><%=product.getPrice()%></span>
											</p>
											<div class="clearfix"></div>
										</div>
									</div>
								</div></li>
							<%
								}
							%>
						</ul>
						<script type="text/javascript">
							$(window).load(function() {
								$("#flexiselDemo2").flexisel({
									visibleItems : 4,
									animationSpeed : 1000,
									autoPlay : true,
									autoPlaySpeed : 3000,
									pauseOnHover : true,
									enableResponsiveBreakpoints : true,
									responsiveBreakpoints : {
										portrait : {
											changePoint : 480,
											visibleItems : 1
										},
										landscape : {
											changePoint : 640,
											visibleItems : 2
										},
										tablet : {
											changePoint : 768,
											visibleItems : 3
										}
									}
								});

							});
						</script>
					</div>
				</div>
			</div>
		</div>

		<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>