package com.nhatdang2604.model.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "ministry")
public class Ministry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4725922612432114732L;
	
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

	//Constructors
	public Ministry() {
		//do nothing
	}
	
	public Ministry(String firstName, String lastName, User user) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.user = user;
	}
	//Getters
	public Integer getId() {return id;}
	public String getFirstName() {return firstName;}
	public String getLastName() {return lastName;}
	public User getUser() {return user;}

	//Setters
	public void setId(Integer id) {this.id = id;}
	public void setFirstName(String firstName) {this.firstName = firstName;}
	public void setLastName(String lastName) {this.lastName = lastName;}
	public void setUser(User user) {this.user = user;}
	
	
	
}
