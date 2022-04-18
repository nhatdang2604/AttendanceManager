package com.nhatdang2604.model.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
			referencedColumnName = "id",
			nullable = true)
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
	private Set<Student> students;

	@OneToOne(
			mappedBy = "course",
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			optional = true)
	private Schedule schedule;
	
	public Course() {
		//do nothing
	}
	
	public Course(Subject subject, Set<Student> students, Schedule schedule) {
		this.subject = subject;
		this.students = students;
		this.schedule = schedule;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
		schedule.setCourse(this);
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

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", subject=" + subject + ", students=" + students + "]";
	}
	
	//Utitlity to add student to course
	public Course add(Student student) {
		
		if (null == students) {
			this.students = new HashSet<>();
		}
		
		this.students.add(student);
		
		return this;
	}
	
	//Utitlity to add student to course
	public Course add(Set<Student> students) {
	
		this.students = students;
			
		return this;
	}
		
}
