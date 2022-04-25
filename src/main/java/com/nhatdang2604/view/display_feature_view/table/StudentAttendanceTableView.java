package com.nhatdang2604.view.display_feature_view.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.nhatdang2604.model.entity.Course;
import com.nhatdang2604.model.entity.Student;
import com.nhatdang2604.model.entity.StudentAttendanceStatus;
import com.nhatdang2604.model.entity.SubjectWeek;
import com.nhatdang2604.view.display_feature_view.detail.AttendanceDetailView;
import com.nhatdang2604.view.display_feature_view.detail.BaseDetailView;

public class StudentAttendanceTableView extends JTable implements ITableViewBehaviour{

	protected JPanel headerView;
	protected BaseDetailView detailView;
	protected DefaultTableModel tableModel;
	protected Student student;
	
	private static final int NON_STATUS_TYPE_COLUMN_COUNT = 3;
	protected int numberOfWeeks;
	protected String[] columnNames;
	
	public static final int COLUMN_INDEX = 0;
	public static final int COURSE_ID_COLUMN_INDEX = 1;
	public static final int COURSE_NAME_COLUMN_INDEX = 2;
	
	protected void setupModelTable() {
		
		//Setup column name dynamically
		int columnCount = NON_STATUS_TYPE_COLUMN_COUNT + numberOfWeeks;
		columnNames = new String[columnCount];
		columnNames[0] = "STT";
		columnNames[1] = "Mã lớp";
		columnNames[2] = "Tên";
		
		
		for (int i = 0; i < numberOfWeeks; ++i) {
			columnNames[NON_STATUS_TYPE_COLUMN_COUNT + i] = "W" + (i + 1);
		}
		
		
		//Make uneditable table
		tableModel = new DefaultTableModel(columnNames, 0) {			
			
			@Override
			public boolean isCellEditable(int row, int column) {				
						
				return false;
			}
		};
	
		
		
		//Enable table model
		this.setModel(tableModel);
	}
	
	public StudentAttendanceTableView() {
		
		this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		numberOfWeeks = SubjectWeek.NUMBER_OF_WEEKS_PER_COURSE;
		
		detailView = new BaseDetailView("Kết quả điểm danh", null);
		
		//Dummy header: nothing in header
		headerView = new JPanel();
		
	}
	
	public StudentAttendanceTableView clearData() {
		
		//Clear the model
		tableModel.setRowCount(0);
		
		return this;
	}
	
	public StudentAttendanceTableView readData(Student data) {
		student = data;
		setupModelTable();
		return this;
	}
	
	public StudentAttendanceTableView update() {
		
		clearData();
		Integer index = 0;
		
		List<Course> courses = student.getCourses();
		for (Course course: courses) {
			++index;
			Object[] row = new Object[NON_STATUS_TYPE_COLUMN_COUNT + numberOfWeeks];
			row[0] = index;
			row[1] = course.getId();
			row[2] = course.getSubject().getName();
			
			//Get all the status, from a student, which in the given course
			List<StudentAttendanceStatus> statuses = student
					.getStatuses()
					.stream()
					.filter(status -> 
						status
						.getSubjectWeek()
						.getSchedule()
						.getCourse()
						.getId()
						.equals(course.getId()))
					.collect(Collectors.toList());
			
			for (int i = 0; i  < numberOfWeeks; ++i) {
				row[i + NON_STATUS_TYPE_COLUMN_COUNT] = statuses.get(i).getAttendanceStatus();
			}
			
			tableModel.addRow(row);
		}
		
		return this;
	}
	
	
	@Override
	public JPanel getHeaderView() {
		return headerView;
	}

	@Override
	public BaseDetailView getDetailView() {
		return detailView;
	}

	@Override
	public JTable getTable() {
		return this;
	}

}
