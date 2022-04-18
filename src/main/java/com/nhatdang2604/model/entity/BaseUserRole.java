package com.nhatdang2604.model.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "base_user_role")
public abstract class BaseUserRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6131587916518800656L;

	@Id
	@Column(name = "id")
	private Integer id;
	
	//Attributes
	@OneToOne(
			cascade = {
					CascadeType.DETACH,
					CascadeType.MERGE,
					CascadeType.PERSIST,
					CascadeType.REFRESH
			},
			fetch = FetchType.LAZY)
	@MapsId
	@JoinColumn(name = "id")
	protected User user;
	
	@Column(name = "first_name")
	protected String firstName;
		
	@Column(name = "last_name")
	protected String lastName;

	public BaseUserRole() {
		//do nothing
	}
	
	public BaseUserRole(User user, String firstName, String lastName) {
		this.user = user;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
}
