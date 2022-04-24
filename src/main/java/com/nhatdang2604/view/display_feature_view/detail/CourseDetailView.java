package com.nhatdang2604.view.display_feature_view.detail;

import java.util.List;

import javax.swing.JButton;

import com.nhatdang2604.model.entity.Course;
import com.nhatdang2604.model.entity.Schedule;

@SuppressWarnings("serial")
public class CourseDetailView extends CRUD_DetailView {
	
	
	private static final String[] DETAIL_FIELDS_NAME = {
			"Ngày bắt đầu",
			"Ngày kết thúc",
			"Thời gian",
			"Ngày trong tuần"
	};
	
	public CourseDetailView(String titled) {
		super(titled, DETAIL_FIELDS_NAME);
	}
	
	
	public List<JButton> getButtons() {
		return buttons;
	}
	
	@Override
	public void setDataToDetailPanel(Object data) {
		Course course = (Course)data;
		Schedule schedule = course.getSchedule();
		
		attributeData.get(0).setText(schedule.getStartDate().toString());
		attributeData.get(1).setText(schedule.getEndDate().toString());
		attributeData.get(2).setText(schedule.getTime().toString());
		attributeData.get(3).setText(schedule.getWeekDay());
	};
}