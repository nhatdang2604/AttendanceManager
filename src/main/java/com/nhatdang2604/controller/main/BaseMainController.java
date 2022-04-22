package com.nhatdang2604.controller.main;

import javax.swing.JFrame;

import com.nhatdang2604.controller.IController;
import com.nhatdang2604.view.frame.BaseMainFrame;

public class BaseMainController implements IController {

	protected BaseMainFrame main;
	
	public BaseMainController(BaseMainFrame main) {
		this.main = main;
	}
	
	@Override
	public void start() {
		//do nothing
	}

	@Override
	public JFrame getCurrentWorkingFrame() {
		return main;
	}

}
