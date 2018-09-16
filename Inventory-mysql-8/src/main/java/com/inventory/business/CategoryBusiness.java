/**
 * Copyright by Nam Ha Minh - a Passionate Java Programmer
 * Websites: http://www.codejava.net and http://namhm.com
 */
package com.inventory.business;

import java.util.List;

import com.inventory.dao.Category;
import com.inventory.dao.GeneralDAO;

/**
 * CategoryBusiness.java
 * @author Nam Ha Minh
 *
 */
public class CategoryBusiness {
	private GeneralDAO dao;
	
	public CategoryBusiness(GeneralDAO dao) {
		this.dao = dao;
	}

	public List<Category> listAll() throws Exception {
		return dao.list(Category.class);
	}
	
	public void save(Category cat) throws Exception {
		dao.save(cat);
	}
	
	public void delete(Category cat) throws Exception {
		dao.delete(cat);
	}
}
