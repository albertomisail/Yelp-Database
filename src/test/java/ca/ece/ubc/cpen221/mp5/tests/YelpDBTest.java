package ca.ece.ubc.cpen221.test;

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
    }
}
