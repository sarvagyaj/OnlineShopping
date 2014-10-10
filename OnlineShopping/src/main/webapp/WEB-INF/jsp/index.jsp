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

<title>Online Shopping</title>

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

<body onload="myFunction()" id="page-top" class="index">
	<input type="hidden" id="firstName" value="${it.firstName}">
	<input type="hidden" id="isAdmin" value="${it.isAdmin}">
	<input type="hidden" id="email" value="${it.email}">

	<!-- Navigation -->
	<nav class="navbar navbar-default navbar-fixed-top">
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

	<!-- Header -->
	<header>
		<div class="container">
			<div class="intro-text">
				<div class="intro-lead-in">Welcome To Our Store!</div>
				<div class="intro-heading">It's Nice To Meet You</div>
				<a href="store" class="page-scroll btn btn-xl">View Store</a>
			</div>
		</div>
	</header>


	<footer>
		<div class="container">
			<div class="row">
				<div class="col-md-4">
					<span class="copyright">Copyright &copy; Your Website 2014</span>
				</div>
				<div class="col-md-4">
					<ul class="list-inline social-buttons">
						<li><a href="#"><i class="fa fa-twitter"></i></a></li>
						<li><a href="#"><i class="fa fa-facebook"></i></a></li>
						<li><a href="#"><i class="fa fa-linkedin"></i></a></li>
					</ul>
				</div>
				<div class="col-md-4">
					<ul class="list-inline quicklinks">
						<li><a href="#">Privacy Policy</a></li>
						<li><a href="#">Terms of Use</a></li>
					</ul>
				</div>
			</div>
		</div>
	</footer>



	<!-- jQuery Version 1.11.0 -->
	<script src="js/jquery-1.11.0.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

	<!-- Plugin JavaScript -->
	<script
		src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
	<script src="js/classie.js"></script>


	<!-- Contact Form JavaScript -->
	<script src="js/jqBootstrapValidation.js"></script>


	<!-- Custom Theme JavaScript -->
	<script src="js/agency.js"></script>


</body>

</html>
