package ca.ece.ubc.cpen221.mp5;

import java.util.Set;
import java.util.function.ToDoubleBiFunction;;

// This interface represents a database of objects of type T. 
// It supports querying for objects from the database as well
// as two basic statistical learning operations on the database.

public interface MP5Db<T> {

	/**
	 * Perform a structured query and return the set of objects that matches the
	 * query
	 * 
	 * @param queryString
	 * @return the set of objects that matches the query
	 */
	//Set<T> getMatches(String queryString);

	/**
	 * Cluster objects into k clusters using k-means clustering
	 * 
	 * @param k
	 *            number of clusters to create (0 < k <= number of objects)
	 * @return a String, in JSON format, that represents the clusters
	 */
	String kMeansClusters_json(int k);

	/**
	 * 
	 * @param user
	 *            represents a user_id in the database
	 * @return a function that predicts the user's ratings for objects (of type
	 *         T) in the database of type MP5Db<T>. The function that is
	 *         returned takes two arguments: one is the database and other other
	 *         is a String that represents the id of an object of type T.
	 */
	ToDoubleBiFunction<MP5Db<T>, String> getPredictorFunction(String user);
	
	/**
	 * Gets the user that matches the desired id
	 * @param userId the id
	 * @return a user such that user.getId()==userId
	 */
	User getUser (String userId);
	
	/**
	 * Gets the review that matches the desired id
	 * @param reviewId the id
	 * @return a user such that review.getId()==reviewId
	 */
	Review getReview(String reviewId);
	
	/**
	 * Gets the product that matches the desired id
	 * @param productId the id
	 * @return a user such that product.getId()==productId
	 */
	Product getProduct(String productId);
	
	/**
	 * Adds a user to the database
	 * @param user the user to be added
	 */
	void addUser(User user);
	
	/**
	 * Adds a product to the database
	 * @param product the product to be added
	 */
	void addProduct(Product product);
	
	/**
	 * Adds a review to the database
	 * @param review the review to be added
	 */
	void addReview(Review review);
	
	/**
	 * Function to check if a user is part of the database
	 * @param id the user id
	 * @return true if there is a record of type user whose id is equal to id
	 */
	boolean containsUser(String id);
	
	/**
	 * Function to check if a product is part of the database
	 * @param id the product id
	 * @return true if there is a record of type product whose id is equal to id
	 */
	boolean containsProduct(String id);
	
	/**
	 * Function to check if a review is part of the database
	 * @param id the review id
	 * @return true if there is a record of type review whose id is equal to id
	 */
	boolean containsReview(String id);
}
