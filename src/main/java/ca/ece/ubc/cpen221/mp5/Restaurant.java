package ca.ece.ubc.cpen221.mp5;

public class Restaurant extends Product{
	private boolean open;
	private String[] neighborhoods;
	private String state;
	private String type;
	private String city;
	private String address;
	private String[] schools;
	private Point coordinates;
	
	
	
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
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
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
}
