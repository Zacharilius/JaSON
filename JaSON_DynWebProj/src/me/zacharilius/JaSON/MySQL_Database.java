package me.zacharilius.JaSON;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.ListIterator;


public class MySQL_Database {
	private static final String username = "root";
	private static final String password = "Run4fun1!";
	private static Connection connection;
	
	public static void main(String[] args){
		System.out.println("MySQL_Database");
		setup();
		removeAllRows();
		dbaseSetup();
	}
	
	private static void setup(){
//		System.out.println("-------- MySQL JDBC Connection Testing ------------");
		 
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Error with JDBC Driver");
			e.printStackTrace();
			return;
		}
		connection = null;
	 
		try {
			connection = DriverManager
			.getConnection("jdbc:mysql://localhost:3306/jason",username, password);
	 
		
	 
		if (connection != null) {
//			System.out.println("Success: Database connection successful");
		} else {
			System.out.println("Failure: Database connection unsuccessful");
		}
		/*
		test();
		getJasons();
		removeAllRows();
		*/
		} 
		catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}
	}
	  public static LinkedList<Jason> getJasons() {
		  setup();
		  LinkedList<Jason> jasonLL = new LinkedList();
		  try{
			  StringBuilder str = new StringBuilder();
			  
			  //Create a statement for each ResultSet
			  Statement statementJason = connection.createStatement();
			  Statement statementTV = connection.createStatement();
			  Statement statementMovie = connection.createStatement();
			  
			  //Create a ResultSet
			  ResultSet jasonResultSet = statementJason.executeQuery("select * from jasons;");
			  ResultSet movieResultSet;
			  ResultSet tvResultSet;
			  while (jasonResultSet.next()) {
				  Jason jason = new Jason();
				  jason.setfName(jasonResultSet.getString("F_NAME") + " ");
			      jason.setlName(jasonResultSet.getString("L_NAME"));
			      jason.setUrl(jasonResultSet.getString("URL"));
			      int id = Integer.parseInt(jasonResultSet.getString("id"));
			      
			      // Get Movies
				  movieResultSet = statementMovie.executeQuery("select * from movies where j_id = " + id);
				  while(movieResultSet.next()){
					  jason.addMovie(movieResultSet.getString("movieName"));
				  }
				  movieResultSet.close();

			      // Get TV Shows
				  tvResultSet = statementTV.executeQuery("select * from tv_shows where j_id = " + id);
				  while(tvResultSet.next()){
					  jason.addTVShow(tvResultSet.getString("tv_Name"));
				  }
				  tvResultSet.close();
				  jasonLL.add(jason);
			  }
			  jasonResultSet.close();
		  }
		  catch (SQLException e) {
				System.out.println("Connection Failed! Check output console");
				e.printStackTrace();
				return jasonLL;
		  }
		  return jasonLL;
	  }
	  public static void writeJason(Jason jason) throws SQLException {
		  setup();
		  Statement statement = connection.createStatement();
		  int jasonID;
		  
		  //Insert into the database
		  PreparedStatement prepStatement = connection.prepareStatement(
				  "INSERT INTO jasons values(DEFAULT, ?, ?, ?)");
		  prepStatement.setString(1, jason.getfName());
		  prepStatement.setString(2, jason.getlName());
		  prepStatement.setString(3, jason.getUrl());
		  prepStatement.executeUpdate();
		  
		  // Gets id from last inserted value
		  String jasonIDQuery = "Select id from jasons where f_name = '" + 
				  jason.getfName() + "' AND l_name = '" + jason.getlName() + "';";
//		  System.out.println(jasonIDQuery);
		  ResultSet resultSet = statement.executeQuery(jasonIDQuery);
		  if(resultSet.next()){
			  jasonID = Integer.parseInt(resultSet.getString("ID"));
		  }
		  else{
			  jasonID = -1;
		  }
		  
		  // Insert into movies table
		  prepStatement = connection.prepareStatement(
				  "INSERT INTO movies values(DEFAULT, ?," + jasonID + " );");
		  ListIterator<String> listIteratorMovies = jason.getMovies().listIterator();
		  while(listIteratorMovies.hasNext()){
			  prepStatement.setString(1, listIteratorMovies.next());
			  prepStatement.executeUpdate();
		  }	
		  
		  
		  
		  
		  // Insert into tvShows table
		  prepStatement = connection.prepareStatement(
				  "INSERT INTO tv_shows values(DEFAULT, ?," + jasonID + " );");
		  ListIterator<String> listIteratorTVShows = jason.getTVShows().listIterator();
		  while(listIteratorTVShows.hasNext()){
			  prepStatement.setString(1, listIteratorTVShows.next());
			  prepStatement.executeUpdate();
		  }		  


		  statement.close();	  
	  }
	  private static void dbaseSetup(){
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
	    	second.setlName("Segel");
	    	second.setUrl("http://ia.media-imdb.com/images/M/MV5BMTUwNzcxNzM1Nl5BMl5BanBnXkFtZTgwNzA5NzU4MjE@._V1_SY317_CR12,0,214,317_AL_.jpg");
	    	second.addMovie("Forgetting Sarah Marshall");
	    	second.addMovie("The Muppets");
	    	second.addTVShow("How I Met Your Mother");
	    	second.addTVShow("Freaks and Geeks");
	    	
	    	try {
				writeJason(first);
		    	writeJason(second);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	  private static void removeAllRows(){
		  try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("set foreign_key_checks=0;");
			statement.executeUpdate("TRUNCATE TABLE jasons;");
			statement.executeUpdate("TRUNCATE TABLE movies;");
			statement.executeUpdate("TRUNCATE TABLE tv_shows;");
			statement.executeUpdate("set foreign_key_checks=1;");

			
			
			System.out.println("Tables removed");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	  }
	
}
