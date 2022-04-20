package com.nhatdang2604.validator;

import com.nhatdang2604.model.entity.User;

public class UserValidator {

	public boolean isPasswordSameAsRetypePassword(String encryptedPassword, String encryptedRetypePassword) {
		return encryptedPassword.equals(encryptedRetypePassword);
	}
	
	public boolean isPasswordOfUser(String encryptedPassword, User user) {
		return encryptedPassword.equals(user.getEncryptedPassword());
	}
	

}
