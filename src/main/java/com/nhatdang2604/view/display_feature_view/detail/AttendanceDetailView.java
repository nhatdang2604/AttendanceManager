package com.nhatdang2604.view.display_feature_view.detail;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.nhatdang2604.model.entity.Course;

@SuppressWarnings("serial")
public class AttendanceDetailView extends BaseDetailView {
	
	protected List<JButton> buttons;
	private List<Course> courses;
	private JComboBox<Course> coursesComboBox;
	
	public static final int SAVE_BUTTON_INDEX = 0;
	
	private static final String[] DETAIL_FIELDS_NAME = {
			"Tìm kết quả điểm danh cho lớp",
	};
	
	
	private void makeButtons() {
		
		//Create buttons
		buttons = new ArrayList<>();
		buttons.add(new JButton("Lưu"));
		
		coursesComboBox = new JComboBox<>();
		paddingPanels.get(1).remove(attributeFields.get(0));
		paddingPanels.get(1).remove(attributeData.get(0));
		paddingPanels.get(1).setLayout(
				new FormLayout(new ColumnSpec[] {
						FormSpecs.DEFAULT_COLSPEC,
						ColumnSpec.decode("default:grow"),},
					new RowSpec[] {
						FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC,}));
		
		paddingPanels.get(1).add(attributeFields.get(0), "2, 4, center, default");
		paddingPanels.get(1).add(coursesComboBox, "2, 6, fill, default");
		
		//Add Buttons to detail panel
		for (JButton button: buttons) {
			paddingPanels.get(2).add(button);
		}
	}
	
	public AttendanceDetailView(String titled) {
		super(titled, DETAIL_FIELDS_NAME);
		makeButtons();	
	}
	
	public List<JButton> getButtons() {
		return buttons;
	}
	
	public JComboBox getComboBox() {
		return coursesComboBox;
	}
	
	public AttendanceDetailView readCourses(List<Course> courses) {
		this.courses = courses;
		coursesComboBox.removeAllItems();
		this.courses.forEach(course -> {
			coursesComboBox.addItem(course);
		});
		
		return this;
	}
	
	public Course getSelectedCourse() {
		return (Course) coursesComboBox.getSelectedItem();
	}
}