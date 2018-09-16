/**
 * Copyright by Nam Ha Minh - a Passionate Java Programmer
 * Websites: http://www.codejava.net and http://namhm.com
 */
package com.inventory.gui;

import javax.swing.SwingUtilities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * ProductPanelTest.java
 * @author Nam Ha Minh
 *
 */
public class ProductPanelTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testShowPanel() {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new ProductFrame().setVisible(true);				
			}
		});		
		
		
		try {
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
