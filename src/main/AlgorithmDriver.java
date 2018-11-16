package main;

import java.util.ArrayList;
import java.util.List;

import algorithms.AbstractTSP;
import model.TSPInput;

public class AlgorithmDriver {

	private AbstractTSP strategy;

	public AlgorithmDriver(AbstractTSP strategy) {
		AbstractTSP.setMinimumCost(Integer.MAX_VALUE);
		AbstractTSP.setBestCircuit(new ArrayList<>());
		
		this.strategy = strategy;
	}

	public void executeStrategy(TSPInput tspInput) {
		AbstractTSP.DISTANCES = tspInput.getDist();
		AbstractTSP.cityIndexes = tspInput.getCityIndexes();

		// Start time of algorithm
		long startTime = System.nanoTime();
		strategy.execute(tspInput);

		// Calculate end time in seconds
		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;
		double seconds = (double)totalTime / 1_000_000_000.0;
		
		int minimumCost = AbstractTSP.getMinimumCost();
		List<Integer> bestCircuit = AbstractTSP.getBestCircuit();
		
		System.out.println(strategy.getClass());
		System.out.println("Best circuit has cost  " + minimumCost);
		System.out.println(bestCircuit);
		System.out.println("Runtime: " + seconds);
		System.out.println("======================================");

	}
}