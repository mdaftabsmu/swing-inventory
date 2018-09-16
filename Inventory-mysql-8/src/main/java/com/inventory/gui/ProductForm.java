/**
 * Copyright by Nam Ha Minh - a Passionate Java Programmer
 * Websites: http://www.codejava.net and http://namhm.com
 */
package com.inventory.gui;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.inventory.dao.Category;
import com.inventory.dao.Product;

/**
 * ProductForm.java
 * @author Nam Ha Minh
 *
 */
public class ProductForm extends JDialog {
	private JLabel labelProductName = new JLabel("Product Name:");
	private JLabel labelCategory = new JLabel("Category:");
	private JLabel labelDescription = new JLabel("Description:");
	private JLabel labelPrice = new JLabel("Price:");
	
	private JTextField fieldProductName = new JTextField(20);
	private JTextField fieldDescription = new JTextField(20);
	private JTextField fieldPrice = new JTextField(20);
	
	private JComboBox<Category> comboCategory = new JComboBox<>();
	
	private JButton buttonSave = new JButton("Save");
	private JButton buttonCancel = new JButton("Cancel");
	
	private List<Category> listCategory;
	
	private Product product;

	public ProductForm(Frame owner) {
		super(owner, true);
		setTitle("Create Product");
		
		setLayout(new GridBagLayout());
		
		initComponents();
		registerEventHandlers();
		
		setSize(380, 280);
		setLocationRelativeTo(null);
	}
	
	private void registerEventHandlers() {
		buttonSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (validateFormInput()) {
					dispose();					
				}
				
			}
		});
		
		buttonCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});		
	}

	protected boolean validateFormInput() {
		String productName = fieldProductName.getText();
		String description = fieldDescription.getText();
		String price = fieldPrice.getText();
		Object selectedItem = comboCategory.getSelectedItem();
		float productPrice = 0; 
		
		if (productName.equals("")) {
			JOptionPane.showMessageDialog(this, "Please enter product name!");
			fieldProductName.requestFocus();
			return false;
		} else if (selectedItem == null) {
			JOptionPane.showMessageDialog(this, "The category cannot be blank!");
			return false;			
		} else if (description.equals("")) {
			JOptionPane.showMessageDialog(this, "Please enter description!");
			fieldDescription.requestFocus();
			return false;
		} else if (price.equals("")) {
			JOptionPane.showMessageDialog(this, "Please enter price!");
			fieldPrice.requestFocus();
			return false;
		}
		
		try {
			productPrice = Float.parseFloat(price);
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "The price must be a number!");
			fieldPrice.requestFocus();
			return false;				
		}
		
		if (product == null) {		
			product = new Product();
		}
		
		product.setName(productName);
		product.setDescription(description);
		product.setPrice(productPrice);
		product.setCategory((Category) selectedItem);
		
		return true;
	}

	public void setListCategory(List<Category> listCategory) {
		this.listCategory = listCategory;
		
		for (Category category : listCategory) {
			comboCategory.addItem(category);
		}
	}

	public Product getProduct() {
		return product;
	}	
	
	public void setProduct(Product product) {
		setTitle("Edit Product");
		fieldProductName.setText(product.getName());
		fieldDescription.setText(product.getDescription());
		fieldPrice.setText(String.valueOf(product.getPrice()));
		comboCategory.setSelectedItem(product.getCategory());
		this.product = product;
	}
	
	
	private void initComponents() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridy = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.anchor = GridBagConstraints.WEST;
		
		add(labelProductName, constraints);
		
		constraints.gridx = 1;
		add(fieldProductName, constraints);
		
		constraints.gridy = 1;
		constraints.gridx = 0;		
		add(labelCategory, constraints);
		
		constraints.gridx = 1;
		add(comboCategory, constraints);

		constraints.gridy = 2;
		constraints.gridx = 0;		
		add(labelDescription, constraints);
		
		constraints.gridx = 1;
		add(fieldDescription, constraints);		
		
		constraints.gridy = 3;
		constraints.gridx = 0;		
		add(labelPrice, constraints);
		
		constraints.gridx = 1;
		add(fieldPrice, constraints);		
		
		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelButtons.add(buttonSave);
		panelButtons.add(buttonCancel);
		
		constraints.gridy = 4;
		constraints.gridx = 0;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.CENTER;
		
		add(panelButtons, constraints);		
		
	}
}