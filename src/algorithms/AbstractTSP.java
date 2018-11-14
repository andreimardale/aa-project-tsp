package algorithms;

import java.util.ArrayList;
import java.util.List;

import model.Point;
import model.TSPInput;

public abstract class AbstractTSP {
	
	public static int [][] DISTANCES;
	public static List<Integer> cityIndexes;
	
	private static int minimumCost = Integer.MAX_VALUE;
	private static List<Integer> bestCircuit = new ArrayList<>();
	
	
	public static int getMinimumCost() {
		return minimumCost;
	}

	public static void setMinimumCost(int minimumCost) {
		AbstractTSP.minimumCost = minimumCost;
	}

	public static List<Integer> getBestCircuit() {
		return bestCircuit;
	}

	public static void setBestCircuit(List<Integer> bestCircuit) {
		AbstractTSP.bestCircuit = new ArrayList<>(bestCircuit);
	}

	


	public abstract void execute(TSPInput tspInput);
}
