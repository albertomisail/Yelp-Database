package ca.ece.ubc.cpen221.mp5;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class YelpRestaurant extends Product{
	private boolean open;
	private String[] neighborhoods;
	private String state;
	private String city;
	private String address;
	private String[] schools;
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
		this.categories = new String[cat.size()];
		for(int i = 0; i < cat.size(); i++){
			this.categories[i] = cat.getString(i);
		}
		this.stars = json.getJsonNumber("stars").doubleValue();
		this.numberOfReviews = json.getInt("review_count");
		this.photoUrl = json.getString("photo_url");
		this.price = json.getInt("price");
		this.open = json.getBoolean("open");
		JsonArray hoods = json.getJsonArray("neighborhoods");
		this.neighborhoods = new String[hoods.size()];
		for(int i = 0; i < hoods.size(); i++){
			this.neighborhoods[i] = hoods.getString(i);
		}
		this.state = json.getString("state");
		this.city = json.getString("city");
		this.address = json.getString("full_address");
		JsonArray sch = json.getJsonArray("schools");
		this.schools = new String[sch.size()];
		for(int i = 0; i < sch.size(); i++){
			this.neighborhoods[i] = sch.getString(i);
		}
		double a = json.getJsonNumber("longitude").doubleValue();
		double b = json.getJsonNumber("latitude").doubleValue();
		this.coordinates = new Point(a,b);
	}

	public Point getPoint(){
		return this.coordinates;
	}
}
