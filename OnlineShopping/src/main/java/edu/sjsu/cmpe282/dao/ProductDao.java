package edu.sjsu.cmpe282.dao;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;

import edu.sjsu.cmpe282.database.DynamoConnection;

public class ProductDao {

	public boolean addProduct(String catalogName, String prodname, String desc,
			double price, int quantity) {
		AmazonDynamoDBClient client = DynamoConnection.getInstance()
				.getClient();

		try {
			Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();

			item.put("catalog_name", new AttributeValue().withS(catalogName));
			item.put("product_id", new AttributeValue().withS("105"));
			item.put("product_name", new AttributeValue().withS(prodname));
			item.put("prod_desc", new AttributeValue().withS(desc));
			item.put("price",
					new AttributeValue().withN(Double.toString(price)));
			item.put("quantity",
					new AttributeValue().withN(Integer.toString(quantity)));
			

			PutItemRequest itemRequest = new PutItemRequest().withTableName(
					"product").withItem(item);
			client.putItem(itemRequest);
			item.clear();
			System.out.println("Product Added");
			return true;

		} catch (AmazonServiceException ase) {
			System.err.println("Failed to create item in table product" + ase);
		}
		return false;
	}

}
