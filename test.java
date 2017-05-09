import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.io.DataOutputStream;

import java.util.Map;
import java.util.*;

import java.net.HttpURLConnection;

import org.json.*;

class test {

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


public static void login( URL url, String user, String pw ) throws IOException
    {
        String data = "api_type=json&user=" + user + "&passwd=" + pw;
        HttpURLConnection ycConnection = null;
        ycConnection = ( HttpURLConnection ) url.openConnection();
        ycConnection.setRequestMethod( "POST" );
        ycConnection.setDoOutput( true );
        ycConnection.setUseCaches( false );
        ycConnection.setRequestProperty( "Content-Type",
            "application/x-www-form-urlencoded; charset=UTF-8" );
        ycConnection.setRequestProperty( "Content-Length", String.valueOf( data.length() ) );

        DataOutputStream wr = new DataOutputStream(
            ycConnection.getOutputStream() );
        r.writeBytes( data );
        wr.flush();
        wr.close();
        InputStream is = ycConnection.getInputStream();
        BufferedReader rd = new BufferedReader( new InputStreamReader( is ) );
        String line;
        StringBuffer response = new StringBuffer();
        while ( ( line = rd.readLine() ) != null )
        {
            response.append( line );
            response.append( '\r' );
        }
        for ( Map.Entry< String, List< String >> r : ycConnection.getHeaderFields().entrySet() )
        {
            System.out.println( r.getKey() + ": " + r.getValue() );
        }
        rd.close();
        System.out.println( response.toString() );
   }

  public static void main(String[] args) throws IOException, JSONException {
    URL url = new URL("https://ssl.reddit.com/api/login/tempForCSCI415");
    login(url, "tempForCSCI415", "Asimplepassword1");
    JSONObject json = readJsonFromUrl("https://www.reddit.com/r/politics/.json?search?q=timestamp%3A1409544241..1417406641&sort=new&restrict_sr=on&syntax=cloudsearch");
    System.out.println(json.toString());
    System.out.println(json.get("id"));
  }
}
