package com.nhatdang2604.view.frame;

import com.nhatdang2604.view.BaseView;
import com.nhatdang2604.view.display_feature_view.BaseFeatureView;
import com.nhatdang2604.view.widget.Category;

public class StudentMainFrame extends BaseMainFrame {

	@Override
	protected void initValueForComponents() {
		
		this.menuPanels.add(new Category("Điểm danh"));
		this.featureViews.add(new BaseFeatureView());
	}
	
	//Only have 1 button, no need to show this button
	@Override
	protected void initLeftPanel() {
		
		super.initLeftPanel();
		
		//Set background the same as border of button
		leftPanel.setBackground(
				menuPanels.get(0).getBackground());
		
		//Hide the only button on the left screen
		menuPanels.get(0).getButton().setVisible(false);
	}
	
	public StudentMainFrame() {
		super();
	}
	
}
