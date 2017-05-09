import java.io.IOException;
import org.json.*;
import org.json.JSONException;
import java.util.*;
import java.util.Calendar;
import java.io.*;

  public class qJson{   
     public static void main(String[] args) throws IOException, InterruptedException{
            String str = new String();
	    FileReader fs = new FileReader("preelection.json");
	    Scanner file = new Scanner(fs);
	try{
	   while(file.hasNextLine()){
	     str += file.nextLine();
		
	    }
            JSONObject obj = new JSONObject(str);
	    JSONObject data = obj.getJSONObject("data");
	    JSONArray children = data.getJSONArray("children");
	    System.out.println(children.length());
	   for(int x = 0;x < children.length();x++){
	   	JSONObject temp = children.getJSONObject(x);
		JSONObject d = temp.getJSONObject("data");
		String title = d.getString("title");
		Long timestamp = d.getLong("created_utc");
		System.out.println(title+" "+timestamp);
		
	   } 
	   /*while (keys.hasNext()) {
		System.out.println(children);
    		String component = value.getString("title");
    		System.out.println(component);
	   }*/
	}catch(JSONException e){
	
	}


        }
}
