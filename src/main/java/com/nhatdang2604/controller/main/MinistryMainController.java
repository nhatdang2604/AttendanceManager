package com.nhatdang2604.controller.main;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.nhatdang2604.model.entity.Course;
import com.nhatdang2604.model.entity.Student;
import com.nhatdang2604.model.entity.Subject;
import com.nhatdang2604.model.entity.User;
import com.nhatdang2604.service.AttendanceStatusService;
import com.nhatdang2604.service.CourseService;
import com.nhatdang2604.service.StudentService;
import com.nhatdang2604.service.SubjectService;
import com.nhatdang2604.service.i.IAttendanceStatusService;
import com.nhatdang2604.service.i.ICourseService;
import com.nhatdang2604.service.i.IStudentService;
import com.nhatdang2604.service.i.ISubjectService;
import com.nhatdang2604.utility.DataWriter;
import com.nhatdang2604.utility.Parser;
import com.nhatdang2604.view.display_feature_view.AttendanceFeatureView;
import com.nhatdang2604.view.display_feature_view.StudentFeatureView;
import com.nhatdang2604.view.display_feature_view.SubjectFeatureView;
import com.nhatdang2604.view.display_feature_view.detail.AttendanceDetailView;
import com.nhatdang2604.view.display_feature_view.detail.CRUD_DetailView;
import com.nhatdang2604.view.display_feature_view.detail.CourseDetailView;
import com.nhatdang2604.view.display_feature_view.detail.StudentDetailView;
import com.nhatdang2604.view.display_feature_view.display_table.StudentDisplayTableView;
import com.nhatdang2604.view.display_feature_view.display_table.SubjectDisplayTableView;
import com.nhatdang2604.view.display_feature_view.table.AttendanceTableView;
import com.nhatdang2604.view.display_feature_view.table.CourseTableView;
import com.nhatdang2604.view.display_feature_view.table.StudentTableView;
import com.nhatdang2604.view.display_feature_view.table.SubjectTableView;
import com.nhatdang2604.view.frame.MinistryMainFrame;
import com.nhatdang2604.view.frame.form.ChangePasswordForm;
import com.nhatdang2604.view.frame.form.CourseForm;
import com.nhatdang2604.view.frame.form.LoginForm;
import com.nhatdang2604.view.frame.form.StudentForm;
import com.nhatdang2604.view.frame.form.SubjectForm;

public class MinistryMainController extends BaseMainController {

	private IStudentService studentService;
	private ISubjectService subjectService;
	private ICourseService courseService;
	private IAttendanceStatusService attendanceStatusService;
	
	public MinistryMainController(LoginForm loginForm, ChangePasswordForm changePasswordForm, User user) {
		super(new MinistryMainFrame(), loginForm, changePasswordForm, user);
		studentService = StudentService.INSTANCE;
		subjectService = SubjectService.INSTANCE;
		courseService = CourseService.INSTANCE;
		attendanceStatusService = AttendanceStatusService.INSTANCE;
		
		initStudentFeature();
		initSubjectFeature();
		initAttendanceFeature();
	}

	private void initUpdateOnStudentFeature(StudentTableView studentTableView) {
		
		//Make the update button activated
		studentTableView.getUpdateButton().addActionListener((event) -> {
					
			Student updateStudent = studentTableView.getSelectedStudent();
			StudentForm updateForm = new StudentForm(updateStudent, main);
					
			//Save the current select row of the update student
			int selectRowIndex = studentTableView.getSelectedRow();
					
			//When the form is filled => submit data by:
			//	Update the student data in backend
			//	Update the student data in frontend
			updateForm.getSubmitButton().addActionListener((e) -> {
				studentService.updateStudent(updateForm.submit());
				studentTableView.readData(studentService.getAllStudents()).update();
				updateForm.dispose();
						
				//Keep selecting the current row
				studentTableView.setRowSelectionInterval(selectRowIndex, selectRowIndex);
				updateAttendanceTable();
			});
					
			//Open the update form
			updateForm.setVisible(true);
		});
	}
	
	private void initCreateOnStudentFeature(StudentTableView studentTableView) {
		
		CRUD_DetailView detailView = (CRUD_DetailView) studentTableView.getDetailView();
		detailView.getButtons().get(CRUD_DetailView.CREATE_BUTTON_INDEX).addActionListener((event) -> {
			StudentForm createForm = new StudentForm(main);
			
			createForm.getSubmitButton().addActionListener((e) -> {
				studentService.createStudent(createForm.submit());
				studentTableView.readData(studentService.getAllStudents()).update();
				createForm.dispose();
				
			});
			
			//Open the create form
			createForm.setVisible(true);
		});;
		
	}
	
