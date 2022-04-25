package com.nhatdang2604.controller.main;

import com.nhatdang2604.controller.IController;
import com.nhatdang2604.model.entity.User;
import com.nhatdang2604.view.frame.BaseMainFrame;
import com.nhatdang2604.view.frame.form.ChangePasswordForm;
import com.nhatdang2604.view.frame.form.LoginForm;

public class BaseMainController implements IController {

	protected BaseMainFrame main;
	protected LoginForm loginForm;
	protected ChangePasswordForm changePasswordForm;
	protected User user;
	
	protected void setupLogoutButton() {
		main.getLogoutButton().addActionListener(event -> {
			main.close();
			loginForm.open();
		});
	}
	
	protected void setupChangePasswordButton() {
		changePasswordForm.setUser(user);
		main.getChangePasswordButton().addActionListener(event -> {
			main.close();
			changePasswordForm.open();
		});
	}
	
	protected void setupButtons() {
		setupLogoutButton();
		setupChangePasswordButton();
	}
	
	public BaseMainController(
			BaseMainFrame main, 
			LoginForm loginForm,
			ChangePasswordForm changePasswordForm,
			User user) {
		
		this.main = main;
		this.loginForm = loginForm;
		this.changePasswordForm = changePasswordForm;
		this.user = user;
		setupButtons();
	}
	
	@Override
	public void start() {
		//do nothing
	}

	@Override
	public BaseMainFrame getCurrentWorkingFrame() {
		return main;
	}

}
