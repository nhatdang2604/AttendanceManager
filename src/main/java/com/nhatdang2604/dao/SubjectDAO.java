package com.nhatdang2604.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nhatdang2604.model.entity.Subject;
import com.nhatdang2604.utility.HibernateUtil;

public enum SubjectDAO implements ISubjectDAO {
	
	INSTANCE;
	
	private SessionFactory factory;
	
	private SubjectDAO() {
		factory = HibernateUtil.INSTANCE.getSessionFactory();
	}
	

	public Subject createOrUpdateSubject(Subject subject) {
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			session.saveOrUpdate(subject);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
		}
		
		return subject;
	}

	public int deleteSubject(String id) {
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			Subject subject = session.get(Subject.class, id);
			
			if (null != subject) {
				session.delete(subject);
			}
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
		}
	
		return 0;
	}

	public Subject findSubjectById(String id) {
		
		Session session = factory.getCurrentSession();
		Subject subject = null;
		
		try {
			session.beginTransaction();
			
			subject = session.get(Subject.class, id);
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
		}
	
		return subject;
	}

}
