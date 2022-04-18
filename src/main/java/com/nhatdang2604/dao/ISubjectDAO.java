package com.nhatdang2604.dao;

import com.nhatdang2604.model.entity.Subject;

public interface ISubjectDAO {

	public Subject createOrUpdateSubject(Subject subject);
	public int deleteSubject(Integer id);
	public Subject findSubjectById(Integer id);
}
