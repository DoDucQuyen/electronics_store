<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div class="container">
		<div class="account col-md-offset-2">
			<h2 class="account-in">Đăng Nhập</h2>
			<form action="AccountController" method="POST">
				<%
					if (request.getAttribute("error") != null) {
				%>
				<div class="error-message">
					<p style="color:red"><%=request.getAttribute("error") %></p>
				</div>
				<%
					}
				%>
				<div>
					<span class="col-md-3">Tên Đăng Nhập *</span> <input type="text"
						name="username">
				</div>
				<div>
					<span class="col-md-3">Mật Khẩu *</span> <input type="password"
						name="password">
				</div>
				<div class="col-md-offset-4">
					<input type="submit" value="Đăng Nhập">
				</div>
				<input type="hidden" name="command" value="login">
			</form>
		</div>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>