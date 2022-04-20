package com.nhatdang2604.view.form;

import javax.swing.JButton;

import com.nhatdang2604.view.BaseView;

public abstract class BaseForm extends BaseView {
	
	public Object submit() {
		return null;
	}
	
	public JButton getSubmitButton() {
		return null;
	}
	
	public BaseForm setError(int errorCode) {
		return this;
	}
	
	public boolean isValidForm() {
		return false;
	}
}
