package main;

import java.util.Arrays;
import java.util.List;

import algorithms.BranchAndBoundTSP;
import algorithms.BruteForceTSP;
import algorithms.DynamicProgrammingTSP;
import algorithms.NearestNeighbourTSP;
import model.Point;
import model.TSPInput;
import utils.DrawingUtils;
import utils.StdDraw;
import utils.TSPReader;

public class Main {

	public static final List<Point> TEST_POINTS = Arrays.asList(new Point(0.70501, 0.58793, 1),
			new Point(0.26077, 0.84765, 2), new Point(0.12284, 0.19949, 3), new Point(0.20125, 0.85198, 4),
			new Point(0.48505, 0.08244, 5), new Point(0.94810, 0.30570, 6), new Point(0.69991, 0.32967, 7),
			new Point(0.15261, 0.59770, 8), new Point(0.56046, 0.39271, 9), new Point(0.80336, 0.23430, 10));
	public static final List<Point> SMALL_TEST_POINTS = Arrays.asList(new Point(0, 0, 1), new Point(2, 2, 2), new Point(2, 0, 3), new Point(0, 2, 4));
	

	public static void main(String[] args) {

		/* Examples of how to use the reading from file infrastructure */
		TSPReader tspReader = new TSPReader("hm10.tsp");
		TSPInput tspInput = tspReader.read();

		AlgorithmDriver driver = new AlgorithmDriver(new DynamicProgrammingTSP());
		driver.executeStrategy(tspInput);

		/* Examples of how to use a hardcoded a graph */
		TSPInput tspInput2 = new TSPInput("Test Case 1", "TSP", "This is my example test case.", 4, "EUC_2D", SMALL_TEST_POINTS);
		AlgorithmDriver driver2 = new AlgorithmDriver(new BruteForceTSP());
		driver.executeStrategy(tspInput2);
	}

	
}
