package com.nhatdang2604.service;

import java.util.List;

import com.nhatdang2604.model.entity.SubjectWeek;

public interface ISubjectWeekService {

	public SubjectWeek createOrUpdateSubjectWeek(SubjectWeek week);
	public List<SubjectWeek> createOrUpdateSubjectWeeks(List<SubjectWeek> weeks);
	public SubjectWeek findSubjectWeekById(Integer courseId, Integer weekIndex);
	public int deleteSubjectWeek(Integer courseId, Integer weekIndex);
}
