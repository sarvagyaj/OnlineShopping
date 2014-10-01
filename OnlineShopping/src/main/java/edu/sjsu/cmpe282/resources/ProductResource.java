package edu.sjsu.cmpe282.resources;

import edu.sjsu.cmpe282.dao.ProductDao;



public class ProductResource {

	public String[] addProduct(String catalogName, String prodname, String desc, double price, int quantity) {
		String[] result = new String[2];
		ProductDao prodDao = new ProductDao();
		if(prodDao.addProduct(catalogName, prodname, desc, price, quantity)) {
			result[0] = "/addProduct";
			result[1]="Product Added Succesfully !!";
			return result;
		}
		result[0]="/addProduct";
		result[1]="Sorry. Error in adding product.";
		return result;
		
	}
	
		
}
