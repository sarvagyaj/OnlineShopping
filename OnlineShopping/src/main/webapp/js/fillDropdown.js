$(document).ready(function() {
	var uri = '/OnlineShopping/allCatalogs';
	$.ajax({
		type : 'GET',
		contentType : 'application/json',
		url : uri,
		success : function(result) {
			$('#catalogName').find("option:gt(0)").remove();
			for (var i = 0; i < result.length; i++) {
				var K = $('<option/>').append(result[i].catalogName);
				$('#catalogName').append(K);
				console.log("name :" + result[i].catalogName);
			}

		}
	});

});