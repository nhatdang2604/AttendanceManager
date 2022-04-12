package com.nhatdang2604.dao;

import com.nhatdang2604.model.crm.CRMUser;
import com.nhatdang2604.model.entity.User;

//Data access object for user account
public interface IUserDAO {

	//Return the User of the crmUser in the database
	//If the user is existed, return the user, else return null
	public User getUserByCRMUser(CRMUser crmUser);
}
