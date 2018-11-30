package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import algorithms.*;
import gui.MainFrame;
import model.Point;
import model.TSPInput;
import utils.TSPGenerator;
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

	static int INF = 9999;
	public static final int dist4[][] = {
			{ INF, 3, 6, 2, 3 },
			{ 3, INF, 5, 2, 3 },
			{ 6, 5, INF, 6, 4 },
			{ 2, 2, 6, INF, 6 },
			{ 3, 3, 4, 6, INF },		
	};

	public static void main(String[] args) {
		
//		TSPGenerator generator = new TSPGenerator("test10.tsp", 5, true, 1, 5, "UNIFORM_DISTRIBUTION", 0.01);
//		generator.generate();
		
		/* Examples of how to use the reading from file infrastructure */
                
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (Exception ex) {

            }
            MainFrame frame = new MainFrame();
               frame.setVisible(true);
		/*TSPReader tspReader = new TSPReader("TSPLIB/d198.tsp");
		TSPInput tspInput = tspReader.read();
		
		List<Integer> ants = Arrays.asList(40);
		List<Integer> iterations = Arrays.asList(2500);
		List<String> results = new ArrayList<>();*/
		
		
		/*for (Integer ant : ants) {
			for (Integer iteration : iterations) {
				System.out.println("******Ants: " + ant + " Iterations: " + iteration + "******");
				AntColonyTSP antColonyTSP = new AntColonyTSP(iteration, ant);
				AlgorithmDriver driver = new AlgorithmDriver(antColonyTSP);
				driver.executeStrategy(tspInput);
				results.add("1)" +  ant + " " + iteration + " " + AbstractTSP.getMinimumCost());
				
				driver = new AlgorithmDriver(antColonyTSP);
				driver.executeStrategy(tspInput);
				results.add("2)" + ant + " " + iteration + " " + AbstractTSP.getMinimumCost());
				
				driver = new AlgorithmDriver(antColonyTSP);
				driver.executeStrategy(tspInput);
				results.add("3)" + ant + " " + iteration + " " + AbstractTSP.getMinimumCost());
				
				driver = new AlgorithmDriver(antColonyTSP);
				driver.executeStrategy(tspInput);
				results.add("4)" + ant + " " + iteration + " " + AbstractTSP.getMinimumCost());
				
				driver = new AlgorithmDriver(antColonyTSP);
				driver.executeStrategy(tspInput);
				results.add("5)" + ant + " " + iteration + " " + AbstractTSP.getMinimumCost());
			}
		}
		
		System.out.println(results);*/
		
		
	}

	
}
