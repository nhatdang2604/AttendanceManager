package com.nhatdang2604.controller;

import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;

import com.nhatdang2604.controller.main.BaseMainController;
import com.nhatdang2604.controller.main.MinistryMainController;
import com.nhatdang2604.controller.main.StudentMainController;
import com.nhatdang2604.model.entity.User;
import com.nhatdang2604.model.formModel.ChangePasswordFormModel;
import com.nhatdang2604.model.formModel.LoginFormModel;
import com.nhatdang2604.service.UserService;
import com.nhatdang2604.service.i.IUserService;
import com.nhatdang2604.view.frame.BaseFrame;
import com.nhatdang2604.view.frame.BaseMainFrame;
import com.nhatdang2604.view.frame.MinistryMainFrame;
import com.nhatdang2604.view.frame.StudentMainFrame;
import com.nhatdang2604.view.frame.form.BaseForm;
import com.nhatdang2604.view.frame.form.ChangePasswordForm;
import com.nhatdang2604.view.frame.form.LoginForm;

public class LoginController implements IController {

	private BaseForm loginForm;
	private BaseForm changePasswordForm;
	private BaseFrame main;
	
	private BaseFrame currentFrame;
	private IUserService userService;

	private BaseMainController mainController;
	
	private User currentUser;
	
	private List<Runnable> pathTransitionExecutions = Arrays.asList(
		()->{							
			currentFrame.close();				//close current frame 
			currentFrame = loginForm.open();},			//open login form
		
		()-> {
			loginForm.close();				//close current frame
			currentFrame = changePasswordForm.open();	//open change password form
		},
		
		()-> {
			loginForm.close();				//close current frame
			currentFrame = mainController.getCurrentWorkingFrame().open();	//open main frame
		},
		
		()-> {
			changePasswordForm.close();				//close current frame
			mainController.getCurrentWorkingFrame().close();	//close main frame
			loginForm.open();
		}
	);
	
	private final int GOTO_LOGIN_IDX = 0;
	private final int GOTO_CHANGE_PASSWORD_IDX = 1;
	private final int GOTO_MAIN_IDX_FROM_LOGIN = 2;
	private final int GOTO_MAIN_IDX_FROM_CHANGE_PASSWORD = 3;
	
	
	private BaseMainFrame getMainViewBaseOnRole(User user) {
		if (user.getRole().equals(User.USER_ROLE.Role_Student.name())) {
			
			mainController = new StudentMainController(
					(LoginForm) loginForm,
					(ChangePasswordForm) changePasswordForm,
					user);
		}
		else if (user.getRole().equals(User.USER_ROLE.Role_Ministry.name())) {
			
			mainController = new MinistryMainController(
					(LoginForm) loginForm,
					(ChangePasswordForm) changePasswordForm,
					user);
		}
		
		return mainController.getCurrentWorkingFrame();
	}
	
	public int initLogin() {
		
		loginForm.getSubmitButton().addActionListener((event) -> {
			LoginFormModel model = (LoginFormModel) loginForm.submit();
			User user = userService.authenticated(model);
			
			if (null == user) {
				
				//Wrong username or password error
				loginForm.setError(0);
			} else {
				
				currentUser = user;
				
				
				//Set up the right main frame, base on role
				main = getMainViewBaseOnRole(user);
				
				if (!user.getIsActive()){
					
					//Force to change password if this is the first time login
					((ChangePasswordForm) changePasswordForm).setUser(user);
					pathTransitionExecutions.get(GOTO_CHANGE_PASSWORD_IDX).run();
				} else {
					
					//Else, goto main menu
					pathTransitionExecutions.get(GOTO_MAIN_IDX_FROM_LOGIN).run();
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
				
				//Set up the right main frame, base on role
				main = getMainViewBaseOnRole(user);
				
				//Goto main frame
				pathTransitionExecutions.get(GOTO_MAIN_IDX_FROM_CHANGE_PASSWORD).run();
			}
			
			
			
		});
		
		return 0;
	}
	
	public LoginController() {
		
		this.loginForm = new LoginForm();
		this.changePasswordForm = new ChangePasswordForm();
		this.userService = UserService.INSTANCE;
		this.currentFrame = loginForm;
		
		initLogin();
		initChangePassword();
		
	}

	@Override
	public void start() {
		currentFrame.setVisible(true);
	}

	@Override
	public BaseFrame getCurrentWorkingFrame() {
		return currentFrame;
	}
	
}
