package com.nhatdang2604.view.display_feature_view.display_table;

import java.util.List;

import javax.swing.JPanel;

import com.nhatdang2604.view.action.ChangeTabListener;
import com.nhatdang2604.view.display_feature_view.table.CourseTableView;
import com.nhatdang2604.view.display_feature_view.table.SubjectTableView;


public class SubjectDisplayTableView extends BaseDisplayTableView {

	private SubjectTableView subjectView;
	private CourseTableView courseView;
	
	public SubjectDisplayTableView(List<JPanel> backgroundParts) {
		super(backgroundParts);
		
		subjectView = new SubjectTableView();
		courseView = new CourseTableView();
		
		this.addTable(subjectView.getTable(), "Môn học");
		this.addTable(courseView.getTable(), "Lớp học");
		
		this.addDetail(subjectView.getDetailView());
		this.addDetail(courseView.getDetailView());
		
		this.addHeader(subjectView.getHeaderView());
		this.addHeader(courseView.getHeaderView());
		
		//Change detail panel and header panel while change tab
		tabbedPanel.addMouseListener(new ChangeTabListener(this, backgroundParts));
				

		initOption();
	}

	public SubjectTableView getSubjectTableView() {return subjectView;}
	public CourseTableView getCourseTableView() {return courseView;}
}
