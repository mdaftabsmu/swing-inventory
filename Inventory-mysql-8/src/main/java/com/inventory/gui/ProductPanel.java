/**
 * Copyright by Nam Ha Minh - a Passionate Java Programmer
 * Websites: http://www.codejava.net and http://namhm.com
 */
package com.inventory.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.inventory.dao.Category;
import com.inventory.dao.Product;

/**
 * ProductPanel.java
 * @author Nam Ha Minh
 *
 */
public class ProductPanel extends JPanel {
	private JTable table = new JTable();
	private ProductTableModel tableModel;
	
	private JButton buttonNew = new JButton("New");
	private JButton buttonDelete = new JButton("Delete");
	private JButton buttonEdit = new JButton("Edit");
	private JButton buttonRefresh = new JButton("Refresh");
	
	private List<Product> listProduct = new ArrayList<>();
	
	private MainWindow mainWindow;
	
	public ProductPanel() {
		initComponents();
		registerEventHandlers();
	}

	public void setMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}	
	
	public void setListProduct(List<Product> listProduct) {
		tableModel.setListProduct(listProduct);
		tableModel.fireTableDataChanged();
	}

	private void initComponents() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 5.0;		
		constraints.weighty = 1.0;
		constraints.insets = new Insets(10, 10, 10, 10);
				
		tableModel = new ProductTableModel();
		
		table.setModel(tableModel);
		add(new JScrollPane(table), constraints);
		
		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 30));
		panelButtons.setPreferredSize(new Dimension(150, 100));
		
		buttonNew.setIcon(new ImageIcon(getClass().getResource("/com/inventory/icons/add.png")));
		buttonEdit.setIcon(new ImageIcon(getClass().getResource("/com/inventory/icons/edit.png")));
		buttonDelete.setIcon(new ImageIcon(getClass().getResource("/com/inventory/icons/delete.png")));
		buttonRefresh.setIcon(new ImageIcon(getClass().getResource("/com/inventory/icons/refresh.png")));
		
		panelButtons.add(buttonNew);
		panelButtons.add(buttonEdit);
		panelButtons.add(buttonDelete);
		panelButtons.add(buttonRefresh);
		
		constraints.gridx = 1;
		constraints.fill = GridBagConstraints.VERTICAL;
		constraints.weightx = 0.0;		
		constraints.weighty = 1.0;		

		add(panelButtons, constraints);
	}

	private void registerEventHandlers() {
		buttonNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainWindow.createNewProduct();				
			}
		});
		
		buttonEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				editProduct();				
			}
		});		
		
		buttonDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteProduct();				
			}
		});		
		
		buttonRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainWindow.listProducts();				
			}
		});		
	}

	protected void editProduct() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0) {
			Product productToEdit = tableModel.getProductAt(selectedRow);
			
			if (productToEdit != null) {
				mainWindow.editProduct(productToEdit);
			}
		}		
	}

	protected void deleteProduct() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0) {
			Product productToDelete = tableModel.getProductAt(selectedRow);
			
			if (productToDelete != null) {
				String message = String.format(
						"Do you really want to delete '%s'?", productToDelete.getName()); 
				int answer = JOptionPane.showConfirmDialog(mainWindow, 
								message, "Confirmation", JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.OK_OPTION) {
					mainWindow.deleteProduct(productToDelete);
				}				
			}
		}
	}
}
