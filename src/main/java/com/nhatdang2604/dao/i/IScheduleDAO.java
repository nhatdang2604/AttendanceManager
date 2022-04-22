package com.nhatdang2604.dao.i;

import com.nhatdang2604.model.entity.Course;
import com.nhatdang2604.model.entity.Schedule;

public interface IScheduleDAO {

	public Schedule createOrUpdateSchedule(Schedule schedule);
	public Schedule findScheduleById(Integer id);
	public int deleteSchedule(Integer id);
}
