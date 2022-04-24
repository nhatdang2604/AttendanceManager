package com.nhatdang2604.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nhatdang2604.dao.i.ICourseDAO;
import com.nhatdang2604.model.entity.Course;
import com.nhatdang2604.model.entity.Student;
import com.nhatdang2604.utility.HibernateUtil;

public enum CourseDAO implements ICourseDAO {
	
	INSTANCE;
	
	private SessionFactory factory;
	
	private CourseDAO() {
		factory = HibernateUtil.INSTANCE.getSessionFactory();
	}

	public Course createCourse(Course course) {
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			//Save the course + schedule
			Integer id = (Integer) session.save(course);
			course.setId(id);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return course;
		
	}

	public Course updateCourse(Course course) {
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			//Save the course + schedule
			session.update(course);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
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
			session.close();
		}
	
		return 0;
	}

	public Course findCourseById(Integer id) {
		
		Session session = factory.getCurrentSession();
		Course course = null;
		
		try {
			session.beginTransaction();
			
			course = session.get(Course.class, id);
			
			
			Hibernate.initialize(course.getSchedule().getSubjectWeeks());
			Hibernate.initialize(course.getSubject());
			Hibernate.initialize(course.getStudents());
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	
		return course;
	}

	@Override
	public int deleteCourses(List<Integer> ids) {
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			ids.forEach(id -> {
				Course course = session.get(Course.class, id);
				
				if (null !=  course) {
					session.delete(course);
				}
			});
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	
		return 0;
	}

	@Override
	public List<Course> getAllCourses() {
		Session session = factory.getCurrentSession();
		List<Course> courses = new ArrayList<>();
		
		try {
			session.beginTransaction();
			courses = session.createQuery("from " + Course.class.getName()).list();
			
			courses.forEach(course -> {
				Hibernate.initialize(course.getSchedule().getSubjectWeeks());
				Hibernate.initialize(course.getSubject());
				Hibernate.initialize(course.getStudents());
			});
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return courses;
	}

}
