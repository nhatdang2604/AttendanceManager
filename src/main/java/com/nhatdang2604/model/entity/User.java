package com.nhatdang2604.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2075932010644137075L;
	
	//Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "user_name")
	private String username;
	
	@Column(name = "encrypted_password")
	private String encryptedPassword;
	
	@Column(name = "role")
	private String role;
	
	//Constructors
	public User() {
		//do nothing
	}

	public User(Integer id, String username, String encryptedPassword, String role) {
		this.id = id;
		this.username = username;
		this.encryptedPassword = encryptedPassword;
		this.role = role;
	}

	//Getters
	public Integer getId() {return id;}
	public String getUsername() {return username;}
	public String getEncryptedPassword() {return encryptedPassword;}
	public String getRole() {return role;}

	//Setters
	public void setId(Integer id) {this.id = id;}
	public void setUsername(String username) {this.username = username;}	
	public void setEncryptedPassword(String encryptedPassword) {this.encryptedPassword = encryptedPassword;}
	public void setRole(String role) {this.role = role;}
	
	
	
	
}
