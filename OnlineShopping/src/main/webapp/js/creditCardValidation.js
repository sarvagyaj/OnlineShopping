$(document).ready(function() {
	$(".to-hide").hide();
	$("#pay").click(function() {
		$(".to-hide").show();
		setAction();
	});
});

function setAction() {
	$("#form").get(0).setAttribute('action', 'placeOrder?user_id='+sessionStorage.userId);
}



$("#form").validate({
	rules : {
		ccNumber : {
			required : true,
			creditcard : true
		}
	}
});
