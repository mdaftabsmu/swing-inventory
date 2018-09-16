/**
 * Copyright by Nam Ha Minh - a Passionate Java Programmer
 * Websites: http://www.codejava.net and http://namhm.com
 */
package com.inventory.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * GeneralDAO.java
 * @author Nam Ha Minh
 *
 */
public class GeneralDAO {
	private Session session;
	private SessionFactory sessionFactory;
	
	public void connect() throws Exception {
		StandardServiceRegistry registry
			= new StandardServiceRegistryBuilder().configure().build();
		
		try {
			sessionFactory
				= new MetadataSources(registry).buildMetadata().buildSessionFactory();
			
			session = sessionFactory.openSession();
		} catch (Exception ex) {
			StandardServiceRegistryBuilder.destroy(registry);
			ex.printStackTrace();
			throw ex;
		}
	}
	
	public void close() throws Exception {
		if (session != null) {
			session.close();
			sessionFactory.close();
		}
	}

	public long save(Object obj) throws Exception {
		session.beginTransaction();
		Long id = (Long) session.save(obj);
		session.getTransaction().commit();
		
		return id;
	}
	
	public void delete(Object obj) throws Exception {
		session.beginTransaction();
		session.delete(obj);
		session.getTransaction().commit();
	}
	
	public <T> List<T> list(Class<T> type) throws Exception {
		List<T> result = 
				session.createCriteria(type)
					.setResultTransformer(
							Criteria.DISTINCT_ROOT_ENTITY).list();
		return result;
	}
	
	public <T> T get(Class<T> type, Serializable id) {		
		return (T) session.get(type, id);
	}
	
}
