package com.nhatdang2604.service;

import org.hibernate.SessionFactory;

import com.nhatdang2604.dao.SubjectDAO;
import com.nhatdang2604.dao.i.ISubjectDAO;
import com.nhatdang2604.model.entity.Subject;
import com.nhatdang2604.service.i.ISubjectService;

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

	public int deleteSubject(Integer id) {
		
		if (null == id) {return 1;}
		return subjectDAO.deleteSubject(id);
	}

	public Subject findSubjectById(Integer id) {
		
		if (null == id) {return null;}
		return subjectDAO.findSubjectById(id);
	}
	
	

}
