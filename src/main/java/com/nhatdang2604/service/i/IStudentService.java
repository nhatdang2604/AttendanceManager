package com.nhatdang2604.service.i;

import java.util.Collection;
import java.util.List;

import com.nhatdang2604.model.entity.Student;

public interface IStudentService {

	public Student createStudent(Student student);
	public Student updateStudent(Student student);
	public Collection<Student> createStudents(Collection<Student> students);
	public Collection<Student> updateStudents(Collection<Student> students);
	public int deleteStudent(Integer id);
	public int deleteStudents(List<Integer> ids);
	public Student findStudentById(Integer id);
	public List<Student> getAllStudents();
}
