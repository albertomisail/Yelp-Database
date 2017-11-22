package ca.ece.ubc.cpen221.mp5;

public class Product extends Record{
	protected String url;
	protected String id;
	protected String name;
	protected String[] categories;
	protected double stars;
	protected int numberOfReviews;
	protected String photoUrl;
	protected int price;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getCategories() {
		return categories;
	}
	public void setCategories(String[] categories) {
		this.categories = categories;
	}
	public double getStars() {
		return stars;
	}
	public void setStars(double stars) {
		this.stars = stars;
	}
	public int getNumberOfReviews() {
		return numberOfReviews;
	}
	public void setNumberOfReviews(int numberOfReviews) {
		this.numberOfReviews = numberOfReviews;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
