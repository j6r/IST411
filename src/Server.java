
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

   public static void main(String args[]) {

      System.out.println("Waiting for Client...");
      try (
              ServerSocket ss = new ServerSocket(1024);
              Socket sock = ss.accept();
              BufferedReader input = new BufferedReader(new InputStreamReader(sock.getInputStream()));
              PrintWriter output = new PrintWriter(sock.getOutputStream(), true);) {
         String inputLine;
         while ((inputLine = input.readLine()) != null) {
            output.println("The Server Reply: " + inputLine);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

   }

}
