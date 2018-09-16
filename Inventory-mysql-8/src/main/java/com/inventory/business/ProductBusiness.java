/**
 * Copyright by Nam Ha Minh - a Passionate Java Programmer
 * Websites: http://www.codejava.net and http://namhm.com
 */
package com.inventory.business;

import java.util.List;

import com.inventory.dao.GeneralDAO;
import com.inventory.dao.Product;

/**
 * ProductBusiness.java
 * @author Nam Ha Minh
 *
 */
public class ProductBusiness {
	private GeneralDAO dao;
	
	public ProductBusiness(GeneralDAO dao) {
		this.dao = dao;
	}

	public List<Product> listAll() throws Exception {
		return dao.list(Product.class);
	}
	
	public void save(Product prod) throws Exception {
		dao.save(prod);
	}
	
	public void delete(Product prod) throws Exception {
		dao.delete(prod);
	}
}
