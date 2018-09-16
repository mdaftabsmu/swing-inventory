/**
 * Copyright by Nam Ha Minh - a Passionate Java Programmer
 * Websites: http://www.codejava.net and http://namhm.com
 */
package com.inventory.gui;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;

import com.inventory.business.CategoryBusiness;
import com.inventory.business.ProductBusiness;
import com.inventory.dao.Category;
import com.inventory.dao.GeneralDAO;
import com.inventory.dao.Product;

/**
 * MainWindow.java
 * @author Nam Ha Minh
 *
 */
public class MainWindow extends JFrame {
	private static final String WINDOW_TITLE = "Inventory Management";
	
	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenu menuCategory;
	private JMenu menuProduct;
	private JMenu menuHelp;
	
	private JMenuItem menuItemExit;

	private JMenuItem menuItemListCategories;
	private JMenuItem menuItemNewCategory;
	
	private JMenuItem menuItemListProducts;
	private JMenuItem menuItemNewProduct;
	
	private JMenuItem menuItemHelpContent;
	private JMenuItem menuItemHelpAbout;
	
	private JToolBar toolbar;
	private JButton buttonNewCategory;
	private JButton buttonListCategories;
	private JButton buttonNewProduct;
	private JButton buttonListProducts;
	private JButton buttonHelp;
	private JButton buttonAbout;
	
	private JPanel panel;
	
	private JTabbedPane tabbedPane;
	
	private GeneralDAO dao;
	private CategoryBusiness categoryBusiness;
	private ProductBusiness productBusiness;
	
	private CategoryPanel categoryPanel;
	private ProductPanel productPanel;
	
	private List<Category> listCategory; 
	
	public MainWindow() throws HeadlessException {
		super(WINDOW_TITLE);
		
		setLayout(new GridBagLayout());
		
		createMenus();
		createToolbar();
		createPanel();
		registerEventHandlers();
		
		intializeBusiness();
		listCategories();
		listProducts();
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
	}

	private void createMenus() {
		menuBar = new JMenuBar();
		
		menuFile = new JMenu("File");
		menuItemExit = new JMenuItem("Exit");
		menuFile.add(menuItemExit);
		
		menuCategory = new JMenu("Category");
		menuItemNewCategory = new JMenuItem("New Category");
		menuItemListCategories = new JMenuItem("List Categories");
		
		menuCategory.add(menuItemNewCategory);
		menuCategory.add(menuItemListCategories);
		
		menuProduct = new JMenu("Product");
		menuItemNewProduct = new JMenuItem("New Product");
		menuItemListProducts = new JMenuItem("List Products");
		
		menuProduct.add(menuItemNewProduct);
		menuProduct.add(menuItemListProducts);
		
		menuHelp = new JMenu("Help");
		menuItemHelpContent = new JMenuItem("Help Content");
		menuItemHelpAbout = new JMenuItem("About");
		
		menuHelp.add(menuItemHelpContent);
		menuHelp.add(menuItemHelpAbout);		
		
		menuBar.add(menuFile);
		menuBar.add(menuCategory);
		menuBar.add(menuProduct);
		menuBar.add(menuHelp);
		
		setJMenuBar(menuBar);
	}

	private void createToolbar() {
		toolbar = new JToolBar();
		
		buttonNewCategory = new JButton("New Category");
		buttonNewCategory.setIcon(new ImageIcon(getClass().getResource("/com/inventory/icons/newcat.png")));
		buttonListCategories = new JButton("List Categories");
		buttonListCategories.setIcon(new ImageIcon(getClass().getResource("/com/inventory/icons/listcat.png")));
		
		buttonNewProduct = new JButton("New Product");
		buttonNewProduct.setIcon(new ImageIcon(getClass().getResource("/com/inventory/icons/newprod.png")));
		buttonListProducts = new JButton("List Products");
		buttonListProducts.setIcon(new ImageIcon(getClass().getResource("/com/inventory/icons/listprod.png")));
		
		buttonHelp = new JButton("Help");
		buttonHelp.setIcon(new ImageIcon(getClass().getResource("/com/inventory/icons/help.png")));
		
		buttonAbout = new JButton("About");
		buttonAbout.setIcon(new ImageIcon(getClass().getResource("/com/inventory/icons/about.png")));
		
		toolbar.add(buttonNewCategory);
		toolbar.add(buttonListCategories);
		toolbar.addSeparator();
		toolbar.add(buttonNewProduct);
		toolbar.add(buttonListProducts);
		toolbar.addSeparator();
		toolbar.add(buttonHelp);
		toolbar.add(buttonAbout);
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1.0;
		
		add(toolbar, constraints);
	}
	
	private void createPanel() {
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1.0;		
		constraints.weighty = 1.0;
		
		tabbedPane = new JTabbedPane();
		
		categoryPanel = new CategoryPanel();
		categoryPanel.setMainWindow(this);
		
		tabbedPane.add("Category", categoryPanel);
		
		productPanel = new ProductPanel();
		productPanel.setMainWindow(this);
		
		tabbedPane.add("Product", productPanel);
		
		panel.add(tabbedPane, BorderLayout.CENTER);
		add(panel, constraints);
	}

