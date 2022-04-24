package com.nhatdang2604.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.nhatdang2604.dao.CourseDAO;
import com.nhatdang2604.dao.i.ICourseDAO;
import com.nhatdang2604.model.entity.Course;
import com.nhatdang2604.model.entity.Schedule;
import com.nhatdang2604.model.entity.Student;
import com.nhatdang2604.model.entity.StudentAttendanceStatus;
import com.nhatdang2604.model.entity.Subject;
import com.nhatdang2604.model.entity.SubjectWeek;
import com.nhatdang2604.service.i.IAttendanceStatusService;
import com.nhatdang2604.service.i.ICourseService;
import com.nhatdang2604.service.i.IScheduleService;
import com.nhatdang2604.service.i.IStudentService;

public enum CourseService implements ICourseService {

	INSTANCE;
	
	private ICourseDAO courseDAO;
	private IScheduleService scheduleService;
	private IStudentService studentService;
	private IAttendanceStatusService attendanceStatusService;
	
	private CourseService() {
		courseDAO = CourseDAO.INSTANCE;
		scheduleService = ScheduleService.INSTANCE;
		studentService = StudentService.INSTANCE;
		attendanceStatusService = AttendanceStatusService.INSTANCE;
	}

	public Course createCourse(Course course) {
		
		
		
		course = courseDAO.createCourse(course);
		//scheduleService.createOrUpdateSchedule(schedule);
		return course;
	}

	public Course updateCourse(Course course) {
		return courseDAO.updateCourse(course);
	}

	public int deleteCourse(Integer id) {
		
		if (null == id) {return 1;}
		
		return courseDAO.deleteCourse(id);
	}

	public Course findCourseById(Integer id) {
		if (null == id) {return null;}
		
		return courseDAO.findCourseById(id);
	}

	public Course addSubjectToCourse(Course course, Subject subject) {
		
		course.setSubject(subject);
		
		return updateCourse(course);
	}

	public Course addScheduleToCourse(Course course, Schedule schedule) {
		
		course.setSchedule(schedule);
		schedule.setCourse(course);
		
		scheduleService.createOrUpdateSchedule(schedule);
		return updateCourse(course);
	}

	public Course addStudentsToCourse(Course course, Set<Student> students) {
		
		course.add(students);
		students.forEach(student -> {
			student.add(course);
		});
		
		studentService.updateStudents(students);
		return updateCourse(course);
	}

	public Course addStudentToCourse(Course course, Student student) {
		
		course.add(student);
		student.add(course);
		
		List<SubjectWeek> weeks = course.getSchedule().getSubjectWeeks();
		
		
		List<StudentAttendanceStatus> statuses = weeks
				.stream()
				.map(week -> new StudentAttendanceStatus(
						student, week, StudentAttendanceStatus.ATTENDANCE_STATUS.None.name()))
				.collect(Collectors.toList());
		
		student.add(statuses);
		
		attendanceStatusService.createOrUpdateStatuses(statuses);
		studentService.updateStudent(student);
		
		return updateCourse(course);
	}

	@Override
	public int deleteCourses(List<Integer> ids) {
		return courseDAO.deleteCourses(ids);
	}

	@Override
	public List<Course> getAllCourses() {
		return courseDAO.getAllCourses();
	}
}
