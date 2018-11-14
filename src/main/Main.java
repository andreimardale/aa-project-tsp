package main;

import java.util.Arrays;
import java.util.List;

import algorithms.BranchAndBoundTSP;
import algorithms.BruteForceTSP;
import algorithms.DynamicProgrammingTSP;
import model.Point;
import model.TSPInput;
import utils.TSPReader;

public class Main {

	public static final List<Point> TEST_POINTS = Arrays.asList(new Point(70.501, 58.793, 1),
			new Point(26.077, 84.765, 2), new Point(12.284, 19.949, 3), new Point(20.125, 85.198, 4),
			new Point(48.505, 8.244, 5), new Point(94.810, 30.570, 6), new Point(69.991, 32.967, 7),
			new Point(15.261, 59.770, 8), new Point(56.046, 39.271, 9), new Point(80.336, 23.430, 10));
	
	public static final List<Point> SMALL_TEST_POINTS = Arrays.asList(new Point(0, 0, 0), new Point(2, 2, 1), new Point(2, 0, 2), new Point(0, 2, 3));
	
	public static final int dist1[][] = {
			{ 0, 10, 15, 20 },
			{ 10, 0, 35, 25 },
			{ 15, 35, 0, 30 },
			{ 20, 25, 30, 0 } };

	public static final int dist2[][] = {
			{ 0, 2, 9, 10 },
			{ 1, 0, 6, 4 },
			{ 15, 7, 0, 8 },
			{ 6, 3, 12, 0 } };

	public static final int dist3[][] = {
			{ 0, 1, 15, 6 },
			{ 2, 0, 7, 3 },
			{ 9, 6, 0, 12 },
			{ 10, 4, 8, 0 } };

	public static void main(String[] args) {

		/* Examples of how to use the reading from file infrastructure */
//		TSPReader tspReader = new TSPReader("hm10.tsp");
//		TSPInput tspInput = tspReader.read();
//
//		AlgorithmDriver driver = new AlgorithmDriver(new DynamicProgrammingTSP());
//		driver.executeStrategy(tspInput);
//		
//		driver = new AlgorithmDriver(new BruteForceTSP());
//		driver.executeStrategy(tspInput);
//		
//		driver = new AlgorithmDriver(new BranchAndBoundTSP());
//		driver.executeStrategy(tspInput);

//		/* Examples of how to use a hardcoded a graph */
		TSPInput tspInput2 = new TSPInput("Test Case 1", "TSP", "This is my example test case.", dist1.length, "EUC_2D", dist1);
		AlgorithmDriver driver2 = new AlgorithmDriver(new BruteForceTSP());
		driver2.executeStrategy(tspInput2);
	
		driver2 = new AlgorithmDriver(new DynamicProgrammingTSP());
		driver2.executeStrategy(tspInput2);

		driver2 = new AlgorithmDriver(new BranchAndBoundTSP());
		driver2.executeStrategy(tspInput2);
		
	}

	
}
