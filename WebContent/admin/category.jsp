<%@page import="dao.CategoryDao"%>
<%@page import="entities.Category"%>
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

<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/chart.min.js"></script>
<script src="js/chart-data.js"></script>
<script src="js/easypiechart.js"></script>
<script src="js/easypiechart-data.js"></script>
<script src="js/bootstrap-datepicker.js"></script>
<script src="js/custom.js"></script>

</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container col-md-offset-5 col-md-3 algo">
		<table class="table table-bordered">
			<tbody>
				<tr>
					<td>Add Category</td>
				</tr>
				<tr>
					<td>
						<form class="form-horizontal" name="category" id="category"
							method="post" action="../CategoryController">
							<div class="control-group">
								<label class="span2 control-label">Parent Name&nbsp</label>
								<div class="controls">
									<select name="item">
										<option value="0">No Parent</option>
										<%
											CategoryDao categoryDao = new CategoryDao();
											for (Category category : categoryDao.getListCategory()) {
										%>
											<option value="<%=category.getId()%>"><%=category.getName()%></option>
										<%	
											}
										%>
									  </select>
								</div>
							</div>
							<div class="control-group">
								<label class="span2 control-label">Name&nbsp</label>
								<div class="controls">
									<input type="text" name="categoryName">
								</div>
							</div>
							<div class="control-group">
								<label class="span2 control-label">Description&nbsp</label>
								<div class="controls">
									<input type="text"
										name="description">
								</div>
							</div>
							<div class="col-md-offset-4 algo">
								<input type="submit" class="btn btn-primary" value="Add Category">
							</div>
						</form>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>