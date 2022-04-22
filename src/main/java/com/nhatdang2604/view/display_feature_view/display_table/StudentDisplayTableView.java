package com.nhatdang2604.view.display_feature_view.display_table;

import java.util.List;

import javax.swing.JPanel;

import com.nhatdang2604.view.action.ChangeTabListener;
import com.nhatdang2604.view.display_feature_view.table.StudentTableView;


public class StudentDisplayTableView extends BaseDisplayTableView {

	private StudentTableView view;
	
	public StudentDisplayTableView(List<JPanel> backgroundParts) {
		super(backgroundParts);
		
		view = new StudentTableView();
		
		this.addTable(view.getTable(), "Học sinh");
		this.addDetail(view.getDetailView());
		this.addHeader(view.getHeaderView());
		
		//Change detail panel and header panel while change tab
		tabbedPanel.addMouseListener(new ChangeTabListener(this, backgroundParts));
				

		initOption();
	}

	public StudentTableView getStudentTableView() {return view;}
}
