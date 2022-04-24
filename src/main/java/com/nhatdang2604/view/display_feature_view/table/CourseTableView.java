package com.nhatdang2604.view.display_feature_view.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.nhatdang2604.model.entity.Course;
import com.nhatdang2604.view.display_feature_view.detail.BaseDetailView;
import com.nhatdang2604.view.display_feature_view.detail.CourseDetailView;
import com.nhatdang2604.view.widget.ButtonEditor;
import com.nhatdang2604.view.widget.ButtonRenderer;

public class CourseTableView extends JTable implements ITableViewBehaviour{

	protected JPanel headerView;
	protected BaseDetailView detailView;
	protected DefaultTableModel tableModel;
	protected List<Course> courseModels;
	
	protected JButton updateButton;
	
	public static final String[] COLUMN_NAMES = {
			"STT", "Mã lớp học", "Tên môn học", "Thao tác", "Chọn"
	};
	
	public static final int COLUMN_INDEX = 0;
	public static final int COURSE_ID_COLUMN_INDEX = 1;
	public static final int NAME_COLUMN_INDEX = 2;
	public static final int UPDATE_COLUMN_INDEX = 3;
	public static final int SELECT_COLUMN_INDEX = 4;
	
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
				case COURSE_ID_COLUMN_INDEX:
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
		TableColumn updateColumn = this.getColumn(COLUMN_NAMES[UPDATE_COLUMN_INDEX]);
		updateColumn.setCellRenderer(new ButtonRenderer(updateButtonName));
		updateColumn.setCellEditor(new ButtonEditor(updateButton));			
	
	}
		
	public CourseTableView() {
		
		setupModelTable();
		initUpdateButton();
		
		detailView = new CourseDetailView("Lớp học") ;
		
		//Dummy header: nothing in header
		headerView = new JPanel();
		
		//Add action when click a row in table:
		//	Pop out the detail information in the detail panel
		this.getSelectionModel().addListSelectionListener(event -> {
			if (this.getSelectedRow() > -1) {
				detailView.setDataToDetailPanel(this.getSelectedCourse());
			}
		});
		
	}
	
	public CourseTableView clearData() {
		
		//Clear the model
		tableModel.setRowCount(0);
		
		return this;
	}
	
	public CourseTableView readData(List<Course> data) {
		courseModels = data;
		return this;
	}
	
	public CourseTableView update() {
		
		clearData();
		int size = courseModels.size();
		for (int index = 0; index < size; ++index) {
			
			Course course = courseModels.get(index);
			Object[] row = {index + 1, course.getId(), course.getSubject().getName()};
			tableModel.addRow(row);		
		}
		return this;
	}
	
	public Course getSelectedCourse() {
		int selectedIndex = this.getSelectedRow();
		return courseModels.get(selectedIndex);
	}
	
	public List<Course> getSelectedCourses() {
		
		//Full scan the table to get all the selected courses
		List<Course> result = new ArrayList<>();
		int size = tableModel.getRowCount();
		for (int i = 0; i < size; ++i) {
			Boolean isSelected = (Boolean) tableModel.getValueAt(i, SELECT_COLUMN_INDEX);
			if (null != isSelected && isSelected) {
				result.add(courseModels.get(i));
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
