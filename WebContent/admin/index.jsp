<%@page import="entities.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin</title>
</head>
<body>
	<%
		Account account = null;
		if (session.getAttribute("account") != null) {
			account = (Account) session.getAttribute("account");
			if(account.getRole() != 1) {
				response.sendRedirect("/ElectronicsStore/index.jsp");
			}
		} else {
			response.sendRedirect("/ElectronicsStore/login.jsp");
		}
	%>
	<jsp:include page="header.jsp"></jsp:include>
</body>
</html>