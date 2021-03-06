package model;

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
	public int getIndex() {
		return index;
	}
	
	@Override
	public String toString() {
		return "[City " + index + "]-Position (" + String.format("%6.5f %6.5f", this.x, this.y) + ")";
	}
}
