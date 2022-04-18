package com.nhatdang2604.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nhatdang2604.model.entity.SubjectWeek;
import com.nhatdang2604.utility.HibernateUtil;

public enum SubjectWeekDAO implements ISubjectWeekDAO {

	INSTANCE;
	
	private SessionFactory factory;
	
	private SubjectWeekDAO() {
		factory = HibernateUtil.INSTANCE.getSessionFactory();
	}

	public SubjectWeek createOrUpdateSubjectWeek(SubjectWeek week) {
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			session.saveOrUpdate(week);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return week;	
	}

	public List<SubjectWeek> createOrUpdateSubjectWeeks(List<SubjectWeek> weeks) {
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			
			weeks.forEach(week -> {
				session.saveOrUpdate(week);
			});
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return weeks;
	}

	public int deleteSubjectWeek(Integer courseId, Integer weekIndex) {
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			String[] params = {"course_id", "week_index"};
			String query = 
					"from " + SubjectWeek.class.getName()
					+ " week where week.course.id = :" + params[0]
					+ " and week.weekIndex = :" + params[1];
			
			SubjectWeek week = (SubjectWeek) session
					.createQuery(query)
					.setParameter(params[0], courseId)
					.setParameter(params[1], weekIndex)
					.setMaxResults(1)
					.getSingleResult();
			
			if (null != week) {
				session.delete(week);
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

	public SubjectWeek findSubjectWeekById(Integer courseId, Integer weekIndex) {
		Session session = factory.getCurrentSession();
		SubjectWeek week = null;
		
		try {
			session.beginTransaction();
			
			String[] params = {"course_id", "week_index"};
			String query = 
					"from " + SubjectWeek.class.getName()
					+ " week where week.course.id = :" + params[0]
					+ " and week.weekIndex = :" + params[1];
			
			week = (SubjectWeek) session
					.createQuery(query)
					.setParameter(params[0], courseId)
					.setParameter(params[1], weekIndex)
					.setMaxResults(1)
					.getSingleResult();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return week;
	}
	
	
}
