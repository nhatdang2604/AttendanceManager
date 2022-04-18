package com.nhatdang2604.dao;

import java.util.List;

import com.nhatdang2604.model.entity.SubjectWeek;

public interface ISubjectWeekDAO {

	public SubjectWeek createOrUpdateSubjectWeek(SubjectWeek week);
	public List<SubjectWeek> createOrUpdateSubjectWeeks(List<SubjectWeek> weeks);
	public int deleteSubjectWeek(Integer courseId, Integer weekIndex);
	public SubjectWeek findSubjectWeekById(Integer courseId, Integer weekIndex);
}
