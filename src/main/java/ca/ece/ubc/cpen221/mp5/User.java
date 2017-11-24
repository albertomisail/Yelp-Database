package ca.ece.ubc.cpen221.mp5;

import java.util.List;
import java.util.Set;

public class User extends Record{
	protected String name;
	protected String url;
	protected int reviewCount;
	protected double averageStars;
	protected Set<String> reviews;

	public void addReview(String review_Id){
		this.reviews.add(review_Id);
	}

	public Set<String> getReviews() {
		return reviews;
	}

	public double getAverageStars() {
		return averageStars;
	}
}
