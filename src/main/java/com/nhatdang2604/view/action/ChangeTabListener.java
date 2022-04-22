package com.nhatdang2604.view.action;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;

import com.nhatdang2604.view.display_feature_view.BaseFeatureView;
import com.nhatdang2604.view.display_feature_view.detail.BaseDetailView;
import com.nhatdang2604.view.display_feature_view.display_table.BaseDisplayTableView;


public class ChangeTabListener implements MouseListener  {
	
	private BaseDisplayTableView displayTableView;
	private List<JPanel> parts;
	
	public ChangeTabListener(BaseDisplayTableView displayTableView, List<JPanel> parts) {
		this.displayTableView = displayTableView;
		this.parts = parts;

	}
	
	public void mouseClicked(MouseEvent e) {
         //do nothing
    }


	public void mouseEntered(MouseEvent arg0) {
		//do nothing
	}


	public void mouseExited(MouseEvent arg0) {
		//do nothing
	}


	//Change detail and header while change tab
	public void mousePressed(MouseEvent arg0) {
		int selectedIndex = displayTableView.getTabbedPanel().getSelectedIndex();
		
		JTable table = displayTableView.getTables().get(selectedIndex);
		BaseDetailView detailView = displayTableView.getDetailViews().get(selectedIndex);
		JPanel headerView = displayTableView.getHeaderViews().get(selectedIndex);
		
        displayTableView.setCurrentTable(table);
        displayTableView.setCurrentDetailView(detailView);
        displayTableView.setCurrentHeaderView(headerView);
        
        parts.forEach((part) -> {
        	part.removeAll();
        });  
      
        parts.get(BaseFeatureView.CENTER_INDEX).add(displayTableView, BorderLayout.CENTER);
        parts.get(BaseFeatureView.DETAIL_INDEX).add(detailView, BorderLayout.CENTER);
        parts.get(BaseFeatureView.HEADER_INDEX).add(headerView, BorderLayout.CENTER);
			
//        displayTableView.getCurrentDetailView().setBackground(new Color(204, 204, 255));
//        displayTableView.getCurrentHeaderView().setBackground(UIManager.getColor("activeCaptionBorder"));
        
        parts.forEach((part) -> {
        	part.repaint();
        });
//        parts.get(BaseFeatureView.HEADER_INDEX).repaint();
//        parts.get(BaseFeatureView.DETAIL_INDEX).repaint();
	}


	public void mouseReleased(MouseEvent arg0) {
		//do nothing
	}
}
