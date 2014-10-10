<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Agency - Start Bootstrap Theme</title>

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/agency.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="font/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link href="http://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css">
<link href='http://fonts.googleapis.com/css?family=Kaushan+Script'
	rel='stylesheet' type='text/css'>
<link
	href='http://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic'
	rel='stylesheet' type='text/css'>
<link
	href='http://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700'
	rel='stylesheet' type='text/css'>

<script src="js/menu.js"></script>

</head>

<body onload="callStore()" id="page-top" class="index">

	<!-- Navigation -->
	<nav class="navbar navbar-default navbar-fixed-top navbar-shrink">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header page-scroll">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand page-scroll" href="./">Shop Online</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li class="hidden"><a href="#page-top"></a></li>
					<li class="admin-menu"><a class="page-scroll"
						href="addProduct">Add Product</a></li>
					<li class="admin-menu"><a class="page-scroll"
						href="addCatalog">Add Catalog</a></li>
					<li><a class="page-scroll" href="store">View Store</a></li>
					<li class="login"><a class="page-scroll"
						href="viewCart?user_id=sarva" id="cart">My Shopping Cart</a></li>
					<li class="login"><a class="page-scroll"
						href="viewCartHistory?user_id=sarva" id="history">View History</a></li>
					<li class="not-login"><a class="page-scroll" href="signin">Sign
							In / Sign Up</a></li>
					<li class="login" onclick=signout()><a class="page-scroll">Sign
							Out</a></li>
					<li class="login"><a class="page-scroll"></a></li>
					<li class="login"><a class="page-scroll"></a></li>
					<li class="login"><h4 class="section-subheading text-muted">
							<div id="userName"></div>
						</h4></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	<!-- Article main content -->
	<section id="services">
		<div class="container">
			<div class="row">


				<div class="col-md-12 col-md-offset-1 col-sm-8 col-sm-offset-2">
					<div class="panel panel-default">
						<div class="panel-body">
							<div class="col-lg-12 text-center">
								<h2 class="section-heading">My Purchase History</h2>
								<h3 class="section-subheading text-muted"></h3>
								<div class="table-responsive">
									<c:forEach var="cart" items="${it}">
										<div style="text-align: left;">
											<h3 class="section-subheading text-muted">${cart.purchaseDate}</h3>
										</div>

										<table id="cartTable" class="table table-bordered">
											<thead>
												<tr>

													<th>Product Name</th>
													<th>Price</th>
													<th>Quantity</th>
													<th>Total</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="item" items="${cart.products}">
													<tr data-id="${item.productID}" data-quan="1"
														data-catalog="${item.catalogName}">
														<td>${item.prodName}</td>
														<td>${item.price}</td>
														<td>${item.quantity}</td>
														<c:set var="result" value="${item.quantity*item.price}" />
														<td>${result}</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										<div style="text-align: right;">
											<h4 class="text-muted">Total Amount: $
												${cart.totalAmtCharged}</h4>
										</div>
										<br>
										<hr>
										<br>
									</c:forEach>


								</div>
							</div>
							<hr>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<article class="col-xs-12 maincontent"></article>
	<!-- /Article -->

	<!-- jQuery Version 1.11.0 -->
	<script src="js/jquery-1.11.0.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

	<!-- Plugin JavaScript -->
	<script
		src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
	<script src="js/classie.js"></script>




</body>
</html>
