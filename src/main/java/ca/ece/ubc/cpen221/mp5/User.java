package ca.ece.ubc.cpen221.mp5;

import java.util.List;

public class User extends Person{
	protected String url;
	protected int reviewCount;
	protected String id;
	protected double averageStars;
	protected List<Review> reviewList;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getReviewCount() {
		return reviewCount;
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
	public double getAverageStars() {
		return averageStars;
	}
	public void setAverageStars(double averageStars) {
		this.averageStars = averageStars;
	}
	
	
}
