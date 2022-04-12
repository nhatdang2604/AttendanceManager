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

	//Return the User of the crmUser in the database
	//If the user is existed, return the user, else return null
	public User getUserByCRMUser(CRMUser crmUser) {

		Session session = factory.getCurrentSession();
		
		User user = null;
		
		try {
			session.beginTransaction();
			
			//Parameterize the query
			String param = "username";
			
			//Make the query
			String query = "from " + User.class.getName() + " u where u.username = :" + param;
			
			user = (User) session.createQuery(query)
					.setParameter(param, crmUser.getUsername())
					.getSingleResult();

			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
		}
		
		
		return user;
	}

	public User getUserById(Integer id) {
		
		Session session = factory.getCurrentSession();
		
		User user = null;
		
		try {
			session.beginTransaction();
			
			user = (User) session.get(User.class, id);

			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
		}
		
		return user;
	}
	
	public User createOrUpdateUser(User user) {
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			session.saveOrUpdate(user);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
		}
		
		return user;
	}

	public int deleteUser(Integer id) {
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			User user = session.get(User.class, id);
			
			if (null != user) {
				session.delete(user);
			}
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
		}
	
		return 0;
	}
	
}
