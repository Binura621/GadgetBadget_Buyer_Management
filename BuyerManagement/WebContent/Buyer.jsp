<%@page import="model.BuyerServlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Buyer Management - GadgetBadget</title>

<link href="css/stylesheetnew.css" rel="stylesheet" />
<link rel="stylesheet" href="View/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	
<script src="Components/jquery-3.5.0.min.js"></script>
<script src="Components/Buyer.js"></script>

</head>

<body>
	<div class="container">

		<p class="font-weight-bold">
		<center>
			<h1>
				<u><i><b>Buyer Management - GadgetBadget</b></i></u>
			</h1>
		</center>
		</p>
		<br>
		<br>

		<fieldset>

			<legend>
				<b>Add Buyer Details</b>
			</legend>
			<form id="BUYER" name="BrUYER" class="border border-light p-5">
			

				<div class="form-outline mb-4">
					<label class="form-label" for="form6Example3"class="col-sm-2 col-form-label col-form-label-sm">buyerName:</label>
					<input type="hidden" id="buyerID" name="buyerID" value="">
					<input type="text" id="buyerName" class="form-control" name="buyerName">
				</div>

				<div class="form-outline mb-4">
					<label class="form-label" for="form6Example3"
						class="col-sm-2 col-form-label col-form-label-sm">projectName:</label>
					<input type="text" id="projectName" class="form-control"
						name="projectName">
				</div>

				<div class="form-outline mb-4">
					<label class="form-label" for="form6Example3"
						class="col-sm-2 col-form-label col-form-label-sm">email:</label> <input
						type="text" id="email" class="form-control" name="email">
				</div>

				<div class="form-outline mb-4">
					<label class="form-label" for="form6Example3"
						class="col-sm-2 col-form-label col-form-label-sm">contactNo:</label>
					<input type="text" id="contactNo" class="form-control"
						name="contactNo">
				</div>
				<div class="form-outline mb-4">
					<label class="form-label" for="form6Example3"
						class="col-sm-2 col-form-label col-form-label-sm">researcherName:</label>
					<input type="text" id="researcherName" class="form-control"
						name="researcherName">
				</div>
	</div>
	
<br>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary btn-lg btn-block">

	
	</fieldset>

	<br>

	<div class="container" id="BuyerGrid">
		<fieldset>
			<legend>
				<b>View Buyer Details</b>
			</legend>
			<form method="post" action="Buyer.jsp" class="table table-striped">
				<%
					BuyerServlet viewBuyer = new BuyerServlet();
					out.print(viewBuyer.readBuyer());
				%>
			</form>
			<br>
		</fieldset>
	</div>
	</div>
</body>
</html>