	private void initDeleteOnStudentFeature(StudentTableView studentTableView) {
		
		CRUD_DetailView detailView = (CRUD_DetailView) studentTableView.getDetailView();
		detailView.getButtons().get(CRUD_DetailView.DELETE_BUTTON_INDEX).addActionListener((event) -> {
			
			int input = JOptionPane.showConfirmDialog(null,
	        		"Bạn có chắc chắn muốn xóa ?\nDữ liệu bị xóa sẽ không thể khôi phục lại được.",
	        		"Xóa",
	        		JOptionPane.YES_NO_OPTION);
			
			if (JOptionPane.YES_OPTION == input) {
				List<Student> deletedStudents = studentTableView.getSelectedStudents();
				List<Integer> ids = deletedStudents
						.stream()
						.map(student -> student.getId())
						.collect(Collectors.toList());
				studentService.deleteStudents(ids);
				studentTableView.readData(studentService.getAllStudents()).update();
				updateAttendanceTable();
				JOptionPane.showMessageDialog(null, "Đã xóa thành công.");
			}
	      
		});;
		
	}
	
	private void initCreateTemplateOnStudentFeature(StudentTableView studentTableView) {
		
		StudentDetailView detailView = (StudentDetailView) studentTableView.getDetailView();
		detailView.getButtons().get(StudentDetailView.CREATE_TEMPLATE_INDEX).addActionListener((event) -> {
			int state = detailView.getFileChooser().showSaveDialog(null);
			if (JFileChooser.APPROVE_OPTION == state) {
				
				String ext = ".csv";
				String path = detailView.getFileChooser().getSelectedFile().getAbsolutePath();
				if (!path.endsWith(ext)) {
					path += ext;
				}
				
				DataWriter writer = new DataWriter();
				writer.generateTemplate(path);
			}
		});
	}
	
	private void initImportOnStudentFeature(StudentTableView studentTableView) {
		
		StudentDetailView detailView = (StudentDetailView) studentTableView.getDetailView();
		detailView.getButtons().get(StudentDetailView.IMPORT_INDEX).addActionListener((event) -> {
			int state = detailView.getFileChooser().showSaveDialog(null);
			if (JFileChooser.APPROVE_OPTION == state) {
				String path = detailView.getFileChooser().getSelectedFile().getAbsolutePath();
				Parser parser = new Parser();
				List<Student> students = parser.readStudentFromText(path);
				studentService.createStudents(students);
				studentTableView.readData(studentService.getAllStudents()).update();
			}
		});
	}
	
	private void initStudentFeature() {
		StudentFeatureView studentFeatureView = (StudentFeatureView) main.getFeatureViews().get(MinistryMainFrame.STUDENT_FEATURE_INDEX);
		StudentDisplayTableView studentDisplayTableView = studentFeatureView.getDisplayTableView();
		StudentTableView studentTableView = studentDisplayTableView.getStudentTableView();

		studentTableView.readData(studentService.getAllStudents()).update();
		
		initCreateOnStudentFeature(studentTableView);
		initUpdateOnStudentFeature(studentTableView);
		initDeleteOnStudentFeature(studentTableView);
		
		initCreateTemplateOnStudentFeature(studentTableView);
		initImportOnStudentFeature(studentTableView);
	}
	
	private void initCreateOnSubjectFeature(SubjectTableView subjectTableView) {
		CRUD_DetailView detailView = (CRUD_DetailView) subjectTableView.getDetailView();
		detailView.getButtons().get(CRUD_DetailView.CREATE_BUTTON_INDEX).addActionListener((event) -> {
			SubjectForm createForm = new SubjectForm(main);
			
			createForm.getSubmitButton().addActionListener((e) -> {
				Subject subject = subjectService.createSubject(createForm.submit());
				
				//Mean the course id is existed => pop out error in the subject form
				if (null == subject) {
					createForm.setError(0);
				} else {
					subjectTableView.readData(subjectService.getAllSubjects()).update();
					createForm.dispose();
				}
				
			});
			
			//Open the create form
			createForm.setVisible(true);
		});;
	}
	
