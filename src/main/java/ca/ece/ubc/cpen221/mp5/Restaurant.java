package ca.ece.ubc.cpen221.mp5;

public class Restaurant extends Product{
	private boolean open;
	private String[] neighborhoods;
	private String state;
	private String city;
	private String address;
	private String[] schools;
	private Point coordinates;
	
	public Restaurant(String line) {
		parse(line);
	}
	private void parse(String line) {
		/*JSONObject json = parseJSONObject(line);
		this.url = json.getString("url");
		this.open = json.getBoolean("open");
		double longitude = json.getDouble("longitude");
		this.id = json.getString("business_id");
		this.name = json.getString("name");
		this.type = json.getString("type");
		this.stars = json.getDouble("stars");
		this.numberOfReviews = json.getInt("review_count");
		this.price = json.getInt("price");
		double latitude = json.getDouble("latitude");
		this.coordinates = new Point(longitude, latitude);*/
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	
	public String[] getNeighborhoods() {
		return neighborhoods;
	}
	public void setNeighborhoods(String[] neighborhoods) {
		this.neighborhoods = neighborhoods;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String[] getSchools() {
		return schools;
	}
	public void setSchools(String[] schools) {
		this.schools = schools;
	}
	
	public Point getPoint() {
		return coordinates;
	}
}
