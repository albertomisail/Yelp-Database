package ca.ece.ubc.cpen221.mp5;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.util.Random;

/**
 * Most generic data type for an entry in the database
 * 
 * @author nancy
 *
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
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Record other = (Record) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add("id",this.getId())
				.add("type", this.getType());

		return builder.build().toString();
	}

	//https://stackoverflow.com/questions/20536566/creating-a-random-string-with-a-z-and-0-9-in-java
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
