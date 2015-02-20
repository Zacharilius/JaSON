package me.zacharilius.JaSON;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.LinkedList;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;


public class JSON {
	
	private static LinkedList<Jason> jasons= new LinkedList<Jason>();
	private static Gson gson = new Gson();
	
    public static void main( String[] args )
    {
    	Jason first = new Jason();
    	first.setfName("Jason");
    	first.setlName("Bateman");
    	first.setUrl("http://www.moviepilot.de/files/images/0487/0030/Jason_Bateman.jpg");
    	first.addMovie("Identity Thief");
    	first.addMovie("Horrible Bosses");
    	first.addTVShow("Arrested Development");
    	first.addTVShow("Sit Down Shut Up");
    	
    	Jason second = new Jason();
    	second.setfName("Jason");
    	second.setlName("Toople");
    	second.setUrl("http://www.moviepilot.de/files/images/0487/0030/Jason_Bateman.jpg");
    	second.addMovie("Identity Thief");
    	second.addMovie("Horrible Bosses");
    	second.addTVShow("Arrested Development");
    	second.addTVShow("Sit Down Shut Up");
    	
    	String f_JSON = gson.toJson(first, Jason.class);
    	writeJSON(f_JSON);
    	
    	jasons.add(first);
    	jasons.add(second);

    	String json = readJSON();
    	
    	System.out.println(json);
    	
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("Created ObjectMapper()");
        Jason jason = new Jason();
        try {
			jason = mapper.readValue(json, Jason.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(jason.toString());


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