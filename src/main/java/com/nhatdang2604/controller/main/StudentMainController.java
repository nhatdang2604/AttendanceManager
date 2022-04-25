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

public class StudentMainController extends BaseMainController {

	private IStudentService studentService;

	private IAttendanceStatusService attendanceStatusService;
	
	private User currentUser;
	
	public StudentMainController() {
		super(new StudentMainFrame());
		studentService = StudentService.INSTANCE;
		attendanceStatusService = AttendanceStatusService.INSTANCE;
	}
	
	private void initStudentAttendanceFeature() {
		StudentAttendanceFeatureView studentAttendanceFeatureView = 
				(StudentAttendanceFeatureView) main.getFeatureViews().get(StudentMainFrame.STUDENT_ATTENDANCE_FEATURE_INDEX);
		StudentAttendanceDisplayTableView studentAttendanceDisplayTableView = studentAttendanceFeatureView.getDisplayTableView();
		
		StudentAttendanceTableView studentAttendanceTableView = 
				studentAttendanceDisplayTableView.getStudentAttendanceTableView();
		studentAttendanceTableView.readData((Student) currentUser.getUserInformation()).update();
		
		
		AttendanceForStudentTableView attendanceForStudentTableView = 
				studentAttendanceDisplayTableView.getAttendanceForStudentTableView();
		initAttendingFeature(attendanceForStudentTableView, studentAttendanceTableView);
		
		
	}
	
	private void updateCurrentUser() {
		Student student = studentService.findStudentById(currentUser.getId());
		currentUser = student.getUser();
	}
	
	private void initAttendingFeature(
			AttendanceForStudentTableView tableView,
			StudentAttendanceTableView studentAttendanceTableView) {
		
		AttendanceForStudentDetailView detailView = (AttendanceForStudentDetailView) tableView.getDetailView();
		
		detailView.getButtons().get(AttendanceForStudentDetailView.SEARCH_BUTTON_INDEX).addActionListener((event) -> {
			Student student = (Student) currentUser.getUserInformation();
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
			studentAttendanceTableView.readData((Student) currentUser.getUserInformation()).update();
		});

	}
	
	public BaseMainController setUser(User user) {
		this.currentUser = user;
		updateCurrentUser();
		
		return this;
	}
	
	@Override
	public void start() {
		initStudentAttendanceFeature();
		main.open();
	}
}
