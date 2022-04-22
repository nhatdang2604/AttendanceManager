package com.nhatdang2604.view.frame.form;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.nhatdang2604.model.entity.Student;
import com.nhatdang2604.model.entity.User;
import com.nhatdang2604.view.display_feature_view.table.StudentTableView;

public class StudentForm extends JDialog {

	//Label for each field
	private List<JLabel> labels;
	
	//Text fields for each label
	private List<JTextField> fields;
	
	//Ok and Cancel button
	private List<JButton> buttons;
	
	public static final int OK_BUTTON_INDEX = 0;
	public static final int CANCEL_BUTTON_INDEX = 1;
	
	private Student model;
	
	private void initComponents() {
		
		labels = new ArrayList<>(
				Arrays.asList(
						new JLabel("  " + StudentTableView.COLUMN_NAMES[StudentTableView.LAST_NAME_COLUMN_INDEX], JLabel.LEADING),
						new JLabel("  " + StudentTableView.COLUMN_NAMES[StudentTableView.FIRST_NAME_COLUMN_INDEX], JLabel.LEADING)));
		
		//Text fields for each label
		fields = new ArrayList<>(
				Arrays.asList(
						new JTextField(),
						new JTextField()));
		
		//Ok and Cancel button
		buttons = new ArrayList<>(
				Arrays.asList(
						new JButton("Tạo"),
						new JButton("Hủy")));
		
		//Close the form if cancel is pressed
		buttons.get(CANCEL_BUTTON_INDEX).addActionListener((event) -> this.dispose());
	}
	
	private void setLayout() {
		int formRowCount = labels.size();
		setLayout(new GridLayout(formRowCount + 1, 2));
		
		for (int i = 0; i < formRowCount; ++i) {
			JLabel label = labels.get(i);
			JTextField field = fields.get(i);
			
			//label.setLabelFor(field);
			add(label);
			add(field);
		}
		
		//Add a dummy panel for spaceing
		add(new JPanel());
		
		//Add panel for Ok and Cancel button
		JPanel buttonPanel = new JPanel();
		buttons.forEach((button) -> buttonPanel.add(button));
		add(buttonPanel);

	}
	
//	private Student makeDefaultStudent() {
//		Student student = new Student();
//		User user = new User();
//		
//		//Init important elements for user
//		
//		
//		student.setUser(user);
//		
//		return student;
//	}
	
	private void init() {
		initComponents();
		setLayout();
		
		setBounds(400, 200, 300, 150);
		setResizable(false);
	}
	
	public StudentForm(Student model, JFrame owner) {
		super(owner, true);
		init();
		setModel(model);
	}
	
	public StudentForm(JFrame owner) {
		super(owner, true);
		init();
		
		model = new Student();
		model.setUser(new User());
	}
	

	public Student submit() {
		
		model.setLastName(fields.get(0).getText().trim());
		model.setFirstName(fields.get(1).getText().trim());
		
		return model;
	}
	
	public StudentForm setModel(Student student) {
		model = student;
		
		fields.get(0).setText(model.getLastName());;
		fields.get(1).setText(model.getFirstName());
		
		return this;
	}
	
	

	public JButton getSubmitButton() {
		return buttons.get(OK_BUTTON_INDEX);
	}
}
