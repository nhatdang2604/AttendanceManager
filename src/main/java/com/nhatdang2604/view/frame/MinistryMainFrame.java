package com.nhatdang2604.view.frame;

import com.nhatdang2604.view.display_feature_view.AttendanceFeatureView;
import com.nhatdang2604.view.display_feature_view.StudentFeatureView;
import com.nhatdang2604.view.display_feature_view.SubjectFeatureView;
import com.nhatdang2604.view.widget.Category;

public class MinistryMainFrame extends BaseMainFrame {

	//Index of each features
	public static final int STUDENT_FEATURE_INDEX = 0;
	public static final int SUBJECT_FEATURE_INDEX = 1;
	public static final int ATTENDANCE_FEATURE_INDEX = 2;
	
	@Override
	protected void initValueForComponents() {
		


		this.menuPanels.add(new Category("   Sinh viên  "));
		this.menuPanels.add(new Category("    Môn học   "));
		this.menuPanels.add(new Category("   Điểm danh  "));
		
		this.featureViews.add(new StudentFeatureView());
		this.featureViews.add(new SubjectFeatureView());
		this.featureViews.add(new AttendanceFeatureView());
	}
	
	
	public MinistryMainFrame() {
		super();
	}
	
}
