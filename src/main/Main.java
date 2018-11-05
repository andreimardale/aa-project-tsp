package main;

import java.awt.Font;
import java.util.Arrays;
import java.util.List;

import algorithms.BranchAndBoundTSP;
import algorithms.BruteForceTSP;
import algorithms.NearestNeighbourTSP;
import model.Point;
import utils.DrawingUtils;
import utils.StdDraw;

public class Main {

	public static final List<Point> TEST_POINTS = Arrays.asList(new Point(0.70501, 0.58793, 1),
			new Point(0.26077, 0.84765, 2), new Point(0.12284, 0.19949, 3), new Point(0.20125, 0.85198, 4),
			new Point(0.48505, 0.08244, 5), new Point(0.94810, 0.30570, 6), new Point(0.69991, 0.32967, 7),
			new Point(0.15261, 0.59770, 8), new Point(0.56046, 0.39271, 9), new Point(0.80336, 0.23430, 10));

	public static void main(String[] args) {
		Point p1 = new Point(2, 2, 1);
		Point p2 = new Point(4, 4, 2);
		Point p3 = new Point(4, 4, 3);
		List<Point> SMALL_TEST_POINTS = Arrays.asList(p1, p2, p3);

		
		
		AlgorithmDriver driver = new AlgorithmDriver(new BruteForceTSP());
		driver.executeStrategy(TEST_POINTS);
		
//		driver = new AlgorithmDriver(new BranchAndBoundTSP());
//		driver = new AlgorithmDriver(new NearestNeighbourTSP());

	}

}
