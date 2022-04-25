package com.nhatdang2604.view.display_feature_view;

import com.nhatdang2604.view.display_feature_view.display_table.BaseDisplayTableView;
import com.nhatdang2604.view.display_feature_view.display_table.StudentAttendanceDisplayTableView;

public class StudentAttendanceFeatureView extends BaseFeatureView {

	private BaseDisplayTableView baseView;
	
	public StudentAttendanceFeatureView() {
		super();
		baseView = new StudentAttendanceDisplayTableView(parts);
		
		this.setCenterView(baseView)
			.setDetailView(baseView.getCurrentDetailView())
			.setHeaderView(baseView.getCurrentHeaderView());
		
		this.setupParts();
	}
	
	public StudentAttendanceDisplayTableView getDisplayTableView() {
		return (StudentAttendanceDisplayTableView) baseView;
	}
}
