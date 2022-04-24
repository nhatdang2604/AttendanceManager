package com.nhatdang2604.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nhatdang2604.dao.i.IAttendanceStatusDAO;
import com.nhatdang2604.model.entity.Student;
import com.nhatdang2604.model.entity.StudentAttendanceStatus;
import com.nhatdang2604.utility.HibernateUtil;

//Using enum for singleton pattern
public enum AttendanceStatusDAO implements IAttendanceStatusDAO {

	//Instance of the class
	INSTANCE;
	
	//Hibernate session factory
	private SessionFactory factory;
	
	private AttendanceStatusDAO() {
		
		//Inject the session factory from hibernate utility
		factory = HibernateUtil.INSTANCE.getSessionFactory();
	}

	@Override
	public StudentAttendanceStatus createOrUpdateStatus(StudentAttendanceStatus status) {
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			session.saveOrUpdate(status);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return status;
	}

	@Override
	public List<StudentAttendanceStatus> createOrUpdateStatuses(List<StudentAttendanceStatus> statuses) {
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			statuses.forEach(status -> {
				session.saveOrUpdate(status);
			});
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return statuses;
	}

	@Override
	public Set<Student> getStatusForStudents(Set<Student> students) {
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			students.forEach(student -> {
				session.update(student);
				Hibernate.initialize(student.getStatuses());
				student.getStatuses().forEach(status -> {
					Hibernate.initialize(status.getSubjectWeek());
					Hibernate.initialize(status.getSubjectWeek().getSchedule());
					Hibernate.initialize(status.getSubjectWeek().getSchedule().getCourse());
				});
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
	
	
}
