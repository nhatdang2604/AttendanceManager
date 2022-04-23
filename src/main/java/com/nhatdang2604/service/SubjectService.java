package com.nhatdang2604.service;

import java.util.List;

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
		
		Subject tryToFind = findSubjectBySubjectId(subject.getSubjectId());
		
		return (null == tryToFind? subjectDAO.createSubject(subject): null);
	}

	public Subject updateSubject(Subject subject) {
		
		Subject tryToFind = findSubjectBySubjectId(subject.getSubjectId());
		
		if (null == tryToFind) {return subjectDAO.updateSubject(subject); }
		if (tryToFind.getId().equals(subject.getId())) {
			
			if(tryToFind.getSubjectId().equals(subject.getSubjectId())) {
				return subjectDAO.updateSubject(subject);
			}
		}
		
		return null;
	}

	public int deleteSubject(Integer id) {
		
		if (null == id) {return 1;}
		return subjectDAO.deleteSubject(id);
	}

	public int deleteSubjects(List<Integer> ids) {
		return subjectDAO.deleteSubjects(ids);
	}
	
	public Subject findSubjectById(Integer id) {
		
		if (null == id) {return null;}
		return subjectDAO.findSubjectById(id);
	}

	@Override
	public List<Subject> getAllSubjects() {
		return subjectDAO.getAllSubjects();
	}

	@Override
	public Subject findSubjectBySubjectId(String subjectId) {
		return subjectDAO.findSubjectBySubjectId(subjectId.trim());
	}
	
	

}
