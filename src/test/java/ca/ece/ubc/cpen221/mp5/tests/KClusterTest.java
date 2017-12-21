package ca.ece.ubc.cpen221.mp5.tests;

        import ca.ece.ubc.cpen221.mp5.*;
        import org.junit.Test;

        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Map;
        import java.util.Set;

        import static org.junit.Assert.assertEquals;

public class KClusterTest {

    @Test
    public void test0() throws IOException {
        YelpDB database = new YelpDB("data/restaurants.json","data/reviews.json","data/users.json");
        System.out.println(database.kMeansClusters_json(130));
        int k =80;
        List<Set<YelpRestaurant>> list = database.kMeansClusters(k);
        for(int i = 0; i < k; i++) {
            assertEquals(false, list.get(i).isEmpty());
        }
        List<Point> centers = new ArrayList<>();
        for(Set<YelpRestaurant> set : list){
            Point center = Point.getCentroid(set);
            centers.add(center);
        }
        for (int i = 0; i < list.size(); i++){
            int clusterNumber = i+1;
            for(YelpRestaurant restaurant : list.get(i)){
                int a = restaurant.getPoint().getClosestPoint(centers);
                assertEquals(clusterNumber, a+1);
            }
        }
        assertEquals(k, list.size());
        System.out.println(list);
    }
}