package me.zacharilius.JaSON;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.LinkedList;

import com.google.gson.Gson;


public class JSON {
	
	private static LinkedList<Jason> jasons= new LinkedList<Jason>();
	private static Gson gson = new Gson();
	
    public static void main( String[] args )
    {
    	
    	
    	    	
    	// Converts from Java to JSON
    	String json = gson.toJson(createJason());
    	System.out.println("toJSON: " + json);
    	writeJSON(json);
    	
    	// Converts from JSON to Java
    	String fileJSON = readJSON();
    	Jason jason = gson.fromJson(fileJSON, Jason.class);
    	System.out.println("fromJSON: " + jason.getfName() + " " + jason.getlName());
		
    }
    public static String getJasons(){
    	StringBuilder str = new StringBuilder();
    	
    	
    	
    	return str.toString();
    }
    public static Jason createJason(){
    	Jason jason = new Jason();
    	jason.setfName("Jason");
    	jason.setlName("Bateman");
    	jason.setUrl("http://www.moviepilot.de/files/images/0487/0030/Jason_Bateman.jpg");
    	jason.addMovie("Identity Thief");
    	jason.addMovie("Horrible Bosses");
    	jason.addTVShow("Arrested Development");
    	jason.addTVShow("Sit Down Shut Up");
    	return jason;
    }
    public static void writeJSON(String json){
    	File outFile;
    	FileWriter fWriter;
    	PrintWriter pWriter; 
		try {
			outFile = new File("Jason.json");
			fWriter = new FileWriter(outFile);
	    	pWriter = new PrintWriter(fWriter);
	    	pWriter.println(json);
	    	pWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public static String readJSON(){
    	File inFile = new File("Jason.json");
    	StringBuffer str = new StringBuffer();
		try {
			Scanner sc = new Scanner(inFile);
			while(sc.hasNextLine()){
				str.append(sc.nextLine());
	    	}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return str.toString();
    }
}