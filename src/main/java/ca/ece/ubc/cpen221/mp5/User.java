package ca.ece.ubc.cpen221.mp5;

import java.util.List;
import java.util.Set;

public class User extends Person{
	protected String url;
	protected int reviewCount;
	protected String id;
	protected double averageStars;
	protected Set<String> reviews;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setAverageStars(double averageStars) {
		this.averageStars = averageStars;
	}
	
	public void addReview(String review) {
		reviews.add(review);
	}
	
	public Set<String> getReviews(){
		return reviews;
	}
	
	public double getAverageStars() {
		return averageStars;
	}
}
