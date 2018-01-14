package hw01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

   private static final String PROMPT_MESSAGE = "Enter your Message: ";

   public static void main(String args[]) {
      try (
              Socket soc = new Socket("localhost", 1024);
              BufferedReader inputString = new BufferedReader(new InputStreamReader(System.in));
              PrintWriter output = new PrintWriter(soc.getOutputStream(), true);
              BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream())); // System.out.println(in.readLine());
              ) {
         String input;
         System.out.println(PROMPT_MESSAGE);

         while ((input = inputString.readLine()) != null) {
            output.println(input);
            System.out.println(in.readLine());
            System.out.println(PROMPT_MESSAGE);
         }
      } catch (IOException ex) {
         Logger.getLogger(Client.class.getName()).log(Level.SEVERE,
                 "Server was not available or returned an error.", ex);
      }

   }
}
