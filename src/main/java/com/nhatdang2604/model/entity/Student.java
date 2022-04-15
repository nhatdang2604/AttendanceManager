package com.nhatdang2604.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7570950875716299948L;

	//Attributes
	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;

	@OneToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn(
			name = "id", 
			referencedColumnName = "id")
	private User user;

	@OneToMany(
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE,
					CascadeType.DETACH,
					CascadeType.REFRESH},
			fetch = FetchType.LAZY,
			mappedBy = "student_id")
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
		//do nothing
	}
	
	public Student(String firstName, String lastName, User user, 
			List<StudentAttendanceStatus> statuses, List<Course> courses) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.user = user;
		this.statuses = statuses;
		this.courses = courses;
	}

	//Getters
	public Integer getId() {return id;}
	public String getFirstName() {return firstName;}
	public String getLastName() {return lastName;}
	public User getUser() {return user;}
	public List<StudentAttendanceStatus> getStatuses() {return statuses;}
	public List<Course> getCourses(){return courses;}
	
	//Setters
	public void setId(Integer id) {this.id = id;}
	public void setFirstName(String firstName) {this.firstName = firstName;}
	public void setLastName(String lastName) {this.lastName = lastName;}
	public void setUser(User user) {this.user = user;}
	public void setStatuses(List<StudentAttendanceStatus> statuses) {this.statuses = statuses;}
	public void setCourses(List<Course> courses)  {this.courses = courses;}
	
}
