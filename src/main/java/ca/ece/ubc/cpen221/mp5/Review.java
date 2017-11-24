package ca.ece.ubc.cpen221.mp5;

import java.util.Date;

public class Review extends Record{
	protected String product_id;
	protected String text;
	protected int stars;
	protected String user_id;
	protected String date;

	public String getUser_id() {
		return user_id;
	}

	public String getProduct_id() {
		return product_id;
	}

	public int getStars() {
		return stars;
	}
}
