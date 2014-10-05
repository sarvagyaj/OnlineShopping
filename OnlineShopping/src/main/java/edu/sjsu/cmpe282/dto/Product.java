package edu.sjsu.cmpe282.dto;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="product")
public class Product {
	private long productID;
	private String prodName;
	private String desc;
	private float price;
	private int quantity;
	private String catalogName;

	public Product() {
	}
	
	public Product( String catalogName,long productID, float price,String desc, String prodName,
			int quantity) {
		this.productID=productID;
		this.prodName=prodName;
		this.desc=desc;
		this.price=price;
		this.quantity = quantity;
		this.catalogName = catalogName;
	}

	@DynamoDBHashKey(attributeName="catalog_name") 
	public String getCatalogName() {
		return catalogName;
	}
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	
	@DynamoDBRangeKey(attributeName="product_id")
	public long getProductID() {
		return productID;
	}
	public void setProductID(long productID) {
		this.productID = productID;
	}

	@DynamoDBAttribute(attributeName="product_name")
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	@DynamoDBAttribute(attributeName="product_desc")
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	@DynamoDBAttribute(attributeName="price")
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}

	@DynamoDBAttribute(attributeName="quantity")
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
