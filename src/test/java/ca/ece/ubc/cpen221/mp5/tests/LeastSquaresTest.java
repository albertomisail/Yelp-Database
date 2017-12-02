package ca.ece.ubc.cpen221.test;

import ca.ece.ubc.cpen221.mp5.MP5Db;
import ca.ece.ubc.cpen221.mp5.Predict;
import ca.ece.ubc.cpen221.mp5.YelpDB;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.function.ToDoubleBiFunction;

public class LeastSquaresTest {
    @Test
    public void test0() throws IOException, IllegalArgumentException{
        YelpDB database = new YelpDB("data/restaurants.json","data/reviewstest.json","data/users.json");
        Predict function = (Predict) database.getPredictorFunction("90wm_01FAIqhcgV_mPON9Q");
        System.out.println(function.applyAsDouble(database, "BJKIoQa5N2T_oDlLVf467Q"));
    }

    @Test
    public void test1() throws IOException, IllegalArgumentException{
        YelpDB database = new YelpDB("data/restaurants.json","data/reviews.json","data/users.json");
        Predict function = (Predict) database.getPredictorFunction("aZK6CwG3efw2RIgtN0bRgw");
        assertEquals(true, 0.001>Math.abs(2.5-function.applyAsDouble(database, "9znPyzc0cS8GHH49R8SUaA")));
        assertEquals(true, 0.001>Math.abs(-1-function.applyAsDouble(null, null)));
        assertEquals(true, 0.001>Math.abs(-1-function.applyAsDouble(database, null)));
        assertEquals(true, 0.001>Math.abs(-1-function.applyAsDouble(null, "sdhbfh")));
        assertEquals(true, 0.001>Math.abs(-1-function.applyAsDouble(database, 1)));
        assertEquals(true, 0.001>Math.abs(-1-function.applyAsDouble(1, "sjdkj")));
        assertEquals(true, 0.001>Math.abs(-1-function.applyAsDouble(2, 2)));
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void test2() throws IOException, IllegalArgumentException{
        YelpDB database = new YelpDB("data/restaurants.json","data/reviews.json","data/users.json");
        Predict function = (Predict) database.getPredictorFunction("9fMogxnnd0m9_FKSi-4AoQ");
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void test3() throws IOException, IllegalArgumentException{
        YelpDB database = new YelpDB("data/restaurants.json","data/reviewstest.json","data/users.json");
        Predict function = (Predict) database.getPredictorFunction("yyRtjexqj9Mu1RvEcl3kPQ");
    }
    
    @Test
    public void test4() throws IOException, IllegalArgumentException{
        YelpDB database = new YelpDB("data/restaurantstest.json","data/reviewstest.json","data/users.json");
        Predict function = (Predict) database.getPredictorFunction("90wm_01FAIqhcgV_mPON9Q");
        assertEquals(true, 0.001>Math.abs(1-(function.applyAsDouble(database, "z4YO4rycvYrZEd3VYtzVvw"))));
        assertEquals(true, 0.001>Math.abs(5-(function.applyAsDouble(database, "WXKx2I2SEzBpeUGtDMCS8A"))));
    }
}
