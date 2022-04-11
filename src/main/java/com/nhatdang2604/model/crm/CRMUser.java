package com.nhatdang2604.model.crm;

//User before loging
//Usage: authentication:
//	CRMUser -> authenticate(CRMUser) -> User
public class CRMUser {

	//Attributes
	private String username;
	private String unencryptedPassword;
	
	//Constructors
	public CRMUser() {
		//do nothing
	}
	
	public CRMUser(String username, String unencryptedPassword) {
		this.username = username;
		this.unencryptedPassword = unencryptedPassword;
	}

	//Getters
	public String getUsername() {return username;}
	public String getUnencryptedPassword() {return unencryptedPassword;}
	
	//Setters
	public void setUsername(String username) {this.username = username;}
	public void setUnencryptedPassword(String unencryptedPassword) {this.unencryptedPassword = unencryptedPassword;}
	
	
	
}