	private void registerEventHandlers() {
		menuItemExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);
			}
		});
		
		menuItemListCategories.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listCategories();
			}
		});		
		
		menuItemNewCategory.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				createNewCategory();
			}
		});			
		
		menuItemNewProduct.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				createNewProduct();
			}
		});	
		
		menuItemListProducts.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listProducts();
			}
		});		
		
		menuItemHelpContent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showHelp();
			}
		});		
		
		menuItemHelpAbout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showAbout();
			}
		});			
		
		buttonNewCategory.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				createNewCategory();
			}
		});
		
		buttonListCategories.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listCategories();
			}
		});		
		
		buttonNewProduct.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				createNewProduct();
			}
		});	
		
		buttonListProducts.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listProducts();
			}
		});		
		
		buttonHelp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showHelp();
			}
		});			
		
		buttonAbout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showAbout();
			}
		});			
		
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				try {
					dao.close();					
				} catch (Exception ex) {
					String message = "Error when closing database connection.\n"
							+ "The program will terminate anyway.\n"
							+ "Error:\n"
							+ ex.getMessage();
					JOptionPane.showMessageDialog(MainWindow.this, 
							message, "Error", JOptionPane.ERROR_MESSAGE);			
				} finally {
					dispose();
					System.exit(0);
				}
			}
		});
	}

	protected void showAbout() {
		String aboutInfo = "Inventory Project\n"
				+ "Version 1.0.0\n"
				+ "Copyright (C) 2017 by Nam Ha Minh\n"
				+ "All rights reserved.";
		JOptionPane.showMessageDialog(this, aboutInfo, "About", JOptionPane.INFORMATION_MESSAGE);
	}

	protected void showHelp() {
		URI helpWebpage;
		try {
			helpWebpage = new URI("http://codejava.net");
			Desktop.getDesktop().browse(helpWebpage);
		} catch (URISyntaxException | IOException e) {
			JOptionPane.showMessageDialog(this, "Could not load Help content!");
		}
	}

	protected void listProducts() {
		try {
			List<Product> listProduct = productBusiness.listAll();
			productPanel.setListProduct(listProduct);
			tabbedPane.setSelectedIndex(1);
		} catch (Exception ex) {
			String message = "Could not load product list. Error:\n"
					+ ex.getMessage();
			JOptionPane.showMessageDialog(this, 
					message, "Error", JOptionPane.ERROR_MESSAGE);			
		}		
	}

	protected void createNewProduct() {
		ProductForm productForm = new ProductForm(this);
		productForm.setListCategory(listCategory);
		productForm.setVisible(true);
		
		Product product = productForm.getProduct();
		
		if (product != null) {
			try {
				productBusiness.save(product);
				listProducts();
			} catch (Exception ex) {
				String message = "Could not save new product. Error:\n"
						+ ex.getMessage();
				JOptionPane.showMessageDialog(this, 
						message, "Error", JOptionPane.ERROR_MESSAGE);			
			}				
		}
	}
	
	protected void editProduct(Product product) {
		ProductForm productForm = new ProductForm(this);
		productForm.setListCategory(listCategory);
		productForm.setProduct(product);
		productForm.setVisible(true);
		
		Product editedProduct = productForm.getProduct();
		
		if (editedProduct != null) {
			try {
				productBusiness.save(editedProduct);
				listProducts();
			} catch (Exception ex) {
				String message = "Could not update product. Error:\n"
						+ ex.getMessage();
				JOptionPane.showMessageDialog(this, 
						message, "Error", JOptionPane.ERROR_MESSAGE);			
			}				
		}		
	}

	protected void listCategories() {
		try {
			listCategory = categoryBusiness.listAll();
			categoryPanel.setListCategory(listCategory);
			tabbedPane.setSelectedIndex(0);
		} catch (Exception ex) {
			String message = "Could not load category list. Error:\n"
					+ ex.getMessage();
			JOptionPane.showMessageDialog(this, 
					message, "Error", JOptionPane.ERROR_MESSAGE);			
		}
	}

	protected void createNewCategory() {
		
		String categoryName = 
				JOptionPane.showInputDialog(this, "Enter Category Name:");
		
		if (categoryName != null) {		
			try {
				categoryBusiness.save(new Category(categoryName));
				listCategories();
			} catch (Exception ex) {
				String message = "Could not save new category. Error:\n"
						+ ex.getMessage();
				JOptionPane.showMessageDialog(this, 
						message, "Error", JOptionPane.ERROR_MESSAGE);			
			}				
		}
		
	}
	
	protected void editCategory(Category category) {
		
		String categoryName = 
				JOptionPane.showInputDialog(this, "Edit Category Name:", category.getName());
		
		if (categoryName != null) {	
			try {
				category.setName(categoryName);			
				categoryBusiness.save(category);
				listCategories();
			} catch (Exception ex) {
				String message = "Could not update category. Error:\n"
						+ ex.getMessage();
				JOptionPane.showMessageDialog(this, 
						message, "Error", JOptionPane.ERROR_MESSAGE);			
			}				
		}
		
	}	
	
	protected void deleteCategory(Category category) {
		try {
			categoryBusiness.delete(category);
			listCategories();
		} catch (Exception ex) {
			String message = "Could not delete category. Error:\n"
					+ ex.getMessage();
			JOptionPane.showMessageDialog(this, 
					message, "Error", JOptionPane.ERROR_MESSAGE);			
		}			
	}
	
	protected void deleteProduct(Product product) {
		try {
			productBusiness.delete(product);
			listProducts();
		} catch (Exception ex) {
			String message = "Could not delete product. Error:\n"
					+ ex.getMessage();
			JOptionPane.showMessageDialog(this, 
					message, "Error", JOptionPane.ERROR_MESSAGE);			
		}			
	}

	private void intializeBusiness() {
		dao = new GeneralDAO();
		
		try {
			dao.connect();
			categoryBusiness = new CategoryBusiness(dao);
			productBusiness = new ProductBusiness(dao);
		} catch (Exception ex) {
			String message = "Could not connect to the database!";
			JOptionPane.showMessageDialog(this, 
					message, "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}
		
	}
}
