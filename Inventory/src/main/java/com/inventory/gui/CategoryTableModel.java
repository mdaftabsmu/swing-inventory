/**
 * Copyright by Nam Ha Minh - a Passionate Java Programmer
 * Websites: http://www.codejava.net and http://namhm.com
 */
package com.inventory.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.inventory.dao.Category;

/**
 * CategoryTableModel.java
 * @author Nam Ha Minh
 *
 */
public class CategoryTableModel extends AbstractTableModel {
	private static final int COLUMN_ID = 0;
	private static final int COLUMN_NAME = 1;
	
	private static final String[] COLUMN_NAMES = {"ID", "Category Name"};
	
	private List<Category> listCategory;
	
	public CategoryTableModel() {
	}

	public CategoryTableModel(List<Category> listCategory) {
		this.listCategory = listCategory;
	}
	

	public void setListCategory(List<Category> listCategory) {
		this.listCategory = listCategory;
	}

	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	@Override
	public int getRowCount() {
		return listCategory == null ? 0 : listCategory.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		Category category = listCategory.get(row);
		
		if (column == COLUMN_ID) {
			return category.getId();
		} else if (column == COLUMN_NAME) {
			return category.getName();
		}
		
		return null;
	}
	
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}
	
	public Category getCategoryAt(int row) {
		if (row >= 0 && row < listCategory.size()) {
			return listCategory.get(row);
		}
		
		return null;
	}

}
