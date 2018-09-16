/**
 * Copyright by Nam Ha Minh - a Passionate Java Programmer
 * Websites: http://www.codejava.net and http://namhm.com
 */
package com.inventory.dao;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Category.java
 * @author Nam Ha Minh
 *
 */
@Entity
@Table(name = "category")
public class Category {
	private long id;
	private String name;
	
	private Set<Product> products;

	public Category() {
	}

	public Category(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Category(long id) {
		this.id = id;
	}

	public Category(String name) {
		this.name = name;
	}

	@Id
	@Column(name = "category_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@Override
	public String toString() {
		return name;
	}

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
	

}
