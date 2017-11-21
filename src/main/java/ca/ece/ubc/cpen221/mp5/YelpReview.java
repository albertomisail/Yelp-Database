package ca.ece.ubc.cpen221.mp5;

public class YelpReview extends Review{
	private int[] votes;
	private String type;
	
	public YelpReview(String line) {
		parse(line);
	}

	private void parse(String line) {
		JSONObject json = parseJSONObject(line);
		this.id = json.getString("review_id");
		this.text = json.getString("text");
		this.stars = json.getInt("stars");
		this.type = json.getString("type");
		this.product_id = json.getString("business_id");
		this.user_id = json.getString("user_id");
	}
}
