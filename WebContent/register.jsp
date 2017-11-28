<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div class="container">
		<div class="account">
			<h2 class="account-in">Đăng Ký</h2>
			<form action="AccountController" method="POST">
				<%
					if (request.getAttribute("error_register") != null) {
				%>
				<div class="error-message">
					<p style="color:red"><%=request.getAttribute("error_register") %></p>
				</div>
				<%
					}
				%>
				<div>
					<span class="col-md-2">Tên Tài Khoản *</span> <input type="text" name="username">
				</div>
				<div>
					<span class="word col-md-2">Mật Khẩu *</span> <input
						type="password" name="password">
				</div>
				<div>
					<span class="col-md-2">Địa chỉ </span> <input type="text" name="address">
				</div>
				<div>
					<span class="col-md-2">Số Điện Thoại </span> <input type="text" name="phone">
				</div>
				<div class="clearfix"></div>
				<div class="col-md-offset-3">
					<input type="submit" value="Đăng ký" >
				</div>
				<input type="hidden" name="command" value="register">				
			</form>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>