package com.nhatdang2604.view.display_feature_view;

import com.nhatdang2604.view.display_feature_view.display_table.BaseDisplayTableView;
import com.nhatdang2604.view.display_feature_view.display_table.StudentDisplayTableView;

public class StudentFeatureView extends BaseFeatureView {

	private BaseDisplayTableView baseView;
	
	public StudentFeatureView() {
		super();
		baseView = new StudentDisplayTableView(parts);
		
		this.setCenterView(baseView)
			.setDetailView(baseView.getCurrentDetailView())
			.setHeaderView(baseView.getCurrentHeaderView());
		
		this.setupParts();
	}
	
	public StudentDisplayTableView getDisplayTableView() {
		return (StudentDisplayTableView) baseView;
	}
}
