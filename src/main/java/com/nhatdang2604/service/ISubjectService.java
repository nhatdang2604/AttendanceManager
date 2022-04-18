package com.nhatdang2604.service;

import com.nhatdang2604.model.entity.Subject;

public interface ISubjectService {

	public Subject createSubject(Subject subject);
	public Subject updateSubject(Subject subject);
	public int deleteSubject(Integer id);
	public Subject findSubjectById(Integer id);
	
}