	private void initUpdateOnSubjectFeature(SubjectTableView subjectTableView) {
		
		//Make the update button activated
		subjectTableView.getUpdateButton().addActionListener((event) -> {
					
			Subject updateSubject = subjectTableView.getSelectedSubject();
			SubjectForm updateForm = new SubjectForm(updateSubject, main);
					
			//Save the current select row of the update subject
			int selectRowIndex = subjectTableView.getSelectedRow();
					
			//When the form is filled => submit data by:
			//	Update the subject data in backend
			//	Update the subject data in frontend
			updateForm.getSubmitButton().addActionListener((e) -> {
				Subject subject = subjectService.updateSubject(updateForm.submit());
				
				if (null == subject) {
					updateForm.setError(0);
				} else {
					subjectTableView.readData(subjectService.getAllSubjects()).update();
					updateForm.dispose();
					
					//Keep selecting the current row
					subjectTableView.setRowSelectionInterval(selectRowIndex, selectRowIndex);
				}
				
			});
					
			//Open the update form
			updateForm.setVisible(true);
		});
	}
	
	private void initDeleteOnSubjectFeature(SubjectTableView subjectTableView, CourseTableView courseTableView) {
		
		CRUD_DetailView detailView = (CRUD_DetailView) subjectTableView.getDetailView();
		detailView.getButtons().get(CRUD_DetailView.DELETE_BUTTON_INDEX).addActionListener((event) -> {
			
			int input = JOptionPane.showConfirmDialog(null,
	        		"Bạn có chắc chắn muốn xóa ?\nDữ liệu bị xóa sẽ không thể khôi phục lại được.",
	        		"Xóa",
	        		JOptionPane.YES_NO_OPTION);
			
			if (JOptionPane.YES_OPTION == input) {
				List<Subject> deletedSubjects = subjectTableView.getSelectedSubjects();
				List<Integer> ids = deletedSubjects
						.stream()
						.map(subject -> subject.getId())
						.collect(Collectors.toList());
				
				subjectService.deleteSubjects(ids);
				subjectTableView.readData(subjectService.getAllSubjects()).update();
				
				//Repopulate data for course view
				courseTableView.readData(courseService.getAllCourses()).update();
				
				updateAttendanceTable();
				JOptionPane.showMessageDialog(null, "Đã xóa thành công.");
			}
	      
		});;
	}
	
	private void initCreateOnCourseFeature(CourseTableView courseTableView) {
		//TODO:
		CourseDetailView detailView = (CourseDetailView) courseTableView.getDetailView();
		detailView.getButtons().get(CRUD_DetailView.CREATE_BUTTON_INDEX).addActionListener((event) -> {
			CourseForm createForm = new CourseForm(main);
			
			//Read all subject and student in database
			createForm
			.setAvailableSubjects(subjectService.getAllSubjects())
			.getAddStudentForm()
			.setStudentModels(studentService.getAllStudents())
			.update();
			
			createForm.getSubmitButton().addActionListener((e) -> {
				Course course = createForm.submit();
				
				//Mean the course id is existed => pop out error in the subject form
				if (null == course.getSubject()) {
					createForm.setError(0);
				} else {
				
					courseService.createCourse(course);
					courseTableView.readData(courseService.getAllCourses()).update();
					createForm.dispose();
					updateAttendanceTable();
				}
				
			});
			
			//Open the create form
			createForm.setVisible(true);
		});;
		
	}
	
	private void initUpdateOnCourseFeature(CourseTableView courseTableView) {

		//Make the update button activated
		courseTableView.getUpdateButton().addActionListener((event) -> {
							
			Course updateCourse = courseTableView.getSelectedCourse();
			CourseForm updateForm = new CourseForm(updateCourse, main);
			
			
			//Check all the already registrated student into the form
			// And add all student to choose
			updateForm
			.setAvailableSubjects(subjectService.getAllSubjects())
			.getAddStudentForm()
			.setStudentModels(studentService.getAllStudents())
			.setCourseModel(updateCourse)
			.update();
			
			//Parse the current subject week data into form
			updateForm
			.getWeekForm()
			.setModel(updateCourse.getSchedule());
			
			//Save the current select row of the update course
			int selectRowIndex = courseTableView.getSelectedRow();
							
			//When the form is filled => submit data by:
			//	Update the subject data in backend
			//	Update the subject data in frontend
			updateForm.getSubmitButton().addActionListener((e) -> {
				Course course = updateForm.submit();
						
				if (null == course.getSubject()) {
					updateForm.setError(0);
				} else {
					try {
						course = courseService.updateCourse(course);
					} catch (Exception ex) {
						//do nothing
					} finally {
						courseTableView.readData(courseService.getAllCourses()).update();
						updateForm.dispose();
								
						//Keep selecting the current row
						courseTableView.setRowSelectionInterval(selectRowIndex, selectRowIndex);
						updateAttendanceTable();
					}
				}
						
			});
							
			//Open the update form
			updateForm.setVisible(true);
		});		
		
	}
	
