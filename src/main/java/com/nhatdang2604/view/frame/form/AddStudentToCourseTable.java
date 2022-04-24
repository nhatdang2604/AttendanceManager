package com.nhatdang2604.view.frame.form;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.nhatdang2604.model.entity.Student;

public class AddStudentToCourseTable extends JTable {

	protected DefaultTableModel tableModel;
	protected List<Student> studentModels;
	protected Set<Student> alreadyChosenStudents;
	
	public static final String[] COLUMN_NAMES = {
			"STT", "Mã số sinh viên", "Họ", "Tên", "Chọn"
	};
	
	public static final int COLUMN_INDEX = 0;
	public static final int STUDENT_ID_COLUMN_INDEX = 1;
	public static final int LAST_NAME_COLUMN_INDEX = 2;
	public static final int FIRST_NAME_COLUMN_INDEX = 3;
	public static final int SELECT_COLUMN_INDEX = 4;
	
	protected void setupModelTable() {
		//Make uneditable table
		tableModel = new DefaultTableModel(COLUMN_NAMES, 0) {			
					
			@Override
			public boolean isCellEditable(int row, int column) {				
						
				//Make Update and Select button cell editable
				if (SELECT_COLUMN_INDEX == column) {
					return true;
				}
						
				//all cells false
				return false;
			}
			
			@Override
		    public Class<?> getColumnClass(int columnIndex) {
				Class clazz = String.class;
				switch (columnIndex) {
				case COLUMN_INDEX:
					clazz = Integer.class;
					break;
				case STUDENT_ID_COLUMN_INDEX:
					clazz = Integer.class;
					break;
				case SELECT_COLUMN_INDEX:
					clazz = Boolean.class;
					break;
		      }
		      return clazz;
		    }
			
		};
				
		//Enable table model
		this.setModel(tableModel);
		
		
	}
	

	public AddStudentToCourseTable() {		
		setupModelTable();
	
	}
	
	public AddStudentToCourseTable clearData() {
		
		//Clear the model
		tableModel.setRowCount(0);
		
		return this;
	}
	
	public AddStudentToCourseTable readData(List<Student> data) {
		studentModels = data;
		return this;
	}
	
	public AddStudentToCourseTable update() {
		
		clearData();
		final int size = (null != studentModels?studentModels.size():0);
		for (int index = 0; index < size; ++index) {
			
			Student student = studentModels.get(index);
			Object[] row = {index + 1, student.getId(), student.getLastName(), student.getFirstName()};
			tableModel.addRow(row);		
		}
		
		
		if (null != alreadyChosenStudents) {
			alreadyChosenStudents.forEach(student -> {

				for (int i = 0; i < size; ++i) {
					if (student.getId().equals(studentModels.get(i).getId())) {
						tableModel.setValueAt(new Boolean(true), i, SELECT_COLUMN_INDEX);
						break;
					}
				}
			});
		}
		
		return this;
	}
	
	public Student getSelectedStudent() {
		int selectedIndex = this.getSelectedRow();
		return studentModels.get(selectedIndex);
	}
	
	public Set<Student> getSelectedStudents() {
		
		//Full scan the table to get all the selected student
		Set<Student> result = new TreeSet<>();
		int size = tableModel.getRowCount();
		for (int i = 0; i < size; ++i) {
			Boolean isSelected = (Boolean) tableModel.getValueAt(i, SELECT_COLUMN_INDEX);
			if (null != isSelected && isSelected) {
				result.add(studentModels.get(i));
			}
			
		}
		
		return result;
	}
	
	public AddStudentToCourseTable setAlreadyChosenStudents(Set<Student> students) {
		this.alreadyChosenStudents = students;
		return this;
	}
}
