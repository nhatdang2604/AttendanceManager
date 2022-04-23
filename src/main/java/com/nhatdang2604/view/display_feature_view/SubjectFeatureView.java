package com.nhatdang2604.view.display_feature_view;

import com.nhatdang2604.view.display_feature_view.display_table.BaseDisplayTableView;
import com.nhatdang2604.view.display_feature_view.display_table.SubjectDisplayTableView;

public class SubjectFeatureView extends BaseFeatureView {

	private BaseDisplayTableView baseView;
	
	public SubjectFeatureView() {
		super();
		baseView = new SubjectDisplayTableView(parts);
		
		this.setCenterView(baseView)
			.setDetailView(baseView.getCurrentDetailView())
			.setHeaderView(baseView.getCurrentHeaderView());
		
		this.setupParts();
	}
	
	public SubjectDisplayTableView getDisplayTableView() {
		return (SubjectDisplayTableView) baseView;
	}
}
