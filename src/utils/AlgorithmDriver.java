package utils;

import java.util.ArrayList;
import java.util.List;

import algorithms.AbstractTSP;
import gui.MainFrame;
import model.TSPInput;

/* The main class for loading algorithms. We use a Strategy Design pattern and this class represents the driver.*/
public class AlgorithmDriver {

	private AbstractTSP strategy; /* The different kind of algorithms. */
	private int minCostForGUI;
	private double runningForGUI;
	private MainFrame mainFrame;

	public AlgorithmDriver(AbstractTSP strategy, MainFrame mainFrame) {
		AbstractTSP.setMinimumCost(Integer.MAX_VALUE);
		AbstractTSP.setBestCircuit(new ArrayList<>());
		this.strategy = strategy;
		this.mainFrame = mainFrame;
	}

	/* Method which starts the algorithm run. First it sets up the input the starts the timer. At the end, it displays the results 
	 * collected from the algorithms abstract class.*/
	public void executeStrategy(TSPInput tspInput) {
		AbstractTSP.DISTANCES = tspInput.getDist();
		AbstractTSP.cityIndexes = tspInput.getCityIndexes();
		AbstractTSP.mainFrame = this.mainFrame;
		// Start time of algorithm
		long startTime = System.nanoTime();
		strategy.execute(tspInput);

		// Calculate end time in seconds
		long endTime = System.nanoTime();
		long totalTime = endTime - startTime;
		double seconds = (double) totalTime / 1_000_000_000.0;

		int minimumCost = AbstractTSP.getMinimumCost();
		List<Integer> bestCircuit = AbstractTSP.getBestCircuit();
		minCostForGUI = minimumCost;
		runningForGUI = seconds;
		System.out.println(strategy.getClass());
		System.out.println("Best circuit has cost  " + minimumCost);
		System.out.println(bestCircuit);
		System.out.println("Runtime: " + seconds);
		System.out.println("======================================");

	}

	public int getMinCost_For_GUI() {
		return minCostForGUI;
	}

	public double getRunningTimeForGUI() {
		return runningForGUI;
	}
}