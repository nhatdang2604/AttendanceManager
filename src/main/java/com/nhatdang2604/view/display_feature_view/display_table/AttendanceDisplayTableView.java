package com.nhatdang2604.view.display_feature_view.display_table;

import java.util.List;

import javax.swing.JPanel;

import com.nhatdang2604.view.action.ChangeTabListener;
import com.nhatdang2604.view.display_feature_view.table.AttendanceTableView;
import com.nhatdang2604.view.display_feature_view.table.SubjectTableView;


public class AttendanceDisplayTableView extends BaseDisplayTableView {

	private AttendanceTableView attendanceView;
	
	public AttendanceDisplayTableView(List<JPanel> backgroundParts) {
		super(backgroundParts);
		
		attendanceView = new AttendanceTableView();
		
		this.addTable(attendanceView.getTable(), "Điểm danh");
		
		this.addDetail(attendanceView.getDetailView());
		
		this.addHeader(attendanceView.getHeaderView());
		
		//Change detail panel and header panel while change tab
		tabbedPanel.addMouseListener(new ChangeTabListener(this, backgroundParts));
				

		initOption();
	}

	public AttendanceTableView getAttendanceTableView() {return attendanceView;}
	
}
