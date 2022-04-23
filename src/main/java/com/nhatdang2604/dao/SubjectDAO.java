package com.nhatdang2604.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nhatdang2604.dao.i.ISubjectDAO;
import com.nhatdang2604.model.entity.Student;
import com.nhatdang2604.model.entity.Subject;
import com.nhatdang2604.utility.HibernateUtil;

public enum SubjectDAO implements ISubjectDAO {
	
	INSTANCE;
	
	private SessionFactory factory;
	
	private SubjectDAO() {
		factory = HibernateUtil.INSTANCE.getSessionFactory();
	}
	

	public Subject createSubject(Subject subject) {
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			Integer id = (Integer) session.save(subject);
			subject.setId(id);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return subject;
	}

	public Subject updateSubject(Subject subject) {
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			session.update(subject);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return subject;
	}
	
	public int deleteSubject(Integer id) {
		
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
			session.close();
		}
	
		return 0;
	}

	public Subject findSubjectById(Integer id) {
		
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
			session.close();
		}
	
		return subject;
	}


	@Override
	public List<Subject> getAllSubjects() {
		Session session = factory.getCurrentSession();
		List<Subject> subjects = new ArrayList<>();
		
		try {
			session.beginTransaction();
			subjects = session.createQuery("from " + Subject.class.getName()).list();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return subjects;
	}


	@Override
	public Subject findSubjectBySubjectId(String subjectId) {
		Session session = factory.getCurrentSession();
		Subject subject = null;
		
		try {
			session.beginTransaction();
			String param = "name";
			String query = "from " + Subject.class.getName() + " s where s.subjectId = :" + param;
			
			subject = (Subject) session
					.createQuery(query)
					.setParameter(param, subjectId)
					.stream()
					.findFirst()
					.orElse(null);
					
	
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return subject;
	}


	@Override
	public int deleteSubjects(List<Integer> ids) {
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			ids.forEach(id -> {
				Subject subject = session.get(Subject.class, id);
				
				if (null != subject) {
					session.delete(subject);
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

}
