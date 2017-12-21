package ca.ece.ubc.cpen221.mp5.tests;

import ca.ece.ubc.cpen221.mp5.*;
import org.junit.Test;

import java.io.IOException;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class YelpDBTest {
    @Test
    public void test1() throws IOException {
        YelpDB database = new YelpDB("data/restaurants.json","data/reviews.json","data/users.json");
        assertEquals(true, database.containsUser("754HGCLgGJLh1VU_WtGjsw"));
        System.out.println(database.getUser("754HGCLgGJLh1VU_WtGjsw"));
        assertEquals(true, database.containsProduct("BJKIoQa5N2T_oDlLVf467Q"));
        System.out.println(database.getProduct("BJKIoQa5N2T_oDlLVf467Q"));
        assertEquals(true, database.containsReview("0f8QNSVSocn40zr1tSSGRw"));
        System.out.println(database.getReview("0f8QNSVSocn40zr1tSSGRw"));
        assertEquals(true, database.getUser(database.getReview("0f8QNSVSocn40zr1tSSGRw").getUser_id()).getReviews()
                                        .contains("0f8QNSVSocn40zr1tSSGRw"));
        assertEquals(false, database.containsProduct("lalal"));
        assertEquals(16, database.getMatches("category(Chinese)||in(Hearst Ave)").size());
        System.out.println(database.getMatches("in(Telegraph Ave) && category(Chinese) || category(Italian) && price <= 2 "));
        assertEquals(10, database.getMatches("in(Telegraph Ave) && category(Chinese) || category(Italian) && price <= 2 ").size());
        assertEquals(0, database.getMatches("rating < 1 && rating = 1 && rating <= 1 && rating > 1 && rating >= 1").size());
        assertEquals(0, database.getMatches("name(aklsdfj)").size());

    }

    @Test
    public void test2() throws IOException{
        YelpDB database = new YelpDB("data/restaurants.json","data/reviews.json","data/users.json");
        Set<YelpRestaurant> s = database.getMatches("in(Telegraph Ave) && category(Chinese) || category(Italian) && price <= 2 ");
        System.out.println(s.size());
        for(YelpRestaurant r : s){
            System.out.println(r);
        }
       Set<YelpRestaurant> s2 =  database.getMatches("name(bloop)");
        assertEquals(0, s2.size());
        YelpRestaurant bloop = new YelpRestaurant("{\"open\": true, \"url\": \"http://www.yelp.com/biz/the-toaster-oven-berkeley\", \"longitude\": -122.2590117, \"neighborhoods\": [\"Telegraph Ave\", \"UC Campus Area\"], \"name\": \"bloop\", \"categories\": [\"Sandwiches\", \"Restaurants\"], \"state\": \"CA\", \"type\": \"business\", \"stars\": 3.5, \"city\": \"Berkeley\", \"full_address\": \"2309 Telegraph Ave\\nTelegraph Ave\\nBerkeley, CA 94704\", \"review_count\": 55, \"photo_url\": \"http://s3-media2.ak.yelpcdn.com/bphoto/1IirdUSB0yi4TorBV5JPoA/ms.jpg\", \"schools\": [\"University of California at Berkeley\"], \"latitude\": 37.8684118, \"price\": 1}", database);
        database.addProduct(bloop);

        s2 = database.getMatches("name(bloop)");
        assertEquals(1, s2.size());

        YelpUser userx = new YelpUser("{\"name\": \"userx\", \"user_id\": \"pineapples\"}", database);
        database.addUser(userx);
        String userxID = userx.getId();
        assertEquals(true, database.containsUser(userxID));
        assertEquals(0, userx.getAverageStars(), 0.01);
        assertEquals(0, userx.getReviews().size());
        userx.addReview("12345");
        assertEquals(1, userx.getReviews().size());
        }
}
