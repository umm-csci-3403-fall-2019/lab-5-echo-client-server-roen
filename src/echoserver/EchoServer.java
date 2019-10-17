package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static final int portNumber = 6013;

    public static void main(String[] args) {
        try {
            // Start listening on the specified port
            ServerSocket sock = new ServerSocket(portNumber);

            // Run forever, which is common for server style services
            while (true) {
                // Wait until someone connects
                Socket client = sock.accept();
                System.out.println("Got a request!");
                
                // Define out input and output streams
                InputStream in = client.getInputStream();
                OutputStream out = client.getOutputStream();

                // Declare our actual output
                int x;
                while ((x = in.read()) != -1) {
                    out.write((byte) x);
                    if (x == '\n') {
                        out.flush();
                    }
                }
                
                // Close the client socket since we're done.
                client.close();
            }
            // *Very* minimal error handling.
        } catch (IOException ioe) {
            System.out.println("We caught an unexpected exception");
            System.err.println(ioe);
        }
    }
}