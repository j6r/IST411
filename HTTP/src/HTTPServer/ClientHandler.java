package HTTPServer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.net.Socket;
import java.util.StringTokenizer;

public class ClientHandler implements Runnable {

    private final Socket socket;
    private final String diary = "src/HTTPServer/diary.txt";
    private final String failMessage = "Failed to read diary";

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
                handlePostRequest(tokenizer);
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
        FileReader diaryReader;
        String line;
        try {
            diaryReader = new FileReader(diary);
            BufferedReader diaryBuffer = new BufferedReader(diaryReader);
            StringBuilder responseBuffer = new StringBuilder();
            while((line = diaryBuffer.readLine()) != null) {
                responseBuffer.append(line);
                responseBuffer.append("\n");
            }
            if (responseBuffer.toString().equals("")) {
                sendResponse(socket, 200, "The diary is empty");
            }
            else {
                sendResponse(socket, 200, responseBuffer.toString());
            }
        } 
        catch (IOException e) {
            System.out.println(failMessage);
            sendResponse(socket, 200, failMessage);
        }
    }

    private void handlePostRequest(StringTokenizer tokenizer) {
        // TODO
    }
}
