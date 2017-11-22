package ca.ece.ubc.cpen221.mp5;


import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

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

	public int[] getVotes() {
		return votes;
	}
}
