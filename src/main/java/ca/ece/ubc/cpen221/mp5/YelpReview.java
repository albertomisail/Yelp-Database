package ca.ece.ubc.cpen221.mp5;


import javax.json.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;

public class YelpReview extends Review{
	private int[] votes;
	
	public YelpReview(String line) throws UnsupportedEncodingException {
		parse(line);
	}

	private void parse(String line) throws UnsupportedEncodingException {
		InputStream stream = new ByteArrayInputStream(line.getBytes(StandardCharsets.UTF_8.name()));
		JsonReader reader = Json.createReader(stream);
		JsonObject json = reader.readObject();
		reader.close();
		this.type = json.getString("type");
		this.id = json.getString("review_id");
		this.product_id = json.getString("business_id");
		this.text = json.getString("text");
		this.stars = json.getInt("stars");
		this.user_id = json.getString("user_id");
		this.date = json.getString("date");
		JsonObject votes = json.getJsonObject("votes");
		this.votes = new int[3];
		this.votes[0] = votes.getInt("funny");
		this.votes[1] = votes.getInt("useful");
		this.votes[2] = votes.getInt("cool");

	}

	@Override
	public String toString(){
		JsonArrayBuilder votesJson = Json.createArrayBuilder();
		for(int i = 0; i < votes.length; i++){
			votesJson.add(votes[i]);
		}

		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add("votes",votesJson);
		return super.toString()+builder.build().toString().substring(1);
	}

	public YelpReview(String info, YelpDB database) throws UnsupportedEncodingException, NullPointerException, ClassCastException{
		InputStream stream = new ByteArrayInputStream(info.getBytes(StandardCharsets.UTF_8.name()));
		JsonReader reader = Json.createReader(stream);
		JsonObject json = reader.readObject();
		reader.close();
		this.type = "review";

		this.product_id = json.getString("business_id");
		this.text = json.getString("text");
		this.stars = json.getInt("stars");
		this.user_id = json.getString("user_id");
		this.date = json.getString("date");
		JsonObject votes = json.getJsonObject("votes");
		this.votes = new int[3];
		this.votes[0] = votes.getInt("funny");
		this.votes[1] = votes.getInt("useful");
		this.votes[2] = votes.getInt("cool");
		this.type = json.getString("type");
		String reviewId = Record.getSaltString();
		while(database.containsReview(reviewId)){
			reviewId = Record.getSaltString();
		}
		this.id = reviewId;

	}
}
