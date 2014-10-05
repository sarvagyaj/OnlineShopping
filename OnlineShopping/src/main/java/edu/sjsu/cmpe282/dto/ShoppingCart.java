package edu.sjsu.cmpe282.dto;

import java.util.Date;
import java.util.List;

public class ShoppingCart {
	private String userId;
	public enum Status {
		ACTIVE("active"), INACTIVE("inactive");
		
		private String value;
		
		private Status(String v){
			value= v;
		}
		
		public String getStatusCode() {
			return value;
		}
	};
	
	private Date purchaseDate;
	private List<String> productIdList;
	private double totalAmountCharged;
	private Status status=Status.ACTIVE;
	


	public ShoppingCart(String userId, Date purchaseDate,
			List<String> productIdList, double totalAmountCharged) {
		super();
		this.userId = userId;
		this.purchaseDate = purchaseDate;
		this.productIdList = productIdList;
		this.totalAmountCharged = totalAmountCharged;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public List<String> getProductIdList() {
		return productIdList;
	}

	public void setProductIdList(List<String> productIdList) {
		this.productIdList = productIdList;
	}

	public double getTotalAmountCharged() {
		return totalAmountCharged;
	}

	public void setTotalAmountCharged(double totalAmountCharged) {
		this.totalAmountCharged = totalAmountCharged;
	}
	
}
