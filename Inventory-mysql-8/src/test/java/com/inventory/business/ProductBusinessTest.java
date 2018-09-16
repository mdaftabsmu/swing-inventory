/**
 * Copyright by Nam Ha Minh - a Passionate Java Programmer
 * Websites: http://www.codejava.net and http://namhm.com
 */
package com.inventory.business;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.inventory.dao.Category;
import com.inventory.dao.GeneralDAO;
import com.inventory.dao.Product;

/**
 * ProductBusinessTest.java
 * @author Nam Ha Minh
 *
 */
public class ProductBusinessTest {
	private GeneralDAO dao;
	private ProductBusiness business;
	
	@Before
	public void setUp() throws Exception {
		dao = new GeneralDAO();
		dao.connect();
		business = new ProductBusiness(dao);
	}

	@After
	public void tearDown() throws Exception {
		dao.close();
	}

	@Test
	public void testListAll() throws Exception {
		List<Product> listProduct = business.listAll();
		
		assertFalse(listProduct.isEmpty());
	}

	@Test
	public void testSave() throws Exception {
		List<Product> listProduct = business.listAll();
		int sizeBefore = listProduct.size();
		
		Product newProduct = new Product();
		
		newProduct.setName("Vitamin C");
		newProduct.setDescription("Supply the Vitamin C your body needs");
		newProduct.setPrice(25.0f);
		
		Category catBook = new Category(4, "Health Care");
		newProduct.setCategory(catBook);
		
		business.save(newProduct);
		
		listProduct = business.listAll();
		int sizeAfter = listProduct.size();
		
		assertTrue(sizeAfter == sizeBefore + 1);
	}

	@Test
	public void testDelete() throws Exception {
		Product productToDelete = new Product();
		
		productToDelete.setId(4);	
		
		business.delete(productToDelete);
		
		List<Product> listProduct = business.listAll();
		
		assertFalse(listProduct.contains(productToDelete));
	}

}
