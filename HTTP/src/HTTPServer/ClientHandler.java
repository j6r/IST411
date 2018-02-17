package HTTPServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHandler implements Runnable {

   private final Socket socket;

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

   private static final Logger LOG = Logger.getLogger(ClientHandler.class.getName());

   public ClientHandler(Socket socket) {
      this.socket = socket;
   }

   @Override
   public void run() {
      System.out.println("\nClientHandler Started for "
              + this.socket);
      handleRequest(this.socket);
      System.out.println("ClientHandler Terminated for "
              + this.socket + "\n");
   }

   public void handleRequest(Socket socket) {
      try (BufferedReader in = new BufferedReader(
              new InputStreamReader(socket.getInputStream()));) {
         String headerLine = in.readLine();
         StringTokenizer tokenizer
                 = new StringTokenizer(headerLine);

         String httpMethod = tokenizer.nextToken();

         if (httpMethod.equals("GET")) {
            handleGetRequest(tokenizer);
         } else if (httpMethod.equals("POST")) {
            handlePostRequest(in);
         } else {
            System.out.println("The HTTP method is not recognized");
            sendResponse(socket, 405, "Method Not Allowed");
         }

      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   public void sendResponse(Socket socket,
           int statusCode, String responseString) {
      String statusLine;
      String serverHeader = "Server: WebServer\r\n";
      String contentTypeHeader = "Content-Type: text/html\r\n";

      try (DataOutputStream out
              = new DataOutputStream(socket.getOutputStream());) {
         if (statusCode == 200) {
            statusLine = "HTTP/1.0 200 OK" + "\r\n";
            String contentLengthHeader = "Content-Length: "
                    + responseString.length() + "\r\n";

            out.writeBytes(statusLine);
            out.writeBytes(serverHeader);
            out.writeBytes(contentTypeHeader);
            out.writeBytes(contentLengthHeader);
            out.writeBytes("\r\n");
            out.writeBytes(responseString);
         } else if (statusCode == 405) {
            statusLine = "HTTP/1.0 405 Method Not Allowed" + "\r\n";
            out.writeBytes(statusLine);
            out.writeBytes("\r\n");
         } else {
            statusLine = "HTTP/1.0 404 Not Found" + "\r\n";
            out.writeBytes(statusLine);
            out.writeBytes("\r\n");
         }

         out.close();
      } catch (IOException ex) {
         // Handle exception
      }
   }

   private void handleGetRequest(StringTokenizer tokenizer) {
      System.out.println("Get method processed");
      String httpQueryString = tokenizer.nextToken();
      StringBuilder responseBuffer = new StringBuilder();
      responseBuffer
              .append("<html><h1>WebServer Home Page.... </h1><br>")
              .append("<b>Welcome to my web server!</b><BR>")
              .append("</html>");
      sendResponse(socket, 200, responseBuffer.toString());
   }

   /**
    * Handles post requests by adding the message content to the diary data file
    *
    * @param br bufferedReader for the request
    * @throws IOException if an error occurs reading data from the request or
    * writing data to file
    */
   private void handlePostRequest(BufferedReader br) throws IOException {

      LOG.log(Level.INFO, "Processing POST request");
      String inputLine;

      try (BufferedWriter fos = Files.newBufferedWriter(DATA_FILE,
              StandardOpenOption.APPEND, StandardOpenOption.CREATE)) {

         inputLine = br.readLine();
         while (inputLine != null && !inputLine.isEmpty()) {
            inputLine = br.readLine();
         }

         while ((inputLine = br.readLine()) != null) {
            fos.write(inputLine);
            fos.write("\n\n");
            fos.flush();
         }

         sendResponse(socket, 200, POST_RESPONSE_SUCCESS);

      } catch (IOException ex) {
         LOG.log(Level.SEVERE, "Error occured while rocessing POST request", ex);
         sendResponse(socket, 200, POST_RESPONSE_FAIL);
         throw ex;
      }

   }

}
