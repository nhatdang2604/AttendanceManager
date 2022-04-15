package com.nhatdang2604.service;

import java.util.ArrayList;
import java.util.List;

import com.nhatdang2604.dao.CourseDAO;
import com.nhatdang2604.dao.ICourseDAO;
import com.nhatdang2604.model.entity.Course;
import com.nhatdang2604.model.entity.Schedule;
import com.nhatdang2604.model.entity.Student;
import com.nhatdang2604.model.entity.Subject;
import com.nhatdang2604.model.entity.SubjectWeek;

public enum CourseService implements ICourseService {

	INSTANCE;
	
	private ICourseDAO courseDAO;
	
	private CourseService() {
		courseDAO = CourseDAO.INSTANCE;
	}

	public Course createCourse(Course course) {
		
		Schedule schedule = course.getSchedule();
		
		//Add schedule if empty
		if (null == schedule) {
			schedule = new Schedule(course, null, null, null, null, null);
			course.setSchedule(schedule);
		}
		
		//Add subject week if empty
		if (null == course.getSchedule().getSubjectWeeks()) {
			
			List<SubjectWeek> weeks = new ArrayList<SubjectWeek>();
			for (int weekIndex = 1; weekIndex <= SubjectWeek.NUMBER_OF_WEEKS_PER_COURSE; ++weekIndex) {
				weeks.add(new SubjectWeek(schedule, weekIndex, null));
			}
			
			course.getSchedule().setSubjectWeeks(weeks);
			
		}
		
		return courseDAO.createOrUpdateCourse(course);
	}

	public Course updateCourse(Course course) {
		return courseDAO.createOrUpdateCourse(course);
	}

	public int deleteCourse(Integer id) {
		return courseDAO.deleteCourse(id);
	}

	public Course findCourseById(Integer id) {
		return courseDAO.findCourseById(id);
	}

	public Course addSubjectToCourse(Course course, Subject subject) {
		
		course.setSubject(subject);
		
		return updateCourse(course);
	}

	public Course addScheduleToCourse(Course course, Schedule schedule) {
		
		course.setSchedule(schedule);
		
		return updateCourse(course);
	}

	public Course addStudentsToCourse(Course course, List<Student> students) {
		
		course.setStudents(students);
		
		return updateCourse(course);
	}

	public Course addStudentToCourse(Course course, Student student) {
		
		if (null == course.getStudents()) {
			course.setStudents(new ArrayList<Student>());
		}
		
		course.getStudents().add(student);
		
		return updateCourse(course);
	}
}
