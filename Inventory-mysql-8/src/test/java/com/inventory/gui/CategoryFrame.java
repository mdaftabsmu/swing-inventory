/**
 * Copyright by Nam Ha Minh - a Passionate Java Programmer
 * Websites: http://www.codejava.net and http://namhm.com
 */
package com.inventory.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * CategoryFrame.java
 * @author Nam Ha Minh
 *
 */
public class CategoryFrame extends JFrame {
	public CategoryFrame() {
		super("Test Category Panel");
		
		setLayout(new BorderLayout());
		
		add(new CategoryPanel(), BorderLayout.CENTER);
		
		setSize(800, 700);
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
