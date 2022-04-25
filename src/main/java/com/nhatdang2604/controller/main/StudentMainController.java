package com.nhatdang2604.controller.main;

import java.util.ArrayList;
import java.util.List;

import com.nhatdang2604.model.entity.Schedule;
import com.nhatdang2604.model.entity.Student;
import com.nhatdang2604.model.entity.StudentAttendanceStatus;
import com.nhatdang2604.model.entity.User;
import com.nhatdang2604.service.AttendanceStatusService;
import com.nhatdang2604.service.StudentService;
import com.nhatdang2604.service.i.IAttendanceStatusService;
import com.nhatdang2604.service.i.ICourseService;
import com.nhatdang2604.service.i.IStudentService;
import com.nhatdang2604.service.i.ISubjectService;
import com.nhatdang2604.view.display_feature_view.StudentAttendanceFeatureView;
import com.nhatdang2604.view.display_feature_view.detail.AttendanceForStudentDetailView;
import com.nhatdang2604.view.display_feature_view.display_table.StudentAttendanceDisplayTableView;
import com.nhatdang2604.view.display_feature_view.table.AttendanceForStudentTableView;
import com.nhatdang2604.view.display_feature_view.table.StudentAttendanceTableView;
import com.nhatdang2604.view.display_feature_view.table.StudentTableView;
import com.nhatdang2604.view.frame.StudentMainFrame;
import com.nhatdang2604.view.frame.form.ChangePasswordForm;
import com.nhatdang2604.view.frame.form.LoginForm;

public class StudentMainController extends BaseMainController {

	private IStudentService studentService;
	private IAttendanceStatusService attendanceStatusService;
	
	public StudentMainController(LoginForm loginForm, ChangePasswordForm changePasswordForm, User user) {
		super(new StudentMainFrame(), loginForm, changePasswordForm, user);
		studentService = StudentService.INSTANCE;
		attendanceStatusService = AttendanceStatusService.INSTANCE;
		setupForUser();
		initStudentAttendanceFeature();
	}
	
	private void initStudentAttendanceFeature() {
		StudentAttendanceFeatureView studentAttendanceFeatureView = 
				(StudentAttendanceFeatureView) main.getFeatureViews().get(StudentMainFrame.STUDENT_ATTENDANCE_FEATURE_INDEX);
		StudentAttendanceDisplayTableView studentAttendanceDisplayTableView = studentAttendanceFeatureView.getDisplayTableView();
		
		StudentAttendanceTableView studentAttendanceTableView = 
				studentAttendanceDisplayTableView.getStudentAttendanceTableView();
		studentAttendanceTableView.readData((Student) user.getUserInformation()).update();
		
		
		AttendanceForStudentTableView attendanceForStudentTableView = 
				studentAttendanceDisplayTableView.getAttendanceForStudentTableView();
		initAttendingFeature(attendanceForStudentTableView, studentAttendanceTableView);
		
		
	}
	
	private void updateCurrentUser() {
		Student student = studentService.findStudentById(user.getId());
		user = student.getUser();
	}
	
	private void initAttendingFeature(
			AttendanceForStudentTableView tableView,
			StudentAttendanceTableView studentAttendanceTableView) {
		
		AttendanceForStudentDetailView detailView = (AttendanceForStudentDetailView) tableView.getDetailView();
		
		detailView.getButtons().get(AttendanceForStudentDetailView.SEARCH_BUTTON_INDEX).addActionListener((event) -> {
			Student student = (Student) user.getUserInformation();
			List<StudentAttendanceStatus> result = new ArrayList<>();
			for (StudentAttendanceStatus status: student.getStatuses()) {
				Schedule schedule = status.getSubjectWeek().getSchedule();
				if (schedule.getTime().equals(detailView.getSelectedTime()) &&
						status.getSubjectWeek().getDate().equals(detailView.getSelectedDate())) {
					result.add(status);
				}
			}
			
			tableView.readData(result).update();
			
		});
		
		detailView.getButtons().get(AttendanceForStudentDetailView.SAVE_BUTTON_INDEX).addActionListener((event) -> {
			List<StudentAttendanceStatus> result = tableView.getUpdatedStatuses();
			attendanceStatusService.createOrUpdateStatuses(result);
			
			updateCurrentUser();
			studentAttendanceTableView.readData((Student) user.getUserInformation()).update();
		});

	}
	
//	public BaseMainController setUser(User user) {
//		this.user = user;
//		updateCurrentUser();
//		
//		return this;
//	}
	
	private void setupForUser() {
		Student student = (Student) user.getUserInformation();
		student = studentService.findStudentById(student.getId());
		user = student.getUser();
	}
	
	@Override
	public void start() {
		main.open();
	}
}
