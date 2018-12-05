package main;

import java.util.ArrayList;
import java.util.List;

import algorithms.AbstractTSP;
import gui.MainFrame;
import model.TSPInput;

public class AlgorithmDriver {

	private AbstractTSP strategy;
	private int minCostForGUI;
	private double runningForGUI;
	private MainFrame mainFrame;

	public AlgorithmDriver(AbstractTSP strategy, MainFrame mainFrame) {
		AbstractTSP.setMinimumCost(Integer.MAX_VALUE);
		AbstractTSP.setBestCircuit(new ArrayList<>());
		this.strategy = strategy;
		this.mainFrame = mainFrame;
	}

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
		System.out.println(minimumCost);
		/*System.out.println(strategy.getClass());
		System.out.println("Best circuit has cost  " + minimumCost);
		System.out.println(bestCircuit);
		System.out.println("Runtime: " + seconds);
		System.out.println("======================================");*/

	}

	public int getMinCost_For_GUI() {
		return minCostForGUI;
	}

	public double getRunningTimeForGUI() {
		return runningForGUI;
	}
}