package edu.sjsu.cmpe282.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;

import edu.sjsu.cmpe282.database.DynamoConnection;
import edu.sjsu.cmpe282.dto.Product;

public class ProductDao {

	public boolean addProduct(String catalogName, String prodname, String desc,
			double price, int quantity) {
		AmazonDynamoDBClient client = DynamoConnection.getInstance()
				.getClient();

		try {
			//AtomicInteger productId
			
			Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();

			item.put("catalog_name",
					new AttributeValue().withS(catalogName.toLowerCase()));
			item.put("product_id", new AttributeValue().withN("102"));
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

	public Product getProduct(String catalogName, int productId) {
		AmazonDynamoDBClient client = DynamoConnection.getInstance()
				.getClient();
		List<String> attributesToGet = new ArrayList<String>(Arrays.asList(
				"catalog_name", "product_id", "price", "prod_desc",
				"product_name", "quantity"));

		HashMap<String, AttributeValue> key = new HashMap<String, AttributeValue>();
		key.put("catalog_name", new AttributeValue().withS(catalogName));
		key.put("product_id",
				new AttributeValue().withN(Integer.toString(productId)));

		GetItemRequest getItemRequest = new GetItemRequest()
				.withTableName("product").withKey(key)
				.withAttributesToGet(attributesToGet).withConsistentRead(true);

		GetItemResult result = client.getItem(getItemRequest);
		Map<String, AttributeValue> map = result.getItem();
		Product product = printItem(map);
		return product;
	}

	public List<Product> getProducts(String catalogName) {
		AmazonDynamoDBClient client = DynamoConnection.getInstance()
				.getClient();
		List<Product> productArray = new ArrayList<Product>();

		Condition hashKeyCondition = new Condition().withComparisonOperator(
				ComparisonOperator.EQ.toString()).withAttributeValueList(
				new AttributeValue().withS(catalogName));

		Map<String, Condition> keyConditions = new HashMap<String, Condition>();
		keyConditions.put("catalog_name", hashKeyCondition);

		QueryRequest queryRequest = new QueryRequest()
				.withTableName("product")
				.withKeyConditions(keyConditions)
				.withAttributesToGet(
						Arrays.asList("catalog_name", "product_id", "price",
								"prod_desc", "product_name", "quantity"));

		QueryResult result = client.query(queryRequest);
		for (Map<String, AttributeValue> item : result.getItems()) {
			Product product = printItem(item);
			productArray.add(product);
		}
		return productArray;
	}

	public Product getProduct_notworks(int productId) {
		AmazonDynamoDBClient client = DynamoConnection.getInstance()
				.getClient();
		Product product = new Product();

		Condition rangeKeyCondition = new Condition().withComparisonOperator(
				ComparisonOperator.EQ.toString()).withAttributeValueList(
				new AttributeValue().withN(Integer.toString(productId)));

		Map<String, Condition> keyConditions = new HashMap<String, Condition>();
		keyConditions.put("product_id", rangeKeyCondition);

		QueryRequest queryRequest = new QueryRequest()
				.withTableName("product")
				.withKeyConditions(keyConditions)
				.withAttributesToGet(
						Arrays.asList("catalog_name", "product_id", "price",
								"prod_desc", "product_name", "quantity"));

		QueryResult result = client.query(queryRequest);
		for (Map<String, AttributeValue> item : result.getItems()) {
			product = printItem(item);
		}
		return product;
	}

	private Product printItem(Map<String, AttributeValue> attributeList) {
		Product productEntry = null;

		Map<String, String> map = new HashMap<String, String>();
		for (Map.Entry<String, AttributeValue> item : attributeList.entrySet()) {
			String attributeName = item.getKey();
			AttributeValue value = item.getValue();
			String string = value.getS() == null ? value.getN() : value.getS();
			map.put(attributeName, string);
		}
		productEntry = new Product(map.get("catalog_name").toString(),
				Integer.parseInt(map.get("product_id")), Float.parseFloat(map
						.get("price")), map.get("prod_desc").toString(), map
						.get("product_name").toString(), Integer.parseInt(map
						.get("quantity")));

		return productEntry;
	}

}
