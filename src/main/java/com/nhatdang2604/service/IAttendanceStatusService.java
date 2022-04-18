package com.nhatdang2604.service;

import java.util.List;

import com.nhatdang2604.model.entity.StudentAttendanceStatus;

public interface IAttendanceStatusService {

	public StudentAttendanceStatus createOrUpdateStatus(StudentAttendanceStatus status);
	public List<StudentAttendanceStatus> createOrUpdateStatuses(List<StudentAttendanceStatus> statuses);
}
