package com.nhatdang2604.service;

import java.util.ArrayList;
import java.util.List;

import com.nhatdang2604.dao.ScheduleDAO;
import com.nhatdang2604.dao.SubjectWeekDAO;
import com.nhatdang2604.dao.i.IScheduleDAO;
import com.nhatdang2604.model.entity.Schedule;
import com.nhatdang2604.model.entity.SubjectWeek;
import com.nhatdang2604.service.i.IScheduleService;
import com.nhatdang2604.service.i.ISubjectWeekService;

public enum ScheduleService implements IScheduleService {
	
	INSTANCE;
	
	private IScheduleDAO scheduleDAO;
	private ISubjectWeekService subjectWeekService;
	
	private ScheduleService() {
		scheduleDAO = ScheduleDAO.INSTANCE;
		subjectWeekService = SubjectWeekService.INSTANCE;
	}

	public Schedule createOrUpdateSchedule(Schedule schedule) {
		
		//Add subject week if empty
		if (null == schedule.getSubjectWeeks()) {
					
			List<SubjectWeek> weeks = new ArrayList<SubjectWeek>();
			for (int weekIndex = 1; weekIndex <= SubjectWeek.NUMBER_OF_WEEKS_PER_COURSE; ++weekIndex) {
				weeks.add(new SubjectWeek(schedule, weekIndex, null));
			}
					
			schedule.setSubjectWeeks(weeks);
					
		}
		
		Schedule result = scheduleDAO.createOrUpdateSchedule(schedule);
		subjectWeekService.createOrUpdateSubjectWeeks(schedule.getSubjectWeeks());
		return result;
	}

	public Schedule findScheduleById(Integer id) {
		return scheduleDAO.findScheduleById(id);
	}

	public int deleteSchedule(Integer id) {
		return scheduleDAO.deleteSchedule(id);
	}
}
