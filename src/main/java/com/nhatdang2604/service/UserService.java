package com.nhatdang2604.service;

import com.nhatdang2604.dao.UserDAO;
import com.nhatdang2604.dao.i.IUserDAO;
import com.nhatdang2604.model.entity.User;
import com.nhatdang2604.model.formModel.LoginFormModel;
import com.nhatdang2604.service.i.IUserService;
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
		
		return updateUser(currentUser);
	}

	@Override
	public User createUser(User user) {
		
		//Insert dummy value for username and password
		//	we gonna change username and password base on bussiness later
		String dummyValue = "$dummy";
		user.setUsername(dummyValue);
		user.setEncryptedPassword(HashingUtil.passwordEncryption(dummyValue));
		
		//Create the user to get the id
		user = userDAO.createUser(user);
		
		//Change default username and password base on id (bussiness)
		Integer id = user.getId();
		user.setUsername(id.toString());
		user.setEncryptedPassword(HashingUtil.passwordEncryption(id.toString()));
		user.setIsActive(false);
		
		//update the username and password
		return updateUser(user);
	}

	@Override
	public User updateUser(User user) {
		return userDAO.updateUser(user);
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
