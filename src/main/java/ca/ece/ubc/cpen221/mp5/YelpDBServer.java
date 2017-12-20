package ca.ece.ubc.cpen221.mp5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class YelpDBServer {
    public static final int PORT = 4949;

    private ServerSocket serverSocket;
    private YelpDB database;

    public YelpDBServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        database = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");
    }

    public void serve() throws IOException {
        while (true) {
            final Socket socket = serverSocket.accept();
            Thread handler = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        try {
                            handle(socket);
                        } finally {
                            socket.close();
                        }
                    } catch (IOException e) {
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
                socket.getOutputStream()), true);

        try {
            for (String line = in.readLine(); line != null;
                 line = in.readLine()) {
                System.err.println("Request " + line);
                handleSimpleRequests(line, out);
                out.flush();
            }
        } finally {
            out.close();
            in.close();
        }

    }

    private void handleSimpleRequests(String line, PrintWriter out) {
        String[] request = line.split(" ", 2);
        String handler = request[0];
        String query = null;
        if (request.length == 2) query = request[1];
        if (handler.equals("QUERY")) {
            structuredQuery(query, out);
        } else if (handler.equals("GETRESTAURANT")) {
            getRestaurantRequest(query, out);
        } else if (handler.equals("ADDUSER")) {
            addUserRequest(query, out);
        } else if (handler.equals("ADDRESTAURANT")) {
            addRestaurantRequest(query, out);
        } else if (handler.equals("ADDREVIEW")) {
            addReviewRequest(query, out);
        } else {
            out.println("INVALID REQUEST");
        }
    }

    private void structuredQuery(String query, PrintWriter out) {
        try {
            out.println(database.getMatches(query).toString());
            //**TODO Make json array -> string representation -> store in out
        } catch (Exception e) {
            out.print("ERR: INVALID_QUERY\n");

        }
    }

    private void getRestaurantRequest(String restaurantId, PrintWriter out) {
        if (!database.containsProduct(restaurantId)) {
            out.print("ERR: NO_SUCH_RESTAURANT\n");
        } else {
            String restaurant = ((YelpRestaurant) database.getProduct(restaurantId)).toString();
            out.print(restaurant + "\n");
        }
    }

    private void addUserRequest(String userInfo, PrintWriter out) {
        try {
            YelpUser user = new YelpUser(userInfo, this.database);
            this.database.addUser(user);
            out.print(user.toString() + "\n");
        } catch (Exception e) {
            out.print("ERR: INVALID_USER_STRING\n");
        }
    }

    private void addRestaurantRequest(String restaurantInfo, PrintWriter out) {
        try {
            YelpRestaurant restaurant = new YelpRestaurant(restaurantInfo, this.database);
            this.database.addProduct(restaurant);
            out.print(restaurant.toString() + "\n");
        } catch (Exception e) {
            out.print("ERR: INVALID_RESTAURANT_STRING\n");
        }
    }

    private void addReviewRequest(String reviewInfo, PrintWriter out) {
        try {
            YelpReview review = new YelpReview(reviewInfo, this.database);
            if (!this.database.containsUser(review.getUser_id()) || !this.database.containsProduct(review.getProduct_id())) {
                if (this.database.containsProduct(review.getProduct_id())) {
                    out.print("ERR: NO_SUCH_USER");
                } else {
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

    /**
     * Start a YelpServer running on the default port.
     */
    public static void main(String[] args) {
        try {
            YelpDBServer server = new YelpDBServer(PORT);
            server.serve();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
