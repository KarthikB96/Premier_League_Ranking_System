package fileParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
	//creating a file and writing into the file
//	public void createFile(List<String> persons,String fileName) {
//		try {
//			FileWriter fileWriter = new FileWriter(fileName);
//			BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
//			for(String s : persons) {
//				bufferWriter.write(s);
//				bufferWriter.newLine();
//			}
//			bufferWriter.flush();
//			}
//			catch(Exception e) {
//				e.printStackTrace();
//			}
//	}
	//reading the file
	public List<String> readFile(String fileName) {
		List<String> studentString = new ArrayList<String>();
		try{
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferReader = new BufferedReader(fileReader);
			String studentLine = null;
			while((studentLine=bufferReader.readLine())!=null) {
//				System.out.println(studentLine);
				studentString.add(studentLine);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return studentString;
		
	}
	
	//this is a static method which is used to convert the String to integer.This method is used in student and teacher constructor to change the ids and age from string to integers.
	public static int CreateInteger(String integer) {
		try {
			int integerValue;
			integerValue = new Integer(integer);
			return integerValue;
		}
		catch(Exception e) {
			System.out.println("Invalid Integer type");
			return -1;
		}
	}
	
	//this is a static method which is used to convert the String to double.This method is used in student constructor to change the gpa from string to integer.
	public static double CreateDouble(String doubleString) {
		try {
			double doubleValue;
			doubleValue = new Double(doubleString);
			return doubleValue;
		}
		catch(Exception e) {
			System.out.println("Invalid Integer type");
			return -1;
		}
	}
	/**
	 * demo method to test writing and reading of files.
	 */
	public static void demo() {
		String filePath1 = "./csv/2014-2015.csv" ;
		String filePath2 = "./csv/2015-2016.csv" ;
		String filePath3 = "./csv/2016-2017.csv" ;
		String filePath4 = "./csv/2017-2018.csv" ;
		String filePath5 = "./csv/2018-2019.csv" ;
		String filePath6 = "./csv/2019-2020.csv" ;
		
		System.out.println("FileUtil.demo() Started...");
		FileUtil file = new FileUtil();
		file.readFile(filePath1);
		for(String i : file.readFile(filePath1)) {
			System.out.println(i);
		}
		System.out.println("FileUtil.demo() Finished...");
	}
}

