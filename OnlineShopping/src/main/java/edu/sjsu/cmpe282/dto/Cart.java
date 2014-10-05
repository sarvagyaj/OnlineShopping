package edu.sjsu.cmpe282.dto;

import java.util.Set;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="shopping_cart")
public class Cart {
	  private String userId;
      private String status;
      private double totalAmtCharged;
      private Set<String> productList;			//list of prod ids in one purchase
           
    @DynamoDBHashKey(attributeName="user_id") 
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@DynamoDBRangeKey(attributeName="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@DynamoDBAttribute(attributeName="total_amt")
	public double getTotalAmtCharged() {
		return totalAmtCharged;
	}
	public void setTotalAmtCharged(double totalAmtCharged) {
		this.totalAmtCharged = totalAmtCharged;
	}
	
	@DynamoDBAttribute(attributeName="product_list")
	public Set<String> getProdcutList() {
		return productList;
	}
	public void setProdcutList(Set<String> prodcutList) {
		this.productList = prodcutList;
	}
	

	public String toString(){
		return "Cart [userid ="+userId+", status= "+status+ ", product's ids = "+productList+"]";
	}

      
      
      
}
