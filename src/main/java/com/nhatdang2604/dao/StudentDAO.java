package com.nhatdang2604.dao;

import java.util.Collection;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nhatdang2604.model.entity.BaseUserRole;
import com.nhatdang2604.model.entity.Student;
import com.nhatdang2604.utility.HibernateUtil;

public enum StudentDAO implements IStudentDAO {

	INSTANCE;
	
	private SessionFactory factory;
	
	private StudentDAO() {
		factory = HibernateUtil.INSTANCE.getSessionFactory();
	}

	@Override
	public Student createOrUpdateStudent(Student student) {
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			session.saveOrUpdate(student);
			Hibernate.initialize(student.getStatuses());
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return student;

	}

	@Override
	public Collection<Student> createOrUpdateStudents(Collection<Student> students) {
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			students.forEach(student -> {
				session.saveOrUpdate(student);
				Hibernate.initialize(student.getStatuses());
			});
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return students;

	}

	@Override
	public int deleteStudent(Integer id) {
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			Student student = session.get(Student.class, id);
			
			if (null != student) {
				session.delete(student);
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

	@Override
	public Student findStudentById(Integer id) {
		Session session = factory.getCurrentSession();
		
		Student student = null;
		
		try {
			session.beginTransaction();
			
			student = session.get(Student.class, id);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return student;
	}
}
