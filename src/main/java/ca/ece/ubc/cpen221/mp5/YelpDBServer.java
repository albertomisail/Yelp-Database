package ca.ece.ubc.cpen221.mp5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class YelpDBServer {
    public static  final int PORT = 4949;

    private ServerSocket serverSocket;

    public YelpDBServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void serve() throws IOException {
        while (true){
            final Socket socket = serverSocket.accept();
            Thread handler = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        try {
                            handle(socket);
                        }finally {
                            socket.close();
                        }
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            });
            handler.start();
        }
    }

    private void handle(Socket socket) throws IOException {
        System.err.println("Client connected");

        BufferedReader in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));

        PrintWriter out = new PrintWriter(new OutputStreamWriter(
                socket.getOutputStream()),true);


            for(String line = in.readLine(); line != null;
                    line = in.readLine()) {
                System.err.println("Request " + line);
                //analysis of queries happens here

                out.flush();
            }

            out.close();
            in.close();

    }

    public static void main(String[] args ){
        System.out.println("Start serving now");
        try {
            YelpDBServer server = new YelpDBServer(
                    PORT);
            server.serve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
