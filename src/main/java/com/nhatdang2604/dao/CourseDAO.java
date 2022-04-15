package com.nhatdang2604.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nhatdang2604.model.entity.Course;
import com.nhatdang2604.utility.HibernateUtil;

public enum CourseDAO implements ICourseDAO {
	
	INSTANCE;
	
	private SessionFactory factory;
	
	private CourseDAO() {
		factory = HibernateUtil.INSTANCE.getSessionFactory();
	}

	public Course createOrUpdateCourse(Course course) {
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			session.saveOrUpdate(course);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
		}
		
		return course;
		
	}

	public int deleteCourse(Integer id) {

		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			Course course = session.get(Course.class, id);
			
			if (null !=  course) {
				session.delete(course);
			}
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
		}
	
		return 0;
	}

	public Course findCourseById(Integer id) {
		
		Session session = factory.getCurrentSession();
		Course course = null;
		
		try {
			session.beginTransaction();
			
			course = session.get(Course.class, id);
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
		}
	
		return course;
	}

}
