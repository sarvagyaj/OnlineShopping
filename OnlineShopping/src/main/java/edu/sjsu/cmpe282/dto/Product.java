package edu.sjsu.cmpe282.dto;

public class Product {
	private String productID;
	private String prodName;
	private String desc;
	private float price;
	private int quantity;

	public Product(String productID, String prodName, String desc, float price,
			int quantity) {
		this.productID=productID;
		this.prodName=prodName;
		this.desc=desc;
		this.price=price;
		this.quantity = quantity;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
