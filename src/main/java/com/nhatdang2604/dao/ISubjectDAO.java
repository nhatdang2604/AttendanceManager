package com.nhatdang2604.dao;

import com.nhatdang2604.model.entity.Subject;

public interface ISubjectDAO {

	public Subject createOrUpdateSubject(Subject subject);
	public int deleteSubject(String id);
	public Subject findSubjectById(String id);
}
