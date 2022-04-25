package com.nhatdang2604.service.i;

import java.util.List;

import com.nhatdang2604.model.entity.User;
import com.nhatdang2604.model.formModel.LoginFormModel;

public interface IUserService {

	public boolean isTheSameUser(LoginFormModel crmUser, User user);
	
	public User authenticated(LoginFormModel crmUser);
	
	public User changePassword(User currentUser, String newPassword);
	
	public User createUser(User user);
	public List<User> createUsers(List<User> users);
	
	public User updateUser(User user);
	public List<User> updateUsers(List<User> users);
	
	public Integer deleteUser(Integer id);
	public User findUserById(Integer id);
}
