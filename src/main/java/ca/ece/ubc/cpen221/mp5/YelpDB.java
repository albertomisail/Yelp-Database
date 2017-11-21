package ca.ece.ubc.cpen221.mp5;

public class YelpDB implements MP5Db{
	private List<Restaurant> restaurantList;
	
	private List<Set<Restaurant>> kMeansClusters(int k){
		List<Point> centres = new ArrayList<Point>();
		for(int i = 0; i < k; i++) {
			PointGenerator generator = new PointGenerator(centres);
			Thread thread = new Thread(generator);
			thread.start();
		}
		
	}
}
