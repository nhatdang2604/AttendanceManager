package com.nhatdang2604.view.display_feature_view;

import com.nhatdang2604.view.display_feature_view.display_table.AttendanceDisplayTableView;
import com.nhatdang2604.view.display_feature_view.display_table.BaseDisplayTableView;

public class AttendanceFeatureView extends BaseFeatureView {

	private BaseDisplayTableView baseView;
	
	public AttendanceFeatureView() {
		super();
		baseView = new AttendanceDisplayTableView(parts);
		
		this.setCenterView(baseView)
			.setDetailView(baseView.getCurrentDetailView())
			.setHeaderView(baseView.getCurrentHeaderView());
		
		this.setupParts();
	}
	
	public AttendanceDisplayTableView getDisplayTableView() {
		return (AttendanceDisplayTableView) baseView;
	}
}
