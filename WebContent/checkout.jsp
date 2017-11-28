<%@page import="entities.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Check out</title>
</head>
<body>
	<%
		Account account = null;
		if (session.getAttribute("account") != null) {
			account = (Account) session.getAttribute("account");
		} else {
			response.sendRedirect("/ElectronicsStore/login.jsp");
		}
	%>
	<jsp:include page="header.jsp"></jsp:include>

	<div class="container">
		<div class="account">
			<h2 class="account-in">Thanh Toán</h2>
			<form action="CheckoutController" method="POST">
				<div>
					<%
						if (account != null) {
					%>
					<span class="col-md-3">Địa chỉ*</span> <input type="text"
						name="address" value="<%=account.getAddress()%>">
					<%
						} else {
					%>
					<span class="col-md-3">Địa chỉ*</span> <input type="text"
						name="address">
					<%
						}
					%>
				</div>
				<div>
					<%
						if (account != null) {
					%>
					<span class="col-md-3">Số Điện Thoại*</span> <input type="text"
						name="phone" value="<%=account.getPhone()%>">
					<%
						} else {
					%>
						<span class="col-md-3">Số Điện Thoại*</span> <input type="text"
						name="phone">
					<%
						}
					%>
				</div>
				<div class="col-md-offset-4">
					<input type="submit" value="Thanh Toán">
				</div>
			</form>
		</div>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>