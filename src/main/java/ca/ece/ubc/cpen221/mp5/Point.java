package ca.ece.ubc.cpen221.mp5;

import java.util.List;

public class Point {
	private double longitude;
	private double latitude;
	
	private double calculateDistance(Point other) {
		return Math.sqrt(Math.pow(this.getLatitude()-other.getLatitude(), 2)
				+Math.pow(this.getLongitude()-other.getLongitude(), 2));
	}
	
	public Point getClosestPoint(List<Point> centers) {
		Point result = null;
		double distance = Integer.MAX_VALUE;
		for(Point point : centers) {
			if(calculateDistance(point)<=distance) {
				distance = calculateDistance(point);
				result = point;
			}
		}
		return result;
	}
	
	public Point(double a, double b) {
		this.longitude=a;
		this.latitude=b;
	}
	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		return true;
	}
	
	
}
