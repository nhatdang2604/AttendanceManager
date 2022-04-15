package com.nhatdang2604.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class Course implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2197517254423590562L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@OneToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	@JoinColumn(
			name = "subject_id", 
			referencedColumnName = "id")
	private Subject subject;
	
	@ManyToMany(
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE,
					CascadeType.DETACH,
					CascadeType.REFRESH},
			fetch = FetchType.LAZY)
	@JoinTable(
			name = "course_student",
			joinColumns = @JoinColumn(name = "course_id"),
			inverseJoinColumns = @JoinColumn(name = "student_id"))
	private List<Student> students;

	@OneToOne(mappedBy = "id")
	private Schedule schedule;
	
	public Course() {
		//do nothing
	}
	
	public Course(Subject subject, List<Student> students) {
		this.subject = subject;
		this.students = students;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	
	
}
