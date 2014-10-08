function myFunction() {
	var firstName = $("#firstName").val();
	var isAdmin = $("#isAdmin").val();
	var userId = $("#email").val();
	console.log("Page is loaded " + firstName + " " + isAdmin + " "+ userId);

	showMenuItems(firstName, isAdmin,userId);
	sessionStore(firstName, userId, isAdmin);
}

function sessionStore(firstName, userId, isAdmin) {
	if (typeof (Storage) !== "undefined") {
		sessionStorage.firstName = firstName;
		sessionStorage.userId = userId;
		sessionStorage.isAdmin = isAdmin;
	} else {
		console.log("Sorry, your browser does not support web storage...");
	}
}

function callStore() {
	var firstName = sessionStorage.firstName;
	var isAdmin = sessionStorage.isAdmin;
	var userId = sessionStorage.userId;
	
	showMenuItems(firstName,isAdmin,userId);
	
}

function showMenuItems(firstName, isAdmin, userId) {
	if (isAdmin == 0) {
		$(".admin-menu").hide();
	}

	if (firstName) {
		$(".login").show();
		$(".not-login").hide();
	} else {
		$(".login").hide();
		$(".not-login").show();
	}
	$("#userName").html("Hello " + firstName);
	 changeLinks(userId);
}

function signout() {
	console.log("in");
	sessionStorage.removeItem("firstName");
	sessionStorage.removeItem("isAdmin");
	sessionStorage.removeItem("userId");
	
	$.ajax({
		url : '/OnlineShopping/signout',
		type : 'GET',
		contentType : 'application/json',
		success : function(data) {
			window.location.href = data.redirect;
			console.log("done ");
			
		}
	});
}

function changeLinks(userId) {
	console.log("link changed "+userId);
	$("a#history").prop('href','viewCartHistory?user_id='+userId);
	$("a#cart").prop('href','viewCart?user_id='+userId);
	//console.log("link  "+$("a#history").href());
}

