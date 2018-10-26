package main;

import java.awt.Font;
import java.util.Arrays;
import java.util.List;

import algorithms.BranchAndBoundTSP;
import algorithms.BruteForceTSP;
import algorithms.NearestNeighbourTSP;
import model.Point;
import utils.StdDraw;

public class Main {

	public static final List<Point> TEST_POINTS = Arrays.asList(new Point(0.70501, 0.58793),
			new Point(0.26077, 0.84765), new Point(0.12284, 0.19949), new Point(0.20125, 0.85198),
			new Point(0.48505, 0.08244), new Point(0.94810, 0.30570), new Point(0.69991, 0.32967),
			new Point(0.15261, 0.59770), new Point(0.56046, 0.39271), new Point(0.80336, 0.23430));

	public static void main(String[] args) {
		Point p1 = new Point(2, 2);
		Point p2 = new Point(4, 4);

		StdDraw.setCanvasSize(1600, 1200);
//		StdDraw.setXscale(0, 10);
//		StdDraw.setYscale(0, 10);
		StdDraw.setPenRadius(0.05);
		Font font = new Font("Arial", Font.ITALIC, 30);
		StdDraw.setFont(font);

		for (int i = 0; i < TEST_POINTS.size(); i++) {
			TEST_POINTS.get(i).draw();
			StdDraw.text(TEST_POINTS.get(i).getX(), TEST_POINTS.get(i).getY() + 0.02, String.valueOf(i));
		}
		
		AlgorithmDriver driver = new AlgorithmDriver(new BruteForceTSP());
		driver = new AlgorithmDriver(new BranchAndBoundTSP());
		driver = new AlgorithmDriver(new NearestNeighbourTSP());

	}

}
