package me.zacharilius.JaSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.Gson;

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
		
			
		// 1. get received JSON data from request
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }
        
        // 2. initiate GSON mapper
//        Gson gson = new Gson();
        ObjectMapper mapper = new ObjectMapper();

        // 3.  Convert JSON to Jason
//    	Jason jason = gson.fromJson(json, Jason.class);
        Jason jason = mapper.readValue(json, Jason.class);

        // 4.  Set response type to JSON
//    	response.setContentType("application/json");
        response.setContentType("application/json");            

    	// 5.  Add new Jason to jasons
//    	jasons.add(jason);
        jasons.add(jason);

        // 6.  Send List<Jason> as JSON to client
//    	String j = gson.fromJson(jason, Jason.class);
        mapper.writeValue(response.getOutputStream(), jasons);

/**
 *     	

        
        // 2. initiate jackson mapper
        ObjectMapper mapper = new ObjectMapper();

        // 3. Convert received JSON to Article
        Article article = mapper.readValue(json, Article.class);
 
        // 4. Set response type to JSON
        response.setContentType("application/json");            
 
        // 5. Add article to List<Article>
        articles.add(article);
 
        // 6. Send List<Article> as JSON to client
        mapper.writeValue(response.getOutputStream(), articles);
 */
	}

}
