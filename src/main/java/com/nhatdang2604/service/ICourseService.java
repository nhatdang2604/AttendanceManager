package com.nhatdang2604.service;

import java.util.List;

import com.nhatdang2604.model.entity.Course;
import com.nhatdang2604.model.entity.Schedule;
import com.nhatdang2604.model.entity.Student;
import com.nhatdang2604.model.entity.Subject;

public interface ICourseService {
	
	public Course createCourse(Course course);
	public Course updateCourse(Course course);

	public int deleteCourse(Integer id);
	public Course findCourseById(Integer id);
	
	public Course addSubjectToCourse(Course course, Subject subject);
	public Course addScheduleToCourse(Course course, Schedule schedule);
	public Course addStudentsToCourse(Course course, List<Student> students);
	public Course addStudentToCourse(Course course, Student student);
	
}
