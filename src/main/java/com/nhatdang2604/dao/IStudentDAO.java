package com.nhatdang2604.dao;

import java.util.Collection;

import com.nhatdang2604.model.entity.Student;

public interface IStudentDAO {

	public Student createOrUpdateStudent(Student student);
	public Collection<Student> createOrUpdateStudents(Collection<Student> students);
	public int deleteStudent(Integer id);
	public Student findStudentById(Integer id);
}
