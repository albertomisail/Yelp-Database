package ca.ece.ubc.cpen221.mp5;

import javax.json.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.ToDoubleBiFunction;
import java.util.stream.Collectors;

public class YelpDB<R> implements MP5Db{
	private Set<Record> records;
	
	public YelpDB(String restaurantFile, String reviewFile, String userFile) throws IOException {
		records = new HashSet<Record>();
		parseUserFile(userFile);
		parseRestaurantFile(restaurantFile);
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

	private void parseRestaurantFile(String filename) throws IOException {
		String line;
		FileReader fileReader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		while ((line = bufferedReader.readLine()) != null) {
			Record restaurant = new YelpRestaurant(line);
			records.add(restaurant);
		}
		bufferedReader.close();
	}

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

	public String kMeansClusters_json(int k){
		Map<YelpRestaurant, Integer> clusters = kMeansClusters(k);
		JsonObjectBuilder builder = Json.createObjectBuilder();
		for(int i = 1; i <= k; i++){
			Set<YelpRestaurant> restaurants = oneCluster(clusters, i);
			JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
			restaurants.parallelStream().forEach(t->
					{
						arrayBuilder.add(t.toString());
					}
			);
			builder.add("Cluster "+i,arrayBuilder);
		}
		return builder.build().toString();
	}

	private Set<YelpRestaurant> oneCluster(Map<YelpRestaurant, Integer> map, int i){
		Set<YelpRestaurant> result = new HashSet<YelpRestaurant>();
		map.entrySet().parallelStream().forEach(t->
				{
					if(t.getValue().equals(i)){
						result.add(t.getKey());
					}
				}
		);
		return result;
	}

	private Map<YelpRestaurant, Integer> kMeansClusters(int k){
		Map<YelpRestaurant, Integer> result = new HashMap<YelpRestaurant, Integer>();
		Map<YelpRestaurant, Integer> cache = new HashMap<YelpRestaurant, Integer>();
		List<Point> centers = new ArrayList<Point>();
		for(int i = 1; i <= k; i++){
			centers.add(new Point());
		}
		do{
			records.parallelStream().filter(t->t.getType().equals("business"))
					.map(t->(YelpRestaurant)t).forEach(t->
					{
						int i = t.getPoint().getClosestPoint(centers);
						result.put(t, i);
					}
			);
		}while(false);
		return result;
	}

	private boolean differentCluster(Map<YelpRestaurant, Integer> map, Map<String, Integer>cache) {
		Set<Boolean> isFalse = new HashSet<Boolean>();
		map.entrySet().parallelStream().forEach(t->
			{
				if(!cache.containsKey(t.getKey())){
					isFalse.add(false);
				}
				else{
					if(!cache.get(t.getKey()).equals(t.getValue())) isFalse.add(false);
				}
			}
		);
		return !isFalse.isEmpty();
	}

	public ToDoubleBiFunction<YelpDB<Record>, String> getPredictorFunction(String user){
		return new Predict();
	}

	public Set<Record> getRecords() {
		return records;
	}
}
