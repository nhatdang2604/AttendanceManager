package com.nhatdang2604.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "subject")
public class Subject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1701738028659622846L;

	//Fields
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private String id;
	
	@Column(name = "name")
	private String name;

	//Constructors
	public Subject() {
		//do nothing
	}
	
	public Subject(String name) {
		this.name = name;
	}
	//Getters
	public String getId() {return id;}
	public String getName() {return name;}

	//Setters
	public void setName(String name) {this.name = name;}
	public void setId(String id) {this.id = id;}
	
}
