package algorithms;

import java.util.ArrayList;
import java.util.List;

import model.Point;
import model.TSPInput;

public abstract class AbstractTSP {
	
	public static double minimumCost = Double.MAX_VALUE;
	public static List<Point> bestCircuit = new ArrayList<>();
	
	public abstract void execute(TSPInput tspInput);
}
