package com.dm.bookstore.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;

public class TestIO {
	
	

	public  String readFileByLines(String fileName){
		File file= new File(fileName);
		BufferedReader reader = null;
		String lineUserID = null;
		try {
			String tempString = null;
			reader = new BufferedReader(new FileReader(file));
			int line = 1;
			while((tempString = reader.readLine()) != null){
				lineUserID = tempString;
				line ++;
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return lineUserID;
	}
	
	
	
}
