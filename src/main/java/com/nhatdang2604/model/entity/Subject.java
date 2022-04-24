package com.nhatdang2604.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	private String subjectId;
	
	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "subject", 
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<Course> courses;
	
	//Constructors
	public Subject() {
		//do nothing
	}
	
	public Subject(String name) {
		this.name = name;
	}
	
	public Subject(String subjectId, String name) {
		this.subjectId = subjectId;
		this.name = name;
	}
	
	//Getters
	public Integer getId() {return id;}
	public String getSubjectId() {return subjectId;}
	public String getName() {return name;}
	public List<Course> getCourses() {return courses;}
	
	//Setters
	public void setName(String name) {this.name = name;}
	public void setId(Integer id) {this.id = id;}
	public void setSubjectId(String subjectId) {this.subjectId = subjectId;}
	public void setCourses(List<Course> courses) {this.courses = courses;}
	
	@Override
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((subjectId == null) ? 0 : subjectId.hashCode());
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
		Subject other = (Subject) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (subjectId == null) {
			if (other.subjectId != null)
				return false;
		} else if (!subjectId.equals(other.subjectId))
			return false;
		return true;
	}
	
	
}
