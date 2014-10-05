package edu.sjsu.cmpe282.resources;

import java.util.ArrayList;
import java.util.List;

import edu.sjsu.cmpe282.dao.CatalogDao;
import edu.sjsu.cmpe282.dao.ProductDao;
import edu.sjsu.cmpe282.dto.Catalog;
import edu.sjsu.cmpe282.dto.Product;



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
	
	public List<Product> viewProducts(String catalogName) {
		ProductDao productDao = new ProductDao();
		List<Product> productList = productDao.getProducts(catalogName);
		return productList;
		
	}
	
		
}
