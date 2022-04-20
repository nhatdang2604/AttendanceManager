package com.nhatdang2604.model.formModel;

public class ChangePasswordFormModel {
	
	private String encryptedOldPassword;
	private String encryptedNewPassword;
	private String encryptedRetypeNewPassword;
	
	public ChangePasswordFormModel(
			String encryptedOldPassword,
			String encryptedNewPassword,
			String encryptedRetypeNewPassword) {
		this.encryptedOldPassword = encryptedOldPassword;
		this.encryptedNewPassword = encryptedNewPassword;
		this.encryptedRetypeNewPassword = encryptedRetypeNewPassword;
	}

	public String getEncryptedOldPassword() {
		return encryptedOldPassword;
	}

	public void setEncryptedOldPassword(String encryptedOldPassword) {
		this.encryptedOldPassword = encryptedOldPassword;
	}

	public String getEncryptedNewPassword() {
		return encryptedNewPassword;
	}

	public void setEncryptedNewPassword(String encryptedNewPassword) {
		this.encryptedNewPassword = encryptedNewPassword;
	}

	public String getEncryptedRetypeNewPassword() {
		return encryptedRetypeNewPassword;
	}

	public void setEncryptedRetypeNewPassword(String encryptedRetypeNewPassword) {
		this.encryptedRetypeNewPassword = encryptedRetypeNewPassword;
	}
	
	
}
