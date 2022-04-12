package com.nhatdang2604.service;

import com.nhatdang2604.dao.IStudentDAO;
import com.nhatdang2604.dao.StudentDAO;
import com.nhatdang2604.model.entity.Student;
import com.nhatdang2604.model.entity.User;

public enum StudentService implements IStudentService {

	INSTANCE;
	
	private IStudentDAO studentDAO;
	
	private StudentService() {
		studentDAO = StudentDAO.INSTANCE;
	}

	public Student loginAsStudent(User user) {
		return studentDAO.getStudentFromUser(user);
	}
	
}
