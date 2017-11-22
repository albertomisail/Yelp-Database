package ca.ece.ubc.cpen221.mp5;

import java.io.IOException;
import java.util.Date;

public class Prueba {
    public static void main(String [] args ) throws IOException {
        Date d = new Date();
        System.out.println(d.getTime());
        YelpDB a = new YelpDB("data/restaurants.json","data/reviews.json","data/users.json");
        Date d2 = new Date();
        System.out.println(d2.getTime());
    }
}
