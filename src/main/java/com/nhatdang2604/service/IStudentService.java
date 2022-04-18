package com.nhatdang2604.service;

import java.util.Collection;

import com.nhatdang2604.model.entity.Student;

public interface IStudentService {

	public Student createOrUpdateStudent(Student student);
	public Collection<Student> createOrUpdateStudents(Collection<Student> students);
	public int deleteStudent(Integer id);
	public Student findStudentById(Integer id);
}
