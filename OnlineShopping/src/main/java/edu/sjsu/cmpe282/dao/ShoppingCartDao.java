package edu.sjsu.cmpe282.dao;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import edu.sjsu.cmpe282.database.DynamoConnection;
import edu.sjsu.cmpe282.dto.Cart;
import edu.sjsu.cmpe282.dto.Product;

public class ShoppingCartDao {

	public boolean addProductToCart(String userId, String catalogName,
			int productId, int quantity) throws JsonGenerationException,
			JsonMappingException, IOException {
		AmazonDynamoDBClient client = DynamoConnection.getInstance()
				.getClient();

		DynamoDBMapper mapper = new DynamoDBMapper(client);

		String productAsJson = null;
		ProductDao productDao = new ProductDao();
		Product product = productDao.getProduct(catalogName, productId);
		product.setQuantity(quantity);
		ObjectMapper jsonMapper = new ObjectMapper();
		productAsJson = jsonMapper.writeValueAsString(product);

		// Retrieve the item.
		Cart cartRetrieved = mapper.load(Cart.class, userId, "ACTIVE");
		System.out.println("Cart retrieved: " + cartRetrieved);

		if (cartRetrieved == null) {
			Cart cart = new Cart();
			cart.setUserId(userId);
			cart.setStatus("ACTIVE");
			cart.setProdcutList(new HashSet<String>(Arrays
					.asList(productAsJson)));
			cart.setTotalAmtCharged(quantity * product.getPrice());

			mapper.save(cart);
			return true;
		}

		// Update the item.
		Set<String> products = cartRetrieved.getProdcutList();
		boolean productAlreadyPresent = false;
		for (String prod : products) {
			Product cartProduct = null;
			cartProduct = jsonMapper.readValue(prod, Product.class);
			System.out.println(cartProduct);
			if (cartProduct.getProductID() == productId) {
				cartProduct.setQuantity(quantity + cartProduct.getQuantity());
				String prodUpdated = jsonMapper.writeValueAsString(cartProduct);
				products.remove(prod);
				products.add(prodUpdated);
				productAlreadyPresent = true;
				break;
			}
		}
		if (!productAlreadyPresent) {
			products.add(productAsJson);
			cartRetrieved.setProdcutList(products);
		}

		cartRetrieved.setTotalAmtCharged(cartRetrieved.getTotalAmtCharged()
				+ (quantity * product.getPrice()));
		mapper.save(cartRetrieved);
		System.out.println("Item updated: " + cartRetrieved);
		return true;

	}
	
	public void getActiveCart(String userId) {
		AmazonDynamoDBClient client = DynamoConnection.getInstance()
				.getClient();
		DynamoDBMapper mapper = new DynamoDBMapper(client);

		// Retrieve the item.
		Cart cartRetrieved = mapper.load(Cart.class, userId, "ACTIVE");
		System.out.println("Cart retrieved: " + cartRetrieved);
	}
}




