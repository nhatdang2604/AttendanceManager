package com.nhatdang2604.controller;

import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;

import com.nhatdang2604.model.entity.User;
import com.nhatdang2604.model.formModel.ChangePasswordFormModel;
import com.nhatdang2604.model.formModel.LoginFormModel;
import com.nhatdang2604.service.IUserService;
import com.nhatdang2604.service.UserService;
import com.nhatdang2604.view.form.BaseForm;
import com.nhatdang2604.view.BaseView;
import com.nhatdang2604.view.form.ChangePasswordForm;
import com.nhatdang2604.view.form.LoginForm;

public class LoginController implements IController {

	private BaseForm loginForm;
	private BaseForm changePasswordForm;
	private BaseView main;
	
	private BaseView currentFrame;
	private IUserService userService;

	private User currentUser;
	
	private List<Runnable> pathTransitionExecutions = Arrays.asList(
		()->{							
			currentFrame.close();				//close current frame 
			currentFrame = loginForm.open();},			//open login form
		
		()-> {
			currentFrame.close();				//close current frame
			currentFrame = changePasswordForm.open();	//open change password form
		},
		
		()-> {
			currentFrame.close();				//close current frame
			currentFrame = main.open();				//open main frame
		}
	);
	
	private final int GOTO_LOGIN_IDX = 0;
	private final int GOTO_CHANGE_PASSWORD_IDX = 1;
	private final int GOTO_MAIN_IDX = 2;
	
	
	public int initLogin() {
		
		loginForm.getSubmitButton().addActionListener((event) -> {
			final LoginFormModel model = (LoginFormModel) loginForm.submit();
			User user = userService.authenticated(model);
			
			if (null == user) {
				
				//Wrong username or password error
				loginForm.setError(0);
			} else {
				
				currentUser = user;
				
				
				if (!user.getIsActive()){
					
					//Force to change password if this is the first time login
					((ChangePasswordForm) changePasswordForm).setUser(user);
					pathTransitionExecutions.get(GOTO_CHANGE_PASSWORD_IDX).run();
				} else {
					
					//Else, goto main menu
					pathTransitionExecutions.get(GOTO_MAIN_IDX).run();
				}
			}
		});
		
		return 0;
	}
	
	public int initChangePassword() {
		
		changePasswordForm.getSubmitButton().addActionListener((event) -> {
			
			if (changePasswordForm.isValidForm()) {
				
				//Activate the new account
				User user = ((ChangePasswordForm) changePasswordForm).getUser();
				user.setIsActive(true);
				
				//Change password execute
				String encryptedNewPassword = ((ChangePasswordFormModel) changePasswordForm.submit()).getEncryptedNewPassword();
				userService.changePassword(user, encryptedNewPassword);
				
				//Goto main frame
				pathTransitionExecutions.get(GOTO_MAIN_IDX).run();
			}
			
			
			
		});
		
		return 0;
	}
	
	public int initMain() {
		return 0;
	}
	
	public LoginController() {
		this.loginForm = new LoginForm();
		this.changePasswordForm = new ChangePasswordForm();
		this.main = new BaseView();
		this.userService = UserService.INSTANCE;
		this.currentFrame = loginForm;
		
		initLogin();
		initChangePassword();
		initMain();
		
	}

	@Override
	public void start() {
		currentFrame.setVisible(true);
	}

	@Override
	public JFrame getCurrentWorkingFrame() {
		return currentFrame;
	}
	
}
