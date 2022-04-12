package com.nhatdang2604.service;

import com.nhatdang2604.model.crm.CRMUser;
import com.nhatdang2604.model.entity.User;

public interface IUserService {

	public boolean isTheSameUser(CRMUser crmUser, User user);
	
	public User authenticated(CRMUser crmUser);
	
	public User changePassword(User currentUser, String newPassword);
}
