package com.nhatdang2604.service;

import org.hibernate.SessionFactory;

import com.nhatdang2604.dao.ISubjectDAO;
import com.nhatdang2604.dao.SubjectDAO;
import com.nhatdang2604.model.entity.Subject;

public enum SubjectService implements ISubjectService {
	
	INSTANCE;
	
	private ISubjectDAO subjectDAO;
	
	private SubjectService() {
		subjectDAO = SubjectDAO.INSTANCE;
	}

	public Subject createSubject(Subject subject) {
		
		Subject tryToFind = findSubjectById(subject.getId());
		
		return (null == tryToFind? subjectDAO.createOrUpdateSubject(subject): null);
	}

	public Subject updateSubject(Subject subject) {
		
		Subject tryToFind = findSubjectById(subject.getId());
		
		return (null != tryToFind? subjectDAO.createOrUpdateSubject(subject):null);
	}

	public int deleteSubject(String id) {
		
		return subjectDAO.deleteSubject(id);
	}

	public Subject findSubjectById(String id) {
		
		return subjectDAO.findSubjectById(id);
	}
	
	

}
