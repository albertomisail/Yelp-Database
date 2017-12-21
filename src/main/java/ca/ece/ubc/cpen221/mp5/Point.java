package ca.ece.ubc.cpen221.mp5;

import java.util.List;
import java.util.Set;

/**
 * Represent a point in the plane
 * RI: long and lat are not null
 * AF: point is represented with two doubles long and lat (its coordinates)
 */
public class Point {
	private double longitude;
	private double latitude;

	/**
	 * Calculates the distance of a straight line from this point to other
	 * @param other the other point
	 * @return the distance between this and other
	 */
	private double calculateDistance(Point other) {
		return Math.sqrt(Math.pow(this.getLatitude()-other.getLatitude(), 2)
				+Math.pow(this.getLongitude()-other.getLongitude(), 2));
	}


	/**
	 * Calculates the closestPoint from a list of given points
	 * @param centers the list to be considered
	 * @return i such that distance from this to centers.get(i) is less or equal to the distance from this to center.get(j)
	 * 			with j different of i
	 */
	public int getClosestPoint(List<Point> centers) {
		int result = 0;
		double distance = Integer.MAX_VALUE;
		for(int i = 0; i < centers.size(); i++) {
			Point point = centers.get(i);
			if(this.equals(point)) {
				return i;
			}
			if(calculateDistance(point)<distance) {
				distance = calculateDistance(point);
				result = i;
			}
		}
		return result;
	}

	/**
	 * Constructor
	 * @param a longitude
	 * @param b latitude
	 */

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
	
	
	

	/**
	 * Given a set of restaurants it calculates its centroid
	 * @param set the set of restaurant
	 * @return a point p such that p is the centroid of the set
	 */
	public static Point getCentroid(Set<YelpRestaurant> set){
		double longitude = 0.0;
		double latitude = 0.0;
		int restaurantCount = 0;
		for(YelpRestaurant r : set){
			longitude += r.getPoint().getLongitude();
			latitude += r.getPoint().getLatitude();
			restaurantCount++;
		}
		return new Point(longitude/restaurantCount, latitude/restaurantCount);
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
