package ca.ece.ubc.cpen221.mp5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class YelpDBServer {
    public static  final int PORT = 4949;

    private ServerSocket serverSocket;
    private YelpDB database;

    public YelpDBServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        database = new YelpDB("data/restaurants/json","data/reviews.json","data/users.json");
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

    private void handleSimpleRequests(String line, PrintWriter out){
        getRestaurantRequest(line, out);
        addUserRequest(line, out);
        addRestaurantRequest(line, out);
        addReviewRequest(line, out);
    }

    private void getRestaurantRequest(String line, PrintWriter out){
        if(line.length()>=15) {
            String aux = line.substring(0, 15);
            if (aux.equals("GETRESTAURANT ")) {
                String restaurantId = line.substring(15);
                if (!database.containsProduct(restaurantId)) {
                    out.print("ERR: NO_SUCH_RESTAURANT\n");
                } else {
                    String restaurant = ((YelpRestaurant) database.getProduct(restaurantId)).toString();
                    out.print(restaurant + "\n");
                }
            }
        }
    }

    private void addUserRequest(String line, PrintWriter out){
        if(line.length()>=9) {
            String aux = line.substring(0, 9);
            if (aux.equals("ADDUSER ")) {
                try {
                    String userInfo = line.substring(9);
                    YelpUser user = new YelpUser(userInfo, this.database);
                    this.database.addUser(user);
                    out.print(user.toString() + "\n");
                } catch (Exception e) {
                    out.print("ERR: INVALID_USER_STRING\n");
                }
            }
        }
    }

    private void addRestaurantRequest(String line, PrintWriter out){
        if(line.length()>=15) {
            String aux = line.substring(0, 15);
            if (aux.equals("ADDRESTAURANT ")) {
                try {
                    String restaurantInfo = line.substring(15);
                    YelpRestaurant restaurant = new YelpRestaurant(restaurantInfo, this.database);
                    this.database.addProduct(restaurant);
                    out.print(restaurant.toString() + "\n");
                } catch (Exception e) {
                    out.print("ERR: INVALID_RESTAURANT_STRING\n");
                }
            }
        }
    }

    private void addReviewRequest(String line, PrintWriter out){
        if(line.length()>=11) {
            String aux = line.substring(0, 11);
            if (aux.equals("ADDREVIEW ")) {
                try {
                    String reviewInfo = line.substring(11);
                    YelpReview review = new YelpReview(reviewInfo, this.database);
                    if(!this.database.containsUser(review.getUser_id())||!this.database.containsProduct(review.getProduct_id())){
                        if(this.database.containsProduct(review.getProduct_id())){
                            out.print("ERR: NO_SUCH_USER");
                        }
                        else{
                            out.print("ERR: NO_SUCH_RESTAURANT");
                        }
                    }
                    this.database.addReview(review);
                    User user = database.getUser(review.getUser_id());
                    user.addReview(review.getId());
                    out.print(review.toString() + "\n");
                } catch (Exception e) {
                    out.print("ERR: INVALID_REVIEW_STRING\n");
                }
            }
        }
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
