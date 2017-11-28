<%@page import="ultils.Constant"%>
<%@page import="java.util.HashMap"%>
<%@page import="dao.ProductRuleDao"%>
<%@page import="entities.Product"%>
<%@page import="dao.ProductDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Single Product</title>
</head>
<body>
	<%
		ProductDao productDao = new ProductDao();
		Product product = new Product();
		ProductRuleDao productRuleDao = new ProductRuleDao();
		HashMap<Integer, Product> productMap = productDao.getAllProductMap();
		String product_id = "";
		if (request.getParameter("product_id") != null) {
			product_id = request.getParameter("product_id");
			product = productDao.getProduct(Integer.parseInt(product_id));

		}
	%>
	<jsp:include page="header.jsp"></jsp:include>

	<div class="container">
		<div class="single">
			<div class="col-md-9 top-in-single">
				<div class="col-md-5 single-top">
					<ul id="etalage">
						<li><img class="etalage_thumb_image img-responsive"
							src="images/si1.jpg" alt=""></li>
					</ul>

				</div>
				<div class="col-md-7 single-top-in">
					<div class="single-para">
						<h3><%=product.getName()%></h3>
						<div class="para-grid">
							<span class="add-to"><%=product.getPrice()%> VND</span> <a
								href="CartController?command=addToCart&product_id=<%=product.getId() %>" class="hvr-shutter-in-vertical cart-to">Mua</a>
							<div class="clearfix"></div>
						</div>
						<div class="available">
							<h6>Available Options :</h6>
							<ul>
								<li>Quality:<select>
										<option>1</option>
										<option>2</option>
										<option>3</option>
										<option>4</option>
										<option>5</option>
								</select></li>
							</ul>
						</div>
						<p><%=product.getDescription()%>.Aliquam dignissim porttitor
							tortor non fermentum. Curabitur in magna lectus. Duis sed eros
							diam. Lorem ipsum dolor sit amet, consectetur.
						</p>


					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>


	<div class="container">
		<div class="products">
			<h2 class=" products-in">Có thể bạn sẽ mua</h2>
			<div class=" top-products">
				<%
					for (int productId : productRuleDao.getRule(Integer.parseInt(product_id), Constant.NUMBER_ITEM)) {
						product = productMap.get(productId);
				%>
				<div class="col-md-3 md-col">
					<div class="col-md">
						<a href="single.html" class="compare-in"><img
							src="images/pic1.jpg" alt="" /></a>
						<div class="top-content">
							<h5>
								<a href="single.jsp?product_id=<%=product.getId() %>"><%=product.getName() %></a>
							</h5>
							<div class="white">
								<a href="single.html"
									class="hvr-shutter-in-vertical hvr-shutter-in-vertical2">MUA</a>
								<p class="dollar">
									<span class="in-dollar">VND</span><span><%=product.getPrice() %></span>
								</p>
								<div class="clearfix"></div>
							</div>
						</div>
					</div>
				</div>
				<%
					}
				%>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>


	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>