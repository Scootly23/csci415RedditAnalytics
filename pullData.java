import java.io.BufferedReader;
on-20131018.jarmport java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import packages.json.JSONException;
import packages.json.JSONObject;

class JsonReader {

  private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }

  public static void main(String[] args) throws IOException, JSONException {
    ArrayList<JSONObject> files = new ArrayList<JSONObject>();
    long electionDay = 1478476800;
    long firstTimestamp = 1462579200;
    long tempTimestamp = electionDay;
    while(firstTimestamp <= tempTimestamp) {
	JSONObject json = readJsonFromUrl("https://www.reddit.com/r/politics/search.json?sort=new&restrict_sr=on&limit=100&q=timestamp:"+firstTimestamp+".."+tempTimestamp+"&syntax=cloudsearch");
	files.add(json);
	System.out.println(json.toString());
	System.out.println(json.get("id"));
    }
  }
}
