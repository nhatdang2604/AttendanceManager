package com.nhatdang2604.service.i;

import com.nhatdang2604.model.entity.Schedule;

public interface IScheduleService {

	public Schedule createOrUpdateSchedule(Schedule schedule);
	public Schedule findScheduleById(Integer id);
	public int deleteSchedule(Integer id);
}
