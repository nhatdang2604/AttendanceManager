package com.nhatdang2604.service;

import java.util.List;

import com.nhatdang2604.dao.ISubjectWeekDAO;
import com.nhatdang2604.dao.SubjectWeekDAO;
import com.nhatdang2604.model.entity.SubjectWeek;

public enum SubjectWeekService implements ISubjectWeekService {
	
	INSTANCE;
	
	private ISubjectWeekDAO subjectWeekDAO;
	
	private SubjectWeekService() {
		subjectWeekDAO = SubjectWeekDAO.INSTANCE;
	}

	@Override
	public SubjectWeek createOrUpdateSubjectWeek(SubjectWeek week) {
		return subjectWeekDAO.createOrUpdateSubjectWeek(week);
	}

	@Override
	public List<SubjectWeek> createOrUpdateSubjectWeeks(List<SubjectWeek> weeks) {
		return subjectWeekDAO.createOrUpdateSubjectWeeks(weeks);
	}
	
	@Override
	public SubjectWeek findSubjectWeekById(Integer courseId, Integer weekIndex) {
		 return subjectWeekDAO.findSubjectWeekById(courseId, weekIndex);
	}

	@Override
	public int deleteSubjectWeek(Integer courseId, Integer weekIndex) {
		return subjectWeekDAO.deleteSubjectWeek(courseId, weekIndex);
	}
	
}
