package com.nhatdang2604.service;

import java.util.Collection;
import java.util.List;

import com.nhatdang2604.dao.StudentDAO;
import com.nhatdang2604.dao.i.IStudentDAO;
import com.nhatdang2604.model.entity.Student;
import com.nhatdang2604.model.entity.User;
import com.nhatdang2604.service.i.IStudentService;
import com.nhatdang2604.service.i.IUserService;

public enum StudentService implements IStudentService {

	INSTANCE;
	
	private IStudentDAO studentDAO;
	private IUserService userService;
	
	private StudentService() {
		studentDAO = StudentDAO.INSTANCE;
		userService = UserService.INSTANCE;
	}

	@Override
	public Student createStudent(Student student) {
		
		//Create user for the student
		User user = new User();
		user.setRole(User.USER_ROLE.Role_Student.name());
		student.setUser(userService.createUser(user));
		
		return studentDAO.createStudent(student);
	}

	@Override
	public Student updateStudent(Student student) {
		return studentDAO.updateStudent(student);
	}
	
	@Override
	public Collection<Student> createStudents(Collection<Student> students) {
		
		return studentDAO.createStudents(students);
	}

	public Collection<Student> updateStudents(Collection<Student> students) {
		return studentDAO.updateStudents(students);
	}
	
	@Override
	public int deleteStudent(Integer id) {
		
		return studentDAO.deleteStudent(id);
	}

	@Override
	public int deleteStudents(List<Integer> ids) {

		return studentDAO.deleteStudents(ids);
	}
	
	@Override
	public Student findStudentById(Integer id) {
		
		return studentDAO.findStudentById(id);
	}

	@Override
	public List<Student> getAllStudents() {
		
		return studentDAO.getAllStudents();
	}
	
}
