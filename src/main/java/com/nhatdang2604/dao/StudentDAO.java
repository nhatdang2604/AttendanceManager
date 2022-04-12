package com.nhatdang2604.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nhatdang2604.model.entity.Student;
import com.nhatdang2604.model.entity.User;
import com.nhatdang2604.utility.HibernateUtil;

public enum StudentDAO implements IStudentDAO {

	INSTANCE;
	
	private SessionFactory factory;
	
	private StudentDAO() {
		factory = HibernateUtil.INSTANCE.getSessionFactory();
	}
	
	public Student getStudentFromUser(User user) {
		
		Session session = factory.getCurrentSession();
		
		Student student = null;
		
		try {
			session.beginTransaction();
			
			student = session.get(Student.class, user.getId());
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
		}
		
		return student;
	}
}
