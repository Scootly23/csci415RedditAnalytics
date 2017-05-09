import java.io.IOException;
import org.json.*;
import org.json.JSONException;
import java.util.*;
import java.util.Calendar;
import java.io.*;

  public class testJson{   
     public static void main(String[] args) throws IOException, InterruptedException{
            String str = new String();
	    FileReader fs = new FileReader("preelection.json");
	    Scanner file = new Scanner(fs);
	    PrintWriter out = new PrintWriter("preOutput.txt");
	try{
	   while(file.hasNextLine()){
	     str += file.nextLine();
		
	    }
            JSONObject obj = new JSONObject(str);
	    
	    JSONArray data = obj.getJSONArray("all_data");
	   System.out.println("Created JSONArray from all_data");
	   System.out.println(data.length()); 
	   for(int i = 0; i < data.length(); i++){	
	    JSONObject individual = data.getJSONObject(i).getJSONObject("data");	
	    JSONArray children = individual.getJSONArray("children");
	    System.out.println(i);
	   for(int x = 0;x < children.length();x++){
	   	JSONObject temp = children.getJSONObject(x);
		JSONObject d = temp.getJSONObject("data");
		String title = d.getString("title");
		long utc = d.getLong("created_utc");
		out.println(title+"``"+utc);
		System.out.println("Title: "+title+"\nTIMESTAMP: "+utc);
		}	
	   } 
	   /*while (keys.hasNext()) {
		System.out.println(children);
    		String component = value.getString("title");
    		System.out.println(component);
	   }*/
	}catch(JSONException e){
		e.printStackTrace();
	}


        }
}
