package edu.sjsu.cmpe282.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;

import edu.sjsu.cmpe282.database.DynamoConnection;
import edu.sjsu.cmpe282.dto.Catalog;

public class CatalogDao {
	public boolean addCatalog(String catalogName, String desc) {
		AmazonDynamoDBClient client = DynamoConnection.getInstance()
				.getClient();

		try {
			Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
			item.put("catalog_name",
					new AttributeValue().withS(catalogName.toLowerCase()));
			item.put("catalog_desc", new AttributeValue().withS(desc));

			PutItemRequest itemRequest = new PutItemRequest().withTableName(
					"catalog").withItem(item);
			client.putItem(itemRequest);
			item.clear();
			System.out.println("Catalog Added");
			return true;

		} catch (AmazonServiceException ase) {
			System.err.println("Failed to create item in table catalog" + ase);
		}
		return false;
	}

	public List<Catalog> viewStore() {
		AmazonDynamoDBClient client = DynamoConnection.getInstance()
				.getClient();
		List<Catalog> catalogArray = new ArrayList<Catalog>();
		ScanRequest scanRequest = new ScanRequest().withTableName("catalog");

		ScanResult result = client.scan(scanRequest);
		for (Map<String, AttributeValue> item : result.getItems()) {
			catalogArray.add(printItem(item));
		}
		return catalogArray;
	}

	private Catalog printItem(
			Map<String, AttributeValue> attributeList) {
		Catalog catalog_entry=null;
		String[] entry = new String[2];
		int i=0;
		for (Map.Entry<String, AttributeValue> item : attributeList.entrySet()) {
			AttributeValue value = item.getValue();
			entry[i] = value.getS();
			i++;
		}	
			catalog_entry = new Catalog(entry[0], entry[1]);
		
		return catalog_entry;
	}

}
