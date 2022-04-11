package com.nhatdang2604.dao;

import com.nhatdang2604.model.crm.CRMUser;
import com.nhatdang2604.model.entity.User;

//Data access object for user account
public interface IUserDAO {

	//Return true if the crmUser is login successfully, else return false
	public boolean doesLoginSuccessfully(CRMUser crmUser);
	
	//Return the User of the crmUser in the database
	//If the user is existed, return the user, else return null
	public User authenticated(CRMUser crmUser);
}
