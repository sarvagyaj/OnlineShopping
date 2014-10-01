//validation on signup page
$(document).ready(function() {
	$("#form").validate({
		rules : {
			password : {
				required : true
			},
			confirmPassword : {
				equalTo : '#password'
			},
			email : {
				required : true,
				email : true
			}
		},
		messages : {
			confirmPassword : "Passwords do not match"
		}
	});
});
