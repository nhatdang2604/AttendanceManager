package com.nhatdang2604.dao;

import java.util.List;

import com.nhatdang2604.model.entity.StudentAttendanceStatus;

public interface IAttendanceStatusDAO {

	public StudentAttendanceStatus createOrUpdateStatus(StudentAttendanceStatus status);
	public List<StudentAttendanceStatus> createOrUpdateStatuses(List<StudentAttendanceStatus> statuses);
}
