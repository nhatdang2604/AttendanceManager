package com.nhatdang2604.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nhatdang2604.dao.i.IStudentDAO;
import com.nhatdang2604.model.entity.Student;
import com.nhatdang2604.utility.HibernateUtil;

public enum StudentDAO implements IStudentDAO {

	INSTANCE;
	
	private SessionFactory factory;
	
	private StudentDAO() {
		factory = HibernateUtil.INSTANCE.getSessionFactory();
	}

	@Override
	public Student createStudent(Student student) {
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			session.save(student);
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
	public Student updateStudent(Student student) {
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			session.update(student);
			
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
	public Collection<Student> createStudents(Collection<Student> students) {
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			students.forEach(student -> {
				session.save(student);
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
	public Collection<Student> updateStudents(Collection<Student> students) {
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			students.forEach(student -> {
				session.update(student);
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
	public int deleteStudents(List<Integer> ids) {
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			ids.forEach((id) -> {
				Student student = session.get(Student.class, id);
				
				if (null != student) {
					session.delete(student);
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

	@Override
	public List<Student> getAllStudents() {
		Session session = factory.getCurrentSession();
		List<Student> students = new ArrayList<>();
		
		try {
			session.beginTransaction();
			students = session.createQuery("from " + Student.class.getName()).list();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return students;
	}
}
