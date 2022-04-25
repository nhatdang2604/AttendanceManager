package com.nhatdang2604.main;

import com.nhatdang2604.controller.LoginController;
import com.nhatdang2604.utility.HashingUtil;

public class Main {

	public static void main(String[] args) {
//		IUserService service0 = UserService.INSTANCE;
//		ISubjectService service1 = SubjectService.INSTANCE;
//		ICourseService service2 = CourseService.INSTANCE;
//		IStudentService service4 = StudentService.INSTANCE;
//		
//		Subject math = new Subject("MTH0004", "Đại số tuyến tính");
//		
//		Course course = new Course(math, null, null);
//		service2.createCourse(course);
//		
//		System.out.print(course.getId());
//		
//		int linearAlgebraId = 1;
//		course = service2.findCourseById(linearAlgebraId);
//		
//		System.out.println(course);
//		System.out.println(course.getSchedule().getCourse().getId());
		
//		for (SubjectWeek week: course.getSchedule().getSubjectWeeks()) {
//			System.out.println(week.getSchedule().getCourse().getId());
//		}
//		
//		service0.createOrUpdateUser(new User(
//				"test",
//				HashingUtil.passwordEncryption("test"),
//				User.USER_ROLE.Role_Student.name(),
//				true,
//				null));
//		
//		User user = service0.findUserById(1);
//		System.out.println("1st call user:" + user);
//		Student quan = service4.createOrUpdateStudent(new Student(user, "Hoang Quan", "Tran", null, null));
//		//user.setUserInformation(quan);
//		
//		user = service0.findUserById(1);
//		System.out.println("2nd call user:" + user);
//		
//		//service0.createOrUpdateUser(user);
//		service2.addStudentToCourse(course, quan);
//		
//		quan.getStatuses().forEach(status ->{
//			System.out.println(status);
//		});
//		
//		Subject math = new Subject("MTH005", "test");
//		Course dummy = new Course(math, null, null);
//		dummy.setId(5);
//		Schedule schedule = new Schedule(dummy, null, null, null, null, null);
//		List<SubjectWeek> weeks = new ArrayList<SubjectWeek>();
//		for (int i = 1; i <= 15; ++i) {
//			SubjectWeek week = new SubjectWeek(schedule, i, null);
//			weeks.add(week);
//		}
//		
//		service3.createOrUpdateSchedule(schedule);
//		schedule = service3.findScheduleById(schedule.getCourse().getId());
//		
//		for (SubjectWeek week: schedule.getSubjectWeeks()) {
//			System.out.println(week.getSchedule().getCourse().getId());
//		}
//		
//		service0.createOrUpdateUser(new User(
//				"test1",
//				HashingUtil.passwordEncryption("test1"),
//				User.USER_ROLE.Role_Student.name(),
//				false,
//				null));
//		
//		service0.createOrUpdateUser(new User(
//				"admin0",
//				HashingUtil.passwordEncryption("admin0"),
//				User.USER_ROLE.Role_Ministry.name(),
//				false,
//				null));
//		
//		
		LoginController controller = new LoginController();
		controller.start();
//		
//		BaseMainFrame mainView = new MinistryMainFrame();
//		mainView.open();
//		
		
//		StudentForm form = new StudentForm();
//		form.open();
		
//		BaseMainController miController = new MinistryMainController();
//		miController.start();
		
//		IStudentService ss = StudentService.INSTANCE;
//		Student student = ss.findStudentById(1);
//		
//		StudentMainController miController = new StudentMainController();
//		miController.setUser(student.getUser()).start();
		
//		System.out.println(HashingUtil.passwordEncryption("admin"));
	}

}
