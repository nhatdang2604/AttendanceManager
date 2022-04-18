package com.nhatdang2604.utility;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nhatdang2604.model.entity.BaseUserRole;
import com.nhatdang2604.model.entity.Course;
import com.nhatdang2604.model.entity.Ministry;
import com.nhatdang2604.model.entity.Schedule;
import com.nhatdang2604.model.entity.Student;
import com.nhatdang2604.model.entity.StudentAttendanceStatus;
import com.nhatdang2604.model.entity.Subject;
import com.nhatdang2604.model.entity.SubjectWeek;
import com.nhatdang2604.model.entity.User;

//Using enum to implement singleton pattern, for thread safe
public enum HibernateUtil {

	//Instance of the utility
	INSTANCE;
	
	//Factory for managing session in Hibernate
	private SessionFactory sessionFactory;

	//Getter for factory
	public SessionFactory getSessionFactory() {return sessionFactory;}
	
	//Hibernate configuration file name
	//Note: the file path is /src/main/resources/hibernate.cfg.xml
	private String hibernateConfigFile = "hibernate.cfg.xml";
	
	private HibernateUtil() {
		
		try {
			
			//Build the factory, base on the properties on hibernateConfigFile
			sessionFactory = new Configuration()
					.configure(hibernateConfigFile)
					.addAnnotatedClass(BaseUserRole.class)
					.addAnnotatedClass(Course.class)
					.addAnnotatedClass(Ministry.class)
					.addAnnotatedClass(Schedule.class)
					.addAnnotatedClass(Student.class)
					.addAnnotatedClass(StudentAttendanceStatus.class)
					.addAnnotatedClass(Subject.class)
					.addAnnotatedClass(SubjectWeek.class)
					.addAnnotatedClass(User.class)
					.buildSessionFactory();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
