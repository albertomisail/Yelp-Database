package ca.ece.ubc.cpen221.mp5;

import java.io.*;
import java.math.BigInteger;
import java.net.Socket;
import java.util.Scanner;

public class YelpDBClient {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    // Rep invariant: socket, in, out != null

    /**
     * Make a YelpDBClient and connect it to a server running on
     * hostname at the specified port.
     * @throws IOException if can't connect
     */
    public YelpDBClient(String hostname, int port) throws IOException {
        socket = new Socket(hostname, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    /**
     * Send a request to the server. Requires this is "open".
     * @param s, the query
     * @throws IOException if network or server failure
     */
    public void sendRequest(String s) throws IOException {
        out.print(s + "\n");
        out.flush(); // important! make sure x actually gets sent
    }

    /**
     * Get a reply from the next request that was submitted.
     * Requires this is "open".
     * @return the reply to the requested query
     * @throws IOException if network or server failure
     */
    public String getReply() throws IOException {
        String reply = in.readLine();
        if (reply == null) {
            throw new IOException("connection terminated unexpectedly");
        }
        return reply;
    }

    /**
     * Closes the client's connection to the server.
     * This client is now "closed". Requires this is "open".
     * @throws IOException if close fails
     */
    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }

    /**
     * Use a YelpDBClient to request queries
     */
    public static void main(String[] args) {
        try {
            YelpDBClient client = new YelpDBClient("localhost", YelpDBServer.PORT);
            //not sure what code to put here, the rest is based on the fibonacci example on canvas
            client.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
