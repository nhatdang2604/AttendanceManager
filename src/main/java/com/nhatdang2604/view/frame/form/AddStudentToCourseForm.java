package com.nhatdang2604.view.frame.form;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.nhatdang2604.model.entity.Course;
import com.nhatdang2604.model.entity.Schedule;
import com.nhatdang2604.model.entity.Student;
import com.nhatdang2604.model.entity.Subject;

public class AddStudentToCourseForm extends JDialog {

	private JPanel footerPanel;
	private JPanel contentPane;
	
	private JButton okButton;
	private JButton cancelButton;
	private JScrollPane scrollPane;
	private AddStudentToCourseTable table;
	private Course courseModel;
	private List<Student> studentModels;
	
	private void initComponents() {
		
		
		contentPane = new JPanel();
		footerPanel = new JPanel();
		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		table = new AddStudentToCourseTable();
		scrollPane = new JScrollPane(table);
		
		//Setup for cancel button
		cancelButton.addActionListener((event)->{
			this.dispose();
		});
	}
	
	private void setLayout() {
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		contentPane.add(footerPanel, BorderLayout.SOUTH);
		footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		footerPanel.add(okButton);
		footerPanel.add(cancelButton);
		
		contentPane.add(scrollPane, BorderLayout.CENTER);
	}
	
	public void init() {
		initComponents();
		setLayout();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		setContentPane(contentPane);
	}


	public AddStudentToCourseForm(Course courseModel, List<Student> studentModels, JDialog owner) {
		super(owner, true);
		init();
		setCourseModel(courseModel);
		setStudentModels(studentModels);
		this.table.update();
	}
	
	public AddStudentToCourseForm(JDialog owner) {
		super(owner, true);
		init();
		this.table.update();
	}
	
	public JButton getOkButton() {return okButton;}
	
	public AddStudentToCourseForm setCourseModel(Course model) {
		this.courseModel = model;
		this.table.setAlreadyChosenStudents(model.getStudents());
		return this;
	}
	
	public AddStudentToCourseForm setStudentModels(List<Student> models) {
		this.studentModels = models;
		this.table.readData(studentModels);
		return this;
	}
	
	public Set<Student> submit() {
		return table.getSelectedStudents();
	}
	
	public AddStudentToCourseForm update() {
		table.update();
		return this;
	}
}
