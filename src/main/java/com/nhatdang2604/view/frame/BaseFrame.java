package com.nhatdang2604.view.frame;

import javax.swing.JFrame;

import com.nhatdang2604.view.ViewBehaviour;

public class BaseFrame extends JFrame implements ViewBehaviour {
	
	@Override
	public BaseFrame open() {
		this.setVisible(true);
		//this.pack();
		
		return this;
	}

	@Override
	public void close() {
		this.setVisible(false);
	}
}
