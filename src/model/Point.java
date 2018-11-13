package model;

import utils.StdDraw;

public class Point {
	private double x; /* x coordinate of the point */
	private double y; /* y coordinate of the point */
	private final int index;
	
	/* Constructor */
	public Point(double x, double y, int index) {
		this.x = x;
		this.y = y;
		this.index = index;
	}
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	/* Compute the 2D Euclidian Distance between destPoint and current point*/
	public double distanceTo(Point destPoint) {
		double xDistance = destPoint.getX() - this.x;
		double yDistance = destPoint.getY() - this.y;
		
		return Math.sqrt( xDistance * xDistance + yDistance * yDistance);
	}

	@Override
	public String toString() {
		return "[City " + index + "]-Position (" + String.format("%6.5f %6.5f", this.x, this.y) + ")";
	}
	
	
	/* Draw this point with StdDraw library */
	public void draw() {
		StdDraw.point(this.x, this.y);
	}
	
	/* Draw a line from current point to destPoint */
    public void drawTo(Point destPoint) {
        StdDraw.line(this.x, this.y, destPoint.getX(), destPoint.getY());
    }
}
