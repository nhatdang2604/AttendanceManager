package com.nhatdang2604.view.frame.form;

import javax.swing.JButton;

import com.nhatdang2604.view.frame.BaseFrame;

public abstract class BaseForm extends BaseFrame {
	
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
	
	public void clear() {
		//do nothing
	}
	
	@Override
	public void close() {
		clear();
		super.close();
	}
}
