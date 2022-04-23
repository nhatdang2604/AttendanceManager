package com.nhatdang2604.dao.i;

import java.util.List;

import com.nhatdang2604.model.entity.Subject;

public interface ISubjectDAO {

	public Subject createSubject(Subject subject);
	public Subject updateSubject(Subject subject);
	public int deleteSubject(Integer id);
	public int deleteSubjects(List<Integer> ids);
	public Subject findSubjectById(Integer id);
	public Subject findSubjectBySubjectId(String subjectId);
	public List<Subject> getAllSubjects();
}
