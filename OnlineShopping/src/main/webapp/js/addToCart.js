$(document).delegate(':button', 'click', function() {
	var btn = $(this), tr = btn.closest('tr'), productID = tr.attr("data-id"), quantity = tr.attr("data-quan"), catalogName=tr.attr("data-catalog");
	console.log('productID = ' + productID);
	console.log('quantity = ' + quantity);
	

	$.ajax({
		url : '/OnlineShopping/addToCart/sarva?cata='+catalogName+'&product_id=' + productID+'&quan='+quantity,
		type : 'POST',
		contentType : 'application/json',
		success : function(result) {
			console.log("added")
		}
	})
});