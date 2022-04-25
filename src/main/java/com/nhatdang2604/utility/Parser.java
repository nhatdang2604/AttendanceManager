package com.nhatdang2604.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.nhatdang2604.model.entity.Student;

public class Parser {

	//Delimiter for the (word, definition) in the data file
	public static final String DELIMITER = ";";
	
	//Parse student from a line text
	public Student parse(String line) {
		
		final String needle = DELIMITER;
		
		//Tokenizer for parsing
		StringTokenizer tokenizer = new StringTokenizer(line, needle);
		
		//The result student
		Student student = new Student();
		
		//Set properties for student
		student.setLastName(tokenizer.nextToken());
		student.setFirstName(tokenizer.nextToken());
		
		//Return the given student
		return student;
	}
	
	//Read a list of students from a .txt file
	public List<Student> readStudentFromText(String path) {
		
		//The list of students
		List<Student> students = new ArrayList<>();
			
		//Return the empty list if the file isn't existed
		File file = new File(path);
		try {
			if (file.createNewFile()) {
				return students;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//Try to read the file
		BufferedReader reader = null;
		try {
			
			//Try to read the given data file
			reader = new BufferedReader(new FileReader(file));
			
			//Skip the delimiter CSV attribute line and
			//	the column name line
			reader.readLine();
			reader.readLine();
			
			//Text line buffer
			String line;
			
			//Parse the student into list until the end of file
			while (null != (line = reader.readLine()))  {
				Student buffer = parse(line);
				students.add(buffer);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
				
			//Try to close the reader
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return students;
	}
	
}
