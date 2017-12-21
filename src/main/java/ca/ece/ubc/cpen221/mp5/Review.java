package ca.ece.ubc.cpen221.mp5;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.util.Date;

/**
 * A generic review
 * RI: product_id, user_id, text, stars, date are not null
 * AF: (same as that of its record)
 */
public class Review extends Record{
	protected String product_id;
	protected String text;
	protected int stars;
	protected String user_id;
	protected String date;

	public String getUser_id() {
		return user_id;
	}

	public String getProduct_id() {
		return product_id;
	}

	public int getStars() {
		return stars;
	}
	
	/**
	 * Produces a string that is a json rep of the object
	 * @return a string that is a json rep of the object
	 */
	@Override
	public String toString(){
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add("product_id",product_id)
				.add("text", text)
				.add("stars", stars)
				.add("user_id", user_id)
				.add("date", date);
		String jsonRep = builder.build().toString();
		return super.toString()+jsonRep.substring(1, jsonRep.length()-1)+",";
	}
}
