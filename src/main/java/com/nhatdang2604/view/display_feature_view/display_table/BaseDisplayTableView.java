package com.nhatdang2604.view.display_feature_view.display_table;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import com.nhatdang2604.view.BaseView;
import com.nhatdang2604.view.display_feature_view.detail.BaseDetailView;

public class BaseDisplayTableView extends JPanel {

	//parts from BaseFeatureView
	protected List<JPanel> backgroundParts;
	protected List<JTable> tables;
	protected List<BaseDetailView> detailViews;
	protected List<JPanel> headerViews;
	
	protected JTabbedPane tabbedPanel;
	protected JTable table;
	protected BaseDetailView detailView;
	protected JPanel headerView;
	protected JPanel footerView;
	
	private final Color FOOTER_COLOR = new Color(230, 242, 255);
	
	protected void setLayout() {
		double heightRatio = 0.95;
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		
		gbc.weightx = 1;
		gbc.weighty = heightRatio;
		this.add(tabbedPanel, gbc);
		
		gbc.gridy = 1;
		gbc.weighty = 1 - heightRatio;
		this.add(footerView, gbc);
		
	}
	
	//Select detail, header and footer when start program
	protected void initOption() {
		table = tables.get(0);
		detailView = detailViews.get(0);
		headerView = headerViews.get(0);
		//footerView = ...	is no need, cause we don't use footer to select
	}
	
	protected void initComponents() {
		
		tabbedPanel = new JTabbedPane();
		tables = new ArrayList<>();
		detailViews = new ArrayList<>();
		headerViews = new ArrayList<>();
		
//		detailView = new BaseView();
//		headerView = new BaseView();
		
		footerView = new BaseView();
		footerView.setBackground(FOOTER_COLOR);
	}
	
	public BaseDisplayTableView(List<JPanel> backgroundParts) {
		this.backgroundParts = backgroundParts;
		initComponents();
		setLayout();
		
	}
	
	public void addTable(JTable table, String name) {
		tables.add(table);
		JScrollPane scroll = new JScrollPane(
				table,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tabbedPanel.addTab(name, scroll);
	}
	
	public void addHeader(JPanel headerView) {
		headerViews.add(headerView);
	}
	
	public void addDetail(BaseDetailView detailView) {
		detailViews.add(detailView);
	}
	
	public JTabbedPane getTabbedPanel() {return tabbedPanel;}
	public List<JTable> getTables() {return tables; };
	public List<BaseDetailView> getDetailViews() {return detailViews; }
	public List<JPanel> getHeaderViews() {return headerViews; }
	
	public JTable getCurrentTable() {return table; }
	public BaseDetailView getCurrentDetailView() {return detailView; }
	public JPanel getCurrentHeaderView() {return headerView; }
	
	public void setCurrentTable(JTable table) {this.table = table; }
	public void setCurrentDetailView(BaseDetailView detail) {detailView = detail; }
	public void setCurrentHeaderView(JPanel header) {headerView = header; }
}
