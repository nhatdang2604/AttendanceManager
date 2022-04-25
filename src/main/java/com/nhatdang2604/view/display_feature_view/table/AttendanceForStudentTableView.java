package com.nhatdang2604.view.display_feature_view.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.nhatdang2604.model.entity.Course;
import com.nhatdang2604.model.entity.Student;
import com.nhatdang2604.model.entity.StudentAttendanceStatus;
import com.nhatdang2604.view.display_feature_view.detail.AttendanceForStudentDetailView;
import com.nhatdang2604.view.display_feature_view.detail.BaseDetailView;

public class AttendanceForStudentTableView extends JTable implements ITableViewBehaviour{

	protected JPanel headerView;
	protected BaseDetailView detailView;
	protected DefaultTableModel tableModel;
	
	protected List<StudentAttendanceStatus> models;
	
	public static final String[] COLUMN_NAMES = {
			"STT", "Mã lớp học", "Tên môn học", "Ngày", "Điểm danh"
	};
	
	public static final int COLUMN_INDEX = 0;
	public static final int COURSE_ID_COLUMN_INDEX = 1;
	public static final int NAME_COLUMN_INDEX = 2;
	public static final int DATE_COLUMN_INDEX = 3;
	public static final int ATTENDANCE_COLUMN_INDEX = 4;
	public static final int NUMBER_OF_COLUMNS = 5;
	
	
	protected void setupModelTable() {
		//Make uneditable table
		tableModel = new DefaultTableModel(COLUMN_NAMES, 0) {			
					
			@Override
			public boolean isCellEditable(int row, int column) {				
						
				//Make attendance button cell editable
				if (ATTENDANCE_COLUMN_INDEX == column) {
					return true;
				}
						
				//all cells false
				return false;
			}
			
		};
				
	
		//Enable table model
		this.setModel(tableModel);
		
		TableColumn column = columnModel.getColumn(ATTENDANCE_COLUMN_INDEX);
		column.setCellEditor(
				new DefaultCellEditor(
						new JComboBox<String>(
								StudentAttendanceStatus.ATTENDANCE_STATUS)));
	}
		
	public AttendanceForStudentTableView() {
		
		setupModelTable();
		
		detailView = new AttendanceForStudentDetailView("Điểm danh") ;
		
		//Dummy header: nothing in header
		headerView = new JPanel();
		
	}
	
	public List<StudentAttendanceStatus> getUpdatedStatuses() {
		
		//Full scan the table to get all the selected status
		List<StudentAttendanceStatus> result = models;
		
		int size = tableModel.getRowCount();
		for (int i = 0; i < size; ++i) {
			
			String attendanceStatus = (String) tableModel.getValueAt(i, ATTENDANCE_COLUMN_INDEX);
			result.get(i).setAttendanceStatus(attendanceStatus);
		}
		
		models = result;
		return result;
	}
	
	public AttendanceForStudentTableView clearData() {
		
		//Clear the model
		tableModel.setRowCount(0);
		
		return this;
	}
	
	public AttendanceForStudentTableView readData(List<StudentAttendanceStatus> data) {
		models = data;
		return this;
	}
	
	public AttendanceForStudentTableView update() {
		
		clearData();
		int size = models.size();
	
		for (int index = 0; index < size; ++index) {
			
			Course course = models.get(index).getSubjectWeek().getSchedule().getCourse();
			Object[] row = {
					index + 1, 
					course.getId(), 
					course.getSubject().getName(), 
					models.get(index).getSubjectWeek().getDate(),
					models.get(index).getAttendanceStatus()};
			
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
