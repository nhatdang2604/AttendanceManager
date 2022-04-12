package com.nhatdang2604.service;

import com.nhatdang2604.dao.UserDAO;
import com.nhatdang2604.model.crm.CRMUser;
import com.nhatdang2604.model.entity.User;
import com.nhatdang2604.utility.HashingUtil;

public enum UserService implements IUserService {

	INSTANCE;
	
	private UserDAO userDAO;
	
	private UserService() {
		
		//Inject the session factory from hibernate utility
		userDAO = UserDAO.INSTANCE;
	}
	
	//Helper to compare a CRMUser and User
	public boolean isTheSameUser(CRMUser crmUser, User user) {
		
		if (null == crmUser || null == user) return false;
		
		//Compare the username
		boolean isTheSameUsername = user.getUsername().equals(crmUser.getUsername());
			
		//Return false if the username is different
		if (!isTheSameUsername) return false;
			
		//Compare the password hashing
		boolean isTheSamePassword = user.getEncryptedPassword().equals(				//compare the user password with the
				HashingUtil.passwordEncryption(crmUser.getUnencryptedPassword()));	//hashing of the crmUser password
			
		return isTheSamePassword;
	}

	public User authenticated(CRMUser crmUser) {
		
		User user = userDAO.getUserByCRMUser(crmUser);
		
		user = (isTheSameUser(crmUser, user)?user:null);
		
		return user;
	}

}
