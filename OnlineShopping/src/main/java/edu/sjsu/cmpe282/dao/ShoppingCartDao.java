package edu.sjsu.cmpe282.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;

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
			cart.setProductList(new HashSet<String>(Arrays
					.asList(productAsJson)));
			cart.setTotalAmtCharged(quantity * product.getPrice());

			mapper.save(cart);
			return true;
		}

		// Update the item.
		Set<String> products = cartRetrieved.getProductList();
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
			cartRetrieved.setProductList(products);
		}

		cartRetrieved.setTotalAmtCharged(cartRetrieved.getTotalAmtCharged()
				+ (quantity * product.getPrice()));
		mapper.save(cartRetrieved);
		System.out.println("Item updated: " + cartRetrieved);
		return true;

	}
	
	public Cart getActiveCart(String userId) throws JsonParseException, JsonMappingException, IOException {
		AmazonDynamoDBClient client = DynamoConnection.getInstance()
				.getClient();
		DynamoDBMapper mapper = new DynamoDBMapper(client);

		// Retrieve the item.
		Cart cartRetrieved = mapper.load(Cart.class, userId, "ACTIVE");
		if(cartRetrieved==null) {
			return null;
		}
		
		convertProductStringSetToProducts(cartRetrieved);
		System.out.println("Cart retrieved: " + cartRetrieved);
		return cartRetrieved;
	}
	
	public boolean setCartInactive(String userId) {
		AmazonDynamoDBClient client = DynamoConnection.getInstance()
				.getClient();
		DynamoDBMapper mapper = new DynamoDBMapper(client);

		// Update the item
		Cart cartRetrieved = mapper.load(Cart.class, userId, "ACTIVE");
		if(cartRetrieved == null) {
			return false;
		}
		mapper.delete(cartRetrieved);
		cartRetrieved.setStatus("INACTIVE "+System.currentTimeMillis());
		mapper.save(cartRetrieved);
		System.out.println("Item updated: " + cartRetrieved);
		return true;
	}

	public List<Cart> getInactiveCarts(String userId) throws JsonParseException, JsonMappingException, IOException {
		AmazonDynamoDBClient client = DynamoConnection.getInstance()
				.getClient();
		DynamoDBMapper mapper = new DynamoDBMapper(client);

		// Update the item
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		Map<String, Condition> scanFilter = new HashMap<String, Condition>();
		Condition scanCondition = new Condition()
		    .withComparisonOperator(ComparisonOperator.EQ.toString())
		    .withAttributeValueList(new AttributeValue().withS(userId));
		scanFilter.put("user_id", scanCondition);
		scanExpression.setScanFilter(scanFilter);
		List<Cart> allCarts = mapper.scan(Cart.class, scanExpression);
		
		if(allCarts == null) {
			return null;
		}
		
		List<Cart> inactiveCarts = new ArrayList<Cart>();
		for(Cart cart : allCarts) {
			if(!cart.getStatus().equals("ACTIVE")) {
				 if(cart.getStatus().contains(" ")) {
					 String dateString =cart.getStatus().split(" ")[1];
					 Date date = new Date(Long.parseLong(dateString));
					 cart.setPurchaseDate(date);
				}
								
				convertProductStringSetToProducts(cart);
				inactiveCarts.add(cart);
				System.out.println("Cart : "+cart);
			}
		}
		return inactiveCarts;
	}

	
	public void convertProductStringSetToProducts(Cart cartRetrieved) throws JsonParseException, JsonMappingException, IOException {
	ObjectMapper jsonMapper = new ObjectMapper();
	Set<Product> productSet = new HashSet<Product>();
	for (String prod : cartRetrieved.getProductList()) {
		Product cartProduct = jsonMapper.readValue(prod, Product.class);
		productSet.add(cartProduct);
	}
	cartRetrieved.setProducts(productSet);
	cartRetrieved.setProductList(null);
	}
}




