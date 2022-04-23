package com.nhatdang2604.view.display_feature_view.display_table;

import java.util.List;

import javax.swing.JPanel;

import com.nhatdang2604.view.action.ChangeTabListener;
import com.nhatdang2604.view.display_feature_view.table.StudentTableView;
import com.nhatdang2604.view.display_feature_view.table.SubjectTableView;


public class SubjectDisplayTableView extends BaseDisplayTableView {

	private SubjectTableView view;
	
	public SubjectDisplayTableView(List<JPanel> backgroundParts) {
		super(backgroundParts);
		
		view = new SubjectTableView();
		
		this.addTable(view.getTable(), "Môn học");
		this.addDetail(view.getDetailView());
		this.addHeader(view.getHeaderView());
		
		//Change detail panel and header panel while change tab
		tabbedPanel.addMouseListener(new ChangeTabListener(this, backgroundParts));
				

		initOption();
	}

	public SubjectTableView getSubjectTableView() {return view;}
}
