<%@page import="entities.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Header</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/font-awesome.min.css" rel="stylesheet">
<link href="css/datepicker3.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet">

<!--Custom Font-->
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">
<!--[if lt IE 9]>
	<script src="js/html5shiv.js"></script>
	<script src="js/respond.min.js"></script>
	<![endif]-->
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/chart.min.js"></script>
<script src="js/chart-data.js"></script>
<script src="js/easypiechart.js"></script>
<script src="js/easypiechart-data.js"></script>
<script src="js/bootstrap-datepicker.js"></script>
<script src="js/custom.js"></script>
<script>
	window.onload = function() {
		var chart1 = document.getElementById("line-chart").getContext("2d");
		window.myLine = new Chart(chart1).Line(lineChartData, {
			responsive : true,
			scaleLineColor : "rgba(0,0,0,.2)",
			scaleGridLineColor : "rgba(0,0,0,.05)",
			scaleFontColor : "#c5c7cc"
		});
	};
</script>
</head>
<body>
	<%
	Account account = null;
	if (session.getAttribute("account") != null) {
		account = (Account) session.getAttribute("account");
	}
	%>
	<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#sidebar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"><span>Electronics</span>Store</a>
			<ul class="nav navbar-top-links navbar-right">
				<li class="dropdown"><a class="dropdown-toggle count-info"
					data-toggle="dropdown" href="#"> <em class="fa fa-bell"></em><span
						class="label label-info">5</span>
				</a>
					<ul class="dropdown-menu dropdown-alerts">
						<li><a href="#">
								<div>
									<em class="glyphicon glyphicon-shopping-cart"></em> 1 New
									Message <span class="pull-right text-muted small">3 mins
										ago</span>
								</div>
						</a></li>
						<li class="divider"></li>
						<li><a href="#">
								<div>
									<em class="fa fa-heart"></em> 12 New Likes <span
										class="pull-right text-muted small">4 mins ago</span>
								</div>
						</a></li>
						<li class="divider"></li>
						<li><a href="#">
								<div>
									<em class="fa fa-user"></em> 5 New Followers <span
										class="pull-right text-muted small">4 mins ago</span>
								</div>
						</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
	<!-- /.container-fluid --> </nav>

	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
		<div class="profile-sidebar">
			<div class="profile-usertitle">
				<%
					if (session.getAttribute("account") != null) {
				%>
				<div class="profile-usertitle-name"><%=account.getUsername()%></div>
				<%
					}
				%>

			</div>
			<div class="clear"></div>
		</div>
		<div class="divider"></div>
		<ul class="nav menu">
			<li><a href="algorithm.jsp"><em
					class="fa fa-dashboard">&nbsp;</em> Khai phá luật kết hợp</a></li>
			<li><a href="widgets.html"><em class="fa fa-calendar">&nbsp;</em>
					Widgets</a></li>
			<li><a href="category.jsp"><em class="fa fa-calendar">&nbsp;</em>
					Category</a></li>
			<li><a href="../AccountController?command=logout"><em class="fa fa-power-off">&nbsp;</em>
					Logout</a></li>
		</ul>
	</div>
	<!--/.sidebar-->

</body>
</html>