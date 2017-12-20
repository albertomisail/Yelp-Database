package ca.ece.ubc.cpen221.mp5.tests;

import ca.ece.ubc.cpen221.mp5.*;
import org.junit.Test;

import java.io.IOException;

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
}
