package com.dm.bookstore.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestIOImp {
	
	@Test
	public void invokeTest(){
		TestIOImp t = new TestIOImp();
		String fileName = "G:\\files\\userID.txt";
		String userID = t.test(fileName);
	 	List<String> id = Arrays.asList(userID);
	 	String s =id.toString();
	 	String[] strId = s.split(",");
	 	
//	 	for(int i=0;i<strId.length;i++){
//	 		System.out.println(strId[i]);
//	 	}
	 	
	 	String codeName = "bGRtMDE=,bGRtMDI=,bGRtMDM=,bGRtMDQ=";
	 	String[] name = codeName.split(",");
	 	
	 	for(String s2 : name){
	 		System.out.println(s2);
	 	}
	 	
	 	
	}
	
	public String test(String args){
		TestIO tio=new TestIO();
		return tio.readFileByLines(args);
	}
}
