import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String args[]) {

        try {
            System.out.println(" Client has be started");
            Socket soc = new Socket("localhost", 1024);
            BufferedReader inputString = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter your Message");
            String str = inputString.readLine();
            PrintWriter output = new PrintWriter(soc.getOutputStream(),true);
            output.println(str);

            BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            System.out.println(in.readLine());
            inputString.close();
            output.close();
            soc.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(1);

        }



    }
}
