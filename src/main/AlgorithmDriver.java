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
		
		strategy.execute(tspInput);
		
		int minimumCost = AbstractTSP.getMinimumCost();
		List<Integer> bestCircuit = AbstractTSP.getBestCircuit();
		
		System.out.println(strategy.getClass());
		System.out.println("Best circuit has cost  " + minimumCost);
		System.out.println(bestCircuit);
		System.out.println("======================================");

	}
}