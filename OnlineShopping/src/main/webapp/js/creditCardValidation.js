$(document).ready(function() {
	$(".to-hide").hide();
	$("#pay").click(function() {
		$(".to-hide").show();
	});
});

$("#form").validate({
	rules : {
		ccNumber : {
			required : true,
			creditcard : true
		}
	}
});
