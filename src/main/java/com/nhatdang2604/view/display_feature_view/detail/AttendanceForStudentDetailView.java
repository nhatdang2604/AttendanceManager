package com.nhatdang2604.view.display_feature_view.detail;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

@SuppressWarnings("serial")
public class AttendanceForStudentDetailView extends BaseDetailView {
	
	protected List<JButton> buttons;
	private DatePicker datePicker;
	private TimePicker timePicker;
	
	public static final int SAVE_BUTTON_INDEX = 0;
	public static final int SEARCH_BUTTON_INDEX = 1;
	
	private static final String[] DETAIL_FIELDS_NAME = {
			"Ngày", "Giờ"
	};
	
	private void initPickers()  {
		datePicker = new DatePicker();
		timePicker = new TimePicker();
		datePicker.setDateToToday();
		timePicker.setTimeToNow();
	}
	
	private void makeButtons() {
		
		//Create buttons
		buttons = new ArrayList<>();
		buttons.add(new JButton("Lưu"));
		buttons.add(new JButton("Tìm"));
		
		paddingPanels.get(1).remove(attributeFields.get(0));
		paddingPanels.get(1).remove(attributeData.get(0));
		paddingPanels.get(1).setLayout(
				new FormLayout(new ColumnSpec[] {
						FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),},
					new RowSpec[] {
						FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC,}));
		
		paddingPanels.get(1).add(attributeFields.get(0), "2, 4, right, default");
		paddingPanels.get(1).add(datePicker, "4, 4, fill, default");
		
		paddingPanels.get(1).add(attributeFields.get(1), "2, 6, right, default");
		paddingPanels.get(1).add(timePicker, "4, 6, fill, default");
		
		//Add Buttons to detail panel
		for (JButton button: buttons) {
			paddingPanels.get(2).add(button);
		}
	}
	
	public AttendanceForStudentDetailView(String titled) {
		super(titled, DETAIL_FIELDS_NAME);
		initPickers();
		makeButtons();	
	}
	
	public List<JButton> getButtons() {
		return buttons;
	}
	
	public LocalDate getSelectedDate() {
		return datePicker.getDate();
	}
	
	public LocalTime getSelectedTime() {
		return timePicker.getTime();
	}
}