package me.zacharilius.JaSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.*;

/**
 * Servlet implementation class ServletJSON
 */
@WebServlet("/ServletJSON")
public class ServletJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static LinkedList<Jason> jasons= new LinkedList<Jason>();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletJSON() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("Executed");
			
		// 1. get received JSON data from request
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }
//        System.out.println(json);

        // 2. initiate GSON mapper
        ObjectMapper mapper = new ObjectMapper();
//        System.out.println("Created ObjectMapper()");
        
        // 3.  Convert JSON to Jason
        Jason jason = mapper.readValue(json, Jason.class);
//    	System.out.println(jason.toString());
    	
    	
        // 4.  Set response type to JSON
        response.setContentType("application/json");            

    	// 5.  Add new Jason to jasons
        if(jason.getfName().length() != 0 || jason.getlName().length() != 0 || 
        		jason.getUrl().length() != 0 || jason.getMovies().isEmpty() || 
        		jason.getTVShows().isEmpty()){
        	
        	//Replace this with database add
     //   	jasons.add(jason);
        	try {
				MySQL_Database.writeJason(jason);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        }
		jasons = MySQL_Database.getJasons();
		
        // 6.  Send List<Jason> as JSON to client
//    	String j = gson.fromJson(jason, Jason.class);
        mapper.writeValue(response.getOutputStream(), jasons);
	}

}
