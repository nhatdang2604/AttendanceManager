package com.nhatdang2604.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nhatdang2604.model.crm.CRMUser;
import com.nhatdang2604.model.entity.User;
import com.nhatdang2604.utility.HashingUtil;
import com.nhatdang2604.utility.HibernateUtil;

//Using enum for singleton pattern
public enum UserDAO implements IUserDAO {

	//Instance of the class
	INSTANCE;
	
	//Hibernate session factory
	private SessionFactory factory;
	
	private UserDAO() {
		
		//Inject the session factory from hibernate utility
		factory = HibernateUtil.INSTANCE.getSessionFactory();
	}
	
	//Helper to compare a CRMUser and User
	private boolean isTheSameUser(CRMUser crmUser, User user) {
		
		//Compare the username
		boolean isTheSameUsername = user.getUsername().equals(crmUser.getUsername());
		
		//Return false if the username is different
		if (!isTheSameUsername) return false;
		
		//Compare the password hashing
		boolean isTheSamePassword = user.getEncryptedPassword().equals(				//compare the user password with the
				HashingUtil.passwordEncryption(crmUser.getUnencryptedPassword()));	//hashing of the crmUser password
		
		return isTheSamePassword;
	}
	
	//Return true if the crmUser is login successfully, else return false
	public boolean doesLoginSuccessfully(CRMUser crmUser) {
		
		//Get the current session from hibernate
		Session session = factory.getCurrentSession();
		
		return false;
	}

	//Return the User of the crmUser in the database
	//If the user is existed, return the user, else return null
	public User authenticated(CRMUser crmUser) {

		return null;
	}
	
}
