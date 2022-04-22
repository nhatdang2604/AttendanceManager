package com.nhatdang2604.service.i;

import com.nhatdang2604.model.entity.User;
import com.nhatdang2604.model.formModel.LoginFormModel;

public interface IUserService {

	public boolean isTheSameUser(LoginFormModel crmUser, User user);
	
	public User authenticated(LoginFormModel crmUser);
	
	public User changePassword(User currentUser, String newPassword);
	
	public User createUser(User user);
	public User updateUser(User user);
	
	public Integer deleteUser(Integer id);
	public User findUserById(Integer id);
}
