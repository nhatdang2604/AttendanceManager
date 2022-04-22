package com.nhatdang2604.view.display_feature_view.table;

import javax.swing.JPanel;
import javax.swing.JTable;

public interface ITableViewBehaviour {

	public JTable getTable();
	public JPanel getHeaderView();
	public JPanel getDetailView();
}
