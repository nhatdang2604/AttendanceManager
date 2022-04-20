package com.nhatdang2604.service;

import com.nhatdang2604.dao.IUserDAO;
import com.nhatdang2604.dao.UserDAO;
import com.nhatdang2604.model.entity.User;
import com.nhatdang2604.model.formModel.LoginFormModel;
import com.nhatdang2604.utility.HashingUtil;

public enum UserService implements IUserService {

	INSTANCE;
	
	private IUserDAO userDAO;
	
	private UserService() {
		
		//Inject the session factory from hibernate utility
		userDAO = UserDAO.INSTANCE;
	}
	
	//Helper to compare a CRMUser and User
	public boolean isTheSameUser(LoginFormModel crmUser, User user) {
		
		if (null == crmUser || null == user) return false;
		
		//Compare the username
		boolean isTheSameUsername = user.getUsername().equals(crmUser.getUsername());
			
		//Return false if the username is different
		if (!isTheSameUsername) return false;
			
		//Compare the password hashing
		boolean isTheSamePassword = 
				user.getEncryptedPassword()
				.equals(crmUser.getEncryptedPassword());
			
		return isTheSamePassword;
	}

	public User authenticated(LoginFormModel crmUser) {
		
		User user = userDAO.getUserByUserLoginModel(crmUser);
		
		user = (isTheSameUser(crmUser, user)?user:null);
		
		return user;
	}

	public User changePassword(User currentUser, String newEncryptedPassword) {
		
		currentUser.setEncryptedPassword(newEncryptedPassword);
		
		return userDAO.createOrUpdateUser(currentUser);
	}

	@Override
	public User createOrUpdateUser(User user) {
		return userDAO.createOrUpdateUser(user);
	}

	@Override
	public Integer deleteUser(Integer id) {
		return userDAO.deleteUser(id);
	}

	@Override
	public User findUserById(Integer id) {
		return userDAO.findUserById(id);
	}
	
	
}
