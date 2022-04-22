package com.nhatdang2604.view.display_feature_view.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.nhatdang2604.model.entity.Student;
import com.nhatdang2604.view.display_feature_view.detail.BaseDetailView;
import com.nhatdang2604.view.display_feature_view.detail.CRUD_DetailView;
import com.nhatdang2604.view.frame.form.BaseForm;
import com.nhatdang2604.view.frame.form.LoginForm;
import com.nhatdang2604.view.widget.ButtonEditor;
import com.nhatdang2604.view.widget.ButtonRenderer;
import com.nhatdang2604.view.widget.CheckBoxEditor;
import com.nhatdang2604.view.widget.CheckBoxRenderer;

public class StudentTableView extends JTable implements ITableViewBehaviour{

	protected JPanel headerView;
	protected BaseDetailView detailView;
	protected DefaultTableModel tableModel;
	protected List<Student> studentModels;
	
	protected JButton updateButton;
	
	public static final String[] COLUMN_NAMES = {
			"STT", "Mã số sinh viên", "Họ", "Tên", "Thao tác", "Chọn"
	};
	
	public static final int COLUMN_INDEX = 0;
	public static final int STUDENT_ID_COLUMN_INDEX = 1;
	public static final int LAST_NAME_COLUMN_INDEX = 2;
	public static final int FIRST_NAME_COLUMN_INDEX = 3;
	public static final int UPDATE_COLUMN_INDEX = 4;
	public static final int SELECT_COLUMN_INDEX = 5;
	
	protected void setupModelTable() {
		//Make uneditable table
		tableModel = new DefaultTableModel(COLUMN_NAMES, 0) {			
					
			@Override
			public boolean isCellEditable(int row, int column) {				
						
				//Make Update and Select button cell editable
				if (UPDATE_COLUMN_INDEX == column ||
						SELECT_COLUMN_INDEX == column) {
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
				case UPDATE_COLUMN_INDEX:
					clazz = Boolean.class;
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
	
	protected void initUpdateButton() {
		
		//Setup for the Update button in cell
		String updateButtonName = "Cập nhật";
		updateButton = new JButton(updateButtonName);
		TableColumn updateColumn = this.getColumn(COLUMN_NAMES[4]);
		updateColumn.setCellRenderer(new ButtonRenderer(updateButtonName));
		updateColumn.setCellEditor(new ButtonEditor(updateButton));			
	
	}
		
	public StudentTableView() {
		
		setupModelTable();
		initUpdateButton();
		
		
		detailView = new CRUD_DetailView("Sinh viên", null) ;
		
		//Dummy header: nothing in header
		headerView = new JPanel();
		
	}
	
	public StudentTableView clearData() {
		
		//Clear the model
		tableModel.setRowCount(0);
		
		return this;
	}
	
	public StudentTableView readData(List<Student> data) {
		studentModels = data;
		return this;
	}
	
	public StudentTableView update() {
		
		clearData();
		int size = studentModels.size();
		for (int index = 0; index < size; ++index) {
			
			Student student = studentModels.get(index);
			Object[] row = {index + 1, student.getId(), student.getLastName(), student.getFirstName()};
			tableModel.addRow(row);		
		}
		return this;
	}
	
	public Student getSelectedStudent() {
		int selectedIndex = this.getSelectedRow();
		return studentModels.get(selectedIndex);
	}
	
	public List<Student> getSelectedStudents() {
		
		//Full scan the table to get all the selected student
		List<Student> result = new ArrayList<>();
		int size = tableModel.getRowCount();
		for (int i = 0; i < size; ++i) {
			Boolean isSelected = (Boolean) tableModel.getValueAt(i, SELECT_COLUMN_INDEX);
			if (null != isSelected && isSelected) {
				result.add(studentModels.get(i));
			}
			
		}
		
		return result;
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

	
	public JButton getUpdateButton() {
		return updateButton;
	}
}
