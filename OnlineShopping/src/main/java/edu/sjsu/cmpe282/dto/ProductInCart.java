package edu.sjsu.cmpe282.dto;

public class ProductInCart {
	private int productId;
	private int quantityBought;
	private double price;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantityBought() {
		return quantityBought;
	}
	public void setQuantityBought(int quantityBought) {
		this.quantityBought = quantityBought;
	}
	
	
}
