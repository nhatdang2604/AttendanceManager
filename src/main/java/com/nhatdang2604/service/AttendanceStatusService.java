package com.nhatdang2604.service;

import java.util.List;

import com.nhatdang2604.dao.AttendanceStatusDAO;
import com.nhatdang2604.dao.IAttendanceStatusDAO;
import com.nhatdang2604.model.entity.StudentAttendanceStatus;

//Using enum for singleton pattern
public enum AttendanceStatusService implements IAttendanceStatusService {

	//Instance of the class
	INSTANCE;
	
	//Hibernate session factory
	private IAttendanceStatusDAO attendanceStatusDAO;
	
	private AttendanceStatusService() {
		
		attendanceStatusDAO = AttendanceStatusDAO.INSTANCE;
	}

	@Override
	public StudentAttendanceStatus createOrUpdateStatus(StudentAttendanceStatus status) {
		return attendanceStatusDAO.createOrUpdateStatus(status);
	}

	@Override
	public List<StudentAttendanceStatus> createOrUpdateStatuses(List<StudentAttendanceStatus> statuses) {
		return attendanceStatusDAO.createOrUpdateStatuses(statuses);
	}
	
	
}