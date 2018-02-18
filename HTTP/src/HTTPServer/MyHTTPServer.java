package HTTPServer;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyHTTPServer {

   /**
    * The path to the diary data file
    */
   private static final Path DATA_FILE = Paths.get("diary.txt");

   /**
    * The message to send to the client when a new diary entry has been saved
    */
   private static final String POST_RESPONSE_SUCCESS = "<html><body>"
           + "<p>Your entry has been recorded.</p>"
           + "<p><a href=\"index\">view entries</a></p>"
           + "</body></html>";

   /**
    * The message to send tot he client when saving a diary entry fails
    */
   private static final String POST_RESPONSE_FAIL = "<html><body>"
           + "<p>Your entry has been recorded.</p>"
           + "<p><a href=\"index\">view entries</a></p>"
           + "</body></html>";
   
   
   /**
    * The message to send to the client when the diary was unsuccessfully loaded
    */
   private static final String GET_FAIL = "Failed to read diary";
   public static void main(String[] args) throws Exception {
      System.out.println("MyHTTPServer Started");
      HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
      server.createContext("/", new DetailHandler());
      server.createContext("/index", new DetailHandler());
      server.setExecutor(Executors.newCachedThreadPool());
      server.setExecutor(Executors.newFixedThreadPool(5));
      server.start();
   }

   public static String getResponse() {
      StringBuilder responseBuffer = new StringBuilder();
      responseBuffer
              .append("<html><h1>HTTPServer Home Page.... </h1><br>")
              .append("<b>Welcome to the new and improved web "
                      + "server!</b><BR>")
              .append("</html>");
      return responseBuffer.toString();
   }

   static class IndexHandler implements HttpHandler {

      @Override
      public void handle(HttpExchange exchange) throws IOException {
         System.out.println(exchange.getRemoteAddress());

         String response = getResponse();
         exchange.sendResponseHeaders(200, response.length());

         OutputStream out = exchange.getResponseBody();
         out.write(response.getBytes());
         out.close();
      }
   }

   static class DetailHandler implements HttpHandler {

      @Override
      public void handle(HttpExchange exchange) throws IOException {
         // Get request headers
         System.out.println("\nRequest Headers");
         Headers requestHeaders = exchange.getRequestHeaders();
         Set<String> keySet = requestHeaders.keySet();
         for (String key : keySet) {
            List values = requestHeaders.get(key);
            String header = key + " = " + values.toString() + "\n";
            System.out.print(header);
         }

         // Process request
         String requestMethod = exchange.getRequestMethod();

         if (requestMethod.equalsIgnoreCase("GET")) {
            handleGet(exchange);
         } else if (requestMethod.equalsIgnoreCase("POST")) {
            handlePost(exchange);

         }
      }

      /**
       * Sends the specified message to the client and disconnects
       *
       * @param exchange the HttpExchange
       * @param responseMessage the message body
       * @throws IOException
       */
      private void sendResponse(HttpExchange exchange, String responseMessage) throws IOException {
         // Manage response headers
         Headers responseHeaders = exchange.getResponseHeaders();

         // Send response headers
         responseHeaders.set("Content-Type", "text/html");
         responseHeaders.set("Server", "MyHTTPServer/1.0");
         responseHeaders.set("Set-cookie", "userID=Cookie Monster");
         exchange.sendResponseHeaders(200, responseMessage.getBytes().length);

         System.out.println("Response Headers");
         Set<String> responseHeadersKeySet = responseHeaders.keySet();
         responseHeadersKeySet
                 .stream()
                 .map((key) -> {
                    List values = responseHeaders.get(key);
                    String header = key + " = " + values.toString() + "\n";
                    return header;
                 })
                 .forEach((header) -> {
                    System.out.print(header);
                 });

         // Send message body
         try (OutputStream responseBody = exchange.getResponseBody()) {
            responseBody.write(responseMessage.getBytes());
         }
      }

      /**
       * Handle post request
       *
       * @param exchange the HttpExchange for this request
       * @throws IOException if an error occurs reading the request data or
       * writing the data file
       */
      private void handlePost(HttpExchange exchange) throws IOException {
         Logger.getLogger(this.getClass().toString()).log(Level.INFO, "Processing post request");

         try (BufferedReader br = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
                 BufferedWriter fos = Files.newBufferedWriter(DATA_FILE, StandardOpenOption.APPEND, StandardOpenOption.CREATE)) {

            String inputLine;

            // record date
            fos.write("\n\n\n");
            fos.write(LocalDateTime.now().format(DateTimeFormatter.ofPattern("E MMM d, u h:m a")));
            fos.write("\n\n");

            while ((inputLine = br.readLine()) != null) {
               fos.write(inputLine);
               fos.write("\n\n");
            }

            sendResponse(exchange, POST_RESPONSE_SUCCESS);

         }
      }

      /**
       * Process get request
       *
       * @param exchange the HTTPExchange
       * @throws IOException
       */
      private void handleGet(HttpExchange exchange) throws IOException {
        System.out.println("Get method processed");
        FileReader diaryReader;
        String line;
        try {
            diaryReader = new FileReader(DATA_FILE.toString());
            BufferedReader diaryBuffer = new BufferedReader(diaryReader);
            StringBuilder responseBuffer = new StringBuilder();
            while((line = diaryBuffer.readLine()) != null) {
                responseBuffer.append(line);
                responseBuffer.append("</br>");
            }
            if (responseBuffer.toString().equals("")) {
                sendResponse(exchange, "The diary is empty");
            }
            else {
                sendResponse(exchange, responseBuffer.toString());
            }
            diaryBuffer.close();
        } 
        catch (IOException e) {
            System.out.println(GET_FAIL);
            sendResponse(exchange, GET_FAIL);
        }
    }
   }
}
