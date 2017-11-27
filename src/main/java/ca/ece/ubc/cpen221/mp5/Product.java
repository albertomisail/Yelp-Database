package ca.ece.ubc.cpen221.mp5;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.util.Set;

public class Product extends Record{
	protected String url;
	protected String name;
	protected Set<String> categories;
	protected double stars;
	protected int numberOfReviews;
	protected String photoUrl;
	protected int price;

	public int getPrice() {
		return price;
	}

	public String getUrl() {
		return url;
	}

	public String getName() {
		return name;
	}

	public Set<String> getCategories() {
		return categories;
	}

	public double getStars() {
		return stars;
	}

	public int getNumberOfReviews() {
		return numberOfReviews;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	@Override
	public String toString(){
		JsonArrayBuilder categoriesJson = Json.createArrayBuilder();
		for(String s : categories){
			categoriesJson.add(s);
		}
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add("url",this.getUrl())
				.add("name", this.getName())
				.add("stars", this.getStars())
				.add("photo_url", this.getPhotoUrl())
				.add("review_count", this.getNumberOfReviews())
				.add("categories", categoriesJson);
		return super.toString()+builder.build().toString();
	}
}
