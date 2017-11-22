package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class YelpDB implements MP5Db{
	private Set<Record> records;
	
	public YelpDB(String restaurantFile, String reviewFile, String userFile) throws IOException {
		records = new HashSet<Record>();
		parseUserFile(userFile);
		//parseRestaurantFile(restaurantFile);
		parseReviewFile(reviewFile);
		System.out.println(records.size());
	}
	
	private void parseUserFile(String filename) throws IOException {
		String line;
		FileReader fileReader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		while ((line = bufferedReader.readLine()) != null) {
			Record user = new YelpUser(line);
			records.add(user);
		}
		bufferedReader.close();
	}
	/*
	private void parseRestaurantFile(String filename) throws IOException {
		String line;
		FileReader fileReader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		while ((line = bufferedReader.readLine()) != null) {
			Record restaurant = new Restaurant(line);
			records.add(restaurant);
		}
		bufferedReader.close();
	}*/

	private void parseReviewFile(String filename) throws IOException {
		String line;
		FileReader fileReader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		while ((line = bufferedReader.readLine()) != null) {
			Record review = new YelpReview(line);
			records.add(review);
			List<Record> users = records.parallelStream().filter(t->t.getType().equals("user"))
					.filter(t->t.getId().equals(((Review)review).getUser_id())).collect(Collectors.toList());
			User user = (User)users.get(0);
			user.addReview(review.getId());
		}
		bufferedReader.close();
	}
	/*
	private Map<Restaurant, Integer> kMeansClusters(int k) throws InterruptedException{
		Map<Restaurant, Integer> result = new HashMap<Restaurant, Integer>();
		List<Point> centersBef = new ArrayList<Point>();
		List<Point> centers = new ArrayList<Point>();
		//Create k threads that generate random points
		//TODO check this
		//Also check for when k is bigger than the number of restaurants!!
		List<Thread> threads = new ArrayList<Thread>();
		for(int i = 0; i < k; i++) {
			Thread pointGenerator = new Thread(
					new Runnable() {
						public void run() {
							Point center = new Point(Math.random()-122,Math.random()+37);
							centers.add(center);
							centersBef.add(null);
						}
					});
			threads.add(pointGenerator);
			pointGenerator.start();
		}
		finishAllThreads(threads);
		Set<Restaurant> restaurants = records.parallelStream().filter(t->t.getType()=="business")
				.map(t->(Restaurant)t).collect(Collectors.toSet());
		restaurants.parallelStream().map(restaurant -> restaurant.getPoint())
				.map(point->point.getClosestPoint(centers))
		do {
			for (Record one : restaurants) {
				Thread calculateCluster = new Thread(new Runnable() {
					public void run() {
						Restaurant restaurant = (Restaurant)one;
						Point closestCenter = restaurant.getPoint().getClosestPoint(centers);
						Integer clusterNumber = centers.indexOf(closestCenter);
						result.put(restaurant, clusterNumber);
					}
				});
				threads.add(calculateCluster);
				calculateCluster.start();
			}
			finishAllThreads(threads);
			centers = calculateNewCenters(result, k);
		}while(differentCluster(centers, centersBef));
		return result;
	}
	
	private List<Point> calculateNewCenters(Map<Restaurant, Integer> map, int k){
		List<Point> result = new ArrayList<Point>();
		double[][] coordinates = new double[2][k];
		for(Map.Entry<Restaurant, Integer> entry : map.entrySet()) {
			Restaurant restaurant = entry.getKey();
			Point point = restaurant.getPoint();
			coordinates[0][entry.getValue()] += point.getLongitude();
			coordinates[1][entry.getValue()] += point.getLatitude();
		}
		for(int i = 0; i < k; i++) {
			Point point = new Point(coordinates[0][i]/k,coordinates[1][i]/k);
			result.add(point);
		}
		return result;
	}
	
	private boolean differentCluster(List<Point>centerNow, List<Point>centerBefore) {
		for(int i = 0; i < centerNow.size(); i++) {
			if(!centerNow.get(i).equals(centerBefore.get(i))){
				return true;
			}
		}
		return false;
	}
	
	private void finishAllThreads(List<Thread> list) throws InterruptedException {
		for(Thread t : list) {
			t.join();
		}
		list = new ArrayList<Thread>();
	}
	
	/*private Map<String, Restaurant> restaurants;
	private Map<String, YelpUser> users;
	private Map<String, YelpReview> reviews;

	public YelpDB(String restaurantFile, String reviewFile, String userFile) throws IOException {
		this.restaurants = new HashMap<String, Restaurant>();
		this.users = new HashMap<String, YelpUser>();
		this.reviews = new HashMap<String, YelpReview>();
		parseUserFile(userFile);
		parseRestaurantFile(restaurantFile);
		parseReviewFile(reviewFile);
	}

	private void parseUserFile(String filename) throws IOException {
		String line;
		FileReader fileReader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		while ((line = bufferedReader.readLine()) != null) {
			YelpUser user = new YelpUser(line);
			String id = user.getId();
			users.put(id, user);
		}
		bufferedReader.close();
	}
	
	private void parseRestaurantFile(String filename) throws IOException {
		String line;
		FileReader fileReader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		while ((line = bufferedReader.readLine()) != null) {
			Restaurant restaurant = new Restaurant(line);
			String id = restaurant.getId();
			restaurants.put(id, restaurant);
		}
		bufferedReader.close();
	}

	private void parseReviewFile(String filename) throws IOException {
		String line;
		FileReader fileReader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		while ((line = bufferedReader.readLine()) != null) {
			YelpReview review = new YelpReview(line);
			String id = review.getId();
			reviews.put(id, review);
			User user = users.get(review.getUser_id());
			user.addReview(id);
		}
		bufferedReader.close();
	}
	
	private Map<String, Integer> kMeansClusters(int k) throws InterruptedException{
		Map<String, Integer> result = new HashMap<String, Integer>();
		Map<String, Integer> cache =  new HashMap<String, Integer>();
		List<Point> centers = new ArrayList<Point>();
		//Create k threads that generate random points
		//TODO check this
		//Also check for when k is bigger than the number of restaurants!!
		List<Thread> threads = new ArrayList<Thread>();
		for(int i = 0; i < k; i++) {
			Thread pointGenerator = new Thread(
					new Runnable() {
						public void run() {
							Point center = new Point(Math.random()-122,Math.random()+37);
							centers.add(center);
						}
					});
			threads.add(pointGenerator);
			pointGenerator.start();
		}
		finishAllThreads(threads);
		for(Map.Entry<String, Restaurant> entry : restaurants.entrySet()) {
			cache.put(entry.getKey(), -1);
		}
		do {
			for (Map.Entry<String, Restaurant> entry : restaurants.entrySet()) {
				String id = entry.getKey();
				Restaurant restaurant = entry.getValue();
				Thread calculateCluster = new Thread(new Runnable() {
					public void run() {
						Point closestCenter = restaurant.getPoint().getClosestPoint(centers);
						Integer clusterNumber = centers.indexOf(closestCenter);
						result.put(id, clusterNumber);
					}
				});
				threads.add(calculateCluster);
				calculateCluster.start();
			}
			finishAllThreads(threads);
			centers = calculateNewCenters(result, k);
		}while(differentCluster(result, cache));
		return result;
	}
	
	private List<Point> calculateNewCenters(Map<String, Integer> map, int k){
		List<Point> result = new ArrayList<Point>();
		double[][] coordinates = new double[2][k];
		for(Map.Entry<String, Integer> entry : map.entrySet()) {
			Restaurant restaurant = restaurants.get(entry.getKey());
			Point point = restaurant.getPoint();
			coordinates[0][entry.getValue()] += point.getLongitude();
			coordinates[1][entry.getValue()] += point.getLatitude();
		}
		for(int i = 0; i < k; i++) {
			Point point = new Point(coordinates[0][i]/k,coordinates[1][i]/k);
			result.add(point);
		}
		return result;
	}
	
	private boolean differentCluster(Map<String, Integer>result, Map<String, Integer>cache) {
		for(Map.Entry<String, Integer> entry : result.entrySet()) {
			if(cache.get(entry.getKey())!=entry.getValue()) {
				cache = new HashMap<String, Integer>();
				for(Map.Entry<String, Integer> entry2 : result.entrySet()) {
					cache.put(entry2.getKey(), entry2.getValue());
				}
				return true;
			}
		}
		return false;
	}
	
	private void finishAllThreads(List<Thread> list) throws InterruptedException {
		for(Thread t : list) {
			t.join();
		}
		list = new ArrayList<Thread>();
	}
	
	public Restaurant getRestaurant(String id) {
		return restaurants.get(id);
	}
	
	public Review getReview(String id) {
		return reviews.get(id);
	}
	
	public void get(User user) {
		double meanx = user.getAverageStars();
		double sxx = user.getReviews().parallelStream().map(id->this.getReview(id)).map(review->review.getStars())
				.map(x->Math.pow(x-meanx, 2)).reduce(0.0, (x,y)->x+y);
		double sumy = user.getReviews().parallelStream().map(id -> this.getReview(id))
				.map(review -> review.getProduct_id()).map(id -> this.getRestaurant(id)).map(restaurant -> restaurant.getPrice())
				.reduce(0.0, (x,y) -> x+y);
		double meany = sumy/(user.getReviews().size());
		double syy = user.getReviews().parallelStream().map(id->this.getReview(id))
				.map(review->review.getProduct_id()).map(id->this.getRestaurant(id)).map(restaurant->restaurant.getPrice())
				.map(x->Math.pow(x-meany, 2)).reduce(0.0, (x,y)->x+y);
		double sxy = 0;
		for(String review_id : user.getReviews()) {
			Review review = this.getReview(review_id);
			Restaurant restaurant = this.getRestaurant(review.getProduct_id());
			sxy += (review.getStars()-meanx)*(restaurant.getPrice()-meany);
		}
	}*/
	
	
}
