package com.nhatdang2604.view.frame.form;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import com.nhatdang2604.model.entity.User;
import com.nhatdang2604.model.formModel.ChangePasswordFormModel;
import com.nhatdang2604.utility.HashingUtil;
import com.nhatdang2604.validator.UserValidator;

public class ChangePasswordForm extends BaseForm {

	//Change password form have 4 types of warning:
	//	- Type 0: Wrong old password
	//	- Type 1: New password is different from retype new password 
	//	- Type 2: New password is match with old password
	//	- Type 3: There is exists a password which match with the new password
	private static String ERRORS[] = {
			"Sai mật khẩu gốc",
			"<html><body>Nhập lại mật khẩu và<br>mật khẩu mới không trùng khớp</body></html>",
	};
	
	//User holding the form
	private User currentUser;
	
	//Main panel to add components into
	private JPanel contentPane;

	//Components
	private JButton btnChangePassword;				//Change password button
	private JPasswordField passtxtOldPassword;		//Old password text field
	private JPasswordField passtxtNewPassword;		//New password text field
	private JPasswordField passtxtRetypeNewPassword;//Retype new password text field					
	private JLabel jlbOldPassword;					//Label for old password field
	private JLabel jlbNewPassword;					//Label for new password field
	private JLabel jlbRetypeNewPassword;			//Label to retype old password field
	private JCheckBox chckbxShowPassword;			//Checkbox to show/hide password
	//Display when:
	//	1.) Wrong old password: Type = 0
	//	2.) New password is:
	//		a.) Different with retype new password: Type = 1
	//		b.) Exists: Type = 2
	private JLabel jlbWarningText;			
	
	//Form validator
	private UserValidator validator;
	
	//Change the warning text of jlbWarningText;
	@Override
	public BaseForm setError(int errorCode) {
		
		jlbWarningText.setText(ERRORS[errorCode]);
		
		return this;
	}
	
	//Create and add Show/Hide feature for chckbxShowPassword
	private void initCheckbox() {
		chckbxShowPassword = new JCheckBox("Hiện mật khẩu");
		chckbxShowPassword.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				//If the check box is selected
				//	=> Show password of passtxtOldPassword, passtxtNewPassword, passtxtRetypeNewPassword
				if (chckbxShowPassword.isSelected()) {
					passtxtOldPassword.setEchoChar((char)0);
					passtxtNewPassword.setEchoChar((char)0);
					passtxtRetypeNewPassword.setEchoChar((char)0);
				} else {
					//If the check box is not selected
					//	=> Hide password of passtxtOldPassword, passtxtNewPassword, passtxtRetypeNewPassword
					// by setting echo character with (char)'\u2022'
					passtxtOldPassword.setEchoChar('\u2022');
					passtxtNewPassword.setEchoChar('\u2022');
					passtxtRetypeNewPassword.setEchoChar('\u2022');
				}
				
			}
			
		});
	}
	
	//Create all components;
	private void initComponents() {
		validator = new UserValidator();
		btnChangePassword = new JButton("Đổi mật khẩu");
		passtxtOldPassword = new JPasswordField();
		passtxtNewPassword = new JPasswordField();
		passtxtRetypeNewPassword = new JPasswordField();
		jlbOldPassword = new JLabel("Mật khẩu cũ:");
		jlbNewPassword = new JLabel("Mật khẩu mới:");
		
		jlbWarningText = new JLabel();				//No warning when start change password form
		jlbWarningText.setForeground(Color.RED);	//Warning have red text
		
		jlbRetypeNewPassword = new JLabel("Nhập lại mật khẩu:");
		initCheckbox();								//Create and add Show/Hide feature for chckbxShowPassword
	}
	
	//Set size and location of each component
	private void setComponentSizeAndLocation() {
		btnChangePassword.setBounds(257,179,110,30);
		jlbOldPassword.setBounds(50,46,120,30);
		jlbNewPassword.setBounds(50,87,120,30);
		jlbRetypeNewPassword.setBounds(50,128,120,30);
		
	    jlbWarningText.setBounds(170, 11, 197, 30);
	    passtxtOldPassword.setBounds(170,46,197,30);
	    passtxtNewPassword.setBounds(170,87,197,30);
	    passtxtRetypeNewPassword.setBounds(170,128,197,30);
	    chckbxShowPassword.setBounds(141, 183, 110, 23);
	}
	
	//Connect all components into contentPane
	private void addComponents() {
		contentPane.add(btnChangePassword);
		contentPane.add(jlbOldPassword);
		contentPane.add(jlbNewPassword);
		contentPane.add(jlbRetypeNewPassword);
		contentPane.add(jlbWarningText);
		contentPane.add(passtxtOldPassword);
		contentPane.add(passtxtNewPassword);
		contentPane.add(passtxtRetypeNewPassword);
		contentPane.add(chckbxShowPassword);
	}
	
	//Create and set properties of the change password form
	private void initChangePasswordForm() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 450, 270);
		this.setTitle("Đổi mật khẩu");
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		contentPane.setLayout(null);
		this.setResizable(false);
	}
	
	public ChangePasswordForm() {
		initChangePasswordForm();
		initComponents();
		setComponentSizeAndLocation();
		addComponents();
		
		setContentPane(contentPane);
	}

	public ChangePasswordForm setUser(User user) {
		this.currentUser = user;
		return this;
	}
	
	public User getUser() {
		return currentUser;
	}
	
	@Override
	public ChangePasswordFormModel submit() {
		
		ChangePasswordFormModel model = new ChangePasswordFormModel(
				HashingUtil.passwordEncryption(new String(passtxtOldPassword.getPassword())),
				HashingUtil.passwordEncryption(new String(passtxtNewPassword.getPassword())),
				HashingUtil.passwordEncryption(new String(passtxtRetypeNewPassword.getPassword())));
		
		return model;
	}

	
	@Override
	public JButton getSubmitButton() {
		return btnChangePassword;
	}
	
	@Override
	public boolean isValidForm() {
		
		ChangePasswordFormModel model = this.submit();
		
		boolean[] conditions = {
				validator.isPasswordOfUser(model.getEncryptedOldPassword(), currentUser),
				validator.isPasswordSameAsRetypePassword(model.getEncryptedNewPassword(), model.getEncryptedRetypeNewPassword())
		};
		
		int size = conditions.length;
		for (int i = 0; i < size; ++i) {
			if (!conditions[i]) {
				this.setError(i);
				return false;
			}
		}
		
		return true;
	}
}
