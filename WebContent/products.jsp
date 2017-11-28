<%@page import="ultils.Constant"%>
<%@page import="entities.Product"%>
<%@page import="dao.ProductDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product</title>
</head>
<body>
	<%
		ProductDao productDao = new ProductDao();
		int total = 0;
		int pageno = 1;
		String sub_category_id = "";
		String sub_category_name = "";
		if (request.getParameter("sub_category") != null) {
			sub_category_id = request.getParameter("sub_category");

		}
		if (request.getParameter("sub_category_name") != null) {
			sub_category_name = request.getParameter("sub_category_name");

		}
		if (request.getParameter("page") != null) {
			pageno = Integer.parseInt(request.getParameter("page"));
		}
		total = productDao.countTotal(Integer.parseInt(sub_category_id));
	%>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container">
		<div class="products">
			<h2 class=" products-in"><%=sub_category_name%></h2>
			<div class=" top-products">
				<%
					for (Product product : productDao.getListProduct(Integer.parseInt(sub_category_id),
							(pageno - 1) * Constant.NUMBER_PRODUCT_PER_PAGE, Constant.NUMBER_PRODUCT_PER_PAGE)) {
				%>
				<div class="col-md-3 md-col">
					<div class="col-md">
						<a href="single.jsp?product_id=<%=product.getId()%>"
							class="compare-in"><img src="images/pic3.jpg" alt="" /> </a>
						<div class="top-content">
							<h5>
								<a href="single.jsp?product_id=<%=product.getId()%>"><%=product.getName()%></a>
							</h5>
							<div class="white">
								<a
									href="CartController?command=addToCart&product_id=<%=product.getId()%>"
									class="hvr-shutter-in-vertical hvr-shutter-in-vertical2">Mua</a>
								<p class="dollar">
									<span class="in-dollar">VND</span><span><%=product.getPrice()%></span>
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

			<ul class="start col-md-offset-4">
				<%
					if (pageno != 1) {
				%>
				<li><a
					href="products.jsp?sub_category=<%=sub_category_id%>&sub_category_name=<%=sub_category_name%>&page=<%=pageno - 1%>"><i></i></a></li>
				<%
					}
				%>
				<%
					for (int i = 0; i <= total / (Constant.NUMBER_PRODUCT_PER_PAGE + 1); i++) {
				%>
				<li class=""><a
					href="products.jsp?sub_category=<%=sub_category_id%>&sub_category_name=<%=sub_category_name%>&page=<%=i + 1%>">
						<%
							if (pageno == (i + 1)) {
						%><span><%=i + 1%></span>
						<%
							} else {
						%>
						<%=i + 1%>
						<%
 							}
						%>
				</a></li>
				<%
					}
				%>
				<%
					if (pageno != (total / Constant.NUMBER_PRODUCT_PER_PAGE +1)) {
				%>
				<li><a
					href="products.jsp?sub_category=<%=sub_category_id%>&sub_category_name=<%=sub_category_name%>&page=<%=pageno + 1%>"><i
						class="next"> </i></a></li>
				<%
					}
				%>
			</ul>

		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>