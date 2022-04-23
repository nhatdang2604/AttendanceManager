package com.nhatdang2604.view.frame.form;

import java.awt.Color;
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

import com.nhatdang2604.model.entity.Subject;
import com.nhatdang2604.view.display_feature_view.table.SubjectTableView;

public class SubjectForm extends JDialog {

	//Label for each field
	private List<JLabel> labels;
	private JLabel warningText;
	
	//Text fields for each label
	private List<JTextField> fields;
	
	//Ok and Cancel button
	private List<JButton> buttons;
	
	public static final int OK_BUTTON_INDEX = 0;
	public static final int CANCEL_BUTTON_INDEX = 1;
	
	private Subject model;
	
	private void initComponents() {
		
		warningText = new JLabel();	
		warningText.setForeground(Color.RED);		//Warning have red text
		
		labels = new ArrayList<>(
				Arrays.asList(
						new JLabel("  " + SubjectTableView.COLUMN_NAMES[SubjectTableView.SUBJECT_ID_COLUMN_INDEX], JLabel.LEADING),
						new JLabel("  " + SubjectTableView.COLUMN_NAMES[SubjectTableView.NAME_COLUMN_INDEX], JLabel.LEADING)));
		
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
	
	public SubjectForm setError(int errorCode) {
		
		if (0 == errorCode) {
			warningText.setText("Mã môn học đã tồn tại");
		}
		
		return this;
	}
	
	private void setLayout() {
		int formRowCount = labels.size();
		setLayout(new GridLayout(formRowCount + 2, 2));
		
		add(warningText);
		add(new JPanel());	//dummy panel for padding
		
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
	
	private void init() {
		initComponents();
		setLayout();
		
		setBounds(400, 200, 300, 150);
		setResizable(false);
	}
	
	public SubjectForm(Subject model, JFrame owner) {
		super(owner, true);
		init();
		setModel(model);
	}
	
	public SubjectForm(JFrame owner) {
		super(owner, true);
		init();
		
		model = new Subject();
	}
	

	public Subject submit() {
		
		model.setSubjectId(fields.get(0).getText().trim());
		model.setName(fields.get(1).getText().trim());
		
		return model;
	}
	
	public SubjectForm setModel(Subject subject) {
		model = subject;
		
		fields.get(0).setText(model.getSubjectId());;
		fields.get(1).setText(model.getName());
		
		return this;
	}
	
	

	public JButton getSubmitButton() {
		return buttons.get(OK_BUTTON_INDEX);
	}
}
