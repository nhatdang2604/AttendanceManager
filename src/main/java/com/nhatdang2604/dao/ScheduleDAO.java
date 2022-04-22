package com.nhatdang2604.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nhatdang2604.dao.i.IScheduleDAO;
import com.nhatdang2604.model.entity.Schedule;
import com.nhatdang2604.model.entity.SubjectWeek;
import com.nhatdang2604.utility.HibernateUtil;

public enum ScheduleDAO implements IScheduleDAO {

	INSTANCE;
	
	private SessionFactory factory;
	
	private ScheduleDAO() {
		factory = HibernateUtil.INSTANCE.getSessionFactory();
	}

	public Schedule createOrUpdateSchedule(Schedule schedule) {
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			session.saveOrUpdate(schedule);
			
			//Loading SubjectWeek collections before closing session
			Hibernate.initialize(schedule.getSubjectWeeks());
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return schedule;
	}

	public Schedule findScheduleById(Integer id) {
		
		Session session = factory.getCurrentSession();
		
		Schedule result = null;
		
		try {
			session.beginTransaction();
			
			result = session.get(Schedule.class, id);
			
			//Loading SubjectWeek collections before closing session
			Hibernate.initialize(result.getSubjectWeeks());
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return result;
	}

	public int deleteSchedule(Integer id) {
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			Schedule schedule = session.get(Schedule.class, id);
			
			if (null != schedule) {
				session.delete(schedule);
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
