package com.nhatdang2604.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
		name = "subject",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "course_id")
		})
public class Subject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1701738028659622846L;

	//Fields
	
	//True id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	//Id for readablility
	@Column(name = "course_id")
	private String courseId;
	
	@Column(name = "name")
	private String name;

	//Constructors
	public Subject() {
		//do nothing
	}
	
	public Subject(String name) {
		this.name = name;
	}
	
	public Subject(String courseId, String name) {
		this.courseId = courseId;
		this.name = name;
	}
	
	//Getters
	public Integer getId() {return id;}
	public String getCourseId() {return courseId;}
	public String getName() {return name;}

	//Setters
	public void setName(String name) {this.name = name;}
	public void setId(Integer id) {this.id = id;}
	public void setCourseId(String courseId) {this.courseId = courseId;}
	
	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + "]";
	}
	
}
