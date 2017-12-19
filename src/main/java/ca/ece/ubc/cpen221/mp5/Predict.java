package ca.ece.ubc.cpen221.mp5;



import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.ToDoubleBiFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * A function to predict a user's rating of a restaurant based on his previous reviews
 *
 */
/**
 * @author ALBERTO
 *
 */
public class Predict implements ToDoubleBiFunction {
    private double a;
    private double b;
    private double rsquare;
    private double sxx;
    private double syy;
    private double sxy;
    private double meanx;
    private double meany;
    private User aUser;
    
    /**
     * Calculates a user's rating of a restaurant based on his previous reviews
     * @param o the database in which the least square regression is being applied
     * @param o2 the restaurant id in the database
     * 		requires: that the restaurant id is part of the database
     * @return the user rating of the restaurant besed on his previous reviews in the database using the method of simple least squares regression
     *			or -1 if the o is not of type MP5Db or o2 is not of type string
     *			if the result is bigger than 5 it return 5 and if less than 1 it is 1.
     */
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
        calculateMeany(database, aUser);
        calculateMeanx(database, aUser);
        calculateSxx(database, aUser);
        calculateSyy(database, aUser);
        calculateSxy(database, aUser);
        calculateB();
        calculateA();
        calculateR();
        double result = a + b * price;
        if(result<=1) {
        	return 1;
        }
        else if(result>=5) {
        	return 5;
        }
        else {
        	return result;
        }
    }
    
    /**
     * Constructor
     * @param database the database for which the least square regression wants to be calculated
     * @param userId the id of the user for which the least square regression wants to be calculated
     * 		requires: the database contains the userId
     * @throws IllegalArgumentException if there is not enough information to do the regression (no reviews, or all with the same price of restaurant)
     */
    public Predict(MP5Db database, String userId)throws IllegalArgumentException{
        User user = database.getUser(userId);
        this.aUser = user;
        Set<Review> reviews = new HashSet<Review>();
        user.getReviews().parallelStream().forEach(t->{
            Review oneReview = database.getReview(t);
            reviews.add(oneReview);
        });
        if(allReviewsSamePrice(reviews,database)){
            throw new IllegalArgumentException();
        }
        calculateMeany(database, user);
        calculateMeanx(database, user);
        calculateSxx(database, user);
        calculateSyy(database, user);
        calculateSxy(database, user);
        calculateB();
        calculateA();
        calculateR();
    }

    private void calculateB(){
        this.b = this.sxy/this.sxx;
    }

    private void calculateA(){
        this.a = meany - this.b*meanx;
    }

    private void calculateR(){
        this.rsquare = sxy*sxy/sxx/syy;
    }

    /**
     * Determines if all the reviews correspond to restaurants of the same price
     * @param reviews the set of reviews of the user
     * 			requires: any elements of the set is contained by the database
     * @param database the database
     * @return false if there are at least two reviews that correspond to restaurants with different prices
     */
    private boolean allReviewsSamePrice(Set<Review> reviews, MP5Db database){
        Set<Integer> price = new HashSet<>();
        for(Review r : reviews){
            price.add(database.getProduct(r.getProduct_id()).getPrice());
        }
        return price.size()<=1;
    }

    /**
     * Calculate sxx
     * @param database 
     * @param user
     */
    private void calculateSxx(MP5Db database, User user){
        double sxx = 0;
        for(String s : user.getReviews()){
            Review review = database.getReview(s);
            Product restaurant = database.getProduct(review.getProduct_id());
            sxx += (restaurant.getPrice()-meanx)*(restaurant.getPrice()-meanx);
        }
        this.sxx = sxx;
    }
    
    /**
     * Calculate syy
     * @param database 
     * @param user
     */
    private void calculateSyy(MP5Db database, User user){
        double syy = 0;
        for(String s : user.getReviews()){
            Review review= database.getReview(s);
            syy += (review.getStars()-meany)*(review.getStars()-meany);
        }
        this.syy = syy;
    }
    
    /**
     * Calculate sxy
     * @param database 
     * @param user
     */
    private void calculateSxy(MP5Db database, User user){
        double sxy = 0;
        for(String s : user.getReviews()){
            Review review = database.getReview(s);
            Product restaurant = database.getProduct(review.getProduct_id());
            sxy += (restaurant.getPrice()-meanx)*(review.getStars()-meany);
        }
        this.sxy = sxy;
    }
    
    /**
     * Calculate meanX
     * @param database 
     * @param user
     */
    private void calculateMeanx(MP5Db database, User user){
        double sumx = 0;
        for(String s : user.getReviews()){
            Review review = database.getReview(s);
            Product restaurant = database.getProduct(review.getProduct_id());
            sumx += restaurant.getPrice();
        }
        double meanx = sumx/user.getReviews().size();
        this.meanx = meanx;
    }
    
    /**
     * Calculate meanY
     * @param database 
     * @param user
     */
    private void calculateMeany(MP5Db database, User user){
        double sumy = 0;
        for(String s : user.getReviews()){
            Review review = database.getReview(s);
            sumy += review.getStars();
        }
        double meany = sumy/user.getReviews().size();
        this.meany = meany;
    }
}
