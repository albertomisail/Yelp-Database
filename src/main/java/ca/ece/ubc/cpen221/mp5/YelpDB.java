package ca.ece.ubc.cpen221.mp5;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import javax.json.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.ToDoubleBiFunction;
import java.util.stream.Collectors;

/**
 * A YelpDB RI: any record of type review that is in the database, the related
 * user and restaurant are part of the database for any user in the database,
 * the set of reviews corresponding to it should consist by all and only the
 * reviewsId that are related to the user in the database for any restaurant in
 * the database, the review count should equal the number of reviews that refer
 * to that restaurant in the database there are no objects with repeated id and
 * type in the databse AF: a map that map from a pair of id and type to a record
 */
public class YelpDB implements MP5Db {
	private Map<String, Record> records;

	/**
	 * Constructor
	 * 
	 * @param restaurantFile
	 *            the file that contains the restaurants information in json format
	 * @param reviewFile
	 *            the file that contains the reviews information in json format
	 * @param userFile
	 *            the file that contains the users information in json format
	 * @throws IOException
	 *             if any of the files dont exist
	 * @throws UnsupportedEncodingException
	 *             if any of the files is not in json format
	 */
	public YelpDB(String restaurantFile, String reviewFile, String userFile)
			throws IOException, UnsupportedEncodingException {
		records = new ConcurrentHashMap<String, Record>();
		parseUserFile(userFile);
		parseRestaurantFile(restaurantFile);
		parseReviewFile(reviewFile);
	}

	/**
	 * Parse the user file
	 * 
	 * @param filename
	 *            the file containing the information
	 * @throws IOException
	 *             if the file does not exist
	 * @throws UnsupportedEncodingException
	 *             if the file is not in json format
	 */
	private void parseUserFile(String filename) throws IOException, UnsupportedEncodingException {
		String line;
		FileReader fileReader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		while ((line = bufferedReader.readLine()) != null) {
			User user = new YelpUser(line);
			addUser(user);
		}
		bufferedReader.close();
	}

	/**
	 * Parse the restaurant file
	 * 
	 * @param filename
	 *            the file containing the information
	 * @throws IOException
	 *             if the file does not exist
	 * @throws UnsupportedEncodingException
	 *             if the file is not in json format
	 */
	private void parseRestaurantFile(String filename) throws IOException, UnsupportedEncodingException {
		String line;
		FileReader fileReader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		while ((line = bufferedReader.readLine()) != null) {
			Product restaurant = new YelpRestaurant(line);
			addProduct(restaurant);
		}
		bufferedReader.close();
	}

	/**
	 * Parse the review file
	 * 
	 * @param filename
	 *            the file containing the information
	 * @throws IOException
	 *             if the file does not exist
	 * @throws UnsupportedEncodingException
	 *             if the file is not in json format
	 */
	private void parseReviewFile(String filename) throws IOException, UnsupportedEncodingException {
		String line;
		FileReader fileReader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		while ((line = bufferedReader.readLine()) != null) {
			Review review = new YelpReview(line);
			addReview(review);
			User user = (User) records.get(((Review) review).getUser_id() + "user");
			user.addReview(review.getId());
		}
		bufferedReader.close();
	}

	public String kMeansClusters_json(int k) {
		List<Set<YelpRestaurant>> clusters = kMeansClusters(k);
		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
		for (int i = 0; i < clusters.size(); i++) {
			Set<YelpRestaurant> set = clusters.get(i);
			int clusterNumber = i + 1;
			set.parallelStream().forEach(restaurant -> {
				JsonObjectBuilder restaurantBuilder = Json.createObjectBuilder();
				restaurantBuilder.add("x", restaurant.getPoint().getLatitude());
				restaurantBuilder.add("y", restaurant.getPoint().getLongitude());
				restaurantBuilder.add("name", restaurant.getName());
				restaurantBuilder.add("cluster", clusterNumber);
				restaurantBuilder.add("weight", 5.0);
				arrayBuilder.add(restaurantBuilder);
			});
		}
		return arrayBuilder.build().toString();
	}

