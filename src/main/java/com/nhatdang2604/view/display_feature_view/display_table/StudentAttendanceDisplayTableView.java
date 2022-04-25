package com.nhatdang2604.view.display_feature_view.display_table;

import java.util.List;

import javax.swing.JPanel;

import com.nhatdang2604.view.action.ChangeTabListener;
import com.nhatdang2604.view.display_feature_view.table.AttendanceForStudentTableView;
import com.nhatdang2604.view.display_feature_view.table.StudentAttendanceTableView;


public class StudentAttendanceDisplayTableView extends BaseDisplayTableView {

	private StudentAttendanceTableView studentAttendanceView;
	private AttendanceForStudentTableView attendingView;
	
	public StudentAttendanceDisplayTableView(List<JPanel> backgroundParts) {
		super(backgroundParts);
		
		studentAttendanceView = new StudentAttendanceTableView();
		attendingView = new AttendanceForStudentTableView();
		
		this.addTable(studentAttendanceView.getTable(), "Kết quả điểm danh");
		this.addTable(attendingView.getTable(), "Điểm danh");
		
		this.addDetail(studentAttendanceView.getDetailView());
		this.addDetail(attendingView.getDetailView());
		
		this.addHeader(studentAttendanceView.getHeaderView());
		this.addHeader(attendingView.getHeaderView());
		
		//Change detail panel and header panel while change tab
		tabbedPanel.addMouseListener(new ChangeTabListener(this, backgroundParts));
				

		initOption();
	}

	public StudentAttendanceTableView getStudentAttendanceTableView() {return studentAttendanceView;}
	public AttendanceForStudentTableView getAttendanceForStudentTableView() {return attendingView; }
	
}
