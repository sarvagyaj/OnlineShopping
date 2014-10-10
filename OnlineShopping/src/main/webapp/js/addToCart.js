$(document)
		.delegate(
				':button',
				'click',
				function() {
					var btn = $(this), tr = btn.closest('tr'), productID = tr
							.attr("data-id"), quan = tr.attr("data-quan"), catalogName = tr
							.attr("data-catalog");
					console.log('productID = ' + productID);
					console.log('quan =' + quan);
					console.log(catalogName);
					if (sessionStorage.userId) {
						$.ajax({
							url : '/OnlineShopping/addToCart/'
									+ sessionStorage.userId + '?cata='
									+ catalogName + '&product_id=' + productID
									+ '&quan=1',
							type : 'POST',
							contentType : 'application/json',
							success : function(result) {
								console.log("added " + quan);
								alert("Added !! ");
							}
						});
					} else {
						alert("Please login first")
					}
				});