	private void initDeleteOnCourseFeature(CourseTableView courseTableView) {
		CourseDetailView detailView = (CourseDetailView) courseTableView.getDetailView();
		detailView.getButtons().get(CRUD_DetailView.DELETE_BUTTON_INDEX).addActionListener((event) -> {
			
			int input = JOptionPane.showConfirmDialog(null,
	        		"Bạn có chắc chắn muốn xóa ?\nDữ liệu bị xóa sẽ không thể khôi phục lại được.",
	        		"Xóa",
	        		JOptionPane.YES_NO_OPTION);
			
			if (JOptionPane.YES_OPTION == input) {
				List<Course> deletedCourses = courseTableView.getSelectedCourses();
				List<Integer> ids = deletedCourses
						.stream()
						.map(course -> course.getId())
						.collect(Collectors.toList());
				
				courseService.deleteCourses(ids);
				courseTableView.readData(courseService.getAllCourses()).update();
				
				
				updateAttendanceTable();
				JOptionPane.showMessageDialog(null, "Đã xóa thành công.");
			}
	      
		});;
	}
	
	private void initSubjectFeature() {
		SubjectFeatureView subjectFeatureView = (SubjectFeatureView) main.getFeatureViews().get(MinistryMainFrame.SUBJECT_FEATURE_INDEX);
		SubjectDisplayTableView subjectDisplayTableView = subjectFeatureView.getDisplayTableView();
		
		SubjectTableView subjectTableView = subjectDisplayTableView.getSubjectTableView();
		CourseTableView courseTableView = subjectDisplayTableView.getCourseTableView();
		
		subjectTableView.readData(subjectService.getAllSubjects()).update();
		courseTableView.readData(courseService.getAllCourses()).update();
		
		initCreateOnSubjectFeature(subjectTableView);
		initUpdateOnSubjectFeature(subjectTableView);
		initDeleteOnSubjectFeature(subjectTableView, courseTableView);
		
		initCreateOnCourseFeature(courseTableView);
		initUpdateOnCourseFeature(courseTableView);
		initDeleteOnCourseFeature(courseTableView);
	}
	
	private AttendanceTableView updateAttendanceTable() {
		
		//Rebuild the attendance UI
		AttendanceTableView attendanceTableView = ((AttendanceFeatureView) main
				.getFeatureViews()
				.get(MinistryMainFrame.ATTENDANCE_FEATURE_INDEX))
				.getDisplayTableView()
				.getAttendanceTableView();
		AttendanceDetailView attendanceDetailView = (AttendanceDetailView) attendanceTableView.getDetailView();
		
		attendanceDetailView.readCourses(courseService.getAllCourses());
		Course course = attendanceDetailView.getSelectedCourse();
		
		if (null != course) {
			course.setStudents(attendanceStatusService.getStatusForStudents(course.getStudents()));
			attendanceTableView.readData(course).update();
		}
		
		return attendanceTableView;
	}
	
	private void initAttendanceFeature() {
		
		AttendanceTableView attendanceTableView = updateAttendanceTable();
		AttendanceDetailView attendanceDetailView = (AttendanceDetailView) attendanceTableView.getDetailView();
		
		attendanceDetailView.getComboBox().addActionListener((event) -> {
			Course course = attendanceDetailView.getSelectedCourse();
			if (null != course) {
				course.setStudents(attendanceStatusService.getStatusForStudents(course.getStudents()));
			}
			attendanceTableView.readData(course).update();
		});
		
		attendanceDetailView.getButtons().get(AttendanceDetailView.SAVE_BUTTON_INDEX).addActionListener(event -> {
			Set<Student> students = attendanceTableView.getUpdatedStatusStudents();
			studentService.updateStudents(students);
			attendanceTableView.update();
		});
	}
	
	@Override
	public void start() {
		main.open();
	}
}
