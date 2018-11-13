/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import model.Point;

/**
 *
 * @author aliha
 */
public class DistanceDriver {

	public DistanceDriver() {
	}

	// compute the euclidean distance between two points
	public int EUCLIDEAN_2D(Point p1, Point p2) {
		double x_distance = p1.getX() - p2.getX();
		double y_distance = p1.getY() - p2.getY();
		return (int) Math.sqrt(x_distance * x_distance + y_distance * y_distance);
	}
}
