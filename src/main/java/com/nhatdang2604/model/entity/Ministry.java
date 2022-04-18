package com.nhatdang2604.model.entity;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "ministry")
@PrimaryKeyJoinColumn(foreignKey=@ForeignKey(name = "fk_ministry_base"))
public class Ministry extends BaseUserRole {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4725922612432114732L;
	
	//Constructors
	public Ministry() {
		super();
	}
	
	public Ministry(User user, String firstName, String lastName) {
		super(user, firstName, lastName);
	}
	
}
