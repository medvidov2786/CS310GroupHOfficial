package servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import objects.Results;

/**
 * Servlet implementation class GetQueries
 */
@WebServlet("/GetQueries")
public class GetQueries extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetQueries() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 ObjectMapper mapper = new ObjectMapper();
		 ArrayList<String> queries = null;
		 ArrayList<String> images = new ArrayList<String>();
	     Results temp = null;
		 File list = new File("queries.txt");
		  //if query list does not exist create it 
	        if(!list.exists()) {
	        	queries = null;
	        }
	        else {
	        	queries = mapper.readValue(list, ArrayList.class);
	        	 for(int i = 0; i < queries.size(); i++) {
	 	        	File getImage = new File(queries.get(i) + ".json");
	 	        	temp = mapper.readValue(getImage, Results.class);
	 	        	images.add(temp.imageList.get(0));
	 	        }
	        }
	        
	        
	       
	       
	        
	        HttpSession session = request.getSession();
	    	Gson gson = new Gson();
			String queriesJSON = gson.toJson(queries);
			String imagesJSON = gson.toJson(images);
			
	        session.setAttribute("queries", queriesJSON);
	        session.setAttribute("imageQueries", imagesJSON);
		
	}

	

}
