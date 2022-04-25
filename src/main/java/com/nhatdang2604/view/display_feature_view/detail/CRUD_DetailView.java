package com.nhatdang2604.view.display_feature_view.detail;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class CRUD_DetailView extends BaseDetailView {
	
	protected List<JButton> buttons;
	
	public static final int CREATE_BUTTON_INDEX = 0;
	public static final int DELETE_BUTTON_INDEX = 1;
	
	protected void makeButtons() {
		
		//Create buttons
		buttons = new ArrayList<>();
		buttons.add(new JButton("Thêm"));
		buttons.add(new JButton("Xóa"));
		
		
		//Add Buttons to detail panel
		for (JButton button: buttons) {
			paddingPanels.get(2).add(button);
		}
	}
	
	public CRUD_DetailView(String titled, String[] attributeFieldNames) {
		super(titled, attributeFieldNames);
		makeButtons();
		
	}
	
	
	public List<JButton> getButtons() {
		return buttons;
	}
}