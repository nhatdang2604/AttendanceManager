package com.nhatdang2604.dao;

import com.nhatdang2604.model.entity.Course;

public interface ICourseDAO {

	public Course createOrUpdateCourse(Course course);
	public int deleteCourse(Integer id);
	public Course findCourseById(Integer id);
	
}
