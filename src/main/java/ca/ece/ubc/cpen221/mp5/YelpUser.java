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

	public YelpUser(String info, YelpDB database) throws UnsupportedEncodingException, NullPointerException, ClassCastException{
		InputStream stream = new ByteArrayInputStream(info.getBytes(StandardCharsets.UTF_8.name()));
		JsonReader reader = Json.createReader(stream);
		JsonObject json = reader.readObject();
		reader.close();
		this.type = "user";
		String userId = Record.getSaltString();
		while(database.containsUser(userId)){
			userId = Record.getSaltString();
		}
		this.id = userId;
		this.name = json.getString("name");
		this.url = "http://www.yelp.com/user_details?userid="+id;
		this.reviewCount = 0;
		this.averageStars = 0.0;
		this.reviews = new HashSet<String>();
		this.votes = new int[3];
	}

	public String toString(){
		JsonArrayBuilder votesJson = Json.createArrayBuilder();
		for(int i = 0; i < votes.length; i++){
			votesJson.add(votes[i]);
		}

		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add("votes",votesJson);

		return super.toString()+builder.build().toString();
	}
}
