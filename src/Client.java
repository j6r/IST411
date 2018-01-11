
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

   public static void main(String args[]) {
      try (
              Socket soc = new Socket("localhost", 1024);
              BufferedReader inputString = new BufferedReader(new InputStreamReader(System.in));
              PrintWriter output = new PrintWriter(soc.getOutputStream(), true);
              BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream())); // System.out.println(in.readLine());
              ) {
         String input;
         System.out.println("Enter your Message: ");

         while ((input = inputString.readLine()) != null) {
            output.println(input);
            System.out.println(in.readLine());
            System.out.println("Enter your Message: ");
         }
      } catch (Exception e) {
         e.printStackTrace();

      }

   }
}
