package HTTPServer;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
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

   private static final Path DATA_FILE = Paths.get("diary.txt");

   public static void main(String[] args) throws Exception {
      System.out.println("MyHTTPServer Started");
      HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
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
      
      private void sendResponse(HttpExchange exchange) {
         
      }

      /**
       * Handle post request
       *
       * @param exchange the HttpExchange for this request
       * @throws IOException if an error occurs reading the request data or writing the data file
       */
      private void handlePost(HttpExchange exchange) throws IOException {
         Logger.getLogger(this.getClass().toString()).log(Level.INFO,
                 String.format("Processing post request (%d bytes)", exchange.getRequestBody().available()));

         if (!Files.exists(DATA_FILE)) {
            Files.createFile(DATA_FILE);
         }

         try (BufferedReader br = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
                 BufferedWriter fos = Files.newBufferedWriter(DATA_FILE, StandardOpenOption.APPEND)) {

            String inputLine;
            
            // record date
            fos.write(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE));
            fos.write("\n\n");

            while ((inputLine = br.readLine()) != null) {
               fos.write(inputLine);
               fos.write("\n\n");
            }

            fos.close();
         }

      }

      /**
       * Process get request
       *
       * @param exchange the HTTPExchange
       * @throws IOException
       */
      private void handleGet(HttpExchange exchange) throws IOException {
         // Process request body
         System.out.println("Request Body");
         InputStream in = exchange.getRequestBody();
         if (in != null) {
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(in));) {
               String inputLine;
               StringBuilder response = new StringBuilder();
               while ((inputLine = br.readLine()) != null) {
                  response.append(inputLine);
               }
               br.close();
               System.out.println(inputLine);
            } catch (IOException ex) {
               ex.printStackTrace();
            }
         } else {
            System.out.println("Request body is empty");
         }
         // Manage response headers
         Headers responseHeaders = exchange.getResponseHeaders();

         // Send response headers
         String responseMessage = getResponse();
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
   }
}
