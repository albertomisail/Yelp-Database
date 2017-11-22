package ca.ece.ubc.cpen221.mp5;

import javax.json.*;
import javax.json.stream.JsonParser;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;

public class YelpUser extends User{
	private int[] votes;
	
	public int[] getVotes() {
		return votes;
	}
	
	public YelpUser(String line) throws UnsupportedEncodingException {
		parse(line);
	}
	private void parse(String line) throws UnsupportedEncodingException {
		InputStream stream = new ByteArrayInputStream(line.getBytes(StandardCharsets.UTF_8.name()));
		JsonReader reader = Json.createReader(stream);
		JsonObject json = reader.readObject();
		reader.close();
		this.type = json.getString("type");
		this.id = json.getString("user_id");
		this.name = json.getString("name");
		this.url = json.getString("url");
		this.reviewCount = json.getInt("review_count");
		this.averageStars = json.getJsonNumber("average_stars").doubleValue();
		this.reviews = new HashSet<String>();
		JsonObject votes = json.getJsonObject("votes");
		this.votes = new int[3];
		this.votes[0] = votes.getInt("funny");
		this.votes[1] = votes.getInt("useful");
		this.votes[2] = votes.getInt("cool");
	}
}
