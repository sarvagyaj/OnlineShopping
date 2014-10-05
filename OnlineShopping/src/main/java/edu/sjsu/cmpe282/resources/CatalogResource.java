package edu.sjsu.cmpe282.resources;

import java.util.List;
import java.util.Map;

import edu.sjsu.cmpe282.dao.CatalogDao;
import edu.sjsu.cmpe282.dto.Catalog;

public class CatalogResource {
	public String[] addCatalog(String catalogName, String desc) {
		String[] result = new String[2];
		CatalogDao catalogDao = new CatalogDao();
		if(catalogDao.addCatalog(catalogName, desc)) {
			result[0] = "/addCatalog";
			result[1]="Catalog Added Succesfully !!";
			return result;
		}
		result[0]="/addCatalog";
		result[1]="Sorry. Error in adding catalog.";
		return result;
		
	}
	
	public List<Catalog> getAllCatalogs() {
		CatalogDao catalogDao = new CatalogDao();
		List<Catalog> catalog_entry=catalogDao.viewStore();
		return catalog_entry;
	}
}
