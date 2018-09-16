/**
 * Copyright by Nam Ha Minh - a Passionate Java Programmer
 * Websites: http://www.codejava.net and http://namhm.com
 */
package com.inventory.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Product.java
 * @author Nam Ha Minh
 *
 */
@Entity
@Table(name = "PRODUCT")
public class Product {
	private long id;
	private String name;
	private Category category;
	private String description;
	private float price;

	public Product() {
	}

	public Product(String name, Category category, 
			String description, float price) {
		this.name = name;
		this.category = category;
		this.description = description;
		this.price = price;
	}

	@Id
	@Column(name = "PRODUCT_ID")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID")
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Product) {
			Product another = (Product) obj;
			if (this.id == another.id) {
				return true;
			}
		}
		
		return false;
	}
	
	public int hashCode() {
		return new Long(this.id).intValue();
	}
}
