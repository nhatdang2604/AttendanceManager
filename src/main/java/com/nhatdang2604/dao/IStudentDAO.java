package com.nhatdang2604.dao;

import com.nhatdang2604.model.entity.Student;
import com.nhatdang2604.model.entity.User;

public interface IStudentDAO {

	public Student getStudentFromUser(User user);
}
