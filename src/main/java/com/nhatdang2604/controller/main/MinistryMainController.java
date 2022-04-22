package com.nhatdang2604.controller.main;

import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import com.nhatdang2604.model.entity.Student;
import com.nhatdang2604.service.StudentService;
import com.nhatdang2604.service.i.IStudentService;
import com.nhatdang2604.view.display_feature_view.StudentFeatureView;
import com.nhatdang2604.view.display_feature_view.detail.CRUD_DetailView;
import com.nhatdang2604.view.display_feature_view.display_table.StudentDisplayTableView;
import com.nhatdang2604.view.display_feature_view.table.StudentTableView;
import com.nhatdang2604.view.frame.MinistryMainFrame;
import com.nhatdang2604.view.frame.form.StudentForm;

public class MinistryMainController extends BaseMainController {

	private IStudentService studentService;
	
	public MinistryMainController() {
		super(new MinistryMainFrame());
		studentService = StudentService.INSTANCE;
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
				JOptionPane.showMessageDialog(null, "Đã xóa thành công.");
			}
	      
		});;
		
	}
	
	public void initStudentFeature() {
		StudentFeatureView studentFeatureView = (StudentFeatureView) main.getFeatureViews().get(MinistryMainFrame.STUDENT_FEATURE_INDEX);
		StudentDisplayTableView studentDisplayTableView = studentFeatureView.getDisplayTableView();
		StudentTableView studentTableView = studentDisplayTableView.getStudentTableView();

		studentTableView.readData(studentService.getAllStudents()).update();
		
		initCreateOnStudentFeature(studentTableView);
		initUpdateOnStudentFeature(studentTableView);
		initDeleteOnStudentFeature(studentTableView);
		
	}
	
	@Override
	public void start() {
		initStudentFeature();
		main.open();
	}
}