	/**
	 * Produces k non-empty clusters of restaurants based on their location
	 * 
	 * @param k
	 *            the number of clusters
	 * @return a list of set of restaurants, such that if a restaurant is in a set
	 *         s, then its distance to the centroid of s is equal or smaller to the
	 *         distance from such restaurant to the centroid of any other cluster in
	 *         the list different than s
	 */
	public List<Set<YelpRestaurant>> kMeansClusters(int k) {
		List<Set<YelpRestaurant>> result;
		List<Point> cache = new ArrayList<>();
		List<Point> centers;
		Set<YelpRestaurant> restaurants = records.entrySet().parallelStream().map(t -> t.getValue())
				.filter(t -> t.getType().equals("business")).map(t -> (YelpRestaurant) t).collect(Collectors.toSet());

		centers = new ArrayList<Point>();
		for (YelpRestaurant oneRes : restaurants) {
			if(centers.size()==k) {
				break;
			}
			else {
				Point p = oneRes.getPoint();
				if(!centers.contains(p)) {
					centers.add(p);
				}
			}
		}
		do {
			result = new ArrayList<>();
			for (int i = 1; i <= k; i++) {
				result.add(new HashSet<>());
			}
			cache = new ArrayList<>();
			cache.addAll(centers);
			for (YelpRestaurant restaurant : restaurants) {
				int i = restaurant.getPoint().getClosestPoint(centers);
				result.get(i).add(restaurant);
			}
			centers = new ArrayList<>();

			for (Set<YelpRestaurant> set : result) {
				centers.add(Point.getCentroid(set));
			}

		} while (differentCluster(centers, cache));
		return result;
	}

	/**
	 * Checks wether the clusters changed from one step to the other
	 * 
	 * @param centers
	 *            the new list of centroids of clusters
	 * @param cache
	 *            the previous list of centroids of clusters
	 * @return
	 */
	private boolean differentCluster(List<Point> centers, List<Point> cache) {
		for (Point center : centers) {
			if (!cache.contains(center)) {
				return true;
			}
		}
		return false;
	}

	public ToDoubleBiFunction<MP5Db, String> getPredictorFunction(String user) {
		return new Predict(this, user);
	}

	public User getUser(String userId) {
		return (User) records.get(userId + "user");
	}

	public Review getReview(String reviewId) {
		return (Review) records.get(reviewId + "review");
	}

	public Product getProduct(String productId) {
		return (Product) records.get(productId + "business");
	}

	public void addUser(User user) {
		records.put(user.getId() + user.getType(), user);
	}

	public void addProduct(Product restaurant) {
		records.put(restaurant.getId() + restaurant.getType(), restaurant);
	}

	public void addReview(Review review) {
		records.put(review.getId() + review.getType(), review);
	}

	public boolean containsUser(String id) {
		return records.containsKey(id + "user");
	}

	public boolean containsProduct(String id) {
		return records.containsKey(id + "business");
	}

	public boolean containsReview(String id) {
		return records.containsKey(id + "review");
	}

	/**
	 * Perform a structured query and return the set of objects that matches the
	 * query
	 *
	 * @param queryString
	 * @return the set of objects that matches the query
	 */
	public Set<YelpRestaurant> getMatches(String queryString) throws IOException{
		queryString.trim();
		CharStream stream = CharStreams.fromString(queryString);
		QueryLexer lexer = new QueryLexer(stream);
		TokenStream tokens = new CommonTokenStream(lexer);
		QueryParser parser = new QueryParser(tokens);

		ParseTree tree = parser.root();
		//((RuleContext)tree).inspect(parser);


		ParseTreeWalker walker = new ParseTreeWalker();
		QueryListener listener = new QueryBaseListener(records);

		walker.walk(listener, tree);


		Set<YelpRestaurant> results = ((QueryBaseListener)listener).evaluate();
		return results;
	}
}
