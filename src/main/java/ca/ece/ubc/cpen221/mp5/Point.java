package ca.ece.ubc.cpen221.mp5;

import java.util.List;

public class Point {
	private double latitude;
	private double longitude;
	
	private double calculateDistance(Point other) {
		return Math.sqrt(Math.pow(this.getLatitude()-other.getLatitude(), 2)
				+Math.pow(this.getLongitude()-other.getLongitude(), 2));
	}
	
	public Point shortestDistance(List<Point> list) {
		list.stream().max(new Comparator(){
			
		})
	}
	
	public Point(double a, double b) {
		this.latitude=a;
		this.longitude=b;
	}
	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}
	
	
}
