package com.nhatdang2604.view;

import javax.swing.JFrame;

public class BaseView extends JFrame {

	public BaseView open() {
		this.setVisible(true);
		//this.pack();
		
		return this;
	}
	
	public void close() {
		this.setVisible(false);
	}
}
