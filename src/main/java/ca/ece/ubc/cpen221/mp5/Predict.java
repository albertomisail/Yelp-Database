package ca.ece.ubc.cpen221.mp5;

import com.sun.org.apache.regexp.internal.RE;

import java.util.List;
import java.util.Set;
import java.util.function.ToDoubleBiFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Predict implements ToDoubleBiFunction {
    private double a;
    private double b;

    @Override
    public double applyAsDouble(Object o, Object o2) {
        return 0;
    }

    /*public Predict(YelpDB database, String userId){
        Set<Record> records = database.getRecords();
        List<Record> findUser = records.parallelStream().filter(t->t.getType().equals("user"))
                .filter(t->t.getId().equals(userId)).collect(Collectors.toList());
        User user = (User)aux.get(0);
        double sumx = user.getReviews().parallelStream().forEach(review_Id->{
            List<Record> findReview = records.parallelStream().filter(t->t.getType().equals("review"))
                    .filter(t->t.getId().equals(review_Id)).collect(Collectors.toList());
            Review review = (Review) findReview.get(0);
            String restaurant_Id = review.product_id;
            List<Record> findRestaurant = records.parallelStream().filter(t->t.getType().equals("business"))
                    .filter(t->t.getId().equals(restaurant_Id)).collect(Collectors.toList());
            YelpRestaurant restaurant = (YelpRestaurant) findRestaurant.get(0);
            restaurant.getPrice();
        });
    }*/

    /*
	public void get(User user) {
		double meanx = user.getAverageStars();
		double sxx = user.getReviews().parallelStream().map(id->this.getReview(id)).map(review->review.getStars())
				.map(x->Math.pow(x-meanx, 2)).reduce(0.0, (x,y)->x+y);
		double sumy = user.getReviews().parallelStream().map(id -> this.getReview(id))
				.map(review -> review.getProduct_id()).map(id -> this.getRestaurant(id)).map(restaurant -> restaurant.getPrice())
				.reduce(0.0, (x,y) -> x+y);
		double meany = sumy/(user.getReviews().size());
		double syy = user.getReviews().parallelStream().map(id->this.getReview(id))
				.map(review->review.getProduct_id()).map(id->this.getRestaurant(id)).map(restaurant->restaurant.getPrice())
				.map(x->Math.pow(x-meany, 2)).reduce(0.0, (x,y)->x+y);
		double sxy = 0;
		for(String review_id : user.getReviews()) {
			Review review = this.getReview(review_id);
			YelpRestaurant restaurant = this.getRestaurant(review.getProduct_id());
			sxy += (review.getStars()-meanx)*(restaurant.getPrice()-meany);
		}
	}*/
}
