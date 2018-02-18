package HTTPClient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class HttpURLConnectionClient {

    private final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws Exception {
        HttpURLConnectionClient http = new HttpURLConnectionClient();
        System.out.println("Sent Http GET request");
        http.sendGet();
        http.sendPost();

    }

    private void sendGet() {
        try {
            String urlQuery = "http://localhost:8080";
            String userQuery = "java sdk";
            String urlEncoded = urlQuery + URLEncoder.encode(userQuery, "UTF-8");
            System.out.println(urlEncoded);

            String query = "http://localhost:8080?q=java+sdk&ie=utf-8&oe=utf-8";
            URL url = new URL(query);
//            url = new URL(urlEncoded);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            int responseCode = connection.getResponseCode();

            System.out.println("Response Code: " + responseCode);
            if (responseCode == 200) {
                String response = getResponse(connection);
                System.out.println("response: " + response.toString());
            } else {
                System.out.println("Bad Response Code: " + responseCode);
            }
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String getResponse(HttpURLConnection connection) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));) {

            // Process headers
            System.out.println("Request Headers");
            Map<String, List<String>> requestHeaders = connection.getHeaderFields();
            Set<String> keySet = requestHeaders.keySet();
            for (String key : keySet) {
                if ("Set-cookie".equals(key)) {
                    List values = requestHeaders.get(key);
                    String cookie = key + " = " + values.toString() + "\n";
                    String cookieName = cookie.substring(0, cookie.indexOf("="));
                    String cookieValue = cookie.substring(cookie.indexOf("=") + 1, cookie.length());
                    System.out.println(cookieName + ":" + cookieValue);
                }
            }
            System.out.println();

            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            return response.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "";
    }
    private void sendPost(){
        
       
        String url = "http://localhost:8080";   
        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setDoOutput(true);
            
   
            System.out.println("***************");
            System.out.println("Sending 'POST' request to URL: "+ url);
     
            System.out.println("Enter Text:  ");
            Scanner scan = new Scanner(System.in);
            String inputString = scan.nextLine();  
          
            DataOutputStream wr = 
                    new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(inputString);
            wr.flush();
            wr.close();
            
            
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
          
            
            System.out.println(this.getResponse(connection));

            
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (ProtocolException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
        
    }

}
