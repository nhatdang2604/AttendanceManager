package com.nhatdang2604.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nhatdang2604.dao.i.IMinistryDAO;
import com.nhatdang2604.model.entity.Ministry;
import com.nhatdang2604.model.entity.User;
import com.nhatdang2604.utility.HibernateUtil;

public enum MinistryDAO implements IMinistryDAO {

	INSTANCE;
	
	private SessionFactory factory;
	
	private MinistryDAO() {
		factory = HibernateUtil.INSTANCE.getSessionFactory();
	}
	
	public Ministry getMinistryFromUser(User user) {
		
		Session session = factory.getCurrentSession();
		
		Ministry ministry = null;
		
		try {
			session.beginTransaction();
			
			ministry = session.get(Ministry.class, user.getId());
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return ministry;
	}
}
