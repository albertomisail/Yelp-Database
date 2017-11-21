package ca.ece.ubc.cpen221.mp5;

import java.util.List;

public class PointGenerator implements Runnable{
	private List<Point> centers;
	public PointGenerator(List<Point>list) {
		this.centers = list;
	}
	public void run() {
		centers.add(new Point(Math.random()*(-122),Math.random()*37));
			
	}
}
