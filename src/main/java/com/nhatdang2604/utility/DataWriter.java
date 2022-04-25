package com.nhatdang2604.utility;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.nhatdang2604.model.entity.Student;

public class DataWriter {

	private static final String[] COLUMN_NAMES = {
			"Last name", "First name"
	};
	
	public int generateTemplate(String path) {
		
		//Error code
		int errorCode = 0;
		
		//Try to open the data file
		BufferedWriter writer = null;
		
		try {
			
			//Try to open and ready to write into the file with the given path
			writer = new BufferedWriter(new FileWriter(path));
							
			final String delim = Parser.DELIMITER;
			writer.write("sep="+ delim +"\r\n");
			
			int size = COLUMN_NAMES.length;
			for (int i = 0; i < size - 1; ++i) {
				writer.write(COLUMN_NAMES[i] + delim);
			}
			writer.write(COLUMN_NAMES[size - 1] + delim + "\r\n");
			
		} catch (Exception exception) {
			exception.printStackTrace();
			errorCode = 1;
		} finally {
			
			//Try to close the writer
			if (null != writer) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		return errorCode;
	}
	
}
