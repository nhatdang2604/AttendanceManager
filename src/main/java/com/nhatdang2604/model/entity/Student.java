package com.nhatdang2604.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "student")
@PrimaryKeyJoinColumn(foreignKey=@ForeignKey(name = "fk_student_base"))
public class Student extends BaseUserRole implements Comparable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7570950875716299948L;

	
	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = "student")
	private List<StudentAttendanceStatus> statuses;
	
	@ManyToMany(
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE,
					CascadeType.DETACH,
					CascadeType.REFRESH},
			fetch = FetchType.LAZY)
	@JoinTable(
			name = "course_student",
			joinColumns = @JoinColumn(name = "student_id"),
			inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> courses;
	
	//Constructors
	public Student() {
		super();
	}
	
	public Student(User user, String firstName, String lastName,
			List<StudentAttendanceStatus> statuses, List<Course> courses) {
		super(user, firstName, lastName);
		this.statuses = statuses;
		this.courses = courses;
	}

	//Getters
	public List<StudentAttendanceStatus> getStatuses() {return statuses;}
	public List<Course> getCourses(){return courses;}
	
	//Setters
	public void setStatuses(List<StudentAttendanceStatus> statuses) {this.statuses = statuses;}
	public void setCourses(List<Course> courses)  {this.courses = courses;}
	
	//Utitlity to add student to course
	public Student add(Course course) {
			
		if (null == courses) {
			courses = new ArrayList<>();
		}
			
			courses.add(course);
			
			return this;
		}

	//Utitlity to add student to course
	public Student add(List<StudentAttendanceStatus> statuses) {
				
		if (null == this.statuses) {
			this.statuses = new ArrayList<>();
		}
			
			this.statuses.addAll(statuses);
		
			return this;
		}

	@Override
	public String toString() {
		return "Student [getUser()=" + getUser() + ", getFirstName()=" + getFirstName() + ", getLastName()="
				+ getLastName() + "]";
	}

	@Override
	public int compareTo(Object arg0) {
		
		if (null == arg0) return 1;
		Student another = (Student) arg0;
		
		int result = 
				(this.getId() > another.getId()?1:
					(this.getId() < another.getId()? -1: 0));
		
		return result;
	}
	
}
