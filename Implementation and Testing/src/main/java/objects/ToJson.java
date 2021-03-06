package objects;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ToJson {
	ArrayList<String> queries;
	
	 public ToJson(Results myres, String filename) throws IOException {
		 
		 
	       
	        ObjectMapper mapper = new ObjectMapper();

	        //change this to your own path
	        File file = new File( filename + ".json");
	        
	        if(file.exists()) {
	        	return;
	        }
	        
	        File list = new File("queries.txt");
	        
	        //if query list does not exist create it 
	        if(!list.exists()) {
	        	queries = new ArrayList<String>();
	        	//queries.add(filename);
	        	mapper.writeValue(list, queries);
	        }
	        //else {
	        	queries = mapper.readValue(list, ArrayList.class);
	        	if(!queries.contains(filename)) {
	        		queries.add(filename);
	        	}
	        	
	        	mapper.writeValue(list, queries);
	        //}
	        
	            // Serialize Java object info JSON file.
	            mapper.writeValue(file, myres);
	           

//	        try {
//	            // Deserialize JSON file into Java object.
//	            Results newArtist = mapper.readValue(file, Results.class);
//	            for(int i = 0; i < newArtist.restList.size(); i++) {
//	            	System.out.println(newArtist.restList.get(i).name);
//	            }
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
	    }
	 
	 //gt previous queries
	 public ArrayList<String> getQueries() throws JsonParseException, JsonMappingException, IOException {
		 
		 ObjectMapper mapper = new ObjectMapper();
		 File list = new File("queries.txt");
		  //if query list does not exist create it 
	        if(!list.exists()) {
	        	return null;
	        }
	        else {
	        	return mapper.readValue(list, ArrayList.class);
	        }
		 
	 }

}
