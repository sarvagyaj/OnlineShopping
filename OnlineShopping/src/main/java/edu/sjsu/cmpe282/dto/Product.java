package edu.sjsu.cmpe282.dto;



public class Product {
	private int productID;
	private String prodName;
	private String desc;
	private float price;
	private int quantity;
	private String catalogName;

	public Product() {
	}
	
	public Product( String catalogName,int productID, float price,String desc, String prodName,
			int quantity) {
		this.productID=productID;
		this.prodName=prodName;
		this.desc=desc;
		this.price=price;
		this.quantity = quantity;
		this.catalogName = catalogName;
	}

	
	public String getCatalogName() {
		return catalogName;
	}
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	
	
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
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
