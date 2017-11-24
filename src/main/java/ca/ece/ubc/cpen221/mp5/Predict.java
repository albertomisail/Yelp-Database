package ca.ece.ubc.cpen221.mp5;

import com.sun.org.apache.regexp.internal.RE;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.ToDoubleBiFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Predict implements ToDoubleBiFunction {
    private double a;
    private double b;
    private double rsquare;

    @Override
    public double applyAsDouble(Object o, Object o2) {
        if(o==null||o2==null){
            return -1;
        }
        if(!(o instanceof MP5Db)||!(o2 instanceof String)){
            return -1;
        }
        String restaurantId = (String) o2;
        MP5Db database = (MP5Db) o;
        double price = database.getProduct(restaurantId).getPrice();
        return a + b * price;
    }

    public Predict(MP5Db database, String userId){
        User user = database.getUser(userId);
        double sxx = calculateSxx(database, user);
        double syy = calculateSyy(database, user);
        double sxy = calculateSxy(database, user);
        this.b = sxy/sxx;
        Set<Review> reviews = new HashSet<Review>();
        user.getReviews().parallelStream().forEach(t->{
            Review oneReview = database.getReview(t);
            reviews.add(oneReview);
        });
        double meany = user.getAverageStars();
        double sumx = reviews.parallelStream().map(t->t.getProduct_id()).map(t->database.getProduct(t))
                .map(t->t.getPrice()).reduce(0, (x,y)->x+y);
        double meanx = sumx/reviews.size();
        this.a = meany - b*meanx;
        this.rsquare = sxy*sxy/sxx/syy;
    }

    private double calculateSxx(MP5Db database, User user){
        Set<Product> restaurants = new HashSet<Product>();
        user.getReviews().parallelStream().forEach(t->{
            Review oneReview = database.getReview(t);
            Product oneRestaurant = database.getProduct(oneReview.product_id);
            restaurants.add(oneRestaurant);
        });
        double sumx = restaurants.parallelStream().map(t->t.getPrice()).reduce(0, (x,y)->x+y);
        double meanx = sumx/restaurants.size();
        double sxx = restaurants.parallelStream().map(t->t.getPrice()).map(t->Math.pow(t-meanx,2)).reduce(0.0,(x,y)->x+y);
        return sxx;
    }

    private double calculateSyy(MP5Db database, User user){
        double meany = user.getAverageStars();
        Set<Review> reviews = new HashSet<Review>();
        user.getReviews().parallelStream().forEach(t->{
            Review oneReview = database.getReview(t);
            reviews.add(oneReview);
        });
        double syy = reviews.parallelStream().map(t->t.getStars()).map(t->Math.pow(t-meany,2)).reduce(0.0,(x,y)->x+y);
        return syy;
    }

    private double calculateSxy(MP5Db database, User user){
        Set<Review> reviews = new HashSet<Review>();
        user.getReviews().parallelStream().forEach(t->{
            Review oneReview = database.getReview(t);
            reviews.add(oneReview);
        });
        double meany = user.getAverageStars();
        double sumx = reviews.parallelStream().map(t->t.getProduct_id()).map(t->database.getProduct(t))
                .map(t->t.getPrice()).reduce(0, (x,y)->x+y);
        double meanx = sumx/reviews.size();
        List<Double> components = new ArrayList<Double>();
        reviews.parallelStream().forEach(t->{
            Product restaurant = database.getProduct(t.getProduct_id());
            double x_meanx = restaurant.getPrice()-meany;
            double y_meany = t.getStars()-meanx;
            components.add(x_meanx*y_meany);
        });
        return components.parallelStream().reduce(0.0,(x,y)->x+y);
    }
}
