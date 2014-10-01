package edu.sjsu.cmpe282.dto;

public class Catalog {
	private String catalogName;
	private String catalogDesc;
	

	public Catalog(String catalogName, String catalogDesc) {
		super();
		this.catalogName = catalogName;
		this.catalogDesc = catalogDesc;
	}

	public String getCatalogName() {
		return catalogName;
	}
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	public String getCatalogDesc() {
		return catalogDesc;
	}
	public void setCatalogDesc(String catalogDesc) {
		this.catalogDesc = catalogDesc;
	}
	
	
}
