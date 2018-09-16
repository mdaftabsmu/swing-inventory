/**
 * Copyright by Nam Ha Minh - a Passionate Java Programmer
 * Websites: http://www.codejava.net and http://namhm.com
 */
package com.inventory.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.inventory.dao.Product;

/**
 * ProductTableModel.java
 * @author Nam Ha Minh
 *
 */
public class ProductTableModel extends AbstractTableModel {

	private static final int COLUMN_ID = 0;
	private static final int COLUMN_NAME = 1;
	private static final int COLUMN_CATEGORY = 2;
	private static final int COLUMN_DESCRIPTION = 3;
	private static final int COLUMN_PRICE = 4;
	
	private static final String[] COLUMN_NAMES = {"ID", "Name", "Category", "Description", "Price"};
	
	private List<Product> listProduct;
	
	public ProductTableModel() {
	}

	public ProductTableModel(List<Product> listProduct) {
		this.listProduct = listProduct;
	}
	
	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}

	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	@Override
	public int getRowCount() {
		return listProduct == null ? 0 : listProduct.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		Product product = listProduct.get(row);
		
		switch (column) {
		case COLUMN_ID:
			return product.getId();
		case COLUMN_NAME:
			return product.getName();
		case COLUMN_CATEGORY:
			return product.getCategory().getName();
		case COLUMN_DESCRIPTION:
			return product.getDescription();
		case COLUMN_PRICE:
			return product.getPrice();
		}
		
		return null;
	}

	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}	
	
	public Product getProductAt(int row) {
		if (row >= 0 && row < listProduct.size()) {
			return listProduct.get(row);
		}
		
		return null;
	}
}
