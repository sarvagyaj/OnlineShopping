package edu.sjsu.cmpe282.resources;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import edu.sjsu.cmpe282.dao.ShoppingCartDao;

public class ShoppingCartResource {

	public boolean addProductToCart(String userId, String catalogName,  int productId, int quantity) {
		
		ShoppingCartDao cartDao = new ShoppingCartDao();
		boolean productAdded= false;
		try {
			productAdded = cartDao.addProductToCart(userId, catalogName, productId, quantity);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(productAdded) {
			return true;
		}
		return false;
	}

}
