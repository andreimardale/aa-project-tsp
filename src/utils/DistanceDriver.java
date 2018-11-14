package utils;

import model.Point;

public class DistanceDriver {

	public DistanceDriver() {
	}

	/* Compute the euclidean distance between two points */
	public int euclidian2Distance(Point p1, Point p2) {
		double xDistance = p1.getX() - p2.getX();
		double yDistance = p1.getY() - p2.getY();
		
		return (int) Math.round(Math.sqrt(xDistance * xDistance + yDistance * yDistance));
	}
}
