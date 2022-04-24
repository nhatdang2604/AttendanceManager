package com.nhatdang2604.view.frame.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.nhatdang2604.model.entity.Course;
import com.nhatdang2604.model.entity.Schedule;
import com.nhatdang2604.model.entity.Student;
import com.nhatdang2604.model.entity.Subject;
import com.nhatdang2604.model.entity.SubjectWeek;

public class CourseForm extends JDialog {

	private JPanel centerPanel;
	private JPanel footerPanel;
	private JPanel contentPane;
	private List<JLabel> labels;
	private JComboBox<Subject> subjectComboBox;
	private TimePicker timePicker;
	private DatePicker startDatePicker;
	private DatePicker endDatePicker;	
	private JComboBox<String> weekDayComboBox;
	private JButton okButton;
	private JButton cancelButton;
	
	private JButton weekButton;
	private ChangeSubjectWeekInCourseForm weekForm;
	
	private JButton addStudentButton;
	private AddStudentToCourseForm addStudentForm;
	
	private Course model;
	private List<Subject> availableSubjects;
	private JLabel warningText;	
	
	public CourseForm setError(int errorCode) {
		
		//Login form only have warning type = 0: Wrong username or password
		if (0 == errorCode) {
			warningText.setText("Phải chọn một Môn học");
		}
		
		return this;
	}
	
	private void initComponents() {
		warningText = new JLabel();					
		warningText.setForeground(Color.RED);		//Warning have red text
		
		contentPane = new JPanel();
		
		footerPanel = new JPanel();
		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		
		centerPanel = new JPanel();
		labels = new ArrayList<>(Arrays.asList(
				new JLabel("Môn học"),
				new JLabel("Ngày bắt đầu"),
				new JLabel("Ngày kết thúc"),
				new JLabel("Thời gian"),
				new JLabel("Thứ trong tuần")));
		
		subjectComboBox = new JComboBox<>();
		
		weekDayComboBox = new JComboBox<>();
		for(String weekDay: Schedule.WEEK_DAYS) {
			weekDayComboBox.addItem(weekDay);
		}
		
		timePicker = new TimePicker(); 
		timePicker.setTimeToNow();
		
		startDatePicker = new DatePicker();
		startDatePicker.setDateToToday();
		
		endDatePicker = new DatePicker();
		endDatePicker.setDateToToday();
		
		weekButton = new JButton("Chỉnh sửa " + SubjectWeek.NUMBER_OF_WEEKS_PER_COURSE + " tuần");
		weekForm = new ChangeSubjectWeekInCourseForm(this);
		
		addStudentForm = new AddStudentToCourseForm(this);
		addStudentButton = new JButton("Thêm học sinh");
		
		initButtons();
	}
	
	private void initButtons() {
		cancelButton.addActionListener((event)->{
			this.dispose();
		});
		
		addStudentButton.addActionListener((event)->{
			addStudentForm.setVisible(true);
		});
		
		weekButton.addActionListener((event) -> {
			
			weekForm
			.setModel(model.getSchedule())
			.setVisible(true);
		});
		
		addStudentForm.getOkButton().addActionListener((event) -> {
			model.setStudents(addStudentForm.submit());
			addStudentForm.setVisible(false);
		});
		
		weekForm.getOkButton().addActionListener((event) -> {
			Schedule schedule = model.getSchedule();
			schedule.setSubjectWeeks(weekForm.submit());
			weekForm.setVisible(false);
		});
	}
	
	private void setLayout() {
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		contentPane.add(footerPanel, BorderLayout.SOUTH);
		footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		footerPanel.add(okButton);
		footerPanel.add(cancelButton);
		
		contentPane.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		centerPanel.add(warningText, "6, 2, center, default");
		for (int i = 0; i<labels.size(); ++i) {
			String metaLayout = "4, " + (i+2)*2 + ", right, default";
			centerPanel.add(labels.get(i), metaLayout);
		}
		
		centerPanel.add(subjectComboBox, "6, 4, fill, default");
		centerPanel.add(startDatePicker, "6, 6");
		centerPanel.add(endDatePicker, "6, 8");
		centerPanel.add(timePicker, "6, 10");
		centerPanel.add(weekDayComboBox, "6, 12, fill, default");
		centerPanel.add(weekButton, "6, 14, fill, default");
		centerPanel.add(addStudentButton, "6, 16, fill, default");
	}
	
	public void init() {
		initComponents();
		setLayout();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		setContentPane(contentPane);
		
	}
	
	private void initModel() {
		model = new Course();
		model.setStudents(new TreeSet<Student>());
		model.setSubject(new Subject());
		model.setSchedule(new Schedule());
		weekForm.setModel(model.getSchedule());
	}
	
	/**
	 * Create the frame.
	 */
	public CourseForm() {
		init();
		initModel();
	}

	public CourseForm(Course model, JFrame owner) {
		super(owner, true);
		init();
		setModel(model);
	}
	
	public CourseForm(JFrame owner) {
		super(owner, true);
		init();
		initModel();
	}
	
	
	public JButton getOkButton() {return okButton;}
	public JDialog setModel(Course model) {
		this.model = model;
		subjectComboBox.setSelectedItem(model.getSubject());
		
		Schedule schedule = model.getSchedule();
		if (null != schedule) {
			startDatePicker.setDate(schedule.getStartDate());
			endDatePicker.setDate(schedule.getEndDate());
			timePicker.setTime(schedule.getTime());
		}
		
		return this;
	}
	public CourseForm setAvailableSubjects(List<Subject> subjects) {
		this.availableSubjects = subjects;
		subjects.forEach(subject -> {
			subjectComboBox.addItem(subject);
		});
		
		return this;
	}
	
	public Course submit() {
		
		model.setSubject((Subject) subjectComboBox.getSelectedItem());
		Schedule schedule = model.getSchedule();
		
		Set<Student> students = addStudentForm.submit();
		model.setStudents(students);
		
		if (null == schedule) {
			schedule = new Schedule();
			schedule.setCourse(model);
			model.setSchedule(schedule);
			weekForm.setModel(schedule);
		}
		
		schedule.setStartDate(startDatePicker.getDate());
		schedule.setEndDate(endDatePicker.getDate());
		schedule.setTime(timePicker.getTime());
		schedule.setWeekDay((String) weekDayComboBox.getSelectedItem());
		
		List<SubjectWeek> weeks = weekForm.submit();
		schedule.setSubjectWeeks(weeks);
	
		return model;
	}
	
	public AddStudentToCourseForm getAddStudentForm() {return addStudentForm;}
	public ChangeSubjectWeekInCourseForm getWeekForm() {return weekForm;}
	
	public JButton getSubmitButton() {
		return okButton;
	}
}
