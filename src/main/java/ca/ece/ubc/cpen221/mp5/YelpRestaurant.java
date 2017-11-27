package ca.ece.ubc.cpen221.mp5;

import javax.json.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

public class YelpRestaurant extends Product{
	private boolean open;
	private Set<String> neighborhoods;
	private String state;
	private String city;
	private String address;
	private Set<String> schools;
	private Point coordinates;
	
	public YelpRestaurant(String line) throws UnsupportedEncodingException {
		parse(line);
	}
	private void parse(String line) throws UnsupportedEncodingException {
		InputStream stream = new ByteArrayInputStream(line.getBytes(StandardCharsets.UTF_8.name()));
		JsonReader reader = Json.createReader(stream);
		JsonObject json = reader.readObject();
		reader.close();
		this.type = json.getString("type");
		this.id = json.getString("business_id");
		this.url = json.getString("url");
		this.name = json.getString("name");
		JsonArray cat = json.getJsonArray("categories");
		this.categories = new HashSet<>();
		for(int i = 0; i < cat.size(); i++){
			categories.add(cat.getString(i));
		}
		this.stars = json.getJsonNumber("stars").doubleValue();
		this.numberOfReviews = json.getInt("review_count");
		this.photoUrl = json.getString("photo_url");
		this.price = json.getInt("price");
		this.open = json.getBoolean("open");
		JsonArray hoods = json.getJsonArray("neighborhoods");
		this.neighborhoods = new HashSet<>();
		for(int i = 0; i < hoods.size(); i++){
			this.neighborhoods.add(hoods.getString(i));
		}
		this.state = json.getString("state");
		this.city = json.getString("city");
		this.address = json.getString("full_address");
		JsonArray sch = json.getJsonArray("schools");
		this.schools = new HashSet<>();
		for(int i = 0; i < sch.size(); i++){
			this.neighborhoods.add(sch.getString(i));
		}
		double a = json.getJsonNumber("longitude").doubleValue();
		double b = json.getJsonNumber("latitude").doubleValue();
		this.coordinates = new Point(a,b);
	}

	public Point getPoint(){
		return this.coordinates;
	}

	@Override
	public String toString(){
		JsonArrayBuilder schoolsJson = Json.createArrayBuilder();
		for(String s : schools){
			schoolsJson.add(s);
		}
		JsonArrayBuilder neighborhoodsJson = Json.createArrayBuilder();
		for(String s : neighborhoods){
			neighborhoodsJson.add(s);
		}
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add("open",open)
				.add("state", state)
				.add("city", city)
				.add("full_address", address)
				.add("latitude", coordinates.getLatitude())
				.add("longitude", coordinates.getLongitude())
				.add("schools", schoolsJson)
				.add("neighborhoods", neighborhoodsJson);

		return super.toString()+builder.build().toString();
	}

	public YelpRestaurant(String info, YelpDB database) throws UnsupportedEncodingException, NullPointerException, ClassCastException{
		InputStream stream = new ByteArrayInputStream(info.getBytes(StandardCharsets.UTF_8.name()));
		JsonReader reader = Json.createReader(stream);
		JsonObject json = reader.readObject();
		reader.close();
		this.type = "business";
		String restaurantId = Record.getSaltString();
		while(database.containsProduct(restaurantId)){
			restaurantId = Record.getSaltString();
		}
		this.id = restaurantId;
		this.name = json.getString("name");
		this.url = "http://www.yelp.com/biz/"+name;
		JsonArray cat = json.getJsonArray("categories");
		this.categories = new HashSet<>();
		for(int i = 0; i < cat.size(); i++){
			categories.add(cat.getString(i));
		}
		this.stars = 0.0;
		//TODO chech this
		this.numberOfReviews = 0;
		this.photoUrl = json.getString("photo_url");
		this.price = json.getInt("price");
		this.open = json.getBoolean("open");
		JsonArray hoods = json.getJsonArray("neighborhoods");
		this.neighborhoods = new HashSet<>();
		for(int i = 0; i < hoods.size(); i++){
			this.neighborhoods.add(hoods.getString(i));
		}
		this.state = json.getString("state");
		this.city = json.getString("city");
		this.address = json.getString("full_address");
		JsonArray sch = json.getJsonArray("schools");
		this.schools = new HashSet<>();
		for(int i = 0; i < sch.size(); i++){
			this.neighborhoods.add(sch.getString(i));
		}
		double a = json.getJsonNumber("longitude").doubleValue();
		double b = json.getJsonNumber("latitude").doubleValue();
		this.coordinates = new Point(a,b);
	}
}
