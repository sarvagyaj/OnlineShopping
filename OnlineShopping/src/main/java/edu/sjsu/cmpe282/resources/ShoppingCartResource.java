package edu.sjsu.cmpe282.resources;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import edu.sjsu.cmpe282.dao.ShoppingCartDao;
import edu.sjsu.cmpe282.dto.Cart;

public class ShoppingCartResource {

	public boolean addProductToCart(String userId, String catalogName,
			int productId, int quantity) {

		ShoppingCartDao cartDao = new ShoppingCartDao();
		boolean productAdded = false;
		try {
			productAdded = cartDao.addProductToCart(userId, catalogName,
					productId, quantity);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (productAdded) {
			return true;
		}
		return false;
	}

	public Cart getShoppingCart(String userId) {
		ShoppingCartDao cartDao = new ShoppingCartDao();
		Cart cart = null;
		try {
			cart = cartDao.getActiveCart(userId);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cart;

	}

	public String[] placeOrder(String userId) {
		String[] result = new String[2];
		ShoppingCartDao cartDao = new ShoppingCartDao();
		if (cartDao.setCartInactive(userId)) {
			result[0] = "/orderPlaced";
			result[1] = "Order Placed Successfully !!";
			return result;
		}
		result[0] = "/orderPlaced";
		result[1] = "There was an error in placing the order. Please try again later.	";
		return result;
	}

	public List<Cart> getCartHistory(String userId) {
		ShoppingCartDao cartDao = new ShoppingCartDao();
		List<Cart> cartList = null;
		try {
			cartList = cartDao.getInactiveCarts(userId);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (cartList == null) {
			return null;
		}
		return cartList;
	
	}

	public boolean removeProductFromCart(String userId, String catalogName,
			int productId, int quantity) {
		ShoppingCartDao cartDao = new ShoppingCartDao();
		boolean productRemoved = false;
		try {
			productRemoved = cartDao.removeProductFromCart(userId, catalogName,
					productId, quantity);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (productRemoved) {
			return true;
		}
		return false;
		
	}
}
