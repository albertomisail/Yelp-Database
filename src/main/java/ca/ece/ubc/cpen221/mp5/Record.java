package ca.ece.ubc.cpen221.mp5;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.util.Random;

/**
 * Most generic data type for an entry in the database
 * RI: id and type are not null
 *AF: each record has its own unique pair of id and type
 */

public abstract class Record {
	protected String id;
	protected String type;

	public String getType() {
		return type;
	}

	public String getId() {
		return id;
	}
	
	/**
	 * Produces a string that is a json rep of the object
	 * @return a string that is a json rep of the object
	 */
	@Override
	public String toString() {
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add("id",this.getId())
				.add("type", this.getType());

		String jsonRep = builder.build().toString();
		return jsonRep.substring(0, jsonRep.length()-1)+",";
	}
	
	//Taken from
	//https://stackoverflow.com/questions/20536566/creating-a-random-string-with-a-z-and-0-9-in-java
	/**
	 * Produce a random string of length 23
	 * @return a random string of length 23
	 */
	protected static String getSaltString() {
		String SALTCHARS = "qwertyuiopasdfghjklzxcvbnmABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 23) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}
}
