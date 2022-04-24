package com.nhatdang2604.dao.i;

import java.util.List;

import com.nhatdang2604.model.entity.Course;

public interface ICourseDAO {

	public Course createCourse(Course course);
	public Course updateCourse(Course course);
	public int deleteCourse(Integer id);
	public Course findCourseById(Integer id);
	public int deleteCourses(List<Integer> ids);
	public List<Course> getAllCourses();
	
}
