package com.nhatdang2604.service;

import java.util.Collection;

import com.nhatdang2604.dao.IStudentDAO;
import com.nhatdang2604.dao.StudentDAO;
import com.nhatdang2604.model.entity.Student;

public enum StudentService implements IStudentService {

	INSTANCE;
	
	private IStudentDAO studentDAO;
	
	private StudentService() {
		studentDAO = StudentDAO.INSTANCE;
	}

	@Override
	public Student createOrUpdateStudent(Student student) {
		
		return studentDAO.createOrUpdateStudent(student);
	}

	@Override
	public Collection<Student> createOrUpdateStudents(Collection<Student> students) {
		
		return studentDAO.createOrUpdateStudents(students);
	}

	@Override
	public int deleteStudent(Integer id) {
		
		return studentDAO.deleteStudent(id);
	}

	@Override
	public Student findStudentById(Integer id) {
		
		return studentDAO.findStudentById(id);
	}
	
}
