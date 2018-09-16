/**
 * Copyright by Nam Ha Minh - a Passionate Java Programmer
 * Websites: http://www.codejava.net and http://namhm.com
 */
package com.inventory.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * ProductFrame.java
 * @author Nam Ha Minh
 *
 */
public class ProductFrame extends JFrame {
	public ProductFrame() {
		super("Test Category Panel");
		
		setLayout(new BorderLayout());
		
		add(new ProductPanel(), BorderLayout.CENTER);
		
		setSize(800, 700);
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
