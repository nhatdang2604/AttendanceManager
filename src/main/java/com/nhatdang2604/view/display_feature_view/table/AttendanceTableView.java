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

public class AttendanceTableView extends JTable implements ITableViewBehaviour{

	protected JPanel headerView;
	protected BaseDetailView detailView;
	protected DefaultTableModel tableModel;
	protected Course courseModel;
	protected List<List<StudentAttendanceStatus>> statusMatrix;
	
//	public static final String[] COLUMN_NAMES = {
//			"STT", "Mã số sinh viên", "Tên môn học", "Thao tác", "Chọn"
//	};
	
	private static final int NON_STATUS_TYPE_COLUMN_COUNT = 4;
	protected int numberOfWeeks;
	protected String[] columnNames;
	
	public static final int COLUMN_INDEX = 0;
	public static final int STUDENT_ID_COLUMN_INDEX = 1;
	public static final int LAST_NAME_COLUMN_INDEX = 2;
	public static final int FIRST_NAME_COLUMN_INDEX = 3;
	
	protected void setupModelTable() {
		
		//Setup column name dynamically
		int columnCount = NON_STATUS_TYPE_COLUMN_COUNT + numberOfWeeks;
		columnNames = new String[columnCount];
		columnNames[0] = "STT";
		columnNames[1] = "Mã số sinh viên";
		columnNames[2] = "Họ";
		columnNames[3] = "Tên";
		
		
		for (int i = 0; i < numberOfWeeks; ++i) {
			columnNames[NON_STATUS_TYPE_COLUMN_COUNT + i] = "W" + (i + 1);
		}
		
		
		//Make uneditable table
		tableModel = new DefaultTableModel(columnNames, 0) {			
			
			@Override
			public boolean isCellEditable(int row, int column) {				
						
				//Make Update and Select button cell editable
				if (COLUMN_INDEX  == column ||
					STUDENT_ID_COLUMN_INDEX  == column || 
					LAST_NAME_COLUMN_INDEX == column || 
					FIRST_NAME_COLUMN_INDEX == column) {
					
					return false;
				}
						
				//all cells false
				return true;
			}
			
		};
	
		
		
		//Enable table model
		this.setModel(tableModel);
		
		//Setup column 4 -> 4 + numberOfWeeks to use JComboBox
		TableColumnModel columnModel = this.getColumnModel();
		for (int i = 0; i < numberOfWeeks; ++i) {
			TableColumn column = columnModel.getColumn(NON_STATUS_TYPE_COLUMN_COUNT + i);
			column.setPreferredWidth(50);
			column.setCellEditor(
					new DefaultCellEditor(
							new JComboBox<String>(
									StudentAttendanceStatus.ATTENDANCE_STATUS)));
		}
	}
	
	public Set<Student> getUpdatedStatusStudents() {
		
		//Full scan the table to get all the selected courses
		Set<Student> result = courseModel.getStudents();
		
		int size = tableModel.getRowCount();
		for (int i = 0; i < size; ++i) {
			for(int j = 0; j < numberOfWeeks; ++j) {
				String attendanceStatus = (String) tableModel.getValueAt(i, j + NON_STATUS_TYPE_COLUMN_COUNT);
				//statusMatrix.get(i).get(j).setAttendanceStatus(attendanceStatus);
				
				for (Student student: result) {
					List<StudentAttendanceStatus> statuses = student.getStatuses();
					for (StudentAttendanceStatus status: statuses) {
						
						Integer mapperWeekIdx = statusMatrix.get(i).get(j).getSubjectWeek().getWeekIndex();
						Integer mappeeWeekIdx = status.getSubjectWeek().getWeekIndex();
						
						Integer mapperCourseId = statusMatrix.get(i).get(j).getSubjectWeek().getSchedule().getCourse().getId();
						Integer mappeeCourseId = status.getSubjectWeek().getSchedule().getCourse().getId();
						
						if (mapperWeekIdx.equals(mappeeWeekIdx) &&
							mapperCourseId.equals(mappeeCourseId)) {
							
							status.setAttendanceStatus(attendanceStatus);
						}
					}
				}
			}
		}
		
		courseModel.setStudents(result);
		return result;
	}
	
	
	public AttendanceTableView() {
		
		this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		numberOfWeeks = SubjectWeek.NUMBER_OF_WEEKS_PER_COURSE;
		detailView = new AttendanceDetailView("Điểm danh");
		
		//Dummy header: nothing in header
		headerView = new JPanel();
		
	}
	
	public AttendanceTableView clearData() {
		
		//Clear the model
		tableModel.setRowCount(0);
		
		return this;
	}
	
	public AttendanceTableView readData(Course data) {
		courseModel = data;
		setupModelTable();
		return this;
	}
	
	public AttendanceTableView update() {
		
		clearData();
		if (null == courseModel) return this;
		statusMatrix = new ArrayList<>();
		Set<Student> students = courseModel.getStudents();
		Integer index = 0;
		
		for (Student student: students) {
			++index;
			Object[] row = new Object[NON_STATUS_TYPE_COLUMN_COUNT + numberOfWeeks];
			row[0] = index;
			row[1] = student.getId();
			row[2] = student.getLastName();
			row[3] = student.getFirstName();
			
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
						.equals(courseModel.getId()))
					.collect(Collectors.toList());
			
			statusMatrix.add(statuses);
			if (null != statuses && statuses.size() > 0) {
				for (int i = 0; i  < numberOfWeeks; ++i) {
					row[i + NON_STATUS_TYPE_COLUMN_COUNT] = statuses.get(i).getAttendanceStatus();
				}
				tableModel.addRow(row);
			}
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
