package hw01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

   private static final String STARTUP_MESSAGE = "Waiting for Client...";
   private static final String REPLY_MESSAGE = "The Server Reply: ";

   public static void main(String args[]) {

      System.out.println(STARTUP_MESSAGE);

      try (
              ServerSocket ss = new ServerSocket(1024);
              Socket sock = ss.accept();
              BufferedReader input = new BufferedReader(new InputStreamReader(sock.getInputStream()));
              PrintWriter output = new PrintWriter(sock.getOutputStream(), true);) {

         String inputLine;
         while ((inputLine = input.readLine()) != null) {
            output.println(REPLY_MESSAGE + inputLine);
         }

      } catch (IOException ex) {
         Logger.getLogger(Server.class.getName()).log(Level.SEVERE,
                 "An error occurred in communication.", ex);
      }

   }

}
