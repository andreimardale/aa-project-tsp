package algorithms;

import java.util.ArrayList;
import java.util.List;

import model.Point;
import model.TSPInput;

public abstract class AbstractTSP {
	
	public static int [][] DISTANCES;
	
	public static double minimumCost = Double.MAX_VALUE;
	public static List<Integer> bestCircuit = new ArrayList<>();
	
	public abstract void execute(TSPInput tspInput);
}
