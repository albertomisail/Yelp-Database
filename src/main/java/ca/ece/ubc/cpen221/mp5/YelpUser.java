package ca.ece.ubc.cpen221.mp5;

public class YelpUser extends User{
	private String type;
	private int[] votes;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int[] getVotes() {
		return votes;
	}
	public void setVotes(int[] votes) {
		this.votes = votes;
	}
	
	public YelpUser(String line) {
		parse(line);
	}
	
	private void parse(String line) {
		JSONObject json = parseJSONObject(line);
		this.url = json.getString("url");
		this.id = json.getString("user_id");
		this.name = json.getString("name");
		this.type = json.getString("type");
		this.averageStars = json.getDouble("average_stars");
		this.reviewCount = json.getInt("review_count");
	}
}
