package com.nhatdang2604.view.frame.form;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.DatePicker;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.nhatdang2604.model.entity.Schedule;
import com.nhatdang2604.model.entity.SubjectWeek;

public class ChangeSubjectWeekInCourseForm extends JDialog {

	private final int numberOfWeeks;

	private List<JLabel> labels;
	private List<DatePicker> datePickers;
	
	private JPanel contentPane;
	private JPanel centerPanel;
	private JPanel footerPanel;
	private JScrollPane scrollPane;
	
	private JButton okButton;
	private JButton cancelButton;
	
	private List<SubjectWeek> models;

	
	private void initComponents() {
		
		contentPane = new JPanel();
		
		footerPanel = new JPanel();
		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		
		centerPanel = new JPanel();
		scrollPane = new JScrollPane(centerPanel);
		labels = new ArrayList<>();
		datePickers = new ArrayList<>();
		for (int i = 1; i <= numberOfWeeks; ++i) {
			labels.add(new JLabel("Tuần thứ " + i));
			DatePicker datePicker = new DatePicker();
			datePicker.setDateToToday();
			datePickers.add(datePicker);
		}
		
		initButtons();
	
	}
	
	private void initButtons() {
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
		
		List<RowSpec> rowSpecHolder = new ArrayList<>();
		
		for (int i = 0; i < numberOfWeeks + 1; ++i) {
			rowSpecHolder.add(FormSpecs.RELATED_GAP_ROWSPEC);
			rowSpecHolder.add(FormSpecs.DEFAULT_ROWSPEC);
		}
		
		RowSpec[] rowSpecs = new RowSpec[rowSpecHolder.size()];
		centerPanel.setLayout(new FormLayout(
				new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			
				rowSpecHolder.toArray(rowSpecs)));
		
		
		for (int i = 0; i < numberOfWeeks; ++i) {
			String labelMetaLayout = "4, " + (i + 2) * 2 + ", right, default";
			String datePickerMetaLayout = "6, "+ (i + 2) * 2 + ", fill, default";
			
			centerPanel.add(labels.get(i), labelMetaLayout);
			centerPanel.add(datePickers.get(i), datePickerMetaLayout);
		}
		
		
	}
	
	public void init() {
		initComponents();
		setLayout();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		setContentPane(contentPane);
	}
	
	
	
	private void initModels(Schedule schedule) {
		
		if (	null == schedule ||
				null == schedule.getSubjectWeeks() ||
				numberOfWeeks != schedule.getSubjectWeeks().size()) {
			
			models = new ArrayList<>();
			for (int i = 0; i < numberOfWeeks; ++i) {
				SubjectWeek subjectWeek = new SubjectWeek(schedule, i + 1, LocalDate.now());
				models.add(subjectWeek);
			}
			
		} else {
			models = schedule.getSubjectWeeks();
		}		
		
	}


	public ChangeSubjectWeekInCourseForm(JDialog owner) {
		super(owner, true);
		numberOfWeeks = SubjectWeek.NUMBER_OF_WEEKS_PER_COURSE;
		init();
		setModel(null);
	}
	
	public JButton getOkButton() {return okButton;}
	public JDialog setModel(Schedule model) {
		
		initModels(model);
		for (int i = 0; i < numberOfWeeks; ++i) {
			datePickers.get(i).setDate(models.get(i).getDate());
		}
		
		return this;
	}
	
	
	public List<SubjectWeek> submit() {
		
		for (int i = 0; i < numberOfWeeks; ++i) {
			models.get(i).setDate(datePickers.get(i).getDate());
		}
		
		return models;
	}
	
}
