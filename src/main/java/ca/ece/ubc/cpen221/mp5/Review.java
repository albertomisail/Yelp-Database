package ca.ece.ubc.cpen221.mp5;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.util.Date;

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

	@Override
	public String toString(){
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add("product_id",product_id)
				.add("text", text)
				.add("stars", stars)
				.add("user_id", user_id)
				.add("date", date);

		return super.toString()+builder.build().toString();
	}
}
