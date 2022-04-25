package com.nhatdang2604.dao.i;

import java.util.Collection;
import java.util.List;

import com.nhatdang2604.model.entity.Student;

public interface IStudentDAO {

	public Student createStudent(Student student);
	public Student updateStudent(Student student);
	public List<Student> createStudents(List<Student> students);
	public int deleteStudent(Integer id);
	public int deleteStudents(List<Integer> ids);
	public Student findStudentById(Integer id);
	public List<Student> getAllStudents();
	public Collection<Student> updateStudents(Collection<Student> students);
}
