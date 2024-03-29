package com.nhatdang2604.model.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {
	
	public enum USER_ROLE {
		Role_Student,
		Role_Ministry
	}
	
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
	
	@Column(name = "active")
	private Boolean isActive;
	
	@OneToOne(
			mappedBy = "user",
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			optional = true)
	private BaseUserRole userInformation;
	
	//Constructors
	public User() {
		//do nothing
	}

	public User(String username, String encryptedPassword, 
			String role, Boolean isActive, BaseUserRole userInformation) {
		this.username = username;
		this.encryptedPassword = encryptedPassword;
		this.role = role;
		this.isActive = isActive;
		this.userInformation = userInformation;
	}

	//Getters
	public Integer getId() {return id;}
	public String getUsername() {return username;}
	public String getEncryptedPassword() {return encryptedPassword;}
	public String getRole() {return role;}
	public Boolean getIsActive() {return isActive;}
	public BaseUserRole getUserInformation() {return userInformation;}
	
	//Setters
	public void setId(Integer id) {this.id = id;}
	public void setUsername(String username) {this.username = username;}	
	public void setEncryptedPassword(String encryptedPassword) {this.encryptedPassword = encryptedPassword;}
	public void setRole(String role) {this.role = role;}
	public void setIsActive(Boolean isActive) {this.isActive = isActive;}
	public void setUserInformation(BaseUserRole userInformation) {this.userInformation = userInformation;}

	@Override
	public String toString() {
		return "User [id=" + id + ", role=" + role + ", isActive=" + isActive + "]";
	}
	
	
}
