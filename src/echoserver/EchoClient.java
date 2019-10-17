package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;

public class EchoClient {
    public static final int portNumber = 6013;

    public static void main(String[] args) throws IOException {
        String server;
        // Use "127.0.0.1", i.e., localhost, if no server is specified.
        if (args.length == 0) {
            server = "127.0.0.1";
        } else {
            server = args[0];
        }

        try {
            // Connect to the server
            Socket socket = new Socket(server, portNumber);

            InputStream in = socket.getInputStream();
            
            OutputStream out = socket.getOutputStream();
            
            // Get the input stream so we can read from that socket

            // BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            
            int output;
            int input;
            while ((output = System.in.read()) != -1){
                out.write((byte) output);
                out.flush();
                input = in.read();
                System.out.write(input);
                }
            
            

            // Print all the input we receive from the server
            // String line;
            // while ((line = reader.readLine()) != null) {
            // System.out.println(line);
            // }
            System.out.flush();
            // Close the socket when we're done reading from it
            socket.close();

            // Provide some minimal error handling.
        } catch (ConnectException ce) {
            System.out.println("We were unable to connect to " + server);
            System.out.println("You should make sure the server is running.");
        } catch (IOException ioe) {
            System.out.println("We caught an unexpected exception");
            System.err.println(ioe);
        }
    }
}