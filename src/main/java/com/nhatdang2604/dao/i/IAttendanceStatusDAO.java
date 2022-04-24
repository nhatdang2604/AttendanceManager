package com.nhatdang2604.dao.i;

import java.util.List;
import java.util.Set;

import com.nhatdang2604.model.entity.Student;
import com.nhatdang2604.model.entity.StudentAttendanceStatus;

public interface IAttendanceStatusDAO {

	public StudentAttendanceStatus createOrUpdateStatus(StudentAttendanceStatus status);
	public List<StudentAttendanceStatus> createOrUpdateStatuses(List<StudentAttendanceStatus> statuses);
	public Set<Student> getStatusForStudents(Set<Student> students);
}
