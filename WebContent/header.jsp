<%@page import="entities.Item"%>
<%@page import="java.util.Map"%>
<%@page import="entities.Cart"%>
<%@page import="entities.Account"%>
<%@page import="entities.SubCategory"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="dao.SubCategoryDao"%>
<%@page import="entities.Category"%>
<%@page import="dao.CategoryDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Header</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery.min.js"></script>
<!-- Custom Theme files -->
<!--theme-style-->
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<!--//theme-style-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Bonfire Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript">
	
	
	
	
	
	
	
	
	
	
	
	
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 













</script>
<!--fonts-->
<link
	href='http://fonts.googleapis.com/css?family=Exo:100,200,300,400,500,600,700,800,900'
	rel='stylesheet' type='text/css'>
<!--//fonts-->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event) {
			event.preventDefault();
			$('html,body').animate({
				scrollTop : $(this.hash).offset().top
			}, 1000);
		});
	});
</script>
<!--slider-script-->
<script src="js/responsiveslides.min.js"></script>
<script>
	$(function() {
		$("#slider1").responsiveSlides({
			auto : true,
			speed : 500,
			namespace : "callbacks",
			pager : true,
		});
	});
</script>
<!--//slider-script-->
<script>
	$(document).ready(function(c) {
		$('.alert-close').on('click', function(c) {
			$('.message').fadeOut('slow', function(c) {
				$('.message').remove();
			});
		});
	});
</script>
<script>
	$(document).ready(function(c) {
		$('.alert-close1').on('click', function(c) {
			$('.message1').fadeOut('slow', function(c) {
				$('.message1').remove();
			});
		});
	});
</script>
</head>
<body>
	<%
		CategoryDao categoryDao = new CategoryDao();
		SubCategoryDao subCategoryDao = new SubCategoryDao();
		HashMap<Integer, ArrayList<SubCategory>> categoryMap = subCategoryDao.getCategoryMap();
		Account account = null;
		if (session.getAttribute("account") != null) {
			account = (Account) session.getAttribute("account");
		}
		Cart cart;
		if (session.getAttribute("cart") != null) {
			cart = (Cart) session.getAttribute("cart");
		} else {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
	%>
	<div class="header">
		<div class="header-top">
			<div class="container">
				<div class="header-top-in">
					<div class="logo">
						<a href="index.jsp"><img src="images/logo.png" alt=" "></a>
					</div>
					<div class="header-in">
						<ul class="icon1 sub-icon1">
							<%
								if (account != null) {
							%>
							<li><a href="AccountController?command=logout"><%=account.getUsername()%></a></li>
							<%
								} else {
							%>
							<li><a href="login.jsp"> Đăng nhập</a></li>
							<li><a href="register.jsp"> Đăng ký</a></li>
							<%
								}
							%>
							<li><div class="cart">
									<a href="#" class="cart-in"> </a> <span> <%=cart.count()%></span>
								</div>
								<ul class="sub-icon1 list">
									<h3>
										Tổng sản phẩm(<%=cart.count()%>)
									</h3>
									<div class="shopping_cart">
										<%
											for (Map.Entry<Integer, Item> list : cart.getCartItems().entrySet()) {
										%>
										<div class="cart_box">
											<div class="message">
												<div class="list_img">
													<img src="images/14.jpg" class="img-responsive" alt="">
												</div>
												<div class="list_desc">
													<h4>
														<a
															href="single.jsp?product_id=<%=list.getValue().getProduct().getId()%>"><%=list.getValue().getProduct().getName()%></a>
													</h4>
													<%=list.getValue().getQuantity()%>
													x<span class="actual"> <%=list.getValue().getProduct().getPrice()%>VND
													</span>
												</div>
												<div class="col-sm-2 delete">
													<a href="CartController?command=remove&product_id=<%=list.getValue().getProduct().getId() %>" style="color:red">Xóa</a>
												</div>
												<div class="clearfix"></div>
											</div>
										</div>
										<%
											}
										%>
									</div>
									<div class="total">
										<div class="total_left">Tổng số tiền :</div>
										<div class="total_right"><%=cart.totalCart()%>VND
										</div>
										<div class="clearfix"></div>
									</div>
									<div class="login_buttons">
										<div class="check_button">
											<a href="checkout.jsp">Thanh Toán</a>
										</div>
										<div class="clearfix"></div>
									</div>
									<div class="clearfix"></div>
								</ul></li>
						</ul>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
		<div class="header-bottom">
			<div class="container">
				<div class="h_menu4">
					<a class="toggleMenu" href="#">Menu</a>
					<ul class="nav">
						<li class="active"><a href="index.jsp"><i> </i>Trang chủ</a></li>
						<%
							for (Category category : categoryDao.getListCategory()) {
						%>
						<li><%=category.getName()%>
							<ul class="drop">
								<%
									ArrayList<SubCategory> listSubCategory = (ArrayList) categoryMap.get(category.getId());
										if (listSubCategory != null) {

											for (SubCategory subcategory : listSubCategory) {
								%>
								<li><a
									href="products.jsp?sub_category=<%=subcategory.getId()%>&sub_category_name=<%=subcategory.getName()%>&page=1"><%=subcategory.getName()%></a></li>
								<%
									}
										}
								%>

							</ul></li>
						<%
							}
						%>

					</ul>
					<script type="text/javascript" src="js/nav.js"></script>
				</div>
			</div>
		</div>

	</div>
</body>
</html>