/**
 * Copyright by Nam Ha Minh - a Passionate Java Programmer
 * Websites: http://www.codejava.net and http://namhm.com
 */
package com.inventory.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * GeneralDAOTest.java
 * @author Nam Ha Minh
 *
 */
public class GeneralDAOTest {
	private GeneralDAO dao;
	
	@Before
	public void setUp() throws Exception {
		dao = new GeneralDAO();
		dao.connect();
	}

	@After
	public void tearDown() throws Exception {
		dao.close();
	}

	@Test
	public void testConnect() throws Exception {
		GeneralDAO dao = new GeneralDAO();
		dao.connect();
		dao.close();
	}

	@Test
	public void testSave() throws Exception {
		Category cat = new Category("Electronic");
		long id = dao.save(cat);
		
		assertTrue(id > 0);
	}
	
	@Test
	public void testDelete() throws Exception {
		Category cat = new Category(2);
		dao.delete(cat);
		
		assertTrue(true);
	}
	
	@Test
	public void testList() throws Exception {
		List<Category> list = dao.list(Category.class);
		for (Category cat : list) {
			System.out.println(cat);
		}
		assertTrue(list.size() > 0);
	}
	
	@Test
	public void testGet() {
		long id = 3;
		Category category = dao.get(Category.class, id);
		
		System.out.println(category);
		
		assertNotNull(category);;
	}
}
