package com.nhatdang2604.dao;

import com.nhatdang2604.model.entity.User;
import com.nhatdang2604.model.formModel.LoginFormModel;

//Data access object for user account
public interface IUserDAO {

	//Return the User of the user model from login form in the database
	//If the user is existed, return the user, else return null
	public User getUserByUserLoginModel(LoginFormModel crmUser);
	
	public User findUserById(Integer id);
	
	public User createOrUpdateUser(User user);
	
	public int deleteUser(Integer id);
}
