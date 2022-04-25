package com.nhatdang2604.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nhatdang2604.dao.i.IUserDAO;
import com.nhatdang2604.model.entity.User;
import com.nhatdang2604.model.formModel.LoginFormModel;
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
	public User getUserByUserLoginModel(LoginFormModel crmUser) {

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
					.setMaxResults(1)
					.stream()
					.findFirst()
					.orElse(null);
			
			if (null != user) {
				Hibernate.initialize(user.getUserInformation());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		
		return user;
	}

	public User findUserById(Integer id) {
		
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
			session.close();
		}
		
		return user;
	}
	
	public User createUser(User user) {
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			Integer id = (Integer) session.save(user);
			user = session.get(User.class, id);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return user;
	}

	public User updateUser(User user) {
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			session.update(user);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
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
			session.close();
		}
	
		return 0;
	}
	
}
