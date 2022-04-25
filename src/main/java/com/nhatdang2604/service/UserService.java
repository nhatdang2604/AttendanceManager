package com.nhatdang2604.service;

import java.util.List;

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

	private User setDummyValueForUser(User user, int index) {
		String dummyValue = "$dummy" + index;
		user.setUsername(dummyValue);
		user.setEncryptedPassword(HashingUtil.passwordEncryption(dummyValue));
		return user;
	}
	
	private User setIdAsDefaultPassword(User user) {
		Integer id = user.getId();
		user.setUsername(id.toString());
		user.setEncryptedPassword(HashingUtil.passwordEncryption(id.toString()));
		user.setIsActive(false);
		return user;
	}
	
	@Override
	public User createUser(User user) {
		
		//Insert dummy value for username and password
		//	we gonna change username and password base on bussiness later
		user = setDummyValueForUser(user, 0);
		
		//Create the user to get the id
		user = userDAO.createUser(user);
		
		//Change default username and password base on id (bussiness)
		user = setIdAsDefaultPassword(user);
		
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

	@Override
	public List<User> createUsers(List<User> users) {
		
		int size = users.size();
		for (int i = 0; i < size; ++i) {
			users.set(i, setDummyValueForUser(users.get(i), i));
		}
		
		users = userDAO.createUsers(users);
		
		for (int i = 0; i < size; ++i) {
			users.set(i, setIdAsDefaultPassword(users.get(i)));
		}
		
		return updateUsers(users);
	}
	
	@Override
	public List<User> updateUsers(List<User> users)  {
		return userDAO.updateUsers(users);
	}
	
	
}
