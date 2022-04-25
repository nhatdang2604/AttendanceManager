package com.nhatdang2604.view.display_feature_view.detail;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class StudentDetailView extends CRUD_DetailView {
	
	protected JFileChooser fileChooser;
	
	public static final int CREATE_BUTTON_INDEX = 0;
	public static final int DELETE_BUTTON_INDEX = 1;
	public static final int CREATE_TEMPLATE_INDEX = 2;
	public static final int IMPORT_INDEX = 3;
	
	@Override
	protected void makeButtons() {
		
		//Create buttons
		buttons = new ArrayList<>();
		buttons.add(new JButton("Thêm"));
		buttons.add(new JButton("Xóa"));
		buttons.add(new JButton("Tạo template"));
		buttons.add(new JButton("Import từ CSV"));
		
		//Add Buttons to detail panel
		for (JButton button: buttons) {
			paddingPanels.get(2).add(button);
		}
		
	}
	
	protected void initFileChooser() {
		fileChooser = new JFileChooser();
		//FileNameExtensionFilter filter = new FileNameExtensionFilter("csv");
		fileChooser.setFileFilter(new FileFilter() {
			
			final String extension = ".csv";
			
			@Override
			public boolean accept(File file) {
				return (file.isDirectory() || 
						(file.isFile() && file.getName().endsWith(extension)));
			}

			@Override
			public String getDescription() {
				return extension;
			}
			
		});
		
		
	}
	
	public StudentDetailView() {
		super("Sinh viên", null);
		initFileChooser();
		
	}
	
	public List<JButton> getButtons() {
		return buttons;
	}
	
	public JFileChooser getFileChooser() {
		return fileChooser;
	}
	
}