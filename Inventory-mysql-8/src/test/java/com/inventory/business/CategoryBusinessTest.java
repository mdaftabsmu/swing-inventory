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

/**
 * CategoryBusinessTest.java
 * @author Nam Ha Minh
 *
 */
public class CategoryBusinessTest {
	private GeneralDAO dao;
	private CategoryBusiness business;
	
	@Before
	public void setUp() throws Exception {
		dao = new GeneralDAO();
		dao.connect();
		business = new CategoryBusiness(dao);
	}

	@After
	public void tearDown() throws Exception {
		dao.close();
	}

	@Test
	public void testListAll() throws Exception {
		List<Category> listCategory = business.listAll();
		
		assertFalse(listCategory.isEmpty());
	}

	@Test
	public void testSave() throws Exception {
		Category newCat = new Category("Books");
		
		List<Category> listCategory = business.listAll();
		int sizeBefore = listCategory.size();
		
		business.save(newCat);
		
		listCategory = business.listAll();
		int sizeAfter = listCategory.size();
		
		assertTrue(sizeAfter == sizeBefore + 1);
	}

	@Test
	public void testDelete() throws Exception {
		Category catToDelete = new Category(1, "Books");
		
		business.delete(catToDelete);
		
		List<Category> listCategory = business.listAll();
	
		assertFalse(listCategory.contains(catToDelete));
	}

}
