package com.nhatdang2604.utility;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//Using enum to implement singleton pattern, for thread safe
public enum HibernateUtil {

	//Instance of the utility
	INSTANCE;
	
	//Factory for managing session in Hibernate
	private SessionFactory sessionFactory;

	//Getter for factory
	public SessionFactory getSessionFactory() {return sessionFactory;}
	
	//Hibernate configuration file name
	//Note: the file path is /src/main/resources/hibernate.cfg.xml
	private String hibernateConfigFile = "hibernate.cfg.xml";
	
	private HibernateUtil() {
		
		try {
			
			//Build the factory, base on the properties on hibernateConfigFile
			sessionFactory = new Configuration()
					.configure(hibernateConfigFile)
					.buildSessionFactory();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
